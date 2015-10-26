package edu.cqu.fly.xccms.service;

import java.util.Map;

import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.SiteUrl;

public interface SiteUrlService extends CommonService{
	public  Pager  querySiteUrlByPage(SiteUrl siteUrl, int pagesize,int currentpage);
	public Map querySiteUrlForPage(SiteUrl siteUrl,
			int pagesize, int currentpage);
}
