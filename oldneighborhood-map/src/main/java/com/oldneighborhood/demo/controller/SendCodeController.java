package com.oldneighborhood.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oldneighborhood.demo.tools.CommonUtils;
import com.oldneighborhood.demo.tools.MD5Utils;
import com.oldneighborhood.demo.tools.SendMsg;

import net.sf.json.JSONObject;

@RequestMapping("/sendcode")
@Controller
public class SendCodeController {
	
	@Autowired
	private SendMsg sendMsg;
	
	private static final String KEY = "abc123";
	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	
	/**
	 * @Title: sendMsg 
	 * @Description: 发送验证码 
	 * @param requestMap - email
	 * @return JSON String
	 * @throws
	 */
	@RequestMapping(value = "/sendMsg")
	@ResponseBody
	public String sendMsg(@RequestBody Map<String, Object> requestMap) {
		//获取邮箱
		String email = requestMap.get("email").toString();
		//生成随机数
		String randomCode = CommonUtils.createRandomNum();
		
		//生成5分钟后时间，用户校验是否过期
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 5);
		String deadTime = sdf.format(calendar.getTime());
		
		//发送验证码
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("code", randomCode);
		map.put("subject", "老街的守夜人[验证码]");
		sendMsg.sendEmail(map);
		
		//生成MD5值,将hash值和tamp时间返回给前端
		String hash = MD5Utils.getMD5Code(KEY + "@" + deadTime + "@" + randomCode);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("hash", hash);
		resultMap.put("time", deadTime);
		JSONObject json = JSONObject.fromObject(resultMap);
//		System.out.println(json);
		return json.toString();
	}

	/**
	 * @Title: validateNum 
	 * @Description: 校验结果返回json字符串 
	 * @return String 
	 */
	@RequestMapping(value = "/validateCode")
	@ResponseBody
	public String validateCode(@RequestBody Map<String, Object> requestMap) {
		
		String requestHash = requestMap.get("hash").toString();
		String deadTime = requestMap.get("time").toString();
		String code = requestMap.get("code").toString();
		String hash = MD5Utils.getMD5Code(KEY + "@" + deadTime + "@" + code);
		
		Calendar c = Calendar.getInstance();
		String currentTime = sdf.format(c.getTime());
		//校验结果：成功/失败/过期
		if (deadTime.compareTo(currentTime) > 0) {
			if (hash.equalsIgnoreCase(requestHash)) {
				return "{\"result\":\"success\"}";
			} else {
				return "{\"result\":\"error\"}";
			}
		} else {
			return "{\"result\":\"overdue\"}";
		}
	}

}
