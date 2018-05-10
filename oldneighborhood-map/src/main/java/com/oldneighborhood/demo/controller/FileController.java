package com.oldneighborhood.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.oldneighborhood.demo.tools.FileUtil;

@RequestMapping("/file")
@Controller
public class FileController {
	
	@Value("${web.upload-image}")
	private String imagePath;
	
	@Value("${web.upload-file}")
	private String filePath;
	
	@Value("${web.upload-excel}")
	private String excelPath;
	
	@Value("${web.url}")
	private String url;
	
	@RequestMapping("/uploading")
	public String upload() {
		return "uploading";
	}
	
	@RequestMapping("/fileupload")
	public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile multipartFile, HttpServletRequest req, HttpServletResponse res) {
		//允许跨域访问
		res.setHeader("Access-Control-Allow-Headers", "accept, content-type");  
	    res.setHeader("Access-Control-Allow-Method", "POST");  
	    res.setHeader("Access-Control-Allow-Origin", "*");
		String contentType = multipartFile.getContentType();
		//重新命名
		String fileName = multipartFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String filename = UUID.randomUUID().toString().replace("-", "");
		fileName = filename +"."+ suffix;
		System.out.println("filename->>>" + fileName + "\nContentType->>>" + contentType);
		//获取存储路径
//		/oldneighborhood-frontui/src/main/resources/static/image
//		\oldneighborhood-frontui\src\main\webapp\image\
		String filepath = "";
		if (suffix.equals("jpg") | suffix.equals("jpeg") | suffix.equals("png") | suffix.equals("gif") | suffix.equals("ico")) {
//			filePath = req.getSession().getServletContext().getRealPath("image/");
//			String relativePath = req.getSession().getServletContext().getContextPath();
			filepath = imagePath + "/";
		}else if(suffix.equals("xls") | suffix.equals("xlsx")) {
			filepath = excelPath + "/";
		}else {
			filepath = filePath + "/";
		}
		System.out.println("filepath->>>>" + filepath);
		try {
			FileUtil.uploadFile(multipartFile.getBytes(), filepath, fileName);
			return "{\"result\":\"success\",\"filename\":\""+ url +"/common/"+ fileName +"\"}";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{\"result\":\"error\"}";
	}
	
	/**
     * 单文件上传，方式二，利用MultipartHttpServletRequest来解析request中的文件
     * 用 transferTo方法来保存图片，保存到本地磁盘。
     * 普通request是无法解析请求中的文件的。
     * MultipartHttpServletRequest是springmvc框架中的一个接口，
     * 默认实现类是DefaultMultipartHttpServletRequest
     * @author chunlynn 
     * @https://blog.csdn.net/chenchunlin526/article/details/70945877
     */
	
	@RequestMapping("/fileupload2")
	public @ResponseBody String uploadFile(HttpServletRequest request, HttpServletResponse response) {
		//已实例化一个文件解析器
		CommonsMultipartResolver cMR = new CommonsMultipartResolver();//request.getSession().getServletContext()
		if(cMR.isMultipart(request)) {
			//转换
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			MultipartFile file = multiRequest.getFile("file");
			if (!file.isEmpty()) {
				String contentType = file.getContentType();
				//重新命名
				String fileName = file.getOriginalFilename();
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				String filename = UUID.randomUUID().toString().replace("-", "");
				fileName = filename +"."+ suffix;
				System.out.println("filename->>>" + fileName + "\nContentType->>>" + contentType);
				
				//获取存储路径
				String filepath = "";
				if (suffix.equals("jpg") | suffix.equals("jpeg") | suffix.equals("png") | suffix.equals("gif")
						| suffix.equals("ico")) {
					// filePath = req.getSession().getServletContext().getRealPath("image/");
					// String relativePath = req.getSession().getServletContext().getContextPath();
					filepath = imagePath + "/";
				} else if (suffix.equals("xls") | suffix.equals("xlsx")) {
					filepath = excelPath + "/";
				} else {
					filepath = filePath + "/";
				}
				System.out.println("filepath->>>>" + filepath);

				// 创建文件实例
				File tempFile = new File(filepath + fileName);
				if (!tempFile.exists()) {
					tempFile.mkdir();
				}
				try {
					// Transfer the received file to the given destination file.
					file.transferTo(tempFile);
				} catch (IllegalStateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "{\"result\":\"success\",\"filename\":\"" + url + "/common/" + fileName + "\"}";
			}
		}
		return "{\"result\":\"error\"}";
	}

}