package edu.cqu.fly.xccms.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CodingCovert {

	/**
    * 对编码进行MD5加密，返回密文
    * @param sourcecode
    * @return
    */
	public static String getMD5(String sourcecode){
		String key = "1234567890";
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			
			md.update(sourcecode.getBytes());
			md.update(key.getBytes());
			ByteArrayInputStream bs = new ByteArrayInputStream(md.digest());
			StringWriter sw = new StringWriter();
			for (int i = 0; i < 16; i++)
			{
				String bt = Integer.toHexString(bs.read());
				if (bt.length() < 2)
				{
					sw.write("0" + bt);
				}
				else
				{
					sw.write(bt);
				}
			}
			String r = sw.toString();
			bs.close();
			sw.close();
			return r;
		} catch (NoSuchAlgorithmException e) {
			HandleException handleException=new HandleException("加密算法有误");
			handleException.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		
		
	}
}
