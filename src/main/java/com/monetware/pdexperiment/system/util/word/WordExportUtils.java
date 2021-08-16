package com.monetware.pdexperiment.system.util.word;

import cn.afterturn.easypoi.word.WordExportUtil;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * word文档导出
 */
public class WordExportUtils {

    /**
     * 导出word
     * <p>第一步生成替换后的word文件，只支持docx</p>
     * <p>第二步下载生成的文件</p>
     * <p>第三步删除生成的临时文件</p>
     * 模版变量中变量格式：{{foo}}
     * @param templatePath word模板地址
     * @param temDir 生成临时文件存放地址
     * @param fileName 文件名
     * @param params 替换的参数
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public static void exportWord(String templatePath, String temDir, String fileName, Map<String, Object> params, HttpServletRequest request, HttpServletResponse response) {
        Assert.notNull(templatePath, "模板路径不能为空");
        Assert.notNull(temDir, "临时文件路径不能为空");
        Assert.notNull(fileName, "导出文件名不能为空");
        Assert.isTrue(fileName.endsWith(".docx"), "word导出请使用docx格式");
        if (!temDir.endsWith("/")) {
            temDir = temDir + File.separator;
        }
        File dir = new File(temDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            String userAgent = request.getHeader("user-agent").toLowerCase();
            if (userAgent.contains("msie") || userAgent.contains("like gecko")) {
            } else {
                fileName = new String(fileName.getBytes("utf-8"), "ISO-8859-1");
            }
            XWPFDocument doc = WordExportUtil.exportWord07(templatePath, params);
            String tmpPath = temDir + fileName;
            FileOutputStream fos = new FileOutputStream(tmpPath);
            doc.write(fos);
            // 设置强制下载不打开
            response.setContentType("application/force-download");
            // 设置文件名
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
            OutputStream out = response.getOutputStream();
            doc.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            String tmpPath = temDir + fileName;
            File file=new File(tmpPath);
            if(file.exists()&&file.isFile()){
                file.delete();
            }
        }
    }
    public static void main(String[] args) {

        //测试数据准备
        //1.标题
        String title = "t1heluosh1-使用POI导出Word";
        //2.段落数据
        String font_song_four_red = "这里是宋体四号红色字体";
        String font_black_three_black = "这里是黑体三号号黑色字体";
        String font_micro_five_red = "这里是微软雅黑五号红色字体";

        //存放段落数据
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("auth", title);
//        map.put("font_song_four_red", font_song_four_red);
//        map.put("font_black_three_black", font_black_three_black);
//        map.put("font_micro_five_red", font_micro_five_red);

        //3.表格数据
        List<Map<String,String>> excelMapList = new ArrayList<Map<String,String>>();
        Map<String,String> excelMapTemp = null;
        for (int i=1;i<11;i++) {
            excelMapTemp = new HashMap<String,String>();
            excelMapTemp.put("excel.no1", "one-"+i);
            excelMapTemp.put("excel.no2", "two-"+i);
            excelMapTemp.put("excel.no3", "three-"+i);
            excelMapTemp.put("excel.no4", "four-"+i);
            excelMapTemp.put("excel.no5", "five-"+i);
            excelMapList.add(excelMapTemp);
        }

        //模板存放位置
        String demoTemplate = "G:/imgs/25.docx";
        //生成文档存放位置
        String targetPath = "G:/imgs/26.docx";

        //初始化导出
        WordExport export = new WordExport(demoTemplate);
        try {
            export.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            export.export(map);
//            export.exportImg(map);
            //0为表格的下标，第一个表格下标为0，以此类推
            export.export(excelMapList, 0);
            export.generate(targetPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
