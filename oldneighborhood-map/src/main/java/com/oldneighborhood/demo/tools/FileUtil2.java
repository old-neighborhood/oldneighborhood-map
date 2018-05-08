/*package com.oldneighborhood.demo.tools;

import java.io.FileInputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
@Service
public class FileUtil2{
	
//	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

	@Value("${qiniu.accessKey}")
	private String accessKey;
	
	@Value("${qiniu.secretKey}")
	private String secretKey;
	
	@Value("${qiniu.bucket}")
	private String bucket;
	
	@Value("${qiniu.path}")
	private String path;
	
	public String uploadImage(FileInputStream file, String filename) {
		//构造一个带指定Zone对象的配置类 zone0华东机房相关域名
		Configuration cfg = new Configuration(Zone.zone0());
		//构建一个非断点续传的上传对象
		UploadManager uploadManager = new UploadManager(cfg);
		//生成上传凭证，然后准备上传
		Auth auth = Auth.create(accessKey, secretKey);
		//默认不指定key的情况下，以文件内容的hash值作为文件名
		String upToken = auth.uploadToken(bucket);
		try {
			Response res = uploadManager.put(file, filename, upToken, null, null);
		} catch (QiniuException e) {
			e.printStackTrace();
		}
		return null;
	}

}*/