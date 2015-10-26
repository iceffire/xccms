package edu.cqu.fly.xccms.service;

import java.util.List;
import java.util.Map;

import edu.cqu.fly.xccms.pojo.BBSMessage;
import edu.cqu.fly.xccms.pojo.BBSMessageQuery;
import edu.cqu.fly.xccms.pojo.MessageComment;
import edu.cqu.fly.xccms.pojo.Pager;

public interface BBSMessageService extends CommonService {
	public  Pager  queryBBSMessageByPage(BBSMessage bbsMessage, int pagesize,int currentpage);
	public List<MessageComment> queryComment(Long id );
	public Map queryBBSMessageForPage(BBSMessageQuery bbSMessage,
			int pagesize, int currentpage);
	public BBSMessageQuery findBBSMessageQuery(Long id);
	public List<MessageComment> queryCommentsByMsgId(Long id);
}
