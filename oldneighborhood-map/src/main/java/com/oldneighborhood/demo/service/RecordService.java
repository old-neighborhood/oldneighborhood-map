package com.oldneighborhood.demo.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;

public interface RecordService {
	/**
	 * @Title: importRecord  
	 * @Description: 导入的历史数据进行解析
	 */
	public String importRecord(File file) throws MySQLDataException;
	
	/**
	 * @Title: importRecord  
	 * @Description: 依据参数导出数据到文件
	 */
	public ArrayList<Object> exportRecord(Map<String, Object> map) throws MySQLDataException;

}
