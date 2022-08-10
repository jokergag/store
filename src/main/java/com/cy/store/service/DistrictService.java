package com.cy.store.service;

import com.cy.store.bean.District;

import java.util.List;

/**
 * @author joker
 * @create 2022-04-28
 */
public interface DistrictService {

    /**
     * 根据父代号来查询区域信息
     * @param parent
     * @return
     */
    List<District> getByParent(String parent);

    String getNameByCode(String code);


}
