package com.cy.store.service;

import com.cy.store.vo.CartVO;

import java.util.List;

/**
 * @author joker
 * @create 2022-05-02
 */
public interface CartService {

    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    List<CartVO> getVOByUid(Integer uid);

    Integer addNum(Integer cid, Integer uid, String username);
    Integer reduceNum(Integer cid, Integer uid, String username);


    List<CartVO> getVOByCids(Integer uid, Integer[] cids);

}
