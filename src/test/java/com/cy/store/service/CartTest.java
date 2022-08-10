package com.cy.store.service;

import com.cy.store.service.ex.ServiceException;
import com.cy.store.vo.CartVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author joker
 * @create 2022-05-02
 */
@SpringBootTest
public class CartTest {
    @Autowired
    private CartService cartService;

    @Test
    public void addToCart() {
        try {
            Integer uid = 12;
            Integer pid = 10000007;
            Integer amount = 1;
            String username = "admin";
            cartService.addToCart(uid, pid, amount, username);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getVOByCids() {
        Integer[] cids = {1, 2, 6, 7};
        Integer uid = 12;
        List<CartVO> list = cartService.getVOByCids(uid, cids);
        System.out.println("count=" + list.size());
        for (CartVO item : list) {
            System.out.println(item);
        }
    }


}
