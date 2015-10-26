package edu.cqu.fly.xccms.service;

import java.util.Map;

import edu.cqu.fly.xccms.pojo.EduVideo;
import edu.cqu.fly.xccms.pojo.Pager;

public interface EduVideoService extends CommonService{
	public  Pager  queryEduVideoByPage(EduVideo eduVideo, int pagesize,int currentpage);
	public Map queryEduVideoForPage(EduVideo eduVideo,
			int pagesize, int currentpage);
}
