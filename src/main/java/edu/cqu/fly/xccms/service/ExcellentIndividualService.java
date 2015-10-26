package edu.cqu.fly.xccms.service;

import java.util.Map;

import edu.cqu.fly.xccms.pojo.ExcellentIndividual;
import edu.cqu.fly.xccms.pojo.ExcellentIndividualQuery;
import edu.cqu.fly.xccms.pojo.Pager;

public interface ExcellentIndividualService extends CommonService{
	public  Pager  queryExcellentIndividualByPage(ExcellentIndividualQuery excellentIndividual, int pagesize,int currentpage);
	public Map queryExcellentIndividualForPage(ExcellentIndividual excellentIndividual,
			int pagesize, int currentpage);
}
