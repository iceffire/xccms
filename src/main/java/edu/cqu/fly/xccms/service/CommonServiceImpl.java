package edu.cqu.fly.xccms.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.cache.Cache;
import edu.cqu.fly.xccms.dao.CommonDao;
import edu.cqu.fly.xccms.pojo.NewsType;
import edu.cqu.fly.xccms.pojo.SiteUrl;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("commonService")
@Transactional
public class CommonServiceImpl implements CommonService{
	Logger log = Logger.getLogger(CommonServiceImpl.class);
	@SuppressWarnings("restriction")
	@Resource(name="commonDao")
	protected CommonDao commonDao;
	
	
	public <T> T findEntityById(Object id, Class<T> clazz) {
		return commonDao.readEntityById(id, clazz);
	}

	
	public <PK, T> List<T> findEntitiesByIds(Set<PK> ids, Class<T> clazz) {
		return commonDao.readEntitiesByIds(ids, clazz);
	}

	
	public <T> T findEntityByProperty(String propertyName, Object value, Class<T> clazz) {
		return commonDao.readEntityByProperty(propertyName, value, clazz);
	}

	
	public <T> List<T> findEntitiesByProperty(String propertyName, Object value, Class<T> clazz) {
		return commonDao.readEntitiesByProperty(propertyName, value, clazz);
	}

	
	public <T> List<T> findEntitiesByProperties(String propertyName, Set<Object> values, Class<T> clazz) {
		return commonDao.readEntitiesByProperties(propertyName, values, clazz);
	}

	
	public <T> List<T> findAllEntities(Class<T> clazz) {
		return commonDao.readAllEntities(clazz);
	}

	
	public void removeEntityById(Object id, Class<?> clazz) {
		commonDao.deleteEntityById(id, clazz);
	}
	
	
	public void removeEntities(Object[] ids, Class<?> clazz) {
		for(Object id : ids) {
			commonDao.deleteEntityById(id, clazz);
		}
	}

	
	public <T> T addEntity(T entity) {
		return commonDao.saveEntity(entity);
	}
	
	
	public <T> T saveEntity(T entity) {
		return commonDao.updateEntity(entity);
	}
	
	
	public <T> T findEntityByJPQL(String jpql, Class<T> clazz) {
		return commonDao.readEntityByJPQL(jpql, clazz);
	}
	
	
	public <T> List<T> readEntitiesByJPQL(String jpql, Class<T> clazz, Object... params) {
		return commonDao.readEntitiesByJPQL(jpql, clazz, params);
	}
	
	
	public int executeJPQL(String jpql) {
		return commonDao.executeJPQL(jpql);
	}
	
	
	public List<Map<String, Object>> findSetBySql(String sql, Object... objs) {
		return commonDao.readSetBySql(sql, objs);
	}
	
	
	public Map<String, Object> findSingleBySql(String sql, Object... objs) {
		return commonDao.readSingleBySql(sql, objs);
	}
	
	
	public int executeSql(String sql, List<Object> params) {
		return commonDao.executeSql(sql, params);
	}
	
	
	public int executeSql(String sql, Object... params) {
		return commonDao.executeSql(sql, params);
	}
	
	
	public int executeSql(String sql, Map<String, Object> params) {
		return commonDao.executeSql(sql, params);
	}
	
	
	public Object executeSqlReturnKey(String sql, Map<String, Object> params) {
		return commonDao.executeSql(sql, params);
	}


	public <T> List<T> queryByParas(TypedQueryBuilder<T> tq) {
		return commonDao.queryByParas(tq);
	}


	public void cacheSystemConstatnt() {
		List<NewsType>  newstypes = findAllEntities(NewsType.class);
		List<SiteUrl> siteurl = findAllEntities(SiteUrl.class);
	
			log.info("***加载缓存常量***NewsType***");
			Cache.getInstance().put("newstype",newstypes);
			log.info("***加载缓存常量***SiteUrl***");
		   Cache.getInstance().put("siteurl", siteurl);
		   Cache.getInstance().put("imgwidth", ComonUtil.getfileCfg().getProperty("imgwidth"));
		   Cache.getInstance().put("imgheight", ComonUtil.getfileCfg().getProperty("imgheight"));
		  
		   
	}


	public <T> List<T> selectObjectPage(TypedQueryBuilder<T> tqBuilder,int startrecord, int endrecord) {
		return commonDao.selectObjectForPage(tqBuilder, startrecord, endrecord);
	}
}
