package com.monetware.pdexperiment.business.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/27 1:22 下午
 * @description 用户实验附件返回DTO
 */
@Data
public class PdUserTaskFileDTO {

    /**
     * 用户实验附件id
     */
    private Integer id;

    /**
     * 用户实验ID
     */
    private Integer userTaskId;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 文件地址
     */
    private String url;

    /**
     * 文献名
     */
    private String title;

    /**
     * 第一作者（年份）
     */
    private String firstAuthor;

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
     * 证据可行性（强推荐/弱推荐/不清楚
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
     * 证据的临床意义（强推荐/弱推荐/不清楚
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

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    /**
     *
     */
    private List<String> options2;
}
