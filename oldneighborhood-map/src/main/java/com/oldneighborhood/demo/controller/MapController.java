package com.oldneighborhood.demo.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oldneighborhood.demo.service.MapService;
//http://localhost:8085/map/...
@RestController
//@RequestMapping(path= {"/map"})
public class MapController {
	
	@Autowired
	private MapService mapService;
	
	@RequestMapping(path= {"/geo"})
	public String mapGeo(@RequestBody Map<String, Object> reqMap) throws Exception {
		String response = mapService.mapGeo(reqMap.get("address").toString());
		return response;
	}
	
	@RequestMapping("/regeo")
	public String mapRegeo(@RequestBody Map<String, Object> reqMap) throws Exception {
		String response = mapService.mapRegeo(reqMap.get("location").toString());
		return response;
	}
	
	@RequestMapping("/walk")
	public String mapWalk(@RequestBody Map<String, Object> reqMap) throws Exception {
		String response = mapService.mapWalk(
				reqMap.get("origin").toString(),
				reqMap.get("dest").toString());
		return response;
	}
	
	@RequestMapping("/bus")
	public String mapBus(@RequestBody Map<String, Object> reqMap) throws Exception {
		String response = mapService.mapBus(
				reqMap.get("origin").toString(),
				reqMap.get("dest").toString(),
				reqMap.get("citycode").toString());
		return response;
	}
	
	@RequestMapping("/drive")
	public String mapDrive(@RequestBody Map<String, Object> reqMap) throws Exception {
		String response = mapService.mapDrive(
				reqMap.get("origin").toString(),
				reqMap.get("dest").toString());
		return response;
	}
	
	@RequestMapping("/cycle")
	public String mapCycle(@RequestBody Map<String, Object> reqMap) throws Exception {
		String response = mapService.mapCycle(
				reqMap.get("origin").toString(),
				reqMap.get("dest").toString());
		return response;
	}
	
	@RequestMapping("/tips")
	public String mapTips(@RequestBody Map<String, Object> reqMap) throws Exception {
		String response = mapService.mapTips(
				reqMap.get("keywords").toString(), 
				reqMap.get("city").toString());
		return response;
	}
	//多个关键字用“|”分割
	@RequestMapping("/place")
	public String mapPlace(@RequestBody Map<String, Object> reqMap) throws Exception {
		String response = mapService.mapPlace(
				reqMap.get("keywords").toString(),
				reqMap.get("city").toString());
		return response;
	}
	
	@RequestMapping("/ip")
	public String getIP(HttpServletRequest request) {
		return mapService.mapIP(request);
	}
}
