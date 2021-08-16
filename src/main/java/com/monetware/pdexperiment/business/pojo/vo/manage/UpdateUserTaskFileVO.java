package com.monetware.pdexperiment.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 1:36 下午
 * @description 更新用户实验附件传入VO
 */
@Data
public class UpdateUserTaskFileVO {

    /**
     * 用户实验附件id
     */
    private Integer id;

    /**
     * 证据分级
     */
    private String level;

    /**
     * 分级依据
     */
    private String levelReason;

    /**
     * 运动方式
     */
    private String exerciseWay;

    /**
     * 运动强度
     */
    private String exerciseIntensity;

    /**
     * 证据可行性（强推荐/弱推荐/不清楚）
     */
    private String feasibilityLevel;

    /**
     * 证据可行性判断依据
     */
    private String feasibilityReason;

    /**
     * 证据适宜性（强推荐/弱推荐/不清楚）
     */
    private String appropriatenessLevel;

    /**
     * 证据适宜性判断依据
     */
    private String appropriatenessReason;

    /**
     * 证据的临床意义（强推荐/弱推荐/不清楚）
     */
    private String meaningLevel;

    /**
     * 证据的临床意义判断依据
     */
    private String meaningReason;

    /**
     * 证据的有效性（强推荐/弱推荐/不清楚）
     */
    private String effectivenessLevel;

    /**
     * 证据的有效性判断依据
     */
    private String effectivenessReason;

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
