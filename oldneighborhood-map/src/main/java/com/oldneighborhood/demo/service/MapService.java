package com.oldneighborhood.demo.service;

import javax.servlet.http.HttpServletRequest;

public interface MapService {
	
	public String mapGeo(String address) throws Exception;
	
	public String mapRegeo(String location) throws Exception;
	
	public String mapWalk(String origin, String dest);
	
	public String mapBus(String origin, String dest, String citycode);
	
	public String mapDrive(String origin, String dest);
	
	public String mapCycle(String origin, String dest);
	
	public String mapTips(String keywords, String city);
	
	public String mapPlace(String keywords, String city);
	
	public String mapIP(HttpServletRequest request);

}
