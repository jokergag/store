package com.cy.store.controller;

import com.cy.store.controller.ex.*;
import com.cy.store.service.ex.*;
import com.cy.store.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;

/**
 * 控制层的基类
 * @author joker
 * @create 2022-04-24
 */
public class BaseController {

    public static final int OK = 200;

    @ExceptionHandler({ServiceException.class,FileUploadException.class})
    public JsonResult<Void> HandlerException(Throwable e){
        JsonResult<Void> result = new JsonResult<>(e);
        if (e instanceof UsernameDuplicatedException){
            result.setState(4000);
            result.setMessage("用户名已被注册");
        }else if (e instanceof UserNotFoundException){
            result.setState(4001);
            result.setMessage("用户不存在");
        }
        else if (e instanceof PasswordNotMatchException){
            result.setState(4002);
            result.setMessage("用户名或密码错误");
        }
        else if (e instanceof AddressOverstepException){
            result.setState(4003);
            result.setMessage("收货地址数量超过10个");
        }
        else if (e instanceof AddressNotFoundException){
            result.setState(4004);
            result.setMessage("收货地址不存在");
        }
        else if (e instanceof AccessDeniedException){
            result.setState(4005);
            result.setMessage("非法访问");
        }
        else if (e instanceof ProductNotFoundException){
            result.setState(4006);
            result.setMessage("商品数据不存在");
        }
        else if (e instanceof CartNotFoundException){
            result.setState(4007);
            result.setMessage("购物车商品数据不存在");
        }
        else if (e instanceof InsertException){
            result.setState(5000);
            result.setMessage("插入失败");
        }
        else if (e instanceof UpdateException){
            result.setState(5001);
            result.setMessage("修改失败");
        }
        else if (e instanceof DeleteException){
            result.setState(5002);
            result.setMessage("删除失败");
        }
        else if (e instanceof FileUploadException){
            result.setState(6000);
            result.setMessage("文件上传异常");}
        else if (e instanceof FileUploadIOException){
            result.setState(6001);
            result.setMessage("文件上传时读写异常");}
        else if (e instanceof FileEmptyException){
            result.setState(6002);
            result.setMessage("文件为空异常");}
        else if (e instanceof FileSizeException){
            result.setState(6001);
            result.setMessage("文件大小异常");}
        else if (e instanceof FileStateException){
            result.setState(6001);
            result.setMessage("文件状态异常");}
        else if (e instanceof FileTypeException){
            result.setState(6001);
            result.setMessage("文件类型异常");}

        return result;
    }

    /**
     * 获取session对象中的uid
     * @param session
     * @return
     */
    public final Integer getUidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("uid").toString());
    }

    /**
     * 获取登录用户的用户名
     * @param session
     * @return
     */
    public final String getUsernameFromSession(HttpSession session){
        return session.getAttribute("username").toString();
    }



}
