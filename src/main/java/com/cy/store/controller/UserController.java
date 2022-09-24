package com.cy.store.controller;

import com.cy.store.bean.User;
import com.cy.store.controller.ex.*;
import com.cy.store.service.UserService;
import com.cy.store.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author joker
 * @create 2022-04-24
 */
@RestController
@RequestMapping("users")
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @RequestMapping("reg")
    public JsonResult<Void> reg(User user){
        userService.reg(user);
        return new JsonResult<>(OK);
    }
    @RequestMapping("login")
    public JsonResult<User> login(String username, String password, HttpSession session){
        User user = userService.login(username, password);
        //将数据存入session中
        session.setAttribute("uid",user.getUid());
        session.setAttribute("username",user.getUsername());
        return new JsonResult<>(OK,user);
    }

    @RequestMapping("change_password")
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid,username,oldPassword,newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("get_by_uid")
    public JsonResult<User> getByUid(HttpSession session){
        User user = userService.getUserByUid(getUidFromSession(session));
        return new JsonResult<>(OK,user);
    }

    @RequestMapping("change_info")
    public JsonResult<Void>  changeInfo(User user,HttpSession session){

        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changeInfo(uid,username,user);
        return new JsonResult<>(OK);

    }

    //设置上传文件的最大值10M
    public static final int AVATAR_MAX_SIZE = 10*1024*1024;

    //设置上传文件的类型
    public static final List<String> AVATAR_TYPES = new ArrayList<String>();

    static {
        AVATAR_TYPES.add("image/jpeg");
        AVATAR_TYPES.add("image/jpg");
        AVATAR_TYPES.add("image/png");
        AVATAR_TYPES.add("image/bmp");
        AVATAR_TYPES.add("image/gif");
    }

    /**
     * 注意：上传到文件夹的路径我设置成绝对路径后导致了一些异常 正确的相对路径请参考项目文件
     * @param file
     * @param session
     * @return
     */
    @PostMapping("change_avatar")
    public JsonResult<String> changeAvatar(@RequestParam("file") MultipartFile file, HttpSession session) {
        // 判断上传的文件是否为空
        if (file.isEmpty()) {
            // 是：抛出异常
            throw new FileEmptyException("上传的头像文件不允许为空");
        }
        // 判断上传的文件大小是否超出限制值
        if (file.getSize() > AVATAR_MAX_SIZE) { // getSize()：返回文件的大小，以字节为单位
            // 是：抛出异常
            throw new FileSizeException("不允许上传超过" + (AVATAR_MAX_SIZE / 1024) + "KB的头像文件");
        }
        // 判断上传的文件类型是否超出限制
        String contentType = file.getContentType();
        // public boolean list.contains(Object o)：当前列表若包含某元素，返回结果为true；若不包含该元素，返回结果为false。
        if (!AVATAR_TYPES.contains(contentType)) {
            // 是：抛出异常
            throw new FileTypeException("不支持使用该类型的文件作为头像，允许的文件类型：\n" + AVATAR_TYPES);
        }

        // 保存头像文件的文件夹
        File dir = new File("D:\\BaiduNetdiskDownload\\SpringBoot-store\\tools\\photo");

        // 保存的头像文件的文件名
        String suffix = "";
        String originalFilename = file.getOriginalFilename();
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = UUID.randomUUID().toString() + suffix;

        // 创建文件对象，表示保存的头像文件
        File dest = new File(dir, filename);
        // 执行保存头像文件
        try {
            file.transferTo(dest);
        } catch (IllegalStateException e) {
            // 抛出异常
            throw new FileStateException("文件状态异常，可能文件已被移动或删除");
        } catch (IOException e) {
            // 抛出异常
            throw new FileUploadIOException("上传文件时读写错误，请稍后重尝试");
        }
        // 头像路径
        //String avatar = dir + "\\" + filename;
        String avatar = "/image/avatar/" + filename;
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 将头像写入到数据库中
        userService.changeAvatar(uid, avatar, username);
        // 返回成功头像路径
        return new JsonResult<String>(OK,filename);
    }
}
