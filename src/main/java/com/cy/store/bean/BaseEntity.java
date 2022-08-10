package com.cy.store.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类的基类
 * @author joker
 * @create 2022-04-23
 */
@Data
public class BaseEntity implements Serializable {

    private String createdUser;
    private Date createdTime;
    private String modifiedUser;
    private Date modifiedTime;

}
