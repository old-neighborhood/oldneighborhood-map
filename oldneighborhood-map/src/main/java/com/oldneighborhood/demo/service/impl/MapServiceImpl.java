package com.oldneighborhood.demo.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

import com.oldneighborhood.demo.service.MapService;

@Service
public class MapServiceImpl implements MapService{
	
	@Autowired
	private RestOperations restOperations;
	
	@Value("${amap.api.key}")
	private String key;
	
	@Value("${amap.api.url}")
	private String URL;
	
	/**
	 * 地理编码
	 */
	public String mapGeo(String address) throws Exception {
		//&output=JSON缺省为返回JSON格式
		//多个地址查询，batch=true,以"A|B|C"查询，最多十个地址
		String url = URL + "/geocode/geo?address={address}&key={key}&batch=true";
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("key", key);
		uriVariables.put("address", address);
		//url,返回类型，传递参数
		String respose = restOperations.getForObject(url, String.class, uriVariables);
		return respose;
	}
	
	/**
	 * 逆地理编码
	 */
	public String mapRegeo(String location) throws Exception {
		String url = URL + "/geocode/regeo?location={location}&batch=true&"
				+ "key={key}&radius=1000&extensions=all";
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("location", location);
		uriVariables.put("key", key);
		String response = restOperations.getForObject(url, String.class, uriVariables);
//		System.out.println(response);
		return response;
	}
	
	/**
	 * 步行导航
	 */
	public String mapWalk(String ori, String dest) {
		String url = URL + "/direction/walking?origin={origin}&destination={destination}&key={key}";
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("origin", ori);
		uriVariables.put("destination", dest);
		uriVariables.put("key", key);
		return restOperations.getForObject(url, String.class, uriVariables);
	}
	/**
	 * 公交导航
	 */
	public String mapBus(String ori, String dest, String citycode) {
		String url = URL + "/direction/transit/integrated?"
				+ "origin={origin}&destination={destination}&city={citycode}&key={key}";
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("origin", ori);
		uriVariables.put("destination", dest);
		uriVariables.put("citycode", citycode);
		uriVariables.put("key", key);
		return restOperations.getForObject(url, String.class, uriVariables);
	}
	/**
	 * 驾车导航
	 */
	public String mapDrive(String ori, String dest) {
		String url = URL + "/direction/driving?"
				+ "origin={origin}&destination={destination}&key={key}&strategy=10&extensions=all";
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("origin", ori);
		uriVariables.put("destination", dest);
		uriVariables.put("key", key);
		return restOperations.getForObject(url, String.class, uriVariables);
	}
	
	@Value("${amap.api.url2}")
	private String URL2;
	
	/**
	 * 骑行导航
	 */
	public String mapCycle(String ori, String dest) {
		String url = URL2 + "/direction/bicycling?"
				+ "origin={origin}&destination={destination}&key={key}";
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("origin", ori);
		uriVariables.put("destination", dest);
		uriVariables.put("key", key);
		return restOperations.getForObject(url, String.class, uriVariables);
	}
	
	/**
	 * 输入提示
	 */
	public String mapTips(String keywords, String city) {
		String url = URL + "/assistant/inputtips?"
				+ "city={city}&keywords={keywords}&key={key}";
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("keywords", keywords);
		uriVariables.put("city", city);
		uriVariables.put("key", key);
		return restOperations.getForObject(url, String.class, uriVariables);
	}
	
	/**
	 * 关键词搜索
	 */
	public String mapPlace(String keywords, String city) {
		String url = URL + "/place/text?"
				+ "keywords={keywords}&city={city}&offset=20&page=1&key={key}&extensions=all";
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("keywords", keywords);
		uriVariables.put("city", city);
		uriVariables.put("key", key);
		return restOperations.getForObject(url, String.class, uriVariables);
	}
	
	/**
	 * IP定位
	 */
	public String mapIP(HttpServletRequest request) {
		GetIP getIP = new GetIP();
		String ip = getIP.getIP(request);
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>"+ip+"<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		String url = URL + "/ip?ip={ip}&key={key}";
		Map<String, Object> uriVariables = new HashMap<String, Object>();
		uriVariables.put("ip", ip);
		uriVariables.put("key", key);
		return restOperations.getForObject(url, String.class, uriVariables);
	}
}
