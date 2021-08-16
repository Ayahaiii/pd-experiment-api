package com.monetware.pdexperiment.business.pojo.constant;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 2:16 下午
 * @description 实验步骤相关常量
 */
public class StepConstant {

    /**
     * 开始实验——项目申报未完成
     */
    public static final Integer STEP1 = 1;

    /**
     * 项目申报已完成——项目申报准备未完成
     */

    public static final Integer STEP2 = 2;

    /**
     * 项目申报准备已完成——提交审批未完成
     */

    public static final Integer STEP3 = 3;

    /**
     * 提交审批已完成——循证和选证未完成
     */
    public static final Integer STEP4 = 4;

    /**
     * 循证和选证已完成——证据检索与筛选未完成
     */
    public static final Integer STEP5 = 5;

    /**
     * 证据检索与筛选已完成——上传纳入证据未完成
     */
    public static final Integer STEP6 = 6;

    /**
     * 上传纳入证据已完成——证据分级与推荐未完成
     */
    public static final Integer STEP7 = 7;

    /**
     * 证据推荐已完成——证据推荐-评价未完成
     */
    public static final Integer STEP8 = 8;

    /**
     * 证据推荐-评价已完成——最佳证据汇总未完成
     */
    public static final Integer STEP9 = 9;

    /**
     * 最佳证据汇总已完成
     */
    public static final Integer STEP10 = 10;

}
