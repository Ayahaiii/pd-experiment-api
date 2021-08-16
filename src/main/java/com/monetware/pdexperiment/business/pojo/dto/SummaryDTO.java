package com.monetware.pdexperiment.business.pojo.dto;

import com.monetware.pdexperiment.business.pojo.po.PdUserTaskFile;
import lombok.Data;

import java.util.List;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 3:11 下午
 * @description 最佳证据汇总返回DTO
 */
@Data
public class SummaryDTO {

    /**
     * 证据选择列表
     */
    private List<PdUserTaskFileSummaryDTO> pdUserTaskFiles;

    /**
     * 最佳运动方式证据列表
     */
    private List<PdUserTaskFileSummaryDTO> goodExerciseWayFiles;

    /**
     * 最佳运动强度证据列表
     */
    private List<PdUserTaskFileSummaryDTO> goodExerciseIntensityFiles;

}
