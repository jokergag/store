package com.cy.store.service.impl;

import com.cy.store.bean.Cart;
import com.cy.store.mapper.CartMapper;
import com.cy.store.service.CartService;
import com.cy.store.service.ProductService;
import com.cy.store.service.ex.AccessDeniedException;
import com.cy.store.service.ex.CartNotFoundException;
import com.cy.store.service.ex.InsertException;
import com.cy.store.service.ex.UpdateException;
import com.cy.store.vo.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author joker
 * @create 2022-05-02
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private ProductService productService;

    @Override
    public void addToCart(Integer uid, Integer pid, Integer amount, String username) {

        //先查询购物车是否已经添加了该商品
        Cart cart = cartMapper.findByUidAndPid(uid, pid);
        //没有则进行添加操作
        if (cart == null){
            Cart cart1 = new Cart();

            cart1.setUid(uid);
            cart1.setPid(pid);
            Date date = new Date();
            cart1.setCreatedUser(username);
            cart1.setCreatedTime(date);
            cart1.setNum(amount);
            cart1.setModifiedUser(username);
            cart1.setModifiedTime(date);

            //这里需调用商品业务层对象获取该商品价格
            cart1.setPrice(productService.findById(pid).getPrice());

            Integer rows = cartMapper.insert(cart1);
            if (rows != 1) throw new InsertException("插入购物车数据异常");
        }else {

            //已经存在则进行商品数量修改操作
            //从查询结果中取出原数量，与参数amount相加，得到新的数量
            Integer num = cart.getNum() + amount;
            Integer integer = cartMapper.updateNumByCid(cart.getCid(), num, username, new Date());
            if (integer != 1) throw new UpdateException("修改购物车数据异常");
        }
    }

    @Override
    public void delByCid(Integer cid,Integer uid) {
        Cart cart = cartMapper.findByCid(cid);
        if(cart.getUid() != uid) throw new AccessDeniedException("非法访问");
        cartMapper.delByCid(cid);
    }

    @Override
    public List<CartVO> getVOByUid(Integer uid) {
        return cartMapper.findVOByUid(uid);
    }

    @Override
    public Integer addNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            throw new CartNotFoundException("访问的购物车数据不存在");
        }

        // 判断查询结果的用户id与参数uid是否一致
        if (!result.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }

        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = 99;
        if (result.getNum()<99) num = result.getNum() + 1;

        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系管理员");
        }

        // 返回新的数量
        return num;
    }

    @Override
    public Integer reduceNum(Integer cid, Integer uid, String username) {
        // 调用findByCid(cid)根据参数cid查询购物车数据
        Cart result = cartMapper.findByCid(cid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：抛出CartNotFoundException
            throw new CartNotFoundException("尝试访问的购物车数据不存在");
        }

        // 判断查询结果中的uid与参数uid是否不一致
        if (!result.getUid().equals(uid)) {
            // 是：抛出AccessDeniedException
            throw new AccessDeniedException("非法访问");
        }

        // 可选：检查商品的数量是否大于多少(适用于增加数量)或小于多少(适用于减少数量)
        // 根据查询结果中的原数量增加1得到新的数量num
        Integer num = 1;
        if(result.getNum()>1){num = result.getNum() - 1;}

        // 创建当前时间对象，作为modifiedTime
        Date now = new Date();
        // 调用updateNumByCid(cid, num, modifiedUser, modifiedTime)执行修改数量
        Integer rows = cartMapper.updateNumByCid(cid, num, username, now);
        if (rows != 1) {
            throw new InsertException("修改商品数量时出现未知错误，请联系管理员");
        }

        // 返回新的数量
        return num;
    }

    @Override
    public List<CartVO> getVOByCids(Integer uid, Integer[] cids) {

        List<CartVO> list = cartMapper.findVOByCids(cids);

         /*for (CartVO cart : list) {
             if (!cart.getUid().equals(uid)) {
                 list.remove(cart);
             }
         }*/

        Iterator<CartVO> it = list.iterator();
        while (it.hasNext()) {
            CartVO cart = it.next();
            if (!cart.getUid().equals(uid)) {
                it.remove();
            }
        }
        return list;

    }


}
