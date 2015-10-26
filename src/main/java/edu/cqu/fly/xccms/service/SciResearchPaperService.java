package edu.cqu.fly.xccms.service;

import java.util.Map;

import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.SciResearchPaper;
import edu.cqu.fly.xccms.pojo.SciResearchPaperQuery;

public interface SciResearchPaperService extends CommonService{

	public  Pager  querySciResearchPaperByPage(SciResearchPaperQuery sciResearchPaper, int pagesize,int currentpage);
	public Map querySciResearchPaperForPage(SciResearchPaper sciResearchPaper,
			int pagesize, int currentpage);
}
