package com.oldneighborhood.demo.tools;

import java.security.MessageDigest;

/**
 * @ClassName: MD5Utils
 * @Description: 加密
 * @author user005
 * @date 2018年5月2日
 */
public class MD5Utils {
	public  static  String getMD5Code(String message){  
        String md5Result="";  
        try{  
            //1.创建一个提供信息摘要算法的对象，初始化为MD5算法对象  
            MessageDigest md=MessageDigest.getInstance("MD5");  
            //2.将消息变为byte数组  
            byte[] input=message.getBytes();  
            //3.计算后获得字节数组，128位长度的MD5加密  
            byte[] buff=md.digest(input);  
            //4.把数组每一个字节（一个字节占8位）换成16进制的md5字符串  
            md5Result=bytesHex(buff);  

        }catch (Exception e){  
            e.printStackTrace();  
        }  

        return md5Result;  
   }
	
	public static String bytesHex(byte[]bytes){  
        StringBuffer md5Result =new StringBuffer();  
        //把数组每一字节换成换成16进制连成md5字符串  
        int digital;  
        for (int i=0; i<bytes.length; i++){  


            digital=bytes[i];  
            if (digital<0){  
                digital+=256;  
            }  
            if (digital<16){  
                md5Result.append("0");  
            }  
            md5Result.append(Integer.toHexString(digital));  
        }  
      return md5Result.toString().toUpperCase();  
    }  
}
