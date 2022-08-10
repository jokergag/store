package com.cy.store.mapper;

import com.cy.store.bean.User;

import java.util.Date;

/**
 * @author joker
 * @create 2022-04-23
 */
public interface UserMapper {

        Integer insert(User user);

        User findByUsername(String username);

        Integer updatePasswordByUidAfter(Integer uid, String password, String mUser, Date mTime);

        User findByUid(Integer uid);

        Integer updateInfoByUid(User user);

        Integer updateAvatarByUid(Integer uid,String avatar,String mUser,Date mTime);

}
