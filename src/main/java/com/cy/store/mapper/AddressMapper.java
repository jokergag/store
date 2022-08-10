package com.cy.store.mapper;

import com.cy.store.bean.Address;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 收货地址持久层的接口
 * @author joker
 * @create 2022-04-27
 */
public interface AddressMapper {

    Integer insert(Address address);

    /**
     * 统计用户收货地址数量
     * @param uid
     * @return
     */
    Integer countByUid(Integer uid);

    List<Address> findByUid(Integer uid);

    Integer updateNonDefaultByUid(Integer uid);

    Integer updateDefaultByAid(
            Integer aid,
             String modifiedUser,
             Date modifiedTime);

    Address findByAid(Integer aid);

    /**
     * 根据收货地址id删除数据
     * @param aid 收货地址id
     * @return 受影响的行数
     */
    Integer deleteByAid(Integer aid);

    /**
     * 查询某用户最后修改的收货地址
     * @param uid 归属的用户id
     * @return 该用户最后修改的收货地址，如果该用户没有收货地址数据则返回null
     */
    Address findLastModified(Integer uid);
}
