package com.cy.store.mapper;

import com.cy.store.bean.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

/**
 * @author joker
 * @create 2022-04-23
 */
@SpringBootTest
public class UserTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert(){
        User user = new User();
        user.setUsername("张三");
        user.setPassword("123456");
        userMapper.insert(user);
    }
    @Test
    void testAvatar(){

        userMapper.updateAvatarByUid(11,"/abc.jpg","admin",new Date());

    }
    @Test
    void testupdate(){
        User user = new User();
        user.setUid(11);
        user.setEmail("333@qq.com");
        user.setPhone("18888888");
        user.setGender(1);
        userMapper.updateInfoByUid(user);
    }
    @Test
    void find(){
        User user = userMapper.findByUsername("张三");
        System.out.println(user);
    }
    @Test
    void  updatePasswordByUidAfter() {

        Integer result = userMapper.updatePasswordByUidAfter(8, "123", "admin", new Date());
        System.out.println(result);

    }

    @Test
    void findByUid(){
        System.out.println(userMapper.findByUid(8));
    }
}
