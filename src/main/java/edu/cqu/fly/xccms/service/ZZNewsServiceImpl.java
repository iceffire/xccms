package edu.cqu.fly.xccms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.dao.CommonDao;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.ZZNews;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("zzNewsService")
@Transactional
public class ZZNewsServiceImpl extends CommonServiceImpl implements ZZNewsService{

	@SuppressWarnings("restriction")
	@Resource(name="commonDao")
	protected CommonDao commonDao;
	public Pager queryZZNewsByPage(ZZNews zzNews,
			int pagesize, int currentpage) {
		Pager page = new Pager();
		TypedQueryBuilder<ZZNews> tqBuilder = new TypedQueryBuilder<ZZNews>(ZZNews.class,"e");
		
		int startrecord = (currentpage - 1) * pagesize;
		List<ZZNews> list = commonDao.selectObjectForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = queryByParas(tqBuilder).size();
		page.setRows(list);
		page.setTotal(totalCount);
		return page;		
	}
	public Map queryZZNewsForPage(ZZNews zzNews, int pagesize, int currentpage) {
		Map map = new HashMap();
		TypedQueryBuilder<ZZNews> tqBuilder = new TypedQueryBuilder<ZZNews>(ZZNews.class,"e");
		
		
		
			int startrecord = (currentpage - 1) * pagesize;
			List<ZZNews> list = commonDao.selectObjectForPage(tqBuilder,
					startrecord, pagesize);
			int totalCount = commonDao.queryByParas(tqBuilder).size();
			map.put("zznewslist", list);
			map.put(Constant.TOTALCOUNT, totalCount);
			map.put(Constant.TOTALPAGECOUNT,
					ComonUtil.computusTotalPage(totalCount, pagesize));
			return map;
	}

}
