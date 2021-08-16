package com.monetware.pdexperiment.business.service.manage;

import com.monetware.pdexperiment.business.dao.PdTaskDao;
import com.monetware.pdexperiment.business.dao.PdUserTaskDao;
import com.monetware.pdexperiment.business.pojo.dto.PdTaskDTO;
import com.monetware.pdexperiment.business.pojo.dto.TaskDetailDTO;
import com.monetware.pdexperiment.business.pojo.po.PdTask;
import com.monetware.pdexperiment.business.pojo.po.PdUserTask;
import com.monetware.pdexperiment.system.base.CommonIdParam;
import com.monetware.threadlocal.ThreadLocalManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/28 5:30 下午
 * @description 实验管理相关方法
 */
@Slf4j
@Service
public class TaskManageService {

    @Autowired
    PdTaskDao pdTaskDao;
    @Autowired
    PdUserTaskDao pdUserTaskDao;
    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 获取实验列表
     * @Date 5:37 下午 2020/10/28
     * @Param []
     * @return java.util.List<com.monetware.pdexperiment.business.pojo.dto.PdTaskDTO>
     **/
    public List<PdTaskDTO> getTaskList() {
        List<PdTaskDTO> result = new ArrayList<>();
        List<PdTask> pdTaskList = pdTaskDao.selectAll();
        for (PdTask task : pdTaskList) {
            PdTaskDTO dto = new PdTaskDTO();
            BeanUtils.copyProperties(task, dto);
            result.add(dto);
        }
        return result;
    }

    /**
     * 获取用户当前实验详情
     * @param idParam
     * @return
     */
    public TaskDetailDTO getTaskDetail(CommonIdParam idParam) {
        PdTask pdTask=new PdTask();

        pdTask.setId(idParam.getId());
        PdTask select = pdTaskDao.selectOne(pdTask);
        PdUserTask pdUserTask=new PdUserTask();
        pdUserTask.setTaskId(idParam.getId());
        pdUserTask.setUserId(ThreadLocalManager.getUserId());
        pdUserTask.setStatus(2);
        TaskDetailDTO taskDetailDTO=new TaskDetailDTO();
        List<PdUserTask> select1 = pdUserTaskDao.select(pdUserTask);
        if(select1!=null&&select1.size()>0){
            PdUserTask pdUserTask1 = select1.get(0);
            taskDetailDTO.setFinishStepNum(pdUserTask1.getFinishStepNum());
            taskDetailDTO.setUserTaskId(pdUserTask1.getId());
        }
        BeanUtils.copyProperties(select,taskDetailDTO);
        return taskDetailDTO;
    }

    // =========================== budi End ===========================

}
