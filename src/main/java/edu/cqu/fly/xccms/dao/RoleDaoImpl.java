package edu.cqu.fly.xccms.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Repository("roleDao")
public class RoleDaoImpl extends CommonDaoImpl implements RoleDao{

	public <T> List<T> selectBsRoleForPage(TypedQueryBuilder<T> tqBuilder,
			int startrecord, int endrecord) {
		TypedQuery<Long> countTQ = tqBuilder.toCountQuery(em);
		final long allCounts = countTQ.getSingleResult();
		
		TypedQuery<T> tq = tqBuilder.toQuery(em);
		tq.setFirstResult(startrecord);
		tq.setMaxResults(endrecord);
		List<?> list = tq.getResultList();
		return (List<T>) list;
	}

}
