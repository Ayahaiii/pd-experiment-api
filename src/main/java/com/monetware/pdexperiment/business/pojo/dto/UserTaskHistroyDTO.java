package com.monetware.pdexperiment.business.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @description: UserTastHistroy
 * @author: 彭于晏
 * @create: 2020-10-29 10:33
 **/
@Data
public class UserTaskHistroyDTO {
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 实验id
     */
    private Integer taskId;
    /**
     * 完成步骤序号
     */
    private Integer finishStepNum;

    /**
     * 状态：1:已完成，2:未完成
     */
    private Integer status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date beginTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

}
