package edu.cqu.fly.xccms.service;

import java.util.Map;

import edu.cqu.fly.xccms.pojo.OfficeSoftware;
import edu.cqu.fly.xccms.pojo.Pager;

public interface OfficeSoftwareService extends CommonService{
	public  Pager  queryOfficeSoftwareByPage(OfficeSoftware officeSoftware, int pagesize,int currentpage);
	public Map queryOfficeSoftwareForPage(OfficeSoftware officeSoftware,
			int pagesize, int currentpage);
}
