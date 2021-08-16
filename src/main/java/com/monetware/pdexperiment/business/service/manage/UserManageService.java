package com.monetware.pdexperiment.business.service.manage;

import com.monetware.pdexperiment.business.dao.PdUserDao;
import com.monetware.pdexperiment.business.pojo.dto.PdUserDTO;
import com.monetware.pdexperiment.business.pojo.po.PdUser;
import com.monetware.pdexperiment.system.base.ResultData;
import com.monetware.pdexperiment.system.util.redis.RedisUtil;
import com.monetware.threadlocal.ThreadLocalManager;
import com.monetware.threadlocal.TokenContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 3:27 下午
 * @description 用户管理相关方法
 */
@Slf4j
@Service
public class UserManageService {

    @Autowired
    PdUserDao pdUserDao;

    @Autowired
    RedisUtil redisUtil;

    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 用户登录
     * @Date 3:48 下午 2020/10/26
     * @Param []
     * @return java.lang.Integer
     **/
    public Integer userLogin() {
        PdUser pdUser = new PdUser();
        pdUser.setId(ThreadLocalManager.getUserId());
        PdUser pdUser1 = pdUserDao.selectOne(pdUser);
        System.out.println(ThreadLocalManager.getTokenContext().getUserId());
        pdUser.setUsername(ThreadLocalManager.getTokenContext().getName());
        pdUser.setLastLoginTime(new Date());
        if (pdUser1 == null) {
            pdUser.setCreateTime(new Date());
            return pdUserDao.insertSelective(pdUser);
        } else {
            return pdUserDao.updateByPrimaryKey(pdUser);
        }
    }

    /**
     * @Author budi
     * @Description 获取用户信息
     * @Date 3:50 下午 2020/10/26
     * @Param []
     * @return com.monetware.pdexperiment.business.pojo.po.PdUser
     **/
    public PdUserDTO getUserInfo() {
        PdUserDTO dto = new PdUserDTO();
        PdUser pdUser = pdUserDao.selectByPrimaryKey(ThreadLocalManager.getUserId());
        BeanUtils.copyProperties(pdUser, dto);
        return dto;
    }

    /**
     * @Author budi
     * @Description 用户登出
     * @Date 3:52 下午 2020/10/26
     * @Param []
     * @return java.lang.Integer
     **/
    public Integer userLogout() {
        redisUtil.remove(ThreadLocalManager.getTokenContext().getToken());
        return ResultData.OK;
    }

    // =========================== budi End ===========================

}
