package com.yinpei.peipeiaccountingsys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Notice {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private String time;
    private Boolean open;

    @TableField(exist = false)
    private User user;
}
