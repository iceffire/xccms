package edu.cqu.fly.xccms.service;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.dao.CommonDao;
import edu.cqu.fly.xccms.pojo.MessageComment;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.util.dao.TQOrder;
import edu.cqu.fly.xccms.util.dao.TQRestriction;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("messageCommentService")
@Transactional
public class MessageCommentServiceImpl extends CommonServiceImpl implements MessageCommentService{
	@SuppressWarnings("restriction")
	@Resource(name="commonDao")
	protected CommonDao commonDao;
	public Pager queryMessageCommentByPage(MessageComment messageComment,
			int pagesize, int currentpage) {
		Pager page = new Pager();
		TypedQueryBuilder<MessageComment> tqBuilder = new TypedQueryBuilder<MessageComment>(MessageComment.class,"e");
		 tqBuilder.addOrder(new TQOrder("commenttime",false));//按日期排序
		tqBuilder.addRestriction(new TQRestriction( "messageid", "in", Arrays.asList(messageComment.getMessageid())));
		int startrecord = (currentpage - 1) * pagesize;
		List<MessageComment> list = commonDao.selectObjectForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = queryByParas(tqBuilder).size();
		page.setRows(list);
		page.setTotal(totalCount);
		return page;
	}

}
