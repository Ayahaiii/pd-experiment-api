package com.monetware.pdexperiment.business.service.manage;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.RenderData;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.TextRenderData;
import com.monetware.pdexperiment.business.dao.PdUserTaskDao;
import com.monetware.pdexperiment.business.dao.PdUserTaskFileDao;
import com.monetware.pdexperiment.business.pojo.constant.IfGoodExerciseIntensityConstant;
import com.monetware.pdexperiment.business.pojo.constant.IfGoodExerciseWayConstant;
import com.monetware.pdexperiment.business.pojo.dto.PdUserTaskFileDTO;
import com.monetware.pdexperiment.business.pojo.dto.PdUserTaskFileSummaryDTO;
import com.monetware.pdexperiment.business.pojo.dto.SummaryDTO;
import com.monetware.pdexperiment.business.pojo.po.PdUserTask;
import com.monetware.pdexperiment.business.pojo.po.PdUserTaskFile;
import com.monetware.pdexperiment.business.pojo.vo.manage.*;
import com.monetware.pdexperiment.system.base.CommonIdParam;
import com.monetware.pdexperiment.system.base.ErrorCode;
import com.monetware.pdexperiment.system.base.ResultData;
import com.monetware.pdexperiment.system.config.Config;
import com.monetware.pdexperiment.system.exception.ServiceException;
import com.monetware.pdexperiment.system.util.upload.FileUploadUtils;
import com.monetware.pdexperiment.system.util.upload.GetFileNameUtils;
import com.monetware.threadlocal.ThreadLocalManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.Odd;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.monetware.pdexperiment.business.pojo.constant.StepConstant.STEP10;
import static com.monetware.pdexperiment.system.base.ErrorCode.EXPORT_WORD;

/**
 * @author budi
 * @version 1.0
 * @create 2020/10/26 1:25 下午
 * @description 用户实验附件管理相关方法
 */
@Slf4j
@Service
public class UserTaskFileManageService {

    @Autowired
    PdUserTaskFileDao pdUserTaskFileDao;
    @Autowired
    Config config;
    @Autowired
    PdUserTaskDao pdUserTaskDao;
    @Autowired
    UserTaskManageService userTaskManageService;
    // =========================== budi Begin ===========================

    /**
     * @Author budi
     * @Description 新增用户实验附件
     * @Date 1:32 下午 2020/10/26
     * @Param [param]
     * @return java.lang.Integer
     **/
    public Integer addUserTaskFile(AddUserTaskFileVO param) {
        // 新增用户实验附件
        PdUserTaskFile pdUserTaskFile = new PdUserTaskFile();
        BeanUtils.copyProperties(param, pdUserTaskFile);
        pdUserTaskFile.setCreateTime(new Date());
        return pdUserTaskFileDao.insertSelective(pdUserTaskFile);
    }

    /**
     * @Author budi
     * @Description 查找用户实验附件（通过用户实验id）
     * @Date 1:36 下午 2020/10/26
     * @Param [param]
     * @return java.util.List<com.monetware.pdexperiment.business.pojo.po.PdUserTaskFile>
     **/
    public List<PdUserTaskFileDTO> searchUserTaskFileByUserTaskId(CommonIdParam param) {
        // 通过用户实验id查找所有的用户实验附件
        PdUserTaskFile pdUserTaskFile = new PdUserTaskFile();
        pdUserTaskFile.setUserTaskId(param.getId());
        List<PdUserTaskFile> pdUserTaskFiles = pdUserTaskFileDao.select(pdUserTaskFile);
        List<PdUserTaskFileDTO> result = new ArrayList<>();
        for (PdUserTaskFile item : pdUserTaskFiles) {
            PdUserTaskFileDTO dto = new PdUserTaskFileDTO();
            BeanUtils.copyProperties(item, dto);
            result.add(dto);
        }
        return result;
    }

