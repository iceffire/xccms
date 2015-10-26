package edu.cqu.fly.xccms.dao;

import java.util.List;

import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;

public interface ArticleDao extends CommonDao{
	public <T> List<T> selectBsArticleForPage(TypedQueryBuilder<T> tqBuilder,
			int startrecord, int endrecord);
}
