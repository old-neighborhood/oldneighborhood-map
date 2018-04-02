package com.oldneighborhood.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.client.RestOperations;

import com.oldneighborhood.demo.service.MapService;

@Service
public class MapServiceImpl implements MapService{
	
	@Autowired
	private RestOperations restOperations;
	
	@Value("${amap.api.key}")
	private String key;
	
	public String mapGeo(String address) throws UnexpectedRollbackException {
		//&output=JSON缺省为返回JSON格式
		String url = "http://restapi.amap.com/v3/geocode/geo?address={address}&key={key}";
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("key", key);
		uriVariables.put("address", address);
		//url,返回类型，传递参数
		String respose = restOperations.getForObject(url, String.class, uriVariables);
		return respose;
	}
	
	public String mapRegeo(String code) throws Exception {
		return code;
	}
	

}
