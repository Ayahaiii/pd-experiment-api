package com.monetware.pdexperiment.business.pojo.po;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Cookie
 * @create 2020-10-23 17:03
 * @Description: 实验用户表
 */
@Data
@Table(name = "pd_user")
public class PdUser {

    @Id
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 最后一次登陆时间
     */
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    private Date createTime;

}
