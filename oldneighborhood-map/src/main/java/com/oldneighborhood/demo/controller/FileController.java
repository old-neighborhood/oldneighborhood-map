package com.oldneighborhood.demo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.oldneighborhood.demo.tools.FileUtil;


@Controller
public class FileController {
	
	@Value("${web.upload-image}")
	private String imagePath;
	
	@Value("${web.upload-file}")
	private String filePath;
	
	@Value("${web.upload-excel}")
	private String excelPath;
	
	@RequestMapping("/uploading")
	public String upload() {
		return "uploading";
	}
	
	@RequestMapping("/fileupload")
	public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile multipartFile, String filename, HttpServletRequest req) {
		String contentType = multipartFile.getContentType();
		//重新命名
		String fileName = multipartFile.getOriginalFilename();
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
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
			return "{\"result\":\"success\",\"filepath\":\""+ (filepath + fileName) +"\"}";
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "{\"result\":\"error\"}";
	}
	
	/*@RequestMapping("/fileupload2")
	public String uploadFile(@RequestParam("upload") MultipartFile file) {
		FileInputStream fis = null;
		try {
			fis = (FileInputStream) file.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//上传路径名
		String filename = "";
		String path = fileService.uploadImage(fis, filename);
		return path;
	}*/

}