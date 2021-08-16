package com.monetware.pdexperiment.business.pojo.po;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author Cookie
 * @create 2020-10-23 17:04
 * @Description: 实验表
 */
@Data
@Table(name = "pd_task")
public class PdTask {

    @Id
    private Integer id;

    /**
     * 实验名称
     */
    private String name;

    /**
     * 实验封面
     */
    private String coverImg;

    /**
     * 实验人数
     */
    private Integer viewNum;

    /**
     * 首页动画
     */
    private String indexFlash;

    /**
     * 实验简介
     */
    private String digest;

    /**
     * 创建时间
     */
    private Date createTime;

}
