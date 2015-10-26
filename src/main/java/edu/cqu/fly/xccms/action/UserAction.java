package edu.cqu.fly.xccms.action;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;

import edu.cqu.fly.xccms.pojo.BsUserOrg;
import edu.cqu.fly.xccms.pojo.BsUserRole;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.User;
import edu.cqu.fly.xccms.service.UserService;
import edu.cqu.fly.xccms.util.CodingCovert;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.ExportExcel;

public class UserAction extends BaseAction{
	Logger log = Logger.getLogger(this.getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 5262827128119747387L;
	
	@Resource(name="userService")
	UserService userService;

	
	/**
	 * 用户管理
	 * @return
	 */
	public  String  index(){
		return "index";
	}
	/**
	 * 查询用户
	 */
	public  void  query(){
		String isadminquery = request.getParameter("isadminquery");
		String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
	    Map<String, Object> session = ActionContext.getContext().getSession();
	    User onlineuser = (User)session.get("user");
		try {
			User user = new User();
			if(null!=isadminquery&&!isadminquery.equals("")){
				user.setIsadmin(isadminquery);
			}
			Pager pager = userService.queryUserListByPage(onlineuser,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @throws SQLException 
	 * @Title: regUser
	 * @Description: (注册用户)
	 * @param @return 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	@SuppressWarnings("rawtypes")
	public void save() throws SQLException {
		String roleid = request.getParameter("roleid");
		String orgid = request.getParameter("orgid");
		boolean flag = false;
		User param = new User();
		user =getUser(param);
		
		try {
			Map result = userService.createUser(user,roleid,orgid);
			if (ComonUtil.validateMapResult(result)) {
				flag = true;
			}
			userService.saveUserToOtherDb(user);
			response.getWriter().print(flag);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 编辑用户
	 */
	public   void update(){
		boolean flag = false;
		String id = request.getParameter("id");
		String roleid = request.getParameter("roleid");
		String orgid = request.getParameter("orgid");
		User param = new User();
		try {
			param = userService.findEntityById(Long.valueOf(id), User.class);
			user = getUser(param);
			userService.saveEntity(user);
			userService.updateUserToOtherDb(user);
			userService.updateUser(user,roleid,orgid);
			flag = true;
			response.getWriter().print(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 公用获取参数部分
	 * @param user1
	 * @return
	 */
	public  User   getUser(User user1){
		String username = request.getParameter("username");
		String isadmin = request.getParameter("isadmin");
		String userid = request.getParameter("userid");
		String realname = request.getParameter("realname");
		String password = "";
		if(request.getParameter("password").length()>30){
			password = request.getParameter("password");
		}else{
			password = CodingCovert.getMD5(request.getParameter("password"));
		}
		
		if(userid == null || userid.equals("")){
			user1.setUserid(ComonUtil.getUserId());
		}else{
			user1.setUserid(userid);
		}
		
		user1.setPassword(password);
		user1.setUsername(username);
		user1.setRealname(realname);
		user1.setIsadmin(isadmin);
		return user1;
	}
	/**
	 * 删除用户
	 */
	public   void delete(){
		boolean flag = false;
		String id = request.getParameter("id");
		try {
			userService.removeEntityById(Long.valueOf(id),User.class);
			flag = true;
			response.getWriter().print(flag);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询角色
	 */
	public void queryroleByuserid(){
		String userid = request.getParameter("userid"); 
		try {
			List<BsUserRole>  list =userService.queryroleByuserid(userid);
			String json ="";
			if(null!=list&&list.size()>0){
				for(BsUserRole row:list){
					json = json+row.getRoleid()+",";
				}
				json = json.substring(0, json.length()-1);
				log.info(json);
			}
			super.reponseWriter(json);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询机构
	 */
	public void queryorgByuserid(){
		String userid = request.getParameter("userid"); 
		try {
			List<BsUserOrg>  list =userService.queryorgByuserid(userid);
			String json ="";
			if(null!=list&&list.size()>0){
				for(BsUserOrg row:list){
					json = json+row.getOrgid()+",";
				}
				json = json.substring(0, json.length()-1);
				log.info(json);
			}
			super.reponseWriter(json);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出Excel
	 */
	@SuppressWarnings("unchecked")
	public  void  ExportExcel(){
		String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
		try {
			String isadminquery=new String(request.getParameter("isadminquery").getBytes("ISO8859-1"), "UTF-8"); 
			User user = new User();
			
			if(null!=isadminquery&&!isadminquery.equals("")){
				user.setIsadmin(isadminquery);
			}
			Pager pager = userService.queryUserListByPage(user,Integer.valueOf(rows),Integer.valueOf(page));
			List<User> dataset = (List<User>) pager.getRows();
			String[] hearders = new String[] {"序号","用户编号","登陆账号", "机构编码", "是否为管理员","密码"};//表头数组        
		    ExportExcel<User> ex = new ExportExcel<User>();  
		    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
		    String filename = timeFormat.format(new Date())+".xls";  
		    response.setContentType("application/ms-excel;charset=UTF-8");  
		    response.setHeader("Content-Disposition", "attachment;filename=".concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));  
		    OutputStream out = response.getOutputStream();  
		    ex.exportExcel(hearders, dataset, out);  
		    out.close();  
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		   
	}
	
}