    /**
     * @Author budi
     * @Description 修改用户实验附件
     * @Date 1:42 下午 2020/10/26
     * @Param [param]
     * @return java.lang.Integer
     **/
    public Integer updateUserTaskFile(UpdateUserTaskFileVO param) {
        // 修改用户实验附件
        PdUserTaskFile pdUserTaskFile = pdUserTaskFileDao.selectByPrimaryKey(param.getId());
        BeanUtils.copyProperties(param, pdUserTaskFile);
        return pdUserTaskFileDao.updateByPrimaryKeySelective(pdUserTaskFile);
    }

    /**
     * @Author budi
     * @Description 获取汇总页面信息
     * @Date 3:23 下午 2020/10/26
     * @Param [param]
     * @return com.monetware.pdexperiment.business.pojo.dto.SummaryDTO
     **/
    public SummaryDTO getSummaryPageInfo(CommonIdParam param) {
        SummaryDTO dto = new SummaryDTO();
        PdUserTaskFile pdUserTaskFile = new PdUserTaskFile();
        pdUserTaskFile.setUserTaskId(param.getId());

        // 证据选择列表
        List<PdUserTaskFile> pdUserTaskFileList = pdUserTaskFileDao.select((pdUserTaskFile));
        List<PdUserTaskFileSummaryDTO> pdUserTaskFileSummaryDTOList = new ArrayList<>();
        for (PdUserTaskFile file : pdUserTaskFileList) {
            PdUserTaskFileSummaryDTO pdUserTaskFileSummaryDTO = new PdUserTaskFileSummaryDTO();
            BeanUtils.copyProperties(file, pdUserTaskFileSummaryDTO);
            pdUserTaskFileSummaryDTOList.add(pdUserTaskFileSummaryDTO);
        }
        dto.setPdUserTaskFiles(pdUserTaskFileSummaryDTOList);

        // 最佳运动方式证据列表
        pdUserTaskFile.setIfGoodExerciseWay(IfGoodExerciseWayConstant.IF_GOOD_EXERCISE_WAY_YES);
        pdUserTaskFileList = pdUserTaskFileDao.select(pdUserTaskFile);
        pdUserTaskFileSummaryDTOList = new ArrayList<>();
        for (PdUserTaskFile file : pdUserTaskFileList) {
            PdUserTaskFileSummaryDTO pdUserTaskFileSummaryDTO = new PdUserTaskFileSummaryDTO();
            BeanUtils.copyProperties(file, pdUserTaskFileSummaryDTO);
            pdUserTaskFileSummaryDTOList.add(pdUserTaskFileSummaryDTO);
        }
        dto.setGoodExerciseWayFiles(pdUserTaskFileSummaryDTOList);

        // 最佳运动强度证据列表
        pdUserTaskFile.setIfGoodExerciseWay(null);
        pdUserTaskFile.setIfGoodExerciseIntensity(IfGoodExerciseIntensityConstant.IF_GOOD_EXERCISE_INTENSITY_YES);
        pdUserTaskFileList = pdUserTaskFileDao.select(pdUserTaskFile);
        pdUserTaskFileSummaryDTOList = new ArrayList<>();
        for (PdUserTaskFile file : pdUserTaskFileList) {
            PdUserTaskFileSummaryDTO pdUserTaskFileSummaryDTO = new PdUserTaskFileSummaryDTO();
            BeanUtils.copyProperties(file, pdUserTaskFileSummaryDTO);
            pdUserTaskFileSummaryDTOList.add(pdUserTaskFileSummaryDTO);
        }
        dto.setGoodExerciseIntensityFiles(pdUserTaskFileSummaryDTOList);

        return dto;
    }

