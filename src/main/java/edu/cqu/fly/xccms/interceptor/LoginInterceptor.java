package edu.cqu.fly.xccms.interceptor;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

import edu.cqu.fly.xccms.pojo.BsResource;
import edu.cqu.fly.xccms.pojo.User;
import edu.cqu.fly.xccms.service.ResourceService;

@SuppressWarnings("serial")
public class LoginInterceptor implements Interceptor {
	private static final Logger log = Logger.getLogger(LoginInterceptor.class);
	@Autowired
	protected ResourceService resourceService;
	public void destroy() {
		//log.debug("拦截器销毁 destroy。。。");
	}

	public void init() {
		//log.debug("拦截器初始化 init。。。");
	}

	/**
	 * 拦截器，是否登陆
	 */
	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String, Object> session = invocation.getInvocationContext().getSession();
		Map<String, Object> map = ActionContext.getContext().getContextMap();
		map.put("loginStatus", "1");
		map.put("loginError", null);
		String path = invocation.getInvocationContext().getName();
		User bsUser = (User) session.get("user");
		if (null==bsUser) {
			//公共资源
			List<BsResource>  publiclist =resourceService.queryResourcebyuserId(null);
			if(isAuthority(publiclist,path)){
				return invocation.invoke();
			}
			session.put("GOTO_URL_KEY",getOldUrl(invocation));
			return "login";
		} else {
			session.remove("GOTO_URL_KEY");
			//私有资源+受保护的资源
			if(bsUser.getIsadmin().equals("yes")){
				return invocation.invoke();
			}else{
				List<BsResource>  alllist =resourceService.queryResourcebyuserId(bsUser.getUserid());
				if(isAuthority(alllist,path)){
					return invocation.invoke();
				}else{
					return "noaccess";
				}
			}
		}
	}

	/**
	 * 登陆之后要执行的操作
	 * 
	 * @param invocation
	 * @return
	 */
	private String getOldUrl(ActionInvocation invocation) {
		HttpServletRequest request = (HttpServletRequest) invocation
				.getInvocationContext().get(StrutsStatics.HTTP_REQUEST);
		StringBuffer oldUrl = request.getRequestURL();
		String path = invocation.getInvocationContext().getName();
		if (path.contains("Login_login")) {
			return null;
		}
		oldUrl.append("?");
		Map<String, String[]> zzMap = request.getParameterMap();
		if (zzMap != null) {
			for (String s : zzMap.keySet()) {
				String[] value = zzMap.get(s);
				for (String val : value) {
					oldUrl.append(s);
					oldUrl.append("=");
					oldUrl.append(val);
					oldUrl.append("&");
				}

			}
		}
		return oldUrl.toString();
	}
	/**
	 * 判断是否有权限访问该路径
	 * @param resourcelist
	 * @return
	 */
	public  boolean isAuthority(List<BsResource>  resourcelist,String path){
		boolean flag = false;
		for(BsResource bsResource:resourcelist){
			if(path.contains(bsResource.getResourceUrl())){
				flag = true;
				break;
			}
		}
		log.info("路径{"+path+"}是否有权限访问："+flag);
		return flag;
	}
}
