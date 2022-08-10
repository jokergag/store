package com.cy.store.mapper;

import com.cy.store.bean.Order;
import com.cy.store.bean.OrderItem;

/**
 * @author joker
 * @create 2022-05-04
 */
public interface OrderMapper {

    Integer insertOrder(Order order);

    Integer insertOrderItem(OrderItem orderItem);

}
