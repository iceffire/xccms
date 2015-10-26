package edu.cqu.fly.xccms.cache;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import edu.cqu.fly.xccms.service.CommonService;
import edu.cqu.fly.xccms.util.BeanUtil;

/**
 * 
 * <p>
 * Title: InitServlet
 * </p>
 * <p>
 * Description: 加载系统常量，并初始化
 * </p>
 * @author miaoxiaolong
 * 
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected CommonService  commonService;
	private Logger logger = Logger.getLogger(InitServlet.class);

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		logger.info("*********初始化系统变量*********");
		super.init(servletConfig);
		BeanUtil<CommonService> beanutil = new BeanUtil<CommonService>();
		commonService = beanutil.getBean("commonService");
		try {
			commonService.cacheSystemConstatnt();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}