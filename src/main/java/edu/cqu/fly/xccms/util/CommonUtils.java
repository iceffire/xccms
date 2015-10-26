package edu.cqu.fly.xccms.util;

import java.io.File;
import java.util.Date;

import org.apache.commons.io.FileUtils;

public class CommonUtils {

	public static String getFlowNum(){
		Date date = new Date();
		return date.getTime()+"";
	}
	public static void saveFile(File file,String imageFileName){
		try {
			File savefile = new File(new File("/tmp"), CommonUtils.getFlowNum()+"."+imageFileName.split("\\.")[1]);
	         if (!savefile.getParentFile().exists())
	             savefile.getParentFile().mkdirs();
	         FileUtils.copyFile(file, savefile);
		} catch (Exception e) {
			// TODO: handle exception
		} 
		
	}
}
