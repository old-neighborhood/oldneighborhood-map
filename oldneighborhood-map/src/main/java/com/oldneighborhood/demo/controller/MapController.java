package com.oldneighborhood.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oldneighborhood.demo.service.MapService;
//http://localhost:8085/map/...
@RestController
@RequestMapping(path= {"/map"})
public class MapController {
	
	@Autowired
	private MapService mapService;
	
	@RequestMapping(path= {"/geo"})
	public String mapGeo(@RequestBody Map<String, Object> reqMap) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>controller start<<<<<<<<<<<<<<<<<<<");
		String response = mapService.mapGeo(reqMap.get("address").toString());
		System.out.println(">>>>>>>>>>>>>>>>>>>>controller end <<<<<<<<<<<<<<<<<<<<");
		return response;
	}

}
