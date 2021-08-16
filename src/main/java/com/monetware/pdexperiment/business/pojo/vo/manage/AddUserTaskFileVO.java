package com.monetware.pdexperiment.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 1:28 下午
 * @description 新增用户实验附件传入VO
 */
@Data
public class AddUserTaskFileVO {

    /**
     * 用户实验id
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

}
