package com.cy.store.bean;

import lombok.Data;

import java.io.Serializable;

/**
 * @author joker
 * @create 2022-04-23
 */
@Data
public class User extends BaseEntity implements Serializable {

    private Integer uid;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private Integer gender;
    private String avatar;
    private Integer isDelete;

}
