package com.monetware.pdexperiment.business.pojo.vo.manage;

import com.monetware.pdexperiment.business.pojo.po.PdUserTaskFile;
import lombok.Data;

import java.util.List;

/**
 * @description: UpdateLevelVo
 * @author: 彭于晏
 * @create: 2020-10-28 15:39
 **/
@Data
public class UpdateLevelVO {
    /**
     * 用户实验id
     */
    private Integer userTaskId;
    /**
     * 获取修改列表
     */
    private List<PdUserTaskFile> leavlList;
}
