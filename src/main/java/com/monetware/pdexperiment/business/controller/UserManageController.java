package com.monetware.pdexperiment.business.controller;

import com.monetware.pdexperiment.business.pojo.dto.PdUserDTO;
import com.monetware.pdexperiment.business.pojo.po.PdUser;
import com.monetware.pdexperiment.business.service.manage.UserManageService;
import com.monetware.pdexperiment.system.base.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 3:44 下午
 * @description PD用户管理相关api
 */
@Api("PD用户管理相关api")
@RestController
@RequestMapping("/pd")
public class UserManageController {
    
    @Autowired
    UserManageService userManageService;
    
    // =========================== budi Begin ===========================
    
    /**
     * @Author budi
     * @Description 用户登录
     * @Date 3:47 下午 2020/10/26
     * @Param []
     * @return com.monetware.pdexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "用户登录")
    @PostMapping("v1/userLogin")
    public ResultData<Integer> userLogin() {

        return new ResultData<>(0, "调用成功", userManageService.userLogin());
    }
    
    /**
     * @Author budi
     * @Description 获取用户信息
     * @Date 3:55 下午 2020/10/26
     * @Param []
     * @return com.monetware.pdexperiment.system.base.ResultData<com.monetware.pdexperiment.business.pojo.po.PdUser>
     **/
    @ApiOperation(value = "获取用户信息")
    @PostMapping("v1/getUserInfo")
    public ResultData<PdUserDTO> getUserInfo() {
        return new ResultData<>(0, "调用成功", userManageService.getUserInfo());
    }
    
    /**
     * @Author budi
     * @Description 用户登出
     * @Date 3:59 下午 2020/10/26
     * @Param []
     * @return com.monetware.pdexperiment.system.base.ResultData<java.lang.Integer>
     **/
    @ApiOperation(value = "用户登出")
    @PostMapping("v1/userLogout")
    public ResultData<Integer> userLogout() {
        return new ResultData(0, "调用成功", userManageService.userLogout());
    }
    
    // =========================== budi End ===========================
    
}
