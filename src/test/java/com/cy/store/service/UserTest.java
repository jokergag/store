package com.cy.store.service;

import com.cy.store.bean.User;
import com.cy.store.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author joker
 * @create 2022-04-23
 */
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    void testReg(){
        User user = new User();
        user.setUsername("3");
        user.setPassword("3");
        userService.reg(user);
    }
    @Test
    void testavatar(){
        userService.changeAvatar(11,"/nnnn.jpg","admin");
    }
    @Test
    void testgetUserByuid(){
        User user = userService.getUserByUid(11);
        System.out.println(user);
    }
    @Test
    void testupdateinfo(){
        User user = new User();
        user.setEmail("33333@qq.com");
        user.setGender(0);
        user.setPhone("122222");
        userService.changeInfo(11,"admin",user);
    }
    @Test
    void testLogin(){
        User user = userService.login("2", "2");
        System.out.println(user);
    }
    @Test
    void testUpdatePassword(){
        userService.changePassword(10,"admin","2","22");
    }


}
