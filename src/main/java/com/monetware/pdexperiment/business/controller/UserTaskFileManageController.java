package com.monetware.pdexperiment.business.controller;

import com.monetware.pdexperiment.business.pojo.dto.PdUserTaskFileDTO;
import com.monetware.pdexperiment.business.pojo.dto.SummaryDTO;
import com.monetware.pdexperiment.business.pojo.po.PdUserTaskFile;
import com.monetware.pdexperiment.business.pojo.vo.manage.*;
import com.monetware.pdexperiment.business.service.manage.UserTaskFileManageService;
import com.monetware.pdexperiment.system.base.CommonIdParam;
import com.monetware.pdexperiment.system.base.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 2:57 下午
 * @description PD用户实验附件管理相关api
 */
@Api("PD用户实验附件管理相关api")
@RestController
@RequestMapping("/pd")
public class UserTaskFileManageController {
    
    @Autowired
    UserTaskFileManageService userTaskFileManageService;
    
    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 新增用户实验附件
     * @Date 3:00 下午 2020/10/26
     * @Param [param]
     * @return com.monetware.pdexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "新增用户实验附件")
    @PostMapping("v1/addUserTaskFile")
    public ResultData<Integer> addUserTaskFile(@RequestBody AddUserTaskFileVO param) {
        return new ResultData<>(0, "调用成功", userTaskFileManageService.addUserTaskFile(param));
    }
    
    /**
     * @Author budi
     * @Description 查找用户实验附件（通过用户实验id）
     * @Date 3:02 下午 2020/10/26
     * @Param [param]
     * @return com.monetware.pdexperiment.system.base.ResultData<java.util.List<com.monetware.pdexperiment.business.pojo.po.PdUserTaskFile>>
     **/
    @ApiOperation(value = "查找用户实验附件（通过用户实验id）")
    @PostMapping("v1/searchUserTaskFileByUserTaskId")
    public ResultData<List<PdUserTaskFileDTO>> searchUserTaskFileByUserTaskId(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", userTaskFileManageService.searchUserTaskFileByUserTaskId(param));
    }
    
    /**
     * @Author budi
     * @Description 修改用户实验附件
     * @Date 3:06 下午 2020/10/26
     * @Param [param]
     * @return com.monetware.pdexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "修改用户实验附件")
    @PostMapping("v1/updateUserTaskFile")
    public ResultData<Integer> updateUserTaskFile(@RequestBody UpdateUserTaskFileVO param) {
        return new ResultData<>(0, "调用成功", userTaskFileManageService.updateUserTaskFile(param));
    }
    
    /**
     * @Author budi
     * @Description 获取汇总页面信息
     * @Date 3:24 下午 2020/10/26
     * @Param [param]
     * @return com.monetware.pdexperiment.system.base.ResultData<com.monetware.pdexperiment.business.pojo.dto.SummaryDTO>
     **/
    @ApiOperation(value = "获取汇总页面信息")
    @PostMapping("v1/getSummaryPageInfo")
    public ResultData<SummaryDTO> getSummaryPageInfo(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", userTaskFileManageService.getSummaryPageInfo(param));
    }

    /**
     * @Author budi
     * @Description 更新最佳运动方式、最佳运动强度证据列表
     * @Date 2:52 下午 2020/10/29
     * @Param [param]
     * @return com.monetware.pdexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "更新最佳运动方式、最佳运动强度证据列表")
    @PostMapping("v1/updateSummaryPageInfo")
    public ResultData<Integer> updateSummaryPageInfo(@RequestBody UpdateSummaryVO param) {
        return new ResultData<>(0, "调用成功", userTaskFileManageService.updateSummaryPageInfo(param));
    }

    // =========================== budi End ===========================
    //===================================hs end====================================

    /**
     * 文件上传
     * @param file
     * @return
     */
    @ApiOperation(value = "上传文件")
    @PostMapping("v1/uploadUserTaskFile")
    public ResultData<UploadPdfDTO> uploadUserTaskFile(MultipartFile file){

        return new ResultData<>(0,"调用成功",userTaskFileManageService.uploadPdF(file));
    }

    /**
     * 导出word文档
     * @param baseToImgVo
     * @return
     */
    @PostMapping("v1/exportWord")
    @ApiOperation("保存word")
    public ResultData<Integer> exportWord(@RequestBody CommonIdParam baseToImgVo){
        userTaskFileManageService.exportWord(baseToImgVo);
        return new ResultData<>(0,"调用成功");
    }

    /**
     * 修改证据分级
     * @return
     */
    @PostMapping("v1/updateLevel")
    @ApiOperation("修改证据分级")
    public ResultData<Integer> updateLevel(@RequestBody UpdateLevelVO updateLevelVo){
        return new ResultData<>(0,"调用成功",userTaskFileManageService.updateLevel(updateLevelVo));
    }

    /**
     * 修改证据推荐
     * @return
     */
    @PostMapping("v1/updatelevelReason")
    @ApiOperation("修改证据推荐")
    public ResultData<Integer> updatelevelReason(@RequestBody UpdatelevelReasonVO updateLevelVo){
        return new ResultData<>(0,"调用成功",userTaskFileManageService.updatelevelReason(updateLevelVo));
    }
    //================================hs End===================================
}
