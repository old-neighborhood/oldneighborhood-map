package com.oldneighborhood.demo.service;

import java.text.ParseException;
import java.util.Map;

public interface StatisticsService {
	//存储数据
	public String saveTicketData() throws ParseException;
	
	public String saveFlowData() throws ParseException;
	
	//读数据json
	public String getFlowData();
	
	public String getTicketData();
	//从url读取数据
	public String getParkLotData();
	
	
}
