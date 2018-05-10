package com.oldneighborhood.demo.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;
import com.oldneighborhood.demo.service.RecordService;

@Service
public class RecordServiceImpl implements RecordService{

	@Override
	public String importRecord(File file) throws MySQLDataException {
		// TODO Auto-generated method stub
		//这里应当进行历史数据的解析 - >数据持久化
		return null;
	}

	@Override
	public ArrayList<Object> exportRecord(Map<String, Object> map) throws MySQLDataException {
		// TODO Auto-generated method stub
		return null;
	}

}
