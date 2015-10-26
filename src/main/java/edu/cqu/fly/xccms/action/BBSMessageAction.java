package edu.cqu.fly.xccms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;

import edu.cqu.fly.xccms.pojo.BBSMessage;
import edu.cqu.fly.xccms.pojo.BBSMessageQuery;
import edu.cqu.fly.xccms.pojo.MessageComment;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.User;
import edu.cqu.fly.xccms.service.BBSMessageService;
import edu.cqu.fly.xccms.service.UserService;
import edu.cqu.fly.xccms.util.CodingCovert;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;

public class BBSMessageAction extends BaseAction{

	private static final Logger log = Logger.getLogger(BBSMessageAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = -6715729095447340711L;

	@Resource(name="bbsMessageService")
	BBSMessageService bbsMessageService;
	
	
	@Resource(name="userService")
	UserService userService;
	protected List<BBSMessageQuery> bbsmessagelist;
	
	protected BBSMessageQuery bbsMessageQuery;
	
	protected List<MessageComment> commentslist;
	
	protected String errmsg;
	/**
	 * 
	 * @return
	 */
	public  String    index(){
		return "index";
	}
	public String sentbbs(){
		Map<String, Object> session =context.getSession();
		User sessionuser = (User) session.get("user");
		if(sessionuser!=null){
			return "bbs_sent";
		}else{
			return "login";
		}
	}
	/**
	 * bbs用户注册
	 * @return
	 */
	public String bbsreg(){
		String str="login";
		 Map<String, Object> session =context.getSession();
			if (log.isDebugEnabled()) {
				log.debug("bbs注册方法!");
			}
			User userobj = getUser();
			if(userService.queryUserByName(userobj.getUsername())){
				userService.addEntity(userobj);
				session.put("user", userobj);

				onlineUsers.add(userobj);
				str="bbs_index";
			}else{
				showMessage="用户已存在";
			}
			return str;
	}
	  @SuppressWarnings("rawtypes")
	public String bbsLogin(){
		   Map<String, Object> session =context.getSession();
			if (log.isDebugEnabled()) {
				log.debug("bbs登陆方法!");
			}
			String str ="login";
			User sessionuser = (User) session.get("user");
			if(null==sessionuser){
				User userobj = getUser();
				
				Map map = userService.queryUsername(userobj);
				if (ComonUtil.validateMapResult(map)) {
					User user1 = (User) map.get(Constant.USER);
					log.debug("登录成功");
						str = "bbs_index";
				
					session.put("user", user1);
					/***session共享**/
					request.getSession().setAttribute("onlineuser", user1.getUsername());
					ServletContext servletContext = request.getSession().getServletContext();
					servletContext.setAttribute("session", request.getSession());
					/******/
					onlineUsers.add(user1);
				}else{
					 log.debug("登陆失败!");
					 showMessage = "用户名或者密码不正确!";
					 str="login";
				}
			}else{
				str="bbs_index";
			}
			return str;
	   }
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
			return "bbs_index";
		}
	/**
	 * 查询主页
	 */
	public  void query(){
		String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  

		try {
			BBSMessage bbsMessage = new BBSMessage();
			Pager pager = bbsMessageService.queryBBSMessageByPage(bbsMessage,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询发帖列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  String  queryBBSMessageList(){
    	String title = request.getParameter("title");
	    String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数
	    String timeperiod = request.getParameter("timeperiod");
	    String isessence = request.getParameter("isessence");
		if(null==page||page.equals("")){
			page="1";
		}
		if(null==rows||rows.equals("")){
			rows="30";
		}
	    currentPage=Integer.parseInt(page);
		prePage = currentPage-1;
		 nextPage = currentPage+1;
		try {
			BBSMessageQuery bbMessageQuery = new BBSMessageQuery();
		
			if(null!=title&&!title.equals("")){
				bbMessageQuery.setTitle(title);
			}else{
				bbMessageQuery.setTitle("");
			}
		   if(null!=timeperiod&&!timeperiod.equals("")){
			   bbMessageQuery.setQueryPeriod(timeperiod);
		   }else{
			   bbMessageQuery.setQueryPeriod("");
		   }
		
		   if(null!=isessence&&!isessence.equals("")){
			   bbMessageQuery.setIsessence(Integer.parseInt(isessence));
		   }else{
			   bbMessageQuery.setIsessence(0);
		   }
			Map map =bbsMessageService.queryBBSMessageForPage(bbMessageQuery, Integer.valueOf(rows),Integer.valueOf(page));
		bbsmessagelist = (List<BBSMessageQuery>) map.get(Constant.BBSMESSAGE_LIST);
				totalCount = (Integer) map.get(Constant.TOTALCOUNT);
				totalPageCount =(Integer) map.get(Constant.TOTALPAGECOUNT);
			    totalpages = new ArrayList<Integer>();
				for(int i=Integer.valueOf(page);i<=totalPageCount;i++){
					totalpages.add(i);
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bbs_result";
	}
	
	public String queryBBSMessageDetail(){
		super.commonQuery();
		String id = request.getParameter("id");
		try {
			bbsMessageQuery =	bbsMessageService.findBBSMessageQuery(Long.parseLong(id));
			BBSMessage bbs = new BBSMessage();
			bbs.setId(bbsMessageQuery.getId());
			bbs.setContent(bbsMessageQuery.getContent());
			bbs.setCreateTime(bbsMessageQuery.getCreateTime());
			bbs.setScannum(bbsMessageQuery.getScannum()+1);
			bbs.setTitle(bbsMessageQuery.getTitle());
			bbs.setUserid(bbsMessageQuery.getUserid());
			
			
			bbsMessageService.saveEntity(bbs);
			commentslist = bbsMessageService.queryCommentsByMsgId(Long.parseLong(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bbs_detail";
	}
	
	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		BBSMessage bbsMessage = getBBSMessage();
		try {
			bbsMessage.setId(Long.valueOf(id));
			bbsMessageService.saveEntity(bbsMessage);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(){
		boolean flag =false ;
		BBSMessage bbsMessage = getBBSMessage();
	
		try {
			bbsMessage.setScannum(Long.parseLong("0"));
			bbsMessage.setIsessence(0);
			bbsMessageService.saveEntity(bbsMessage);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String addMsg(){
		boolean flag =false ;
		BBSMessage bbsMessage = getBBSMessage();
	
		try {
			bbsMessage.setScannum(Long.parseLong("0"));
			bbsMessage.setIsessence(0);
			bbsMessageService.saveEntity(bbsMessage);
			flag = true;
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(flag){
			return "bbs_index";
		}else{
			errmsg ="发帖失败";
			return "bbs_sent";
		}
	}

	public void delete(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		try {
			bbsMessageService.removeEntityById(Long.valueOf(id),BBSMessage.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 标记精华贴
	 * @throws IOException
	 */
	public void essence() throws IOException{
		response.setHeader("Content-type", "text/html;chartset=utf8");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		try {
            BBSMessage bbsMessage = bbsMessageService.findEntityById(Long.valueOf(id), BBSMessage.class);
            bbsMessage.setIsessence(1);
			bbsMessageService.saveEntity(bbsMessage);
		out.print("标记成功");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			out.print("标记失败");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("标记失败");
		}finally{
			out.flush();
			out.close();
		}
	}
	public BBSMessage  getBBSMessage(){
		String title = request.getParameter("title"); 
		String content = request.getParameter("content"); 
		Map<String, Object> session =context.getSession();
		User sessionuser = (User) session.get("user");
		BBSMessage  bbsMessage=  new BBSMessage();
		bbsMessage.setTitle(title);
		bbsMessage.setContent(content);
		bbsMessage.setUserid(sessionuser.getUserid());
		bbsMessage.setCreateTime(new Date());
		return bbsMessage;
	}
	public List<BBSMessageQuery> getBbsmessagelist() {
		return bbsmessagelist;
	}
	public void setBbsmessagelist(List<BBSMessageQuery> bbsmessagelist) {
		this.bbsmessagelist = bbsmessagelist;
	}
	public BBSMessageQuery getBbsMessageQuery() {
		return bbsMessageQuery;
	}
	public void setBbsMessageQuery(BBSMessageQuery bbsMessageQuery) {
		this.bbsMessageQuery = bbsMessageQuery;
	}
	public List<MessageComment> getCommentslist() {
		return commentslist;
	}
	public void setCommentslist(List<MessageComment> commentslist) {
		this.commentslist = commentslist;
	}

	public User getUser(){
		User userobj = new User();
		String username = request.getParameter("username");
		String password = CodingCovert.getMD5(request.getParameter("pwd"));
		
		userobj.setUsername(username);
		userobj.setPassword(password);
		return userobj;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
	
}
