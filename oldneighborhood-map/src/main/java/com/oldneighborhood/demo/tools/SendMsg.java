package com.oldneighborhood.demo.tools;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class SendMsg {

	@Autowired
	private JavaMailSender javaMailSender;

	public String sendEmail(@RequestBody Map<String, Object> reqMap) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("old_neighborhood<ren_blue@yeah.net>");
		message.setTo(reqMap.get("email").toString());
		message.setSubject(reqMap.get("subject").toString());
		message.setText("老街的熟人/过客：\n\n您好，欢迎成为老街的一员。\n验证码：" + reqMap.get("code").toString() + "，记得在五分钟内完成验证噢。\n\n老街的守夜人");
		javaMailSender.send(message);
		return "success";
	}

}
