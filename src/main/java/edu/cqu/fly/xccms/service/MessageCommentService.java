package edu.cqu.fly.xccms.service;

import edu.cqu.fly.xccms.pojo.MessageComment;
import edu.cqu.fly.xccms.pojo.Pager;

public interface MessageCommentService extends CommonService{
	public  Pager  queryMessageCommentByPage(MessageComment messageComment, int pagesize,int currentpage);
}
