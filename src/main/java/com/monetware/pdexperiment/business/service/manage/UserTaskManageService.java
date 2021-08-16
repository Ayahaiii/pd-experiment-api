package com.monetware.pdexperiment.business.service.manage;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.monetware.pdexperiment.business.dao.PdTaskDao;
import com.monetware.pdexperiment.business.dao.PdUserTaskDao;
import com.monetware.pdexperiment.business.pojo.constant.StepConstant;
import com.monetware.pdexperiment.business.pojo.dto.IFBeginDTO;
import com.monetware.pdexperiment.business.pojo.dto.PdTaskDTO;
import com.monetware.pdexperiment.business.pojo.dto.PdUserTaskDTO;
import com.monetware.pdexperiment.business.pojo.dto.UserTaskHistroyDTO;
import com.monetware.pdexperiment.business.pojo.po.PdTask;
import com.monetware.pdexperiment.business.pojo.po.PdUserTask;
import com.monetware.pdexperiment.business.pojo.vo.manage.AddUserTaskVO;
import com.monetware.pdexperiment.business.pojo.vo.manage.IfBeginVO;
import com.monetware.pdexperiment.business.pojo.vo.manage.UpdateUserTaskVO;
import com.monetware.pdexperiment.system.base.CommonIdParam;
import com.monetware.pdexperiment.system.base.ErrorCode;
import com.monetware.pdexperiment.system.base.PageList;
import com.monetware.pdexperiment.system.base.PageParam;
import com.monetware.pdexperiment.system.exception.ServiceException;
import com.monetware.pdexperiment.system.util.upload.GetFileNameUtils;
import com.monetware.threadlocal.ThreadLocalManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 11:53 上午
 * @description 用户实验管理相关方法
 */
@Slf4j
@Service
public class UserTaskManageService {

    @Autowired
    PdUserTaskDao pdUserTaskDao;
    @Autowired
    PdTaskDao pdTaskDao;
    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 新增用户实验
     * @Date 1:01 下午 2020/10/26
     * @Param [param]
     * @return java.lang.Integer
     **/
    public Integer addUserTask(AddUserTaskVO param) {
        PdTask pdTask=new PdTask();
        pdTask.setId(param.getTaskId());
        PdTask pdTask1 = pdTaskDao.selectOne(pdTask);
        Integer i=1;
        Example example=new Example(PdUserTask.class);
        example.orderBy("id").desc();
        //第一次开始实验添加成功之后获取用户实验表中的一些相关信息。
        List<PdUserTask> pdUserTask = pdUserTaskDao.selectByExample(example);
        if(pdUserTask!=null&&pdUserTask.size()!=0){
            i=pdUserTask.get(0).getId()+1;
        }
        Example example1=new Example(PdUserTask.class);
        example.orderBy("authorizationNum").desc();
        List<PdUserTask> pdUserTaskNum = pdUserTaskDao.selectByExample(example1);
        String s="KY"+ GetFileNameUtils.yearAndMonthAndDay();
        if(pdUserTaskNum!=null&&pdUserTaskNum.size()!=0&&pdUserTask.get(0).getAuthorizationNum()!=null){
            String num=pdUserTask.get(0).getAuthorizationNum();
            String substring = num.substring(num.length() - 3, num.length());
            Integer num1=Integer.valueOf(substring)+1;
            substring=num1.toString();
           switch (substring.length()){
               case 1 :
                   substring="00"+substring;
                   break;
               case 2:
                   substring="0"+substring;
                   break;
               default:
                       break;
           }
           s=s+substring;
        }else{
            s=s+"001";
        }
        // 新增用户实验
        PdUserTask pdUserTask1= new PdUserTask();
        pdUserTask1.setBeginTime(new Date());
        pdUserTask1.setStatus(2);
        pdUserTask1.setId(i);
        pdUserTask1.setProjectName(pdTask1.getName());
        pdUserTask1.setAuthorizationNum(s);
        pdUserTask1.setFinishStepNum(StepConstant.STEP1);
        pdUserTask1.setUserId(ThreadLocalManager.getUserId());
        pdUserTask1.setTaskId(param.getTaskId());
        pdUserTaskDao.insertSelective(pdUserTask1);
        return i;
    }
    
    /**
     * @Author budi
     * @Description 修改用户实验
     * @Date 1:09 下午 2020/10/26
     * @Param [param]
     * @return java.lang.Integer
     **/
    public Integer updateUserTask(UpdateUserTaskVO param) {
        // 修改用户实验
        PdUserTask pdUserTask = pdUserTaskDao.selectByPrimaryKey(param.getId());
        BeanUtils.copyProperties(param, pdUserTask);
        CommonIdParam commonIdParam = new CommonIdParam();
        commonIdParam.setId(param.getId());
        nextStep(commonIdParam);
        return pdUserTaskDao.updateByPrimaryKeySelective(pdUserTask);
    }
    
