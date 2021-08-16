package com.monetware.pdexperiment.business.dao;

import com.monetware.pdexperiment.business.pojo.po.PdUserTask;
import com.monetware.pdexperiment.system.base.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PdUserTaskDao extends MyMapper<PdUserTask> {
}
