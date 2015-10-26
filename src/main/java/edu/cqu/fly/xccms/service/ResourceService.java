package edu.cqu.fly.xccms.service;

import java.util.List;

import edu.cqu.fly.xccms.pojo.BsResource;
import edu.cqu.fly.xccms.pojo.Pager;


public interface ResourceService extends CommonService{
	public List<BsResource> queryResourcebyuserId(String userid);
	public  Pager  queryBsResourceByPage(BsResource bsResource, int pagesize,int currentpage);
}
