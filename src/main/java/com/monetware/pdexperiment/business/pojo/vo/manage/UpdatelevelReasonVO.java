package com.monetware.pdexperiment.business.pojo.vo.manage;

import com.monetware.pdexperiment.business.pojo.po.PdUserTaskFile;
import lombok.Data;

import java.util.List;

/**
 * @description: updatelevelReasonVO
 * @author: 彭于晏
 * @create: 2020-10-28 17:31
 **/
@Data
public class UpdatelevelReasonVO {
    /**
     * 用户实验id
     */
    public Integer userTaskId;
    private List<PdUserTaskFile> leavlReasonList;
}
