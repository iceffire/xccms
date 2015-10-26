package edu.cqu.fly.xccms.staticize;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import edu.cqu.fly.xccms.pojo.BsPagestatic;
import edu.cqu.fly.xccms.service.CommonService;
import edu.cqu.fly.xccms.util.BeanUtil;


/**
 * 
 * <p>
 * Title: HtmlCreatorServlet.java
 * </p>
 * <p>
 * Description: html生成
 * </p>
 * <p>
 * 
 * @author miaoxiaolong
 * 
 */
public class HtmlCreatorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected CommonService commonService;
	private Logger logger = Logger.getLogger(HtmlCreatorServlet.class);

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		BeanUtil<CommonService> beanutil = new BeanUtil<CommonService>();
		commonService = beanutil.getBean("commonService");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		// 得到真实的请求地址
		String templatePath = simpleURLReWrite(request);
		String realPath = request.getSession().getServletContext()
				.getRealPath("/");
		// 想要生成的静态html文件的名字
		String htmlName = getHtmlFileName(request);
		// 静态html的名字，包含绝对路径
		String cachFileName = realPath + File.separator + htmlName;
		logger.debug("静态文件存放地址: " + cachFileName);
		File cacheFile = new File(cachFileName);
		boolean load = true;
		// 如果静态html 存在，就直接显示html，否则，我们就生成它。
		if (cacheFile.exists()) {
			load = false;
		}
		if(htmlName.equals("index/.htm")){
			load = true;
		}
		RequestDispatcher dispatcher  = null;
		if (load) {
			// 例如 index.shtm ，则转发到 index
			dispatcher = request.getRequestDispatcher(templatePath);
			try {
				// 生成静态文件，并且显示这个静态文件
				StaticUtil.convert2Html(templatePath, "UTF-8", realPath,
						htmlName);
				BsPagestatic bsPagestatic = new BsPagestatic();
				bsPagestatic.setFilepath(realPath);
				bsPagestatic.setHtmname(htmlName);
				bsPagestatic.setDynamicurl(templatePath);
				commonService.addEntity(bsPagestatic);
				dispatcher = getServletContext().getRequestDispatcher(
						"/" + htmlName);
				dispatcher.include(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			dispatcher = getServletContext().getRequestDispatcher("/" + htmlName);
			dispatcher.include(request, response);
		}
	}

	// 这个方法就是把http://127.0.0.1:8080/xccms/News_newsdetail_11.shtm
	// 变成 http://127.0.0.1:8080/xccms/News_newsdetail?id=11
	protected String simpleURLReWrite(HttpServletRequest request)
			throws ServletException, IOException {
		String uri = request.getRequestURL().toString();
		String contextPath = request.getContextPath();
		logger.debug("HtmlCreator contextPath = " + contextPath);
		uri = uri.substring(0, uri.length() - 5);
		String[] urls = uri.split("_");
		uri = urls[0];
		if (urls.length > 1) {
			for (int i = 1; i < urls.length; i++) {
				if (i == 1) {
					uri += "_" + urls[i];
				} else {
					uri += "?id=" + urls[i];
				}
			}
		}
		logger.info("静态化地址：" + uri);
		return uri;
	}

	// 这个方法就是根据 http://xyz.com/product_pageNumber_1.shtm
	// 来得到生成的html文件名字，也就是 product_pageNumber_1.html
	private String getHtmlFileName(HttpServletRequest request)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		if (contextPath != null && contextPath.length() > 0)
			uri = uri.substring(contextPath.length());
		uri = uri.substring(1, uri.length() - 5);
		uri += ".htm";
		logger.info("静态化文件名字：" + uri);
		return uri;
	}
}