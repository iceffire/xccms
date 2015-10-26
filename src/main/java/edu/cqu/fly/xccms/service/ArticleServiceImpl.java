package edu.cqu.fly.xccms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.dao.ArticleDao;
import edu.cqu.fly.xccms.pojo.BsArticle;
import edu.cqu.fly.xccms.pojo.BsArticleQuery;
import edu.cqu.fly.xccms.pojo.BsOrg;
import edu.cqu.fly.xccms.pojo.BsUserOrg;
import edu.cqu.fly.xccms.pojo.BsUserRole;
import edu.cqu.fly.xccms.pojo.NewsContribute;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;
import edu.cqu.fly.xccms.util.dao.TQOrder;
import edu.cqu.fly.xccms.util.dao.TQRestriction;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("articleService")
@Transactional
public class ArticleServiceImpl extends CommonServiceImpl implements ArticleService{
	@SuppressWarnings("restriction")
	@Resource(name="articleDao")
	protected ArticleDao articleDao;
	public Map queryArticleByTypeForPage(BsArticleQuery bsArticle,
			int pagesize, int currentpage) {
		Map map = new HashMap();
		TypedQueryBuilder<BsArticle> tqBuilder = new TypedQueryBuilder<BsArticle>(BsArticle.class,"e");
		
	 tqBuilder.addOrder(new TQOrder("createdate",false));//按日期排序
	if(bsArticle.getUser() != null){
		TypedQueryBuilder<BsUserRole> tqBuilder_role= new TypedQueryBuilder<BsUserRole>(BsUserRole.class,"e");
		tqBuilder_role.addRestriction(new TQRestriction( "userid", "in", Arrays.asList(bsArticle.getUser().getUserid())));
	    List<BsUserRole> role = queryByParas(tqBuilder_role);
	 
	    //按角色过滤
	 if(role.get(0).getRoleid() != 1){
		 //××××××××××××××××××××××××××××××××××
		 List<Integer> orgs = new ArrayList<Integer>();
		 for(BsUserOrg userorg : bsArticle.getUserorgs()){
			 orgs.add(userorg.getOrgid());
		 }
		 tqBuilder.addRestriction(new TQRestriction( "createorgid", "in",orgs));
		 //××××××××××××××××××××××××××××××××××
	 }
	}
		

	 
		if (null != bsArticle.getType() && !bsArticle.getType().equals("")) {
			tqBuilder.addRestriction(new TQRestriction( "type", "in", Arrays.asList(bsArticle.getType())));
		}
		if (null != bsArticle.getStartdatacreatenew()) {
			tqBuilder.addRestriction(new TQRestriction( "createdate", ">=", Arrays.asList(bsArticle.getStartdatacreatenew())));
		//	criteria.andCreatedateBetween(bsArticle.getStartdatacreatenew(),
	//				bsArticle.getEnddatacreatenew());
		} else if (null != bsArticle.getEnddatacreatenew()) {
			tqBuilder.addRestriction(new TQRestriction( "createdate", "<=", Arrays.asList(bsArticle.getEnddatacreatenew())));
		}
		if (null != bsArticle.getAuthor()) {
			tqBuilder.addRestriction(new TQRestriction( "author", "like", Arrays.asList("%" + bsArticle.getAuthor() + "%")));
		}
		if (null != bsArticle.getTitle()) {
			tqBuilder.addRestriction(new TQRestriction( "title", "like", Arrays.asList("%" + bsArticle.getTitle() + "%")));
		}
		int startrecord = (currentpage - 1) * pagesize;
		List<BsArticle> list = articleDao.selectBsArticleForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = articleDao.queryByParas(tqBuilder).size();
		map.put(Constant.TOTALCOUNT, totalCount);
		map.put(Constant.TOTALPAGECOUNT,
				ComonUtil.computusTotalPage(totalCount, pagesize));
		map.put(Constant.ACTION_RESULT, Constant.RESULT_SUCCESS);
		map.put(Constant.ARTICLE_LIST, list);
		return map;
	}
	public List<NewsContribute> queryNewsContributes(int count) {
		List list = commonDao.queryObjectBySql("select * from (select article_createorgid,count(*) as newscount from XCCMS_ARTICLE group by article_createorgid) as s order by newscount desc");
		
		List<NewsContribute> newsContributes = new ArrayList<NewsContribute>();
		int n = 0;
		if(list.size()>count){
			n = count;
		}else{
			n = list.size();
		}
		if(count == 0){
			n = list.size();
		}
		for(int i = 0;i<n;i++){
			Object[] objs = (Object[])list.get(i);
			BsOrg org = commonDao.readEntityById(Long.parseLong(objs[0].toString()), BsOrg.class);
			NewsContribute contribute = new NewsContribute();
			contribute.setOrgname(org.getOrgname());
			contribute.setNewscount(Integer.parseInt(objs[1].toString()));
			newsContributes.add(contribute);
		}
		return newsContributes;
	}
	public Map<BsOrg, List<BsArticle>> queryUnitArticles(int start,int end) {
		Map<BsOrg, List<BsArticle>>  maps = new LinkedHashMap<BsOrg, List<BsArticle>>();
		TypedQueryBuilder<BsOrg> tqBuilder_org= new TypedQueryBuilder<BsOrg>(BsOrg.class,"e");
		tqBuilder_org.addOrder(new TQOrder("orgcode",true));//按机构编码排序
		tqBuilder_org.addRestriction(new TQRestriction( "parentid", "in", Arrays.asList(Long.parseLong("1"))));
		List<BsOrg> orgs = commonDao.queryByParas(tqBuilder_org);
		for(BsOrg org : orgs){
			TypedQueryBuilder<BsArticle> tqBuilder_art= new TypedQueryBuilder<BsArticle>(BsArticle.class,"e");
			tqBuilder_art.addOrder(new TQOrder("createdate",false));//按机构编码排序
			tqBuilder_art.addRestriction(new TQRestriction( "createorgid", "in", Arrays.asList(Integer.valueOf(org.getId().intValue()))));
			System.out.println(commonDao.selectObjectForPage(tqBuilder_art, start, end).size());
			maps.put(org, commonDao.selectObjectForPage(tqBuilder_art, start, end));
		}
		return maps;
	}
	/**
	 * 获取主页大图片新闻
	 */
	public List<BsArticle> queryHomePageBigPicnews() {
		TypedQueryBuilder<BsArticle> tqBuilder= new TypedQueryBuilder<BsArticle>(BsArticle.class,"e");
		tqBuilder.addOrder(new TQOrder("createdate",false));//按时间排序
		tqBuilder.addRestriction(new TQRestriction( "hasheaderpic", "in", Arrays.asList(1)));
		
		List<BsArticle> arts = selectObjectPage(tqBuilder,0,5);
		
			return arts;
	}

}
