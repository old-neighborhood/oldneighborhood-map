package com.oldneighborhood.demo.controller;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oldneighborhood.demo.service.StatisticsService;
//FutureController.java
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
	
	@Autowired
	private StatisticsService statisticsService;
	
	//人流量
	@RequestMapping("/crowd")
	public String crowdData() throws ParseException {
		return statisticsService.getFlowData();
	}
	//票务
	@RequestMapping("/ticket")
	public String ticketData() {
		return statisticsService.getTicketData();
	}
	//停车场车流量
	@RequestMapping("/parkinglot")
	public String parkLotData() {
		return statisticsService.getParkLotData();
	}
	
	@RequestMapping("/importFlow")
	public String importFlowData(@RequestBody Map<String, Object> reqMap) {
		//获取文件路径
		String filepath = reqMap.get("").toString();
		return "";
	}
	@RequestMapping("/importTicket")
	public String importTicketData(@RequestBody Map<String, Object> reqMap) {
		//获取文件路径
		String filepath = reqMap.get("").toString();
		return "";
	}
	
}
