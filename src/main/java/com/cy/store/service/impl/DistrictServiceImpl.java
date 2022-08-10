package com.cy.store.service.impl;

import com.cy.store.bean.District;
import com.cy.store.mapper.DistrictMapper;
import com.cy.store.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-28
 */
@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
        List<District> list = districtMapper.findByParent(parent);

        /*
        在进行网络传输时 为避免无效数据的传输 可以将无效数据设置为null
        可以节省流量和提升效率
         */
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);

    }
}
