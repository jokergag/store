package com.cy.store.service;

import com.cy.store.bean.Order;

/**
 * @author joker
 * @create 2022-05-04
 */
public interface OrderService {

    Order create(Integer aid, Integer[] cids, Integer uid, String username);


}
