package com.yinpei.peipeiaccountingsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {

    @TableId(type= IdType.AUTO)
    private Integer id;
    private String companyName;
    private String companyAddress;
    private Integer companyResponsibleOfficerId;
    private Date companyDate;
    private String companyLegalRepresentative;
    private String companyRegisteredCapital;
    private String companyState;

    @TableField(exist=false)
    private String companyResponsibleOfficer;
}
