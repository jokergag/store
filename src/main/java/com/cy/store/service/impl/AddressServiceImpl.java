package com.cy.store.service.impl;

import com.cy.store.bean.Address;
import com.cy.store.mapper.AddressMapper;
import com.cy.store.service.AddressService;
import com.cy.store.service.DistrictService;
import com.cy.store.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author joker
 * @create 2022-04-27
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Value("${user.address.maxCount}")
    private Integer maxCount;

    @Autowired
    AddressMapper addressMapper;
    @Autowired
    private DistrictService districtService;

    @Override
    public Integer addAddress(Integer uid, String username, Address address) {
        //先判断用户收货地址数量是否超过10个
        Integer count = addressMapper.countByUid(uid);
        if (count == maxCount){
            throw new AddressOverstepException("一个用户最多只能添加10条收货地址");
        }

        //对address对象中的数据进行补全:省市区
        String provinceName = districtService.getNameByCode(address.getProvinceCode());
        String cityName = districtService.getNameByCode(address.getCityCode());
        String areaName = districtService.getNameByCode(address.getAreaCode());
        address.setProvinceName(provinceName);
        address.setCityName(cityName);
        address.setAreaName(areaName);

        address.setUid(uid);

        //如果是第一个收货地址则设置为默认地址
        Integer isFirst = count == 0 ? 1 : 0;
        address.setIsDefault(isFirst);

        //补全基本字段
         address.setCreatedUser(username);
         address.setCreatedTime(new Date());
         address.setModifiedUser(username);
         address.setModifiedTime(new Date());

        Integer result = addressMapper.insert(address);
        if (result != 1) throw new InsertException("保存失败");
        return result;
    }

    @Override
    public List<Address> getByUid(Integer uid) {
        List<Address> list = addressMapper.findByUid(uid);
        for (Address address : list) {
            address.setUid(null);
            address.setProvinceCode(null);
            address.setCityCode(null);
            address.setAreaCode(null);
            address.setCreatedUser(null);
            address.setCreatedTime(null);
            address.setModifiedUser(null);
            address.setModifiedTime(null);
        }
        return list;
    }

    @Override
    public void updateDefault(Integer uid, Integer aid, String username) {
        Address address = addressMapper.findByAid(aid);
        if(address == null) throw new AddressNotFoundException("收货地址不存在");

        if(!address.getUid().equals(uid)) throw new AccessDeniedException("非法访问");

        //先将用户所有地址设置为不是默认
        Integer result = addressMapper.updateNonDefaultByUid(uid);
        if (result < 0) throw new UpdateException("修改异常");

        //将用户选中的地址设置为默认地址
        Integer i = addressMapper.updateDefaultByAid(aid, username, new Date());
        if (i != 1) throw new UpdateException("修改异常");

    }

    @Override
    public void delete(Integer aid, Integer uid, String username) {
        Address result = addressMapper.findByAid(aid);
        if (result == null) throw new AddressNotFoundException("收货地址数据不存在");

        if (!result.getUid().equals(uid)) throw new AccessDeniedException("非法访问");

        Integer i = addressMapper.deleteByAid(aid);
        if (i != 1) throw new DeleteException("删除失败");

        // 判断查询结果中的isDefault是否为0
        if (result.getIsDefault() == 0) {
            return;
        }

        // 调用持久层的countByUid()统计目前还有多少收货地址
        Integer count = addressMapper.countByUid(uid);
        // 判断目前的收货地址的数量是否为0
        if (count == 0) {
            return;
        }

        // 调用findLastModified()找出用户最近修改的收货地址数据
        Address lastModified = addressMapper.findLastModified(uid);
        // 从以上查询结果中找出aid属性值
        Integer lastModifiedAid = lastModified.getAid();
        // 调用持久层的updateDefaultByAid()方法执行设置默认收货地址，并获取返回的受影响的行数
        Integer rows2 = addressMapper.updateDefaultByAid(lastModifiedAid, username, new Date());
        // 判断受影响的行数是否不为1
        if (rows2 != 1) {
            // 是：抛出UpdateException
            throw new UpdateException("更新收货地址数据时出现未知错误，请联系系统管理员");
        }
    }

    @Override
    public Address getByAid(Integer aid, Integer uid) {
        // 根据收货地址数据id，查询收货地址详情
        Address address = addressMapper.findByAid(aid);

        if (address == null) {
            throw new AddressNotFoundException("尝试访问的收货地址数据不存在");
        }
        if (!address.getUid().equals(uid)) {
            throw new AccessDeniedException("非法访问");
        }
        address.setProvinceCode(null);
        address.setCityCode(null);
        address.setAreaCode(null);
        address.setCreatedUser(null);
        address.setCreatedTime(null);
        address.setModifiedUser(null);
        address.setModifiedTime(null);
        return address;
    }


}
