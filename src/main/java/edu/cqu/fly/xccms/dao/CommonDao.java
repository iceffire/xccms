package edu.cqu.fly.xccms.dao;

import java.util.List;

import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;

public interface CommonDao extends AbstractBaseDao {
	public <T> List<T> queryByParas(final TypedQueryBuilder<T> tq);
	public <T> List<T> selectObjectForPage(TypedQueryBuilder<T> tqBuilder,
			int startrecord, int endrecord);
}
