package com.oldneighborhood.demo.autotask;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.oldneighborhood.demo.service.StatisticsService;

//可視化暫時不考慮
/**
 * @ClassName: AutoTask
 * @Description: 自动收集数据进行持久化
 * @author user005
 * @date 2018年4月10日
 */
@Component
public class AutoTask {
	
	@Autowired
	private StatisticsService statisticsService;
	//用于获取数据
	
	//从6:00到22:00每分钟运行一次
//	@Scheduled(cron = "0 0/1 6-22 * * ?")
//	public String parkingLotData() {
//		Map<String, Object> map = null;
//		try {
//			ParkingLot pl = statisticsService.parkLotData(map);
//			parkingLotDao.save(pl);
//			return "{\"result\":\"success\"}";
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return "{\"result\":\"error\"}";
//	}
	
	@Scheduled(cron = "0 59 23 * * ?")
	public String FlowData() throws ParseException {
		return statisticsService.saveFlowData();
	}
	//每天23:59运行
	@Scheduled(cron = "0 59 23 * * ?")
	public String TicketData() throws ParseException {
		return  statisticsService.saveTicketData();
	}

}
