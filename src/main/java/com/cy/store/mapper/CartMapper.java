package com.cy.store.mapper;

import com.cy.store.bean.Cart;
import com.cy.store.vo.CartVO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author joker
 * @create 2022-05-01
 */
public interface CartMapper {

    Integer insert(Cart cart);

    /**
     * 当购物车已经存在某商品时 只需要修改商品数量
     * @param cid
     * @param num
     * @param modifiedUser
     * @param modifiedTime
     * @return
     */
    Integer updateNumByCid(
            @Param("cid") Integer cid,
            @Param("num") Integer num,
            @Param("modifiedUser") String modifiedUser,
            @Param("modifiedTime") Date modifiedTime);

    Cart findByUidAndPid(
            @Param("uid") Integer uid,
            @Param("pid") Integer pid);

    List<CartVO> findVOByUid(Integer uid);

    Cart findByCid(Integer cid);

    List<CartVO> findVOByCids(Integer[] cids);
}
