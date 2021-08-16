package com.monetware.pdexperiment.business.controller;

import com.monetware.pdexperiment.business.pojo.dto.PdTaskDTO;
import com.monetware.pdexperiment.business.pojo.dto.TaskDetailDTO;
import com.monetware.pdexperiment.business.service.manage.TaskManageService;
import com.monetware.pdexperiment.system.base.CommonIdParam;
import com.monetware.pdexperiment.system.base.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/28 5:35 下午
 * @description PD实验管理相关api
 */
@Api("PD实验管理相关api")
@RestController
@RequestMapping("/pd")
public class TaskManageController {
    
    @Autowired
    TaskManageService taskManageService;
    
    // =========================== budi Begin ===========================
    
    /**
     * @Author budi
     * @Description 获取实验列表
     * @Date 5:38 下午 2020/10/28
     * @Param []
     * @return com.monetware.pdexperiment.system.base.ResultData<java.util.List<com.monetware.pdexperiment.business.pojo.dto.PdTaskDTO>>
     **/
    @ApiOperation(value = "获取实验列表")
    @PostMapping("v1/getTaskList")
    public ResultData<List<PdTaskDTO>> getTaskList() {
        return new ResultData<>(0, "调用成功", taskManageService.getTaskList());
    }

    /**
     * 获取实验详情
     * @param commonIdParam
     * @return
     */
    @ApiOperation(value = "获取实验详情")
    @PostMapping("v1/getTaskDetail")
    public ResultData<TaskDetailDTO> getTaskDetail(@RequestBody CommonIdParam commonIdParam){

        return new ResultData<>(0, "调用成功", taskManageService.getTaskDetail(commonIdParam));
    }

    
    // =========================== budi End ===========================
    
}
