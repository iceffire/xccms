package edu.cqu.fly.xccms.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.dao.CommonDao;
import edu.cqu.fly.xccms.pojo.NewsType;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("newsTypeService")
@Transactional
public class NewsTypeServiceImpl extends CommonServiceImpl implements NewsTypeService{

	@SuppressWarnings("restriction")
	@Resource(name="commonDao")
	protected CommonDao commonDao;
	
	public Pager queryNewsTypeByPage(NewsType newsType, int pagesize,
			int currentpage) {
		Pager page = new Pager();
		TypedQueryBuilder<NewsType> tqBuilder = new TypedQueryBuilder<NewsType>(NewsType.class,"e");
		
		int startrecord = (currentpage - 1) * pagesize;
		List<NewsType> list = commonDao.selectObjectForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = queryByParas(tqBuilder).size();
		page.setRows(list);
		page.setTotal(totalCount);
		return page;
	}

}
