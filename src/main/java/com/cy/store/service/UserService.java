package com.cy.store.service;

import com.cy.store.bean.User;

/**
 * @author joker
 * @create 2022-04-23
 */
public interface UserService {

    /**
     * 用户注册
     * @param user
     */
    void reg(User user);

    User login(String username,String password);

    void changePassword(Integer uid,String username,String password,String newPassword);

    User getUserByUid(Integer uid);

    void changeInfo(Integer uid,String username,User user);

    void changeAvatar(Integer uid,String avatar,String username);
}
