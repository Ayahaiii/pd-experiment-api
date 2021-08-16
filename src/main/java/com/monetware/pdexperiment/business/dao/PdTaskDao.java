package com.monetware.pdexperiment.business.dao;

import com.monetware.pdexperiment.business.pojo.po.PdTask;
import com.monetware.pdexperiment.system.base.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PdTaskDao extends MyMapper<PdTask> {
}
