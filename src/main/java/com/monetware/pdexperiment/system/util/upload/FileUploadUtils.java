package com.monetware.pdexperiment.system.util.upload;

import com.monetware.pdexperiment.system.base.ResultData;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Cookie
 * @Date: 2020-10-14 11:04
 * @Description: 文件上传
 */
public class FileUploadUtils {

    /**
     * @Author: Cookie
     * @Date: 2019-04-30 16:52
     * @Description: 文件上传至服务器
     */
    public static ResultData<Object> UpLoadFile(MultipartFile file, String path) {
        //获取文件在服务器的储存位置
        File filePath = new File(path);
        if (!filePath.exists() || !filePath.isDirectory()) {
            filePath.mkdirs();
        }

        //获取原始文件名称(包含格式)
        String originalFileName = file.getOriginalFilename();

        //获取文件类型，以最后一个`.`为标识
        String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        //获取文件名称（不包含格式）
        String name = originalFileName.substring(0, originalFileName.lastIndexOf("."));

        //设置文件新名称: 当前时间+文件名称（不包含格式）
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = sdf.format(d);
        String fileName = date + "." + type;

        //将文件保存到服务器指定位置
        File targetFile = new File(path + File.separator + fileName);
        System.out.print(targetFile.getName());
        try {
            file.transferTo(targetFile);
            return new ResultData<>(0, "上传成功", targetFile.getName());
        } catch (IOException e) {
            e.printStackTrace();
            return new ResultData<>(1, "上传失败");
        }
    }

    /**
     * @Author: Cookie
     * @Date: 2019-04-30 17:51
     * @Description: 删除文件
     */
    public static boolean deleteFile(String filePath) {
        boolean flag;
        File file = new File(filePath);
        if (file.exists() && file.isFile() && file.delete())
            flag = true;
        else
            flag = false;
        return flag;
    }

    /**
     * bash64码转图片
     * @param imgStr
     * @param fileName
     * @return
     */
    public static String bash64ToImg(String imgStr, String fileName,String path) {
        BASE64Decoder decoder = new BASE64Decoder();
        if (imgStr != null && imgStr.substring(0, 5).equals("data:")) {
            try {
                File file=new File(path);
                if(!file.exists()){
                    file.mkdirs();
                }
                byte[] b = decoder.decodeBuffer(imgStr.substring(imgStr.indexOf(",") + 1));
                for (int i = 0; i < b.length; ++i) {
                    // 调整异常数据
                    if (b[i] < 0) {
                        b[i] += 256;
                    }
                }
                // 生成jpg图片
                OutputStream out = new FileOutputStream(fileName);
                out.write(b);
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                return "fail";
            }

        }
        return "ok";
    }
}
