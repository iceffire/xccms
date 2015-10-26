package edu.cqu.fly.xccms.service;

import edu.cqu.fly.xccms.pojo.NewsType;
import edu.cqu.fly.xccms.pojo.Pager;

public interface NewsTypeService  extends CommonService{
	public  Pager  queryNewsTypeByPage(NewsType newsType, int pagesize,int currentpage);
}
