package com.cy.store.mapper;

import com.cy.store.bean.Cart;
import com.cy.store.vo.CartVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

/**
 * @author joker
 * @create 2022-05-02
 */
@SpringBootTest
public class CartTest {

    @Autowired
    private CartMapper cartMapper;

    @Test
    public void insert() {
        Cart cart = new Cart();
        cart.setUid(1);
        cart.setPid(2);
        cart.setNum(3);
        cart.setPrice(4L);
        Integer rows = cartMapper.insert(cart);
    }
    @Test
    public void del() {
        cartMapper.delByCid(1);
    }

    @Test
    public void updateNumByCid() {
        Integer cid = 1;
        Integer num = 10;
        String modifiedUser = "购物车管理员";
        Date modifiedTime = new Date();
        Integer rows = cartMapper.updateNumByCid(cid, num, modifiedUser, modifiedTime);
    }

    @Test
    public void findByUidAndPid() {
        Integer uid = 1;
        Integer pid = 2;
        Cart result = cartMapper.findByUidAndPid(uid, pid);
        System.out.println(result);
    }
    @Test
    public void findVOByUid() {
        List<CartVO> list = cartMapper.findVOByUid(12);
        list.forEach(System.out::println);
    }
    @Test
    public void findByCid() {
        Integer cid = 3;
        Cart result = cartMapper.findByCid(cid);
        System.out.println(result);
    }
    @Test
    public void findVOByCids() {
        Integer[] cids = {1, 2, 6, 8};
        List<CartVO> list = cartMapper.findVOByCids(cids);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }
}
