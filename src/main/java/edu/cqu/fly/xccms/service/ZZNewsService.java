package edu.cqu.fly.xccms.service;

import java.util.Map;

import edu.cqu.fly.xccms.pojo.ExcellentIndividual;
import edu.cqu.fly.xccms.pojo.ZZNews;
import edu.cqu.fly.xccms.pojo.Pager;

public interface ZZNewsService extends CommonService{
	public  Pager  queryZZNewsByPage(ZZNews zzNews, int pagesize,int currentpage);
	public Map queryZZNewsForPage(ZZNews zzNews,
			int pagesize, int currentpage);
}
