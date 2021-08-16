package com.monetware.pdexperiment.business.pojo.vo.manage;

import lombok.Data;

/**
 * @description: UploadPdfVO
 * @author: 彭于晏
 * @create: 2020-10-27 16:23
 **/
@Data
public class UploadPdfDTO {
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 文件地址
     */
    private String fileUrl;
}