    /**
     * @Author budi
     * @Description 更新最佳运动方式、最佳运动强度证据列表
     * @Date 2:50 下午 2020/10/29
     * @Param [param]
     * @return java.lang.Integer
     **/
    public Integer updateSummaryPageInfo(UpdateSummaryVO param) {

        // 更新最佳运动方式证据列表
        List<PdUserTaskFileSummaryDTO> way = param.getWay();
        for (PdUserTaskFileSummaryDTO item : way) {
            PdUserTaskFile pdUserTaskFile = pdUserTaskFileDao.selectByPrimaryKey(item.getId());
            pdUserTaskFile.setWaySummary(item.getWaySummary());
            pdUserTaskFile.setIfGoodExerciseWay(item.getIfGoodExerciseWay());
            pdUserTaskFileDao.updateByPrimaryKeySelective(pdUserTaskFile);
        }

        //更新最佳运动强度证据列表
        List<PdUserTaskFileSummaryDTO> intensity = param.getIntensity();
        for (PdUserTaskFileSummaryDTO item : intensity) {
            PdUserTaskFile pdUserTaskFile = pdUserTaskFileDao.selectByPrimaryKey(item.getId());
            pdUserTaskFile.setIntensitySummary(item.getIntensitySummary());
            pdUserTaskFile.setIfGoodExerciseIntensity(item.getIfGoodExerciseIntensity());
            pdUserTaskFileDao.updateByPrimaryKeySelective(pdUserTaskFile);
        }

        return ResultData.OK;
    }

    // =========================== budi End ===========================

    //=============================hs Begin ===========================

