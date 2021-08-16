package com.monetware.pdexperiment.system.util.excel;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ExportExcelUtils<T> {

	private String title; // 导出表格的表名

	private String[] rowName;// 导出表格的列名

	private String[] indexName;

	private List<T> dataList = new ArrayList<T>(); // 对象数组的List集合

	private HttpServletResponse response;

	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private SimpleDateFormat simpleDateFormatMinute = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 传入要导入的数据
	 */
	public ExportExcelUtils(String title, String[] rowName, String[] indexName, List<T> dataList, HttpServletResponse response) {
		this.title = title;
		this.rowName = rowName;
		this.indexName = indexName;
		this.dataList = dataList;
		this.response = response;
	}

	/**
	 *  传入要导入的数据
	 */
	public ExportExcelUtils(String title, List<T> dataList, String[] indexName, HttpServletResponse response) {
		this.title = title;
		this.indexName = indexName;
		this.dataList = dataList;
		this.response = response;
	}

	/**
	 * 导出数据
	 */
	public void exportData() {
		exportData(null);
	}

	private int getRowNameIndex(String index) {

		if (index == null || index == " ") {
			return -1;
		}
		for (int i = 0; i < indexName.length; i++) {
			if (index.equals(indexName[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 导出数据流
	 */
	public void exportData(Map<Integer, String> indexMap) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(); // 创建一个excel对象
			HSSFSheet sheet = workbook.createSheet(title); // 创建表格
			// 产生表格标题行
			HSSFRow rowm = sheet.createRow(0); // 行
			HSSFCell cellTiltle = rowm.createCell(0); // 单元格
			/**
			 * 参数说明 从0开始 第一行 第一列 都是从角标0开始 行 列 行列 (0,0,0,5) 合并第一行 第一列 到第一行 第六列
			 * 起始行，起始列，结束行，结束列
			 * new Region() 这个方法使过时的
			 */
			int columnNum = rowName.length; // 表格列的长度
			HSSFRow rowRowName = sheet.createRow(0); // 在第二行创建行
			HSSFCellStyle cells = workbook.createCellStyle();
			cells.setBottomBorderColor(HSSFColor.BLACK.index);
			rowRowName.setRowStyle(cells);

			// 循环 将列名放进去
			for (int i = 0; i < columnNum; i++) {
				HSSFCell cellRowName = rowRowName.createCell((int) i);
				cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // 单元格类型
				HSSFRichTextString text = new HSSFRichTextString(rowName[i]); // 得到列的值
				cellRowName.setCellValue(text); // 设置列的值
			}

			// 将查询到的数据设置到对应的单元格中
			if(dataList!=null){
				for (int i = 0; i < dataList.size(); i++) {
					T obj = dataList.get(i);// 遍历每个对象
					HSSFRow row = sheet.createRow(i + 1);// 创建所需的行数
					Field[] fields = obj.getClass().getDeclaredFields();
					for (int j = 0; j < fields.length; j++) {

						fields[j].setAccessible(true);
						HSSFCell cell = null; // 设置单元格的数据类型
						cell = row.createCell(getRowNameIndex(fields[j].getName()), HSSFCell.CELL_TYPE_STRING);
						if (fields[j].get(obj) != null && fields[j].get(obj) != "") {
							if ("java.util.Date".equals(fields[j].getType().getName())) {
								cell.setCellValue(simpleDateFormatMinute.format(fields[j].get(obj))); // 设置单元格的值
							} else {
								cell.setCellValue(fields[j].get(obj).toString()); // 设置单元格的值
							}
						} else {
							cell.setCellValue("  ");
						}
						fields[j].setAccessible(false);
					}
				}
			}
			if (workbook != null) {
				try {
					// excel 表文件名
					String fileName = title + ".xls";
					String fileNameCode = URLEncoder.encode(fileName, "UTF-8");
					String headStr = "attachment; filename=\"" + fileNameCode + "\"";
					response.setContentType("APPLICATION/OCTET-STREAM");
					response.setHeader("Content-Disposition", headStr);
					OutputStream outPut = response.getOutputStream();
//					String filepath = "/Users/lu/Documents/experiment/upload/";
//					FileOutputStream out=new FileOutputStream(filepath+fileName);
//					workbook.write(out);
//					out.flush();
//					out.close();
					workbook.write(outPut);
					outPut.flush();
					outPut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 导出文件
	 */
	public void exportFile(Map<Integer, String> indexMap,String savePath) {
		try {
			HSSFWorkbook workbook = new HSSFWorkbook(); // 创建一个excel对象
			HSSFSheet sheet = workbook.createSheet(title); // 创建表格
			// 产生表格标题行
			HSSFRow rowm = sheet.createRow(0); // 行
			HSSFCell cellTiltle = rowm.createCell(0); // 单元格
			/**
			 * 参数说明 从0开始 第一行 第一列 都是从角标0开始 行 列 行列 (0,0,0,5) 合并第一行 第一列 到第一行 第六列
			 * 起始行，起始列，结束行，结束列
			 * new Region() 这个方法使过时的
			 */
			int columnNum = rowName.length; // 表格列的长度
			HSSFRow rowRowName = sheet.createRow(0); // 在第二行创建行
			HSSFCellStyle cells = workbook.createCellStyle();
			cells.setBottomBorderColor(HSSFColor.BLACK.index);
			rowRowName.setRowStyle(cells);

			// 循环 将列名放进去
			for (int i = 0; i < columnNum; i++) {
				HSSFCell cellRowName = rowRowName.createCell((int) i);
				cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING); // 单元格类型
				HSSFRichTextString text = new HSSFRichTextString(rowName[i]); // 得到列的值
				cellRowName.setCellValue(text); // 设置列的值
			}

			// 将查询到的数据设置到对应的单元格中
			if(dataList!=null){
				for (int i = 0; i < dataList.size(); i++) {
					T obj = dataList.get(i);// 遍历每个对象
					HSSFRow row = sheet.createRow(i + 1);// 创建所需的行数
					Field[] fields = obj.getClass().getDeclaredFields();
					for (int j = 0; j < fields.length; j++) {

						fields[j].setAccessible(true);
						HSSFCell cell = null; // 设置单元格的数据类型
						cell = row.createCell(getRowNameIndex(fields[j].getName()), HSSFCell.CELL_TYPE_STRING);
						if (fields[j].get(obj) != null && fields[j].get(obj) != "") {
							if ("java.util.Date".equals(fields[j].getType().getName())) {
								cell.setCellValue(simpleDateFormatMinute.format(fields[j].get(obj))); // 设置单元格的值
							} else {
								cell.setCellValue(fields[j].get(obj).toString()); // 设置单元格的值
							}
						} else {
							cell.setCellValue("  ");
						}
						fields[j].setAccessible(false);
					}
				}
			}
			if (workbook != null) {
				try {
					// excel 表文件名
					String fileName = title + ".xls";
					String fileNameCode = URLEncoder.encode(fileName, "UTF-8");
					String headStr = "attachment; filename=\"" + fileNameCode + "\"";
//					response.setContentType("APPLICATION/OCTET-STREAM");
//					response.setHeader("Content-Disposition", headStr);
//					OutputStream outPut = response.getOutputStream();
					String filepath = savePath+"/";
					FileOutputStream out=new FileOutputStream(filepath+fileName);
					workbook.write(out);
					out.flush();
					out.close();
//					workbook.write(outPut);
//					outPut.flush();
//					outPut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
