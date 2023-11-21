package com.yinpei.peipeiaccountingsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Department {

    @TableId(type= IdType.AUTO)
    private Integer departmentId;
    private String departmentName;
    private Integer departmentManagerId;

    @TableField(exist = false)
    private String departmentManager;
    @TableField(exist = false)
    private List<User> departmentEmps;


}