    /**
     * 导出word文档
     * @param baseToImgVo
     */
    public void exportWord(CommonIdParam baseToImgVo) {
            //获取用户实验id
            Integer userTaskId = baseToImgVo.getId();
            //查询当前用户实验
            PdUserTask rctUserTask = new PdUserTask();
            rctUserTask.setId(userTaskId);
            PdUserTask userTask = pdUserTaskDao.selectOne(rctUserTask);
            //获取上传地址
            String path = config.getUploadWord();
            //或用word新文件名
            String wordName = GetFileNameUtils.getWordName();
            //获取模板地址
            String templatePath = config.getTemplatePath();
            //图片路径，请注意你是linux还是windows

        try {
            Map<String, Object> datas = new HashMap<String, Object>();
            datas.put("auth", ThreadLocalManager.getTokenContext().getName());
            datas.put("registerNum", userTask.getAuthorizationNum());
            //课题申请号代修改 TODO 代修改Word
//        datas.put("registerNum",userTask.());
            //研究的问题
            datas.put("researchQuestion", userTask.getResearchQuestion());
            //研究的方法
            datas.put("researchMethod", userTask.getResearchMethod());
            //预期结果
            datas.put("researchResult", userTask.getResearchResult());
            //中文检索
            datas.put("cnSearchParam", userTask.getCnSearchParam());
            //英文检索
            datas.put("enSearchParam", userTask.getEnSearchParam());

            //分级列表表头
            List<RenderData> gradingList = new ArrayList<RenderData>();
            gradingList.add(new TextRenderData("d0d0d0", "作者年份"));
            gradingList.add(new TextRenderData("d0d0d0", "证据分级"));
            gradingList.add(new TextRenderData("d0d0d0", "分级依据"));

            //推荐列表表头
            List<RenderData> recommendList = new ArrayList<RenderData>();
            recommendList.add(new TextRenderData("d0d0d0", "作者年份"));
            recommendList.add(new TextRenderData("d0d0d0", "证据等级"));
            recommendList.add(new TextRenderData("d0d0d0", "提取运动方式"));
            recommendList.add(new TextRenderData("d0d0d0", "提取运动强度"));
            recommendList.add(new TextRenderData("d0d0d0", "推荐细则评价"));
            recommendList.add(new TextRenderData("d0d0d0", "推荐等级"));


            //获取列表数据
            PdUserTaskFile pdUserTaskFile = new PdUserTaskFile();
            pdUserTaskFile.setUserTaskId(baseToImgVo.getId());
            List<PdUserTaskFile> select = pdUserTaskFileDao.select(pdUserTaskFile);

            //分级列表
            List<Object> gradingListData = new ArrayList<>();
            //推荐列表
            List<Object> recommendListData = new ArrayList<>();
            for (PdUserTaskFile userTaskFile : select) {
                if (userTaskFile != null) {
                    //TODO 代修改 最佳证据汇总表
                    StringBuffer gradingString = new StringBuffer();
                    StringBuffer recommendString = new StringBuffer();
                    //证据分级
                    gradingString.append(userTaskFile.getFirstAuthor() + ";");
                    gradingString.append(userTaskFile.getLevel() + ";");
                    gradingString.append(userTaskFile.getLevelReason());
                    gradingListData.add(gradingString);
                    //证据推荐
                    recommendString.append(userTaskFile.getFirstAuthor() + ";");
                    recommendString.append(userTaskFile.getLevel() + ";");
                    recommendString.append(userTaskFile.getExerciseWay() + ";");
                    recommendString.append(userTaskFile.getExerciseIntensity() + ";");
                    //推荐规则
                    recommendString.append("证据可行性：" + userTaskFile.getFeasibilityLevel() +
                            "证据可行性判断依据" + userTaskFile.getFeasibilityReason() +
                            "证据适宜性：" + userTaskFile.getAppropriatenessLevel() +
                            "证据适宜性判断依据" + userTaskFile.getAppropriatenessReason() +
                            "证据的临床意义：" + userTaskFile.getMeaningLevel() +
                            "证据的临床意义判断依据" + userTaskFile.getMeaningReason() +
                            "证据的有效性：" + userTaskFile.getEffectivenessLevel() +
                            "证据的有效性判断依据" + userTaskFile.getEffectivenessReason()
                            + ";");
                    //推荐等级
                    recommendString.append(userTaskFile.getRecommendLevel() + ";");
                    recommendListData.add(recommendString);

                }
            }

            TableRenderData grandingTable = new TableRenderData(gradingList, gradingListData);
            TableRenderData recommendTable = new TableRenderData(recommendList, recommendListData);
            datas.put("granding", grandingTable);
            datas.put("recommend", recommendTable);


            //最佳证据
            List<RenderData> List1 = new ArrayList<RenderData>();
            List1.add(new TextRenderData("d0d0d0", "理由分析"));
            List1.add(new TextRenderData("d0d0d0", "第一作者（年份）"));
            List1.add(new TextRenderData("d0d0d0", "证据等级"));
            List1.add(new TextRenderData("d0d0d0", "证据强度"));
            List<RenderData> List2 = new ArrayList<RenderData>();
            List2.add(new TextRenderData("d0d0d0", "理由分析"));
            List2.add(new TextRenderData("d0d0d0", "第一作者（年份）"));
            List2.add(new TextRenderData("d0d0d0", "证据等级"));
            List2.add(new TextRenderData("d0d0d0", "证据强度"));
            CommonIdParam commonIdParam=new CommonIdParam();
            commonIdParam.setId(userTaskId);
            SummaryDTO summaryPageInfo = getSummaryPageInfo(commonIdParam);
            List<PdUserTaskFileSummaryDTO> goodExerciseWayFiles = summaryPageInfo.getGoodExerciseWayFiles();
            List<Object>o1=new ArrayList<>();
            for (PdUserTaskFileSummaryDTO goodExerciseWayFile : goodExerciseWayFiles) {
                StringBuffer stringBuffer=new StringBuffer();
                stringBuffer.append(goodExerciseWayFile.getWaySummary()+";");
                stringBuffer.append(goodExerciseWayFile.getFirstAuthor()+";");
                stringBuffer.append(goodExerciseWayFile.getLevel()+";");
                stringBuffer.append(goodExerciseWayFile.getRecommendLevel()+";");
                o1.add(stringBuffer);
            }
            TableRenderData table1 = new TableRenderData(List1, o1);
            datas.put("exerciseWay",table1);
            List<PdUserTaskFileSummaryDTO> goodExerciseIntensityFiles = summaryPageInfo.getGoodExerciseIntensityFiles();
            List<Object>o2=new ArrayList<>();
            for (PdUserTaskFileSummaryDTO goodExerciseIntensityFile : goodExerciseIntensityFiles) {
                StringBuffer stringBuffer=new StringBuffer();
                stringBuffer.append(goodExerciseIntensityFile.getIntensitySummary()+";");
                stringBuffer.append(goodExerciseIntensityFile.getFirstAuthor()+";");
                stringBuffer.append(goodExerciseIntensityFile.getLevel()+";");
                stringBuffer.append(goodExerciseIntensityFile.getRecommendLevel()+";");
                o2.add(stringBuffer);
            }
            TableRenderData table2 = new TableRenderData(List2, o2);
            datas.put("exerciseIntensity",table2);
            //开始创建word文档
            XWPFTemplate template = XWPFTemplate.compile(templatePath)
                    .render(datas);
            FileOutputStream out;

                File file = new File(path + File.separator + ThreadLocalManager.getUserId());
                if (!file.exists()) {
                    file.mkdirs();
                }
                out = new FileOutputStream(path + File.separator + ThreadLocalManager.getUserId() + File.separator + wordName);
                template.write(out);
                out.flush();
                out.close();
                template.close();

        }catch (Exception e){
            e.printStackTrace();
            throw new  ServiceException(EXPORT_WORD);
        }
            //把最新的word预览地址放到word文档中
            String wordUrl=config.getUploadWordUrl()+ThreadLocalManager.getUserId()+"/"+wordName;
            PdUserTask pdUserTask=new PdUserTask();
            pdUserTask.setResultFileUrl(wordUrl);
            pdUserTask.setId(userTaskId);
            pdUserTask.setFinishStepNum(STEP10);
            pdUserTaskDao.updateByPrimaryKeySelective(pdUserTask);
    }

