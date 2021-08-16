package com.monetware.pdexperiment.business.pojo.vo.manage;

import com.monetware.pdexperiment.business.pojo.dto.PdUserTaskFileSummaryDTO;
import lombok.Data;

import java.util.List;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/29 2:25 下午
 * @description 更新最佳证据汇总传入VO
 */
@Data
public class UpdateSummaryVO {

    /**
     * 最佳运动方式证据列表
     */
    private List<PdUserTaskFileSummaryDTO> way;

    /**
     * 最佳运动强度证据列表
     */
    private List<PdUserTaskFileSummaryDTO> intensity;

}
