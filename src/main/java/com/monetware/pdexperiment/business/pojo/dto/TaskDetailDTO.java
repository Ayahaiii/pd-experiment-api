package com.monetware.pdexperiment.business.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @description: TaskDetail
 * @author: 彭于晏
 * @create: 2020-10-28 18:39
 **/
@Data
public class TaskDetailDTO {

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

    /**
     * 用户实验id
     */
    private Integer userTaskId;

    /**
     * 完成步骤序号
     */
    private Integer finishStepNum;
}
