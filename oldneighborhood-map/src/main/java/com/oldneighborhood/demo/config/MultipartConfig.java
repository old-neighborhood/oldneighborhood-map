package com.oldneighborhood.demo.config;

import java.io.File;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultipartConfig {
	
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		//文件大小限制
		factory.setMaxFileSize("10MB");
		//总上传数据大小
		factory.setMaxRequestSize("10MB");
		//设置文件临时文件夹路径
		String location = System.getProperty("user.dir") + "/data/tmp";
        File tmpFile = new File(location);
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }
		factory.setLocation(location);
		return factory.createMultipartConfig();
	}
	

}
