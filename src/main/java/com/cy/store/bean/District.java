package com.cy.store.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author joker
 * @create 2022-04-28
 */
@Data
public class District extends BaseEntity implements Serializable {

    private Integer id;
    private String parent;
    private String code;
    private String name;

}
