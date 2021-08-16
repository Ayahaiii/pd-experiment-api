package com.monetware.pdexperiment.business.pojo.vo.manage;

import lombok.Data;

import java.util.Date;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 1:01 下午
 * @description 更新用户实验传入VO
 */
@Data
public class UpdateUserTaskVO {

    /**
     * 用户实验id
     */
    private Integer id;

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

}
