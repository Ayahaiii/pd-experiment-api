package com.monetware.pdexperiment.business.pojo.dto;

import lombok.Data;

/**
 * @description: IFBeginDTO
 * @author: 彭于晏
 * @create: 2020-10-28 13:52
 **/
@Data
public class IFBeginDTO {
    /**
     * 用户实验id
     */
    private Integer userTaskId;

    /**
     * 完成步骤序号
     */
    private Integer finishStepNum;
}
