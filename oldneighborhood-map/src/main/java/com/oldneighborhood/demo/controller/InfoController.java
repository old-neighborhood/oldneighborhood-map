package com.oldneighborhood.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InfoController {
	
	@RequestMapping("/info")
	public String infoPage() {
		return "info";
	}

}