    /**
     * 上传文件
     * @param file
     */
    public UploadPdfDTO uploadPdF(MultipartFile file) {
        String bash =config.getUploadPdf();
        //TODO 代修改
        String upload = config.getUploadPDFUrl()+File.separator+ThreadLocalManager.getUserId();
        String filebash=bash+ThreadLocalManager.getUserId();
//        String filebash=bash+1;
        System.out.println(filebash);
        ResultData<Object> objectResultData = FileUploadUtils.UpLoadFile(file, filebash);
        if(objectResultData.getCode()==1){
            throw new ServiceException(ErrorCode.UPLOAD_FAIL);
        }
        if(objectResultData.getCode()==0){
            UploadPdfDTO uploadPdfDTO=new UploadPdfDTO();
            uploadPdfDTO.setFileName(file.getOriginalFilename());
            uploadPdfDTO.setFileUrl(upload + File.separator + objectResultData.getData());
            return uploadPdfDTO;
        }
        return null;
    }

    /**
     * 等级
     * @param updateLevelVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer updateLevel(UpdateLevelVO updateLevelVo) {
        CommonIdParam commonIdParam=new CommonIdParam();
        commonIdParam.setId(updateLevelVo.getUserTaskId());
        userTaskManageService.nextStep(commonIdParam);
        List<PdUserTaskFile> leavlList = updateLevelVo.getLeavlList();
        if(leavlList!=null){
            for (PdUserTaskFile pdUserTaskFile : leavlList) {
                pdUserTaskFileDao.updateByPrimaryKeySelective(pdUserTaskFile);
            }
        }
        return 1;
    }

    /**
     * 推荐
     * @param updateLevelVo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer updatelevelReason(UpdatelevelReasonVO updateLevelVo) {
        CommonIdParam commonIdParam=new CommonIdParam();
        commonIdParam.setId(updateLevelVo.getUserTaskId());
        userTaskManageService.nextStep(commonIdParam);

        List<PdUserTaskFile> leavlList = updateLevelVo.getLeavlReasonList();
        if(leavlList!=null){
            for (PdUserTaskFile pdUserTaskFile : leavlList) {
                pdUserTaskFileDao.updateByPrimaryKeySelective(pdUserTaskFile);
            }
        }
        return 1;
    }
    //=============================hs End   ===========================

}
