package com.monetware.pdexperiment.business.controller;

import com.monetware.pdexperiment.business.pojo.dto.IFBeginDTO;
import com.monetware.pdexperiment.business.pojo.dto.PdUserTaskDTO;
import com.monetware.pdexperiment.business.pojo.dto.UserTaskHistroyDTO;
import com.monetware.pdexperiment.business.pojo.po.PdUserTask;
import com.monetware.pdexperiment.business.pojo.vo.manage.AddUserTaskVO;
import com.monetware.pdexperiment.business.pojo.vo.manage.IfBeginVO;
import com.monetware.pdexperiment.business.pojo.vo.manage.UpdateUserTaskVO;
import com.monetware.pdexperiment.business.service.manage.UserTaskFileManageService;
import com.monetware.pdexperiment.business.service.manage.UserTaskManageService;
import com.monetware.pdexperiment.system.base.CommonIdParam;
import com.monetware.pdexperiment.system.base.PageList;
import com.monetware.pdexperiment.system.base.PageParam;
import com.monetware.pdexperiment.system.base.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 2:49 下午
 * @description PD用户实验管理相关api
 */
@Api("PD用户实验管理相关api")
@RestController
@RequestMapping("/pd")
public class UserTaskManageController {
    
    @Autowired
    UserTaskManageService userTaskManageService;
    
    // =========================== budi Begin ===========================
    
    /**
     * @Author budi
     * @Description 新增用户实验
     * @Date 2:53 下午 2020/10/26
     * @Param [param]
     * @return com.monetware.pdexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "C")
    @PostMapping("v1/addUserTask")
    public ResultData<Integer> addUserTask(@RequestBody AddUserTaskVO param) {
        return new ResultData<>(0, "调用成功", userTaskManageService.addUserTask(param));
    }
    
    /**
     * @Author budi
     * @Description 修改用户实验
     * @Date 2:54 下午 2020/10/26
     * @Param [param]
     * @return com.monetware.pdexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "修改用户实验")
    @PostMapping("v1/@@re@")
    public ResultData<Integer> updateUserTask(@RequestBody UpdateUserTaskVO param) {
        return new ResultData<>(0, "调用成功", userTaskManageService.updateUserTask(param));
    }
    
    /**
     * @Author budi
     * @Description 查找用户实验
     * @Date 2:55 下午 2020/10/26
     * @Param [param]
     * @return com.monetware.pdexperiment.system.base.ResultData<com.monetware.pdexperiment.business.pojo.po.PdUserTask>
     **/
    @ApiOperation(value = "查找用户实验")
    @PostMapping("v1/searchUserTaskById")
    public ResultData<PdUserTaskDTO> searchUserTaskById(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", userTaskManageService.searchUserTaskById(param));
    }
    
    /**
     * @Author budi
     * @Description 下一步
     * @Date 2:57 下午 2020/10/26
     * @Param [param]
     * @return com.monetware.pdexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "下一步")
    @PostMapping("v1/nextStep")
    public ResultData<Integer> nextStep(@RequestBody CommonIdParam param) {
        return new ResultData<>(0, "调用成功", userTaskManageService.nextStep(param));
    }
    
    // =========================== budi End ===========================

    // =========================== hs begin ===========================

    /**
     * 判断用户是否开始
     * @param ifBeginVO
     * @return
     */
    @ApiOperation(value = "判断用户是否开始")
    @PostMapping("v1/ifBegin")
      public ResultData<IFBeginDTO> ifBegin(@RequestBody IfBeginVO ifBeginVO){
        IFBeginDTO ifBeginDTO = userTaskManageService.ifBegin(ifBeginVO);
        if(ifBeginDTO==null){
            return new ResultData(1,"实验未开始");
        }
        return new ResultData<>(0,"继续实验",ifBeginDTO);
      }

    /**
     * 获取当前步骤
     * @param rctUserTaskVo
     * @return
     */
    @PostMapping("/getCurrentStep")
    @ApiOperation("获取当前步骤")
    public ResultData<Integer> getCurrentStep(@RequestBody CommonIdParam rctUserTaskVo){

        return new ResultData<>(0,"调用成功",userTaskManageService.getCurrentStep(rctUserTaskVo));
    }

    /**
     * 查询用户做过的实验表
     * @param pageParam
     * @return
     */
    @PostMapping("v1/getUserTaskList")
    @ApiOperation("查询用户做过的实验表")
    public ResultData<PageList<UserTaskHistroyDTO>> getUserHistroy(@RequestBody PageParam pageParam){

        return new ResultData<>(0,"调用成功",userTaskManageService.getUserHistroy(pageParam));
    }

    /**
     * 删除用户id
     * @param rctUserTaskVo
     * @return
     */
    @PostMapping("v1/delUserTask")
    @ApiOperation("删除用户实验")
    public ResultData<Integer> delUserTask(@RequestBody CommonIdParam rctUserTaskVo){

        return new ResultData<>(0,"调用成功",userTaskManageService.delUserTask(rctUserTaskVo));
    }

    /**
     * 结束用户实验
     * @param rctUserTaskVo
     * @return
     */
    @PostMapping("v1/filalyTask")
    @ApiOperation("结束用户实验")
    public ResultData<Integer> filalyTask(@RequestBody CommonIdParam rctUserTaskVo){

        return  new ResultData<>(0,"调用成功",userTaskManageService.filalyTask(rctUserTaskVo));
    }
    // =========================== hs End ===========================
    
}
