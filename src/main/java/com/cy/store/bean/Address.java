package com.cy.store.bean;


import lombok.Data;

import java.io.Serializable;

/**
 * @author joker
 * @create 2022-04-27
 */
@Data
public class Address extends BaseEntity implements Serializable {

    private Integer aid;
    private Integer uid;
    private String name;
    private String provinceName;
    private String provinceCode;
    private String cityName;
    private String cityCode;
    private String areaName;
    private String areaCode;
    private String zip;
    private String address;
    private String phone;
    private String tel;
    private String tag;
    private Integer isDefault;


}
