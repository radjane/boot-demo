package cn.fulong.bootdemo.entity;


import lombok.Data;

import java.io.Serializable;

/**
 *@Author:GHB
 *@Date:2019-06-22
 *
 */

@Data
public class SysUser implements Serializable {

    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private int    age;
    private String    name;




}
