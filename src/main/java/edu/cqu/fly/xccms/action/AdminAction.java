package edu.cqu.fly.xccms.action;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;

import edu.cqu.fly.xccms.pojo.User;
import edu.cqu.fly.xccms.service.SystemService;
import edu.cqu.fly.xccms.service.UserService;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;

public class AdminAction extends BaseAction{

	private static final Logger log = Logger.getLogger(AdminAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 8151859323017355708L;

	@Resource(name="systemService")
	SystemService systemService;
	
	@Resource(name="userService")
	UserService userService;
	private User userobj ;
	
	public User getUserobj() {
		return userobj;
	}

	public void setUserobj(User userobj) {
		this.userobj = userobj;
	}

 

	@SuppressWarnings("rawtypes")
	public String dologin() {
		
		
		
		Map<String, Object> session =context.getSession();
		if (log.isDebugEnabled()) {
			log.debug("后台登陆方法!");
		}
		String str ="login";
		User sessionuser = (User) session.get("user");
		if(null==sessionuser||sessionuser.getIsadmin().equals("no")){
			User userobj = new User();
			try {
				userobj.setUsername(idNumber);
				userobj.setPassword(password);
				userobj.setIsadmin(usertype);
				String randomcode = (String) session.get("RANDOMIMAGES");
				log.info("checkcode|randomcode:"+checkcode+"|"+randomcode);
				if(randomcode.equalsIgnoreCase(checkcode)){
						Map map = userService.queryUsername(userobj);
						if (ComonUtil.validateMapResult(map)) {
							User user1 = (User) map.get(Constant.USER);
								log.debug("登录成功");
								if(user1.getIsadmin().equals("yes")){
									str = "adminindex";
								}else{
									//super.commonquery();
									String url =(String) session.get("GOTO_URL_KEY");
									log.info("url:"+url);
									if(null==url||url.equals("")){
										response.sendRedirect("index");
									}else{
										if(url.contains("OpenApp_index")){
											response.sendRedirect("OpenApi_index");
											//response.sendRedirect(url);
										}else{
											response.sendRedirect(url);
										}
									}
								}
								showMessage = "";
								session.put("user", user1);
								/***session共享**/
								request.getSession().setAttribute("onlineuser", user1.getUsername());
								ServletContext servletContext = request.getSession().getServletContext();
								servletContext.setAttribute("session", request.getSession());
								/******/
								onlineUsers.add(user1);
						} else {
							 log.debug("登陆失败!");
							 showMessage = "用户名或者密码不正确!";
						}
					}else{
						showMessage = "验证码不正确!";
					}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			str = "adminindex";
		}
		return str;
	}
	
	/**
	 * 退出登陆
	 * 
	 * @return
	 */
	public String logout() {
		if (log.isDebugEnabled()) {
			log.debug("logout 退出。。。");
		}
		Map<String, Object> session = ActionContext.getContext().getSession();

		onlineUsers.remove(session.get("user"));
		session.remove("user");
		/***session共享失效**/
		request.getSession().removeAttribute("onlineuser");
		/******/
		showMessage = "";
		return "login";
	}
	

}
