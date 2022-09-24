package com.cy.store.service.impl;

import com.cy.store.bean.User;
import com.cy.store.mapper.UserMapper;
import com.cy.store.service.UserService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * @author joker
 * @create 2022-04-23
 */
@Service
public class UserServiceImpl implements UserService {

    //自动注入
    @Autowired
    private UserMapper userMapper;

    /**
     * 定义一个md5算法的加密处理
     * @param password
     * @param salt
     * @return
     */
    private String MD5(String password,String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

    //注册用户
    @Override
    public void reg(User user) {
        //先判断用户名是否已经注册
        String username = user.getUsername();
        User user1 = userMapper.findByUsername(username);
        if (user1 != null)
            throw new UsernameDuplicatedException("用户名已经被注册");

        //密码加密处理
        String password = user.getPassword();
        //获取盐值
        String salt = UUID.randomUUID().toString().toUpperCase();
        //一定要记得记录盐值
        user.setSalt(salt);
        //将密码和盐值作为一个整体加密
        String newPassword = MD5(password, salt);
        user.setPassword(newPassword);

        //补全其他数据
        user.setIsDelete(0);
        user.setCreatedUser(user.getUsername());
        user.setModifiedUser(user.getUsername());
        Date date = new Date();
        user.setCreatedTime(date);
        user.setModifiedTime(date);

        //执行注册业务
        Integer result = userMapper.insert(user);
        if (result != 1){
            throw new InsertException("数据插入失败");
        }
    }

    //登录
    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if ( user == null){
            throw new UserNotFoundException("用户不存在");
        }
        //获取盐值以便按相同规则加密
        String salt = user.getSalt();
        String md5 = MD5(password, salt);
        String password1 = user.getPassword();
        if (!md5.equals(password1)){
            throw new PasswordNotMatchException("用户密码错误");
        }
        //判断用户是否被删除
        if (user.getIsDelete() == 1) {
            throw new UserNotFoundException("用户已注销");
        }
        // 将查询结果中的uid、username、avatar封装到新的user对象中
        User user1 = new User();
        user1.setUid(user.getUid());
        user1.setUsername(user.getUsername());
        user1.setAvatar(user.getAvatar());

        return user1;
    }

    //修改密码
    @Override
    public void changePassword(Integer uid, String username, String password, String newPassword) {
        User user = userMapper.findByUid(uid);
        if (user == null || user.getIsDelete() == 1) throw new UserNotFoundException("用户未找到");
        String md5 = MD5(password, user.getSalt());
        if (!user.getPassword().equals(md5)){
            throw new PasswordNotMatchException("密码错误");
        }
        String newMD5 = MD5(newPassword, user.getSalt());
        Integer i = userMapper.updatePasswordByUidAfter(uid, newMD5, username, new Date());
        if (i != 1){
            throw new UpdateException("修改失败");
        }
    }

    /**
     * 根据uid查询用户信息
     * @param uid
     * @return
     */
    @Override
    public User getUserByUid(Integer uid) {
        User user = userMapper.findByUid(uid);
        if (user == null || user.getIsDelete() == 1) throw new UserNotFoundException("未找到用户");
        User user1 = new User();
        user1.setUsername(user.getUsername());
        user1.setPhone(user.getPhone());
        user1.setGender(user.getGender());
        user1.setEmail(user.getEmail());
        user1.setAvatar(user.getAvatar());
        return user1;
    }

    /**
     * 修改用户邮箱 电话 性别信息
     * @param uid
     * @param username
     * @param user
     */
    @Override
    public void changeInfo(Integer uid, String username, User user) {
        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) throw new UserNotFoundException("未找到用户");
        user.setUid(uid);
        user.setModifiedUser(username);
        user.setModifiedTime(new Date());

        Integer i = userMapper.updateInfoByUid(user);
        if (i != 1) throw new UpdateException("修改异常");

    }

    /**
     * 修改用户头像根据uid
     * @param uid
     * @param avatar 头像的路径
     * @param username
     */
    @Override
    public void changeAvatar(Integer uid, String avatar, String username) {

        User result = userMapper.findByUid(uid);
        if (result == null || result.getIsDelete() == 1) throw new UserNotFoundException("用户数据不存在");

        Integer rows = userMapper.updateAvatarByUid(uid, avatar, username, new Date());

        if (rows != 1) throw new UpdateException("上传头像失败");
    }

}
