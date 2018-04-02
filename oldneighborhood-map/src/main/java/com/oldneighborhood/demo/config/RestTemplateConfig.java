package com.oldneighborhood.demo.config;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

//https://blog.csdn.net/mitkey/article/details/53956520
@Configuration
public class RestTemplateConfig {
	
	@Bean
	//仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean
	@ConditionalOnMissingBean({RestOperations.class, RestTemplate.class})
	//https://www.cnblogs.com/quiet-snowy-day/p/6228198.html关于restTemplate 与restOperations
	public RestOperations restOperations() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setReadTimeout(5000);
		//已经建立连接，并开始读取服务端资源。如果到了指定的时间，没有可能的数据被客户端读取，则报异常。
		requestFactory.setConnectTimeout(5000);//建立连接的时间
		
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		
//		/**
//		 * 一种方法设置utf-8编码
//		 */
//		List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
//		//移除默认编码集
//		Iterator<HttpMessageConverter<?>> iterator = messageConverters.iterator();
//		while (iterator.hasNext()) {
//			HttpMessageConverter<?> converter = iterator.next();
//			//instanceof 相当于oc语言中的 isMemberOfClass
//			if (converter instanceof StringHttpMessageConverter) {
//				iterator.remove();
//			}
//		}
		//使用uft-8编码集
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		//https://www.cnblogs.com/accessking/p/Java.html - 转换类排序问题，无index可能导致非json String转换错误
		messageConverters.add(new StringHttpMessageConverter(StandardCharsets.UTF_8));
		restTemplate.setMessageConverters(messageConverters);
		/**
		 * 设置——通过header
		 */
		
		return restTemplate;
		
	}
}
