package com.yinpei.peipeiaccountingsys.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @TableId(type= IdType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private Integer departmentId;
    private String address;
    private String avatar;
    private Date birthday;
    private String token;
    private String role;
}
