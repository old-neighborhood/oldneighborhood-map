package com.oldneighborhood.demo.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(
			urlPatterns= {"/druid/*"} , 
			initParams = {
					//
//					@WebInitParam(name="allow" , value="127.0.0.1") ,
//					@WebInitParam(name="deny" , value="") ,
					@WebInitParam(name="loginUsername" , value="oldneighborhood") , 
					@WebInitParam(name="loginPassword" , value="oldneighborhood") , 
					@WebInitParam(name="resetEnable" , value="true")
			}
		)
public class DruidStatViewServlet extends StatViewServlet {
	private static final long serialVersionUID = 1L;
}
