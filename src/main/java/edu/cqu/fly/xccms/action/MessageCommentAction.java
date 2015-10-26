package edu.cqu.fly.xccms.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.BBSMessageQuery;
import edu.cqu.fly.xccms.pojo.MessageComment;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.User;
import edu.cqu.fly.xccms.service.BBSMessageService;
import edu.cqu.fly.xccms.service.MessageCommentService;

public class MessageCommentAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5216074887576657854L;
	@Resource(name="messageCommentService")
	MessageCommentService messageCommentService;
	
	@Resource(name="bbsMessageService")
	BBSMessageService bbsMessageService;
	protected Long messageid;
	
protected BBSMessageQuery bbsMessageQuery;
	
	protected List<MessageComment> commentslist;
	/**
	 * 
	 * @return
	 */
	public  String    index(){
		messageid = Long.parseLong(request.getParameter("messageid"));
		return "index";
	}
	/**
	 * 查询主页
	 */
	public  void query(){
		messageid = Long.parseLong(request.getParameter("messageid"));
		String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  

		try {
			MessageComment messageComment = new MessageComment();
			messageComment.setMessageid(messageid);
			Pager pager = messageCommentService.queryMessageCommentByPage(messageComment,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		MessageComment messageComment = getMessageComment();
		try {
			messageComment.setId(Long.valueOf(id));
			messageCommentService.saveEntity(messageComment);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String  save(){
		boolean flag =false ;
		MessageComment messageComment = getMessageComment();
	
		try {
			messageCommentService.saveEntity(messageComment);
			bbsMessageQuery =	bbsMessageService.findBBSMessageQuery(messageComment.getMessageid());
			commentslist = bbsMessageService.queryCommentsByMsgId(messageComment.getMessageid());
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "bbs_detail";
	}

	public void delete(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		try {
			messageCommentService.removeEntityById(Long.valueOf(id),MessageComment.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public MessageComment  getMessageComment(){
		String messageid = request.getParameter("messageid"); 
		String commentctx = request.getParameter("commentctx"); 
		Map<String, Object> session =context.getSession();
		User sessionuser = (User) session.get("user");
		MessageComment  bbsMessage=  new MessageComment();
	   bbsMessage.setMessageid(Long.parseLong(messageid));
	   bbsMessage.setCommentctx(commentctx);
	   bbsMessage.setCommenttime(new Date());
	if(sessionuser == null){
		  bbsMessage.setUserid("");
	}else{
		bbsMessage.setUserid(sessionuser.getUserid());
	}
	   
		return bbsMessage;
	}
	public Long getMessageid() {
		return messageid;
	}
	public void setMessageid(Long messageid) {
		this.messageid = messageid;
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
	
	
}
