package com.monetware.pdexperiment.business.pojo.vo.manage;

import lombok.Data;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 11:47 上午
 * @description 新增用户实验传入VO
 */
@Data
public class AddUserTaskVO {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 实验id
     */
    private Integer taskId;

}
