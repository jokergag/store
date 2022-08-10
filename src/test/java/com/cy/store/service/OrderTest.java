package com.cy.store.service;

import com.cy.store.bean.Order;
import com.cy.store.service.ex.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author joker
 * @create 2022-05-04
 */
@SpringBootTest
public class OrderTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        try {
            Integer aid = 9;
            Integer[] cids = {3,8,9};
            Integer uid = 12;
            String username = "订单管理员";
            Order order = orderService.create(aid, cids, uid, username);
            System.out.println(order);
        } catch (ServiceException e) {
            System.out.println(e.getClass().getSimpleName());
            System.out.println(e.getMessage());
        }
    }

}
