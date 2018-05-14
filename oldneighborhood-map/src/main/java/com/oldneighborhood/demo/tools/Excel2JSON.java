package com.oldneighborhood.demo.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//https://blog.csdn.net/gaugamela/article/details/60954813
//https://blog.csdn.net/superdog007/article/details/48338337
public class Excel2JSON {
	private Logger logger = LoggerFactory.getLogger(Excel2JSON.class);
	
	private Workbook workbook;
	private Sheet sheet;
	private Row row;
	
	/**
	 * @Title: readExcelUtil
	 * @Description: 读取excel文件 - > workbook
	 */
	public String readExcelUtil(String filepath) {
		if (filepath==null) {
			return "{\"result\":\"filenull\"}";
		}
		String suffix = filepath.substring(filepath.lastIndexOf("."));
		try {
			InputStream ins = new FileInputStream(filepath);
			if (".xls".equals(suffix)) {
				workbook = new HSSFWorkbook(ins);
			}else if(".xlsx".equals(suffix)) {
				workbook = new XSSFWorkbook(ins);
			}else {
				workbook = null;
				return "{\"}";
			}
		} catch(FileNotFoundException e1) {
			e1.printStackTrace();
			logger.error("FileNotFound", e1);
		} catch(IOException e2) {
			e2.printStackTrace();
			logger.error("IOException", e2);
		}
		return "{\"result\":\"workbook\"}";
	}
	
	/**
	 * @Title: readExcelTitle
	 * @Description: 读取表格表头的内容
	 * @return String[]
	 * @throws Exception 
	 */
	public String[] readExcelTitle() throws Exception {
		if(workbook==null) {
			throw new Exception("Workbook为空！");
		}
		sheet = workbook.getSheetAt(0);
		row = sheet.getRow(0);
		//标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		System.out.println("col>>>>>>>>>" + colNum);
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			title[i] = row.getCell(i).getCellFormula();
		}
		return title;
	}

}
