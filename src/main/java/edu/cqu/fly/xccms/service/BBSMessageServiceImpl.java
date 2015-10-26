package edu.cqu.fly.xccms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.dao.CommonDao;
import edu.cqu.fly.xccms.pojo.BBSMessage;
import edu.cqu.fly.xccms.pojo.BBSMessageQuery;
import edu.cqu.fly.xccms.pojo.MessageComment;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;
import edu.cqu.fly.xccms.util.DateUtil;
import edu.cqu.fly.xccms.util.dao.TQOrder;
import edu.cqu.fly.xccms.util.dao.TQRestriction;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("bbsMessageService")
@Transactional
public class BBSMessageServiceImpl extends CommonServiceImpl implements BBSMessageService{
	@SuppressWarnings("restriction")
	@Resource(name="commonDao")
	protected CommonDao commonDao;
	public Pager queryBBSMessageByPage(BBSMessage bbsMessage, int pagesize,
			int currentpage) {
		Pager page = new Pager();
		TypedQueryBuilder<BBSMessage> tqBuilder = new TypedQueryBuilder<BBSMessage>(BBSMessage.class,"e");
		
		int startrecord = (currentpage - 1) * pagesize;
		List<BBSMessage> list = commonDao.selectObjectForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = queryByParas(tqBuilder).size();
		page.setRows(list);
		page.setTotal(totalCount);
		return page;
	}
	/**
	 * 活取发帖评论
	 */
	public List<MessageComment> queryComment(Long id) {
		TypedQueryBuilder<MessageComment> tqBuilder = new TypedQueryBuilder<MessageComment>(MessageComment.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "messageid", "in", Arrays.asList(id)));
		return queryByParas(tqBuilder);
	}
	public Map queryBBSMessageForPage(BBSMessageQuery bbSMessage, int pagesize,
			int currentpage) {
		Map map = new HashMap();
		TypedQueryBuilder<BBSMessage> tqBuilder = new TypedQueryBuilder<BBSMessage>(BBSMessage.class,"e");
		
		 tqBuilder.addOrder(new TQOrder("createTime",false));//按类型排序
		
		if(!bbSMessage.getTitle().equals("")){
			tqBuilder.addRestriction(new TQRestriction( "title", "like", Arrays.asList("%"+bbSMessage.getTitle()+"%")));
		}
		if(!bbSMessage.getQueryPeriod().equals("")){
			Date querydate = null;
			if(bbSMessage.getQueryPeriod().equals(Constant.ONE_HOUR)){
				querydate = DateUtil.get1HourAgo();
			}else if(bbSMessage.getQueryPeriod().equals(Constant.FOUR_HOUR)){
				querydate = DateUtil.get4HourAgo();
			}else if(bbSMessage.getQueryPeriod().equals(Constant.EIGHT_HOUR)){
				querydate = DateUtil.get8HourAgo();
			}else if(bbSMessage.getQueryPeriod().equals(Constant.ONE_DAY)){
				querydate = DateUtil.get1DayAgo();
			}else if(bbSMessage.getQueryPeriod().equals(Constant.TWO_DAY)){
				querydate = DateUtil.get2DayAgo();
			}else if(bbSMessage.getQueryPeriod().equals(Constant.ONE_WEEK)){
				querydate = DateUtil.get1WeekAgo();
			}else if(bbSMessage.getQueryPeriod().equals(Constant.ONE_MONTH)){
				querydate = DateUtil.get1MonthAgo();
			}else if(bbSMessage.getQueryPeriod().equals(Constant.HALF_YEAR)){
				querydate = DateUtil.get6MonthAgo();
			}else if(bbSMessage.getQueryPeriod().equals(Constant.ONE_YEAR)){
				querydate = DateUtil.get1YearAgo();
			}
			tqBuilder.addRestriction(new TQRestriction( "createTime", ">=", Arrays.asList(querydate)));
		}
		if(bbSMessage.getIsessence() == 1){
			tqBuilder.addRestriction(new TQRestriction( "isessence", "in", Arrays.asList(bbSMessage.getIsessence())));
		}
			int startrecord = (currentpage - 1) * pagesize;
			List<BBSMessage> list = commonDao.selectObjectForPage(tqBuilder,
					startrecord, pagesize);
			List<BBSMessageQuery> bbsMessageQuerys = new ArrayList<BBSMessageQuery>();
			
			for(BBSMessage bbs : list){
				BBSMessageQuery bbsquery = new BBSMessageQuery();
				bbsquery.setId(bbs.getId());
				bbsquery.setContent(bbs.getContent());
				bbsquery.setTitle(bbs.getTitle());
				bbsquery.setUserid(bbs.getUserid());
				bbsquery.setCreateTime(bbs.getCreateTime());
				
				MessageComment comment = getLastComment(bbs.getId());
				if(comment != null){
					bbsquery.setLastcommenttime(comment.getCommenttime());
				}else{
					bbsquery.setLastcommenttime(bbs.getCreateTime());
				}
				bbsquery.setReplyNum(getCommentNum(bbs.getId()));
				bbsquery.setScannum(bbs.getScannum());
				bbsMessageQuerys.add(bbsquery);
			}
			int totalCount = commonDao.queryByParas(tqBuilder).size();
			map.put(Constant.BBSMESSAGE_LIST, bbsMessageQuerys);
			map.put(Constant.TOTALCOUNT, totalCount);
			map.put(Constant.TOTALPAGECOUNT,
					ComonUtil.computusTotalPage(totalCount, pagesize));
			return map;
	}

	public MessageComment getLastComment(Long msgid){
	
		List<MessageComment>  comments =	queryCommentsByMsgId(msgid);
		if(comments.size() > 0){
			return comments.get(comments.size()-1);
		}else{
			return null;
		}
		 
	}
	/**
	 * 取得评论数
	 * @param msgid
	 * @return
	 */
	public Integer getCommentNum(Long msgid){
return queryCommentsByMsgId(msgid).size();
	}
	public BBSMessageQuery findBBSMessageQuery(Long id) {
		BBSMessageQuery bbsquery = new BBSMessageQuery();
		 BBSMessage bbs = commonDao.readEntityById(id, BBSMessage.class);
			bbsquery.setId(bbs.getId());
			bbsquery.setContent(bbs.getContent());
			bbsquery.setTitle(bbs.getTitle());
			bbsquery.setUserid(bbs.getUserid());
			bbsquery.setCreateTime(bbs.getCreateTime());
			bbsquery.setReplyNum(getCommentNum(bbs.getId()));
			bbsquery.setScannum(bbs.getScannum());
			MessageComment comment = getLastComment(bbs.getId());
			if(comment != null){
				bbsquery.setLastcommenttime(comment.getCommenttime());
			}else{
				bbsquery.setLastcommenttime(bbs.getCreateTime());
			}
		return bbsquery;
	}
	/**
	 * 获取回复列表
	 */
	public List<MessageComment> queryCommentsByMsgId(Long id) {
		TypedQueryBuilder<MessageComment> tqBuilder = new TypedQueryBuilder<MessageComment>(MessageComment.class,"e");
		
		 tqBuilder.addOrder(new TQOrder("commenttime",true));//按回复时间排序
		
		tqBuilder.addRestriction(new TQRestriction( "messageid", "in", Arrays.asList(id)));
		return queryByParas(tqBuilder);
	}
}
