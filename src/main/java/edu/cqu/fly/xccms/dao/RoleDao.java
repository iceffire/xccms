package edu.cqu.fly.xccms.dao;

import java.util.List;

import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;

public interface RoleDao extends CommonDao{
	public <T> List<T> selectBsRoleForPage(TypedQueryBuilder<T> tqBuilder,
			int startrecord, int endrecord);
}
