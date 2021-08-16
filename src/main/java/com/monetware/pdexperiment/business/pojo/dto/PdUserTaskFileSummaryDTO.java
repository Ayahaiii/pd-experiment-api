package com.monetware.pdexperiment.business.pojo.dto;

import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/29 11:20 上午
 * @description 用户实验附件汇总相关返回DTO
 */
@Data
public class PdUserTaskFileSummaryDTO {

    /**
     * 用户实验附件id
     */
    private Integer id;

    /**
     * 第一作者（年份）
     */
    private String firstAuthor;

    /**
     * 证据分级
     */
    private String level;

    /**
     * 推荐等级
     */
    private String recommendLevel;

    /**
     * 最佳运动方式证据汇总
     */
    private String waySummary;

    /**
     * 是否是最佳运动方式证据
     * 1. 是
     * 2. 否
     */
    private Integer ifGoodExerciseWay;

    /**
     *最佳运动强度证据汇总
     */
    private String intensitySummary;

    /**
     * 是否是最佳运动强度证据
     * 1. 是
     * 2. 否
     */
    private Integer ifGoodExerciseIntensity;

}
