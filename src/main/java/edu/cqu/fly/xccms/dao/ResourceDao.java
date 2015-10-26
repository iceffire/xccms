package edu.cqu.fly.xccms.dao;

import java.util.List;

import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;



public interface ResourceDao extends CommonDao{

	public <T> List<T> selectBsResourceForPage(TypedQueryBuilder<T> tqBuilder,
			int startrecord, int endrecord);
}
