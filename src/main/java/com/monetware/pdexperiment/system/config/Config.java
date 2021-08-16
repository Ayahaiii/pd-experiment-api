package com.monetware.pdexperiment.system.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description: Config
 * @author: 彭于晏
 * @create: 2020-10-16 11:45
 **/
@Component
@Data
public class Config {
    @Value("${config.uploadFile}")
    private String uploadFile;
    /**
     * 上传流程图地址
     */
    @Value("${config.uploadimgs}")
    private String uploadImgs;
    /**
     * 上传word文档地址
     */
    @Value("${config.uploadWord}")
    private String uploadWord;
    /**
     * 图片下载地址
     */
    @Value("${config.uploadImgsUrl}")
    private String uploadImgUrl;

    /**
     * word模板真实地址
     */
    @Value("${config.templatePath}")
    private String templatePath;
    /**
     * word下载地址
     */
    @Value("${config.uploadWordUrl}")
    private String uploadWordUrl;

    /**
     * pdf 上传地址
     */
    @Value("${config.uploadPdf}")
    private String uploadPdf;
    /**
     * 下载地址
     */
    @Value("${config.uploadPDFUrl}")
    private String uploadPDFUrl;
}
