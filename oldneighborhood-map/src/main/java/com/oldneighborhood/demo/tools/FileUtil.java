package com.oldneighborhood.demo.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
	
	public static void uploadFile(byte[] file, String filePath, String fileName) {
		File targetFile = new File(filePath);
		if(!targetFile.exists()) {
			targetFile.mkdirs();
		}
		try {
			FileOutputStream out = new FileOutputStream(filePath + fileName);
			out.write(file);
			out.flush();
			out.close();
			System.out.println(targetFile.getAbsolutePath());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
