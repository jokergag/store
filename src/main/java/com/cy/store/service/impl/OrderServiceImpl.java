package com.cy.store.service.impl;

import com.cy.store.bean.Address;
import com.cy.store.bean.Order;
import com.cy.store.bean.OrderItem;
import com.cy.store.mapper.OrderMapper;
import com.cy.store.service.AddressService;
import com.cy.store.service.CartService;
import com.cy.store.service.OrderService;
import com.cy.store.service.ex.InsertException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author joker
 * @create 2022-05-04
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;

    @Override
    public Order create(Integer aid, Integer[] cids, Integer uid, String username) {
        // 创建当前时间对象
        Date now = new Date();
        // 根据cids查询所勾选的购物车列表中的数据
        List<CartVO> carts = cartService.getVOByCids(uid, cids);
        // 计算这些商品的总价
        long totalPrice = 0;
        for (CartVO cart : carts) {
            totalPrice += cart.getRealPrice() * cart.getNum();
        }
        // 创建订单数据对象
        Order order = new Order();
        // 补全数据：uid
        order.setUid(uid);
        // 查询收货地址数据
        Address address = addressService.getByAid(aid, uid);
        // 补全数据：收货地址相关的6项
        order.setRecvName(address.getName());
        order.setRecvPhone(address.getPhone());
        order.setRecvProvince(address.getProvinceName());
        order.setRecvCity(address.getCityName());
        order.setRecvArea(address.getAreaName());
        order.setRecvAddress(address.getAddress());

        // 补全数据：totalPrice
        order.setTotalPrice(totalPrice);
        // 补全数据：status
        order.setStatus(0);
        // 补全数据：下单时间
        order.setOrderTime(now);
        // 补全数据：日志
        order.setCreatedUser(username);
        order.setCreatedTime(now);
        order.setModifiedUser(username);
        order.setModifiedTime(now);

        // 插入订单数据
        Integer rows = orderMapper.insertOrder(order);
        if (rows != 1) throw new InsertException("插入订单数据异常");
        // 遍历carts，循环插入订单商品数据
        for (CartVO cart : carts) {
            // 创建订单商品数据
            OrderItem item = new OrderItem();
            // 补全数据：setOid(order.getOid())
            item.setOid(order.getOid());
            // 补全数据：pid, title, image, price, num
            item.setPid(cart.getPid());
            item.setTitle(cart.getTitle());
            item.setImage(cart.getImage());
            item.setPrice(cart.getRealPrice());
            item.setNum(cart.getNum());
            // 补全数据：4项日志
            item.setCreatedUser(username);
            item.setCreatedTime(now);
            item.setModifiedUser(username);
            item.setModifiedTime(now);
            // 插入订单商品数据
            Integer rows2 = orderMapper.insertOrderItem(item);
            if (rows2 != 1) {
                throw new InsertException("插入订单商品数据时出现未知错误，请联系系统管理员");
            }
        }

        return order;

    }


}
