package com.monetware.pdexperiment.system.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分页参数实体类 用于VO继承
 */
@Data
public class PageParam implements Serializable {


    @ApiModelProperty(value = "当前页")
    private int pageNum = 1;

    @ApiModelProperty(value = "查询的记录数")
    private int pageSize = 10;

}
