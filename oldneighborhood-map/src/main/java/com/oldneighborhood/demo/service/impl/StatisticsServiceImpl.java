package com.oldneighborhood.demo.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.oldneighborhood.demo.dao.FlowDao;
import com.oldneighborhood.demo.dao.TicketDao;
import com.oldneighborhood.demo.entity.Flow;
import com.oldneighborhood.demo.entity.ParkingLot;
import com.oldneighborhood.demo.entity.Ticket;
import com.oldneighborhood.demo.service.StatisticsService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @ClassName: StatisticsServiceImpl
 * @Description:
 * 1.历史数据直接从数据库中取出展示
 * 2.每隔十分钟试图读取停车场数据更新到前端
 * 3.每天读取人流量数据存入数据库中，每天统计票务存入数据库中
 * @author user005
 * @date 2018年4月10日
 */
@Service
public class StatisticsServiceImpl implements StatisticsService{
	
	@Autowired
	private FlowDao flowDao;
	@Autowired
	private TicketDao ticketDao;
	//返回所有数据JSON
	@Override
	public String getFlowData() {
		List<Flow> flows = flowDao.findAll();
		JSONArray json = JSONArray.fromObject(flows);
		return json.toString();
	}
	
	@Override
	public String getTicketData() {
		List<Ticket> tickets = ticketDao.findAll();
		JSONArray json = JSONArray.fromObject(tickets);
		return json.toString();
	}
	
	//public调用controller 获取url(已经拼接后url)
	@Value("${config.url}")
	private String configURL;
	
	RestTemplate rs = new RestTemplate();
	
	/**
	 * 触发请求 -> 返回result=notfound/success[,site_name="",pl_ammount=""]
	 */
	@Override
	public String getParkLotData() {
		//获取API url key
//		String plurl = "http://111.231.107.63:8087/config/api/oneAPI";
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("api_name", "parkinglot");
		String api = rs.getForObject(configURL, String.class, reqMap);
		JSONObject json = JSONObject.fromObject(api);
		//返回是否为空？
		if (json.isEmpty() || json.get("api_url").toString().equals("null")) {
			return "{\"result\":\"notfound\"}";
		}
		//访问Rest API接口
		String _url = json.getString("api_url");
		String _key = json.getString("api_key");
		Map<String, Object> _map = new HashMap<String, Object>();
		_map.put("key", _key);
		String resflow = rs.getForObject(_url, String.class, _map);
		JSONObject js = JSONObject.fromObject(resflow);
		js.put("result", "success");
		System.out.println(js.toString());
//		ParkingLot pl = new ParkingLot(
//				js.getString("site_name"), 
//				Timestamp.valueOf(js.getString("pl_now")), 
//				js.getInt("pl_amount"), 
//				js.getInt("pl_available"));
		return js.toString();
	}
	

	/**
	 * 请求 - 存储到数据库 - 返回操作结果
	 */
	@Override
	public String saveTicketData() throws ParseException {
		Map<String, Object> reqMap = new HashMap<String, Object>();
		reqMap.put("api_name", "ticket");
		String res = rs.getForObject(configURL, String.class, reqMap);
		JSONObject js = JSONObject.fromObject(res);
		if (js.isEmpty()|| js.get("api_url").toString().equals("null")) {
			return "{\"result\":\"notfound\"}";
		}
		Ticket ticket = new Ticket(
				js.getString("site_name"),
				new SimpleDateFormat("yyyy-MM-dd").parse(js.getString("ticket_date")), 
				js.getInt("ticket_online"), 
				js.getInt("ticket_offline"),
				js.getInt("ticket_amount"));
		Ticket ticket_new = ticketDao.save(ticket);
		if (ticket_new!=null) {
			return "{\"result\":\"success\"}";
		}
		return "{\"result\":\"error\"}";
	}
	
	

	@Override
	public String saveFlowData() throws ParseException {
		String res = rs.getForObject(configURL, String.class);
		JSONObject js = JSONObject.fromObject(res);
		Flow flow = new Flow(
				js.getString("site_name"),
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(js.getString("")), 
				js.getInt(""));
		return "";
	}
	
	
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
