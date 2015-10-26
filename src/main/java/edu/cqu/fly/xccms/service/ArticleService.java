package edu.cqu.fly.xccms.service;

import java.util.List;
import java.util.Map;

import edu.cqu.fly.xccms.pojo.BsArticle;
import edu.cqu.fly.xccms.pojo.BsArticleQuery;
import edu.cqu.fly.xccms.pojo.BsOrg;
import edu.cqu.fly.xccms.pojo.NewsContribute;

public interface ArticleService extends CommonService{

	@SuppressWarnings("rawtypes")
	public Map queryArticleByTypeForPage(BsArticleQuery bsArticle,
			int pagesize, int currentpage);
	public  List<NewsContribute>  queryNewsContributes(int count);
	public Map<BsOrg,List<BsArticle>> queryUnitArticles(int start,int end);
	public List<BsArticle> queryHomePageBigPicnews();
}
