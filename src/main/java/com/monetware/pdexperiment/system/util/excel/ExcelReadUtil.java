package com.monetware.pdexperiment.system.util.excel;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * excel读写工具类
 */
@Slf4j
public class ExcelReadUtil {
	private final static String XLS = "xls";
	private final static String XLSX = "xlsx";

	/**
	 * 读入excel文件，解析后返回
	 *
	 * @param file
	 * @throws IOException
	 */
	public static List<String[]> readExcelAllSheets(MultipartFile file) throws IOException {
		// 检查文件
		checkFile(file);
		// 获得Workbook工作薄对象
		Workbook workbook = getWorkBook(file);
		// 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<String[]> list = new ArrayList<String[]>();
		if (workbook != null) {
			for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
				// 获得当前sheet工作表
				Sheet sheet = workbook.getSheetAt(sheetNum);
				if (sheet == null) {
					continue;
				}
				// 获得当前sheet的开始行
				int firstRowNum = sheet.getFirstRowNum();
				// 获得当前sheet的结束行
				int lastRowNum = sheet.getLastRowNum();
				// 循环除了第一行的所有行
				for (int rowNum = firstRowNum ; rowNum <= lastRowNum; rowNum++) {
					// 获得当前行
					Row row = sheet.getRow(rowNum);
					if (row == null) {
						continue;
					}
					// 获得当前行的开始列
					int firstCellNum = row.getFirstCellNum();
					// 获得当前行的列数
					int lastCellNum = row.getPhysicalNumberOfCells();
					String[] cells = new String[row.getPhysicalNumberOfCells()];
					// 循环当前行
					for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
						Cell cell = row.getCell(cellNum);
						cells[cellNum] = getCellValue(cell);
					}
					list.add(cells);
				}
			}
			workbook.close();
		}
		return list;
	}

	/**
	 * 读入excel第一个工作簿文件，解析后返回
	 *
	 * @param file
	 * @throws Exception
	 */
	public static List<String[]> readExcelBySheet(MultipartFile file, int sheetNum) throws IOException {
		// 检查文件
		checkFile(file);
		// 获得Workbook工作薄对象
		Workbook workbook = getWorkBook(file);
		// 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<String[]> list = new ArrayList<String[]>();
		if (workbook != null) {
			// 获得当前sheet工作表
			Sheet sheet = workbook.getSheetAt(sheetNum);
			if (sheet == null) {
				throw new IOException("Excel必须包含至少一个工作簿！");
			}
			// 获得当前sheet的开始行
			int firstRowNum = sheet.getFirstRowNum();
			// 获得当前sheet的结束行
			int lastRowNum = sheet.getLastRowNum();
			// 循环除了第一行的所有行
			for (int rowNum = firstRowNum ; rowNum <= lastRowNum; rowNum++) {
				// 获得当前行
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				// 获得当前行的开始列
				int firstCellNum = row.getFirstCellNum();
				// 获得当前行的列数
				int physicalNumberOfCells = row.getPhysicalNumberOfCells();
				int lastCellNum = row.getLastCellNum();
				if(rowNum==0 && lastCellNum!=physicalNumberOfCells){
					throw new IOException("Excel第一行表头单元格间不能有空单元格！");
				}
				String[] cells = new String[row.getPhysicalNumberOfCells()];
				// 循环当前行
				for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
					Cell cell = row.getCell(cellNum);
					cells[cellNum] = getCellValue(cell);
				}
				list.add(cells);
			}
			workbook.close();
		}
		return list;
	}

	public static void checkFile(MultipartFile file) throws IOException {
		// 判断文件是否存在
		if (null == file) {
			log.error("文件不存在！");
			throw new FileNotFoundException("文件不存在！");
		}
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 判断文件是否是excel文件
		if (!fileName.endsWith(XLS) && !fileName.endsWith(XLSX)) {
            log.error("不是excel文件！");
			throw new IOException(fileName + "不是excel文件");
		}
	}

	public static Workbook getWorkBook(MultipartFile file) {
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			// 获取excel文件的io流
			InputStream is = file.getInputStream();
			// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if (fileName.endsWith(XLS)) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith(XLSX)) {
				// 2007
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		return workbook;
	}

	public static String getCellValue(Cell cell) {
		String cellValue = "";
		if (cell == null) {
			return cellValue;
		}
		// 把数字当成String来读，避免出现1读成1.0的情况
		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		// 判断数据的类型
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_NUMERIC: // 数字
			cellValue = String.valueOf(cell.getNumericCellValue());
			break;
		case Cell.CELL_TYPE_STRING: // 字符串
			cellValue = String.valueOf(cell.getStringCellValue());
			break;
		case Cell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA: // 公式
			cellValue = String.valueOf(cell.getCellFormula());
			break;
		case Cell.CELL_TYPE_BLANK: // 空值
			cellValue = "";
			break;
		case Cell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;
		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}

	public static List<String[]> readExcelByInput(InputStream inputStream,int sheetNum) throws IOException {
		// 获得Workbook工作薄对象
		Workbook workbook =  new XSSFWorkbook(inputStream);
		// 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<String[]> list = new ArrayList<String[]>();
		if (workbook != null) {
			// 获得当前sheet工作表
			Sheet sheet = workbook.getSheetAt(sheetNum);
			if (sheet == null) {
				throw new IOException("Excel必须包含至少一个工作簿！");
			}
			// 获得当前sheet的开始行
			int firstRowNum = sheet.getFirstRowNum();
			// 获得当前sheet的结束行
			int lastRowNum = sheet.getLastRowNum();
			// 循环除了第一行的所有行
			for (int rowNum = firstRowNum ; rowNum <= lastRowNum; rowNum++) {
				// 获得当前行
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				// 获得当前行的开始列
				int firstCellNum = row.getFirstCellNum();
				// 获得当前行的列数
				int physicalNumberOfCells = row.getPhysicalNumberOfCells();
				int lastCellNum = row.getLastCellNum();
				if(rowNum==0 && lastCellNum!=physicalNumberOfCells){
					throw new IOException("Excel第一行表头单元格间不能有空单元格！");
				}
				String[] cells = new String[row.getPhysicalNumberOfCells()];
				// 循环当前行
				for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
					Cell cell = row.getCell(cellNum);
					cells[cellNum] = getCellValue(cell);
				}
				list.add(cells);
			}
			workbook.close();
		}
		return list;
	}

	/**
	 * 按sheet_name读取excel
	 */
	public static List<String[]> readExcelBySheetName(MultipartFile file, String sheetName) throws IOException {
		// 检查文件
		checkFile(file);
		// 获得Workbook工作薄对象
		Workbook workbook = getWorkBook(file);
		//计算fx
		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
		// 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<String[]> list = new ArrayList<String[]>();
		if (workbook != null) {
			// 获得当前sheet工作表
			Sheet sheet = workbook.getSheet(sheetName);
			if (sheet == null) {
				throw new IOException("Excel必须包含至少一个工作簿！");
			}
			// 获得当前sheet的开始行
			int firstRowNum = sheet.getFirstRowNum();
			// 获得当前sheet的结束行
			int lastRowNum = sheet.getLastRowNum();
			// 循环除了第一行的所有行
			for (int rowNum = firstRowNum ; rowNum <= lastRowNum; rowNum++) {
				// 获得当前行
				Row row = sheet.getRow(rowNum);
				if (row == null) {
					continue;
				}
				// 获得当前行的开始列
				int firstCellNum = row.getFirstCellNum();
				// 获得当前行的列数
				int physicalNumberOfCells = row.getPhysicalNumberOfCells();
				int lastCellNum = row.getLastCellNum();
				if(rowNum==0 && lastCellNum!=physicalNumberOfCells){
					throw new IOException("Excel第一行表头单元格间不能有空单元格！");
				}
				String[] cells = new String[row.getPhysicalNumberOfCells()];
				// 循环当前行
				for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
					Cell cell = row.getCell(cellNum);
					if(cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
						//计算fx
						evaluator.evaluateFormulaCell(cell);
						CellValue cellValue = evaluator.evaluate(cell);
						Double celldata = cellValue.getNumberValue();
						cells[cellNum] = celldata.toString();
					}else{
						cells[cellNum] = getCellValue(cell);
					}
				}
				list.add(cells);
			}
			workbook.close();
		}
		return list;
	}
}
