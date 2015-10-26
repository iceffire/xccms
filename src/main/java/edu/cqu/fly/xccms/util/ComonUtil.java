package edu.cqu.fly.xccms.util;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.imageio.ImageIO;

import edu.cqu.fly.xccms.cache.Cache;
import edu.cqu.fly.xccms.pojo.NewsType;

/**
 * <p>
 * Title: ComonUtil.java
 * </p>
 * 
 * @authormiaoxiaolong
 * 
 */
public class ComonUtil {
	/**
	 * 校验返回结果
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean validateMapResult(Map map) {
		int result = (Integer) map.get(Constant.ACTION_RESULT);
		if (result == 0) {
			return true;
		} else {
			return false;
		}
	}

	/***
	 * 检查string是否为空
	 */
	public static boolean validateEmptyForString(String str) {
		if (null == str || str.equals("") || str.length() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 校验list是否为空
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean validateEmptyForList(List list) {
		if (null == list || list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 计算页数
	 * 
	 * @param totalcount
	 * @return
	 */
	public static int computusTotalPage(int totalcount, int pagesize) {
		int totalPage = totalcount % pagesize != 0 ? totalcount / pagesize + 1
				: totalcount / pagesize;
		return totalPage;
	}

	/**
	 * 取时间
	 */
	public static Date getDate(int addmonth) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, addmonth);
		return c.getTime();
	}

	/***
	 * 得到订单的唯一id 订单号=用户编号+当前时间(17)+4位随机数+增长序列号
	 * 
	 * @return
	 */
	public static String getUserId() {
		GregorianCalendar now = new GregorianCalendar();
		SimpleDateFormat fmtrq = new SimpleDateFormat("MMddhhmmssSSS",
				Locale.CHINA);
		String nowDate = fmtrq.format(now.getTime());
		return nowDate;

	}

	public static Date getAddDate(Date currentdate, int addmonth) {
		Calendar c = Calendar.getInstance();
		c.setTime(currentdate);
		c.add(Calendar.MONTH, addmonth);
		return c.getTime();
	}

	

	/**
	 * 消除转义字符
	 * 
	 * @param inputStr
	 * @return
	 */
	public static String processSpecialChar(String inputStr) {
		return inputStr.replace("\\", "");
	}
	
    /** 
     * 改变图片的大小到宽为size，然后高随着宽等比例变化 
     * @param is 上传的图片的输入流 
     * @param os 改变了图片的大小后，把图片的流输出到目标OutputStream 
     * @param size 新图片的宽 
     * @param format 新图片的格式 
     * @throws IOException 
     */  
    public static void resizeImage(InputStream is, OutputStream os, int size, String format) throws IOException {  
      /*  BufferedImage prevImage = ImageIO.read(is);  
        double width = prevImage.getWidth();  
        double height = prevImage.getHeight();  
        double percent = size/width;  
        int newWidth = (int)(width * percent);  
        int newHeight = (int)(height * percent);  
        BufferedImage image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_BGR);  
        Graphics graphics = image.createGraphics();  
        graphics.drawImage(prevImage, 0, 0, newWidth, newHeight, null);  
        ImageIO.write(image, format, os);  
        os.flush();  
        is.close();  
        os.close();  */
    	
    }  
    public static void changeImageSize(File srcfile,int width,int height) throws IOException{

    	BufferedImage bimg = ImageIO.read(srcfile);

    	Image image = bimg.getScaledInstance(width, height, Image.SCALE_SMOOTH);

    	BufferedImage target = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);

    	Graphics g = target.getGraphics();  

    	g.drawImage(image, 0, 0, null);

    	g.dispose(); 

    	ImageIO.write(target, "jpg", new File(srcfile.getAbsolutePath())); 
    }
    /**
     * 
     * @param typecode
     * @return type
     */
    public static String getNewstypename(String typecode){
    	String typename="";
    	List<NewsType> typelist = (List<NewsType>)Cache.getInstance().get("newstype");
    	for(NewsType newsType : typelist){
    		if(newsType.getTypecode().equals(typecode)){
    			typename = newsType.getType();
    			break;
    		}
    	}
    	return typename;
    }
    public static Properties getfileCfg(){
    	Properties prop = new Properties();
    	try {
    		String path = ComonUtil.class.getClassLoader().getResource("").toURI().getPath();
    		InputStream in = new BufferedInputStream (new FileInputStream(path+"/fileconfig.properties"));
        	prop.load(in);     ///加载属性列表
        	return prop;
		} catch (Exception e) {
			return null;
		}
    	
    }
}
