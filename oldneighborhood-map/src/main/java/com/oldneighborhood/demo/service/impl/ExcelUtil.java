package com.oldneighborhood.demo.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


//https://blog.csdn.net/gaugamela/article/details/60954813
//https://blog.csdn.net/superdog007/article/details/48338337
public class ExcelUtil {
	
	/**
	 * @Title: readExcelUtil
	 * @Description: 读取excel文件 - > json
	 */
	public static String extractExcel(String filepath) {
		Workbook workbook = null;
		Sheet sheet = null;
		Row row = null;
		if (filepath==null || filepath.length()==0) {
			return "{\"result\":\"null\"}";
		}
		try {
			workbook = WorkbookFactory.create(new FileInputStream(filepath));
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = workbook.getSheetAt(0);
		
		
		
		return "{\"result\":\"success\"}";
	}
	
}
