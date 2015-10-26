package edu.cqu.fly.xccms.service;

import java.util.Map;

import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.ResourceTable;

public interface ResourceTableService extends CommonService{
	public  Pager  queryResourceTableByPage(ResourceTable resourceTable, int pagesize,int currentpage);
	public Map queryResourceTableForPage(ResourceTable resourceTable,
			int pagesize, int currentpage);
}
