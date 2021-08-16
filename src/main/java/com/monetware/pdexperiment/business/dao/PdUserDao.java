package com.monetware.pdexperiment.business.dao;

import com.monetware.pdexperiment.business.pojo.po.PdUser;
import com.monetware.pdexperiment.system.base.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PdUserDao extends MyMapper<PdUser> {
}
