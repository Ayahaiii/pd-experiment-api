package com.monetware.pdexperiment.business.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/27 1:16 下午
 * @description 用户实验返回DTO
 */
@Data
public class PdUserTaskDTO {

    /**
     * 用户实验id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 实验id
     */
    private Integer taskId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 对象
     */
    private String population;

    /**
     * 干预措施
     */
    private String intervention;

    /**
     * 对照措施
     */
    private String comparisons;

    /**
     * 结局指标
     */
    private String outcomes;

    /**
     * 研究问题
     */
    private String researchQuestion;

    /**
     * 研究方法
     */
    private String researchMethod;

    /**
     * 研究成果
     */
    private String researchResult;

    /**
     * 申请时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date applyTime;

    /**
     * 中文检索式
     */
    private String cnSearchParam;

    /**
     * 英文检索式
     */
    private String enSearchParam;

    /**
     * 数据库&数据库数量（json）
     */
    private String databaseNum;

    /**
     * 阅读后全文纳入数量
     */
    private Integer allLiteratureNum;

    /**
     * 排除后文献数量
     */
    private Integer remainLiteratureNum;

    /**
     * 最终文献数量
     */
    private Integer finalLiteratureNum;

    /**
     * 成果文件地址
     */
    private String resultFileUrl;

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginTime;

    /**
     * 结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endTime;
    /**
     * 批准号
     */
    private String authorizationNum;
}
