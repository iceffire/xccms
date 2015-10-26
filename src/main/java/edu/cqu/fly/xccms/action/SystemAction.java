package edu.cqu.fly.xccms.action;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import edu.cqu.fly.xccms.pojo.User;

public class SystemAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1270374690774841270L;

	public static List<User> onlineUsers =new ArrayList<User>();

}
