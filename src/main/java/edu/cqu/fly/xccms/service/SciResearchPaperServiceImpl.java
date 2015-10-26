package edu.cqu.fly.xccms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.dao.CommonDao;
import edu.cqu.fly.xccms.pojo.BsUserOrg;
import edu.cqu.fly.xccms.pojo.BsUserRole;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.SciResearchPaper;
import edu.cqu.fly.xccms.pojo.SciResearchPaperQuery;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;
import edu.cqu.fly.xccms.util.dao.TQOrder;
import edu.cqu.fly.xccms.util.dao.TQRestriction;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("sciResearchPaperService")
@Transactional
public class SciResearchPaperServiceImpl extends CommonServiceImpl implements SciResearchPaperService{

	@Resource(name="commonDao")
	protected CommonDao commonDao;
	public Pager querySciResearchPaperByPage(SciResearchPaperQuery sciResearchPaper,
			int pagesize, int currentpage) {
		Pager page = new Pager();
		TypedQueryBuilder<SciResearchPaper> tqBuilder = new TypedQueryBuilder<SciResearchPaper>(SciResearchPaper.class,"e");
		if(sciResearchPaper.getUser() != null){
			TypedQueryBuilder<BsUserRole> tqBuilder_role= new TypedQueryBuilder<BsUserRole>(BsUserRole.class,"e");
			tqBuilder_role.addRestriction(new TQRestriction( "userid", "in", Arrays.asList(sciResearchPaper.getUser().getUserid())));
		    List<BsUserRole> role = queryByParas(tqBuilder_role);
		 
		    //按角色过滤
		 if(role.get(0).getRoleid() != 1){
			 //××××××××××××××××××××××××××××××××××
			 List<Integer> orgs = new ArrayList<Integer>();
			 for(BsUserOrg userorg : sciResearchPaper.getUserorgs()){
				 orgs.add(userorg.getOrgid());
			 }
			 tqBuilder.addRestriction(new TQRestriction( "uploadorgid", "in",orgs));
			 //××××××××××××××××××××××××××××××××××
		 }
		}
		int startrecord = (currentpage - 1) * pagesize;
		List<SciResearchPaper> list = commonDao.selectObjectForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = queryByParas(tqBuilder).size();
		page.setRows(list);
		page.setTotal(totalCount);
		return page;
	}

	public Map querySciResearchPaperForPage(SciResearchPaper sciResearchPaper,
			int pagesize, int currentpage) {
		Map map = new HashMap();
		TypedQueryBuilder<SciResearchPaper> tqBuilder = new TypedQueryBuilder<SciResearchPaper>(SciResearchPaper.class,"e");
		
		 tqBuilder.addOrder(new TQOrder("uploadtime",true));//按上传时间排序
		
		
			int startrecord = (currentpage - 1) * pagesize;
			List<SciResearchPaper> list = commonDao.selectObjectForPage(tqBuilder,
					startrecord, pagesize);
			int totalCount = commonDao.queryByParas(tqBuilder).size();
			map.put("paperlist", list);
			map.put(Constant.TOTALCOUNT, totalCount);
			map.put(Constant.TOTALPAGECOUNT,
					ComonUtil.computusTotalPage(totalCount, pagesize));
			return map;
	}

}
