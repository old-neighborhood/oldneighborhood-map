package com.oldneighborhood.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oldneighborhood.demo.entity.Saler;
import com.oldneighborhood.demo.entity.User;
import com.oldneighborhood.demo.service.SalerService;
import com.oldneighborhood.demo.service.UserService;

/**
 * 
 * @ClassName: RegisterController
 * @Description: 注册模块
 * @author user005
 * @date 2018年3月22日
 * 
 * @url:	"http://localhost:8081/oldneighborhood/usersignup"
 * @return:	"result":"success"/"result":"error"
 * @url:	"http://localhost:8081/oldneighborhood/salersignup"
 * @return:	"result":"success"/"result":"error"
 */
@RequestMapping("/register")
@RestController
public class RegisterController {

	@Autowired
	private UserService userService;

	@RequestMapping(path = { "/usersignup" })
	public String userSingUp(@RequestBody Map<String, Object> reqMap) {
		System.out.println(reqMap);
		boolean flag = false;
		User user = new User(
				reqMap.get("username").toString(),
				reqMap.get("password").toString(),
				reqMap.get("email").toString());
		flag = userService.userSignup(user);
		System.out.println(flag);
		return flag ? "{\"result\":\"success\"}" : "{\"result\":\"error\"}";
	}

	@Autowired
	private SalerService salerService;
	// http://localhost:8081/oldneighborhood/salersignup
	@RequestMapping(path = { "/salersignup" })
	public String salerSignup(@RequestBody Map<String, Object> reqMap) {
		Saler saler = new Saler(
				reqMap.get("username").toString(),
				reqMap.get("password").toString(),
				reqMap.get("email").toString());
//		Saler testsaler = new Saler("bryanJ", "qwertyu", "13612341234");
		boolean flag = false;
		flag = salerService.salerSignup(saler);
		return flag ? "{\"result\":\"success\"}" : "{\"result\":\"error\"}";
	}

}