    /**
     * @Author budi
     * @Description 查找用户实验
     * @Date 1:12 下午 2020/10/26
     * @Param [param]
     * @return com.monetware.pdexperiment.business.pojo.po.PdUserTask
     **/
    public PdUserTaskDTO searchUserTaskById(CommonIdParam param) {
        PdUserTaskDTO dto = new PdUserTaskDTO();
        // 通过用户实验id查找用户实验
        PdUserTask pdUserTask = new PdUserTask();
        pdUserTask.setId(param.getId());
        pdUserTask = pdUserTaskDao.selectOne(pdUserTask);
        BeanUtils.copyProperties(pdUserTask, dto);
        return dto;
    }
    
    /**
     * @Author budi
     * @Description 下一步
     * @Date 2:51 下午 2020/10/26
     * @Param [param]
     * @return java.lang.Integer
     **/
    public Integer nextStep(CommonIdParam param) {
        // 根据传入的用户实验id找到用户实验对象
        PdUserTask pdUserTask = pdUserTaskDao.selectByPrimaryKey(param.getId());
        switch (pdUserTask.getFinishStepNum()) {
            case 0:
                pdUserTask.setFinishStepNum(StepConstant.STEP1);
                break;
            case 1:
                pdUserTask.setFinishStepNum(StepConstant.STEP2);
                break;
            case 2:
                pdUserTask.setFinishStepNum(StepConstant.STEP3);
                break;
            case 3:
                pdUserTask.setFinishStepNum(StepConstant.STEP4);
                break;
            case 4:
                pdUserTask.setFinishStepNum(StepConstant.STEP5);
                break;
            case 5:
                pdUserTask.setFinishStepNum(StepConstant.STEP6);
                break;
            case 6:
                pdUserTask.setFinishStepNum(StepConstant.STEP7);
                break;
            case 7:
                pdUserTask.setFinishStepNum(StepConstant.STEP8);
                break;
            case 8:
                pdUserTask.setFinishStepNum(StepConstant.STEP9);
                break;
            case 9:
                pdUserTask.setFinishStepNum(StepConstant.STEP10);
                break;
        }
        return pdUserTaskDao.updateByPrimaryKeySelective(pdUserTask);
    }


    // =========================== budi End ===========================
    // =========================== hs Begin ===========================
    public IFBeginDTO ifBegin(IfBeginVO ifBeginVO) {
        PdUserTask pdUserTask=new PdUserTask();
        pdUserTask.setUserId(ThreadLocalManager.getUserId());
        pdUserTask.setTaskId(ifBeginVO.getTaskId());
        pdUserTask.setStatus(2);
        List<PdUserTask> select = pdUserTaskDao.select(pdUserTask);
        if(select!=null && select.size()>0){
            PdUserTask pdUserTask1 = select.get(0);
            IFBeginDTO ifBeginDTO=new IFBeginDTO();
            ifBeginDTO.setFinishStepNum(pdUserTask1.getFinishStepNum());
            ifBeginDTO.setUserTaskId(pdUserTask1.getId());
            return ifBeginDTO;
        }
        return null;
    }

    /**
     * 获取当前步骤
     * @param rctUserTaskVo
     * @return
     */
    public Integer getCurrentStep(CommonIdParam rctUserTaskVo) {
        if(rctUserTaskVo.getId()==null){
            throw new ServiceException(ErrorCode.PARAM_ERROR);
        }
        PdUserTask pdUserTask=new PdUserTask();
        pdUserTask.setId(rctUserTaskVo.getId());
        PdUserTask pdUserTask1 = pdUserTaskDao.selectOne(pdUserTask);
        return pdUserTask1.getFinishStepNum();
    }

    /**
     * 获取用户实验历史记录
     * @param pageParam
     * @return
     */
    public PageList<UserTaskHistroyDTO> getUserHistroy(PageParam pageParam) {
        Page  page = PageHelper.startPage(pageParam.getPageNum(), pageParam.getPageSize());
        Example example=new Example(PdUserTask.class);
        example.orderBy("id").desc();
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",ThreadLocalManager.getUserId());
        criteria.andNotEqualTo("status",3);
        List<PdUserTask> select = pdUserTaskDao.selectByExample(example);
        List<UserTaskHistroyDTO>dtoList=new ArrayList<>();
        for (PdUserTask userTask : select) {
            UserTaskHistroyDTO userTaskHistroyDTO=new UserTaskHistroyDTO();
            BeanUtils.copyProperties(userTask,userTaskHistroyDTO);
            dtoList.add(userTaskHistroyDTO);
        }
        PageList<UserTaskHistroyDTO> pageList=new PageList<>(page,dtoList);
        return pageList;
    }

    /**
     * 删除用户实验id
     * @param rctUserTaskVo
     * @return
     */
    public Integer delUserTask(CommonIdParam rctUserTaskVo) {

        PdUserTask pdUserTask=new PdUserTask();
        pdUserTask.setId(rctUserTaskVo.getId());
        pdUserTask.setStatus(3);
        return pdUserTaskDao.updateByPrimaryKeySelective(pdUserTask);

    }

    public Integer filalyTask(CommonIdParam commonIdParam) {
        PdUserTask pdUserTask=new PdUserTask();
        pdUserTask.setId(commonIdParam.getId());
        pdUserTask.setStatus(1);
        pdUserTask.setEndTime(new Date());
        pdUserTaskDao.updateByPrimaryKeySelective(pdUserTask);
        return null;
    }
    // =========================== budi End ===========================

}
