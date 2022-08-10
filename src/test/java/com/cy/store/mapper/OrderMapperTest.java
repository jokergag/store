package com.cy.store.mapper;

import com.cy.store.bean.Order;
import com.cy.store.bean.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author joker
 * @create 2022-05-04
 */
@SpringBootTest
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertOrder() {
        Order order = new Order();
        order.setUid(12);
        order.setRecvName("小王");
        order.setRecvAddress("马上送到我家");
        orderMapper.insertOrder(order);
    }

    @Test
    public void insertOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setOid(1);
        orderItem.setPid(10086);
        orderItem.setTitle("父见子未亡，掏出七匹狼");
        orderMapper.insertOrderItem(orderItem);
    }
}
