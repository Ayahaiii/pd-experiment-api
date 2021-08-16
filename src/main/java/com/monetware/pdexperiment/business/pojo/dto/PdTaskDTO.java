package com.monetware.pdexperiment.business.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/28 5:31 下午
 * @description 实验返回DTO
 */
@Data
public class PdTaskDTO {

    /**
     * 实验id
     */
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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}
