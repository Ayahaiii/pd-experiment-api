package com.monetware.pdexperiment.business.pojo.vo.manage;

import lombok.Data;

/**
 * @description: IfBeginVO
 * @author: 彭于晏
 * @create: 2020-10-28 13:52
 **/
@Data
public class IfBeginVO {
    /**
     * 实验id
     */
    private Integer taskId;
    /**
     * 用户实验id
     */
    private Integer userTaskId;
}
