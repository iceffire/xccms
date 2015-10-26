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
import edu.cqu.fly.xccms.pojo.ExcellentIndividual;
import edu.cqu.fly.xccms.pojo.ExcellentIndividualQuery;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;
import edu.cqu.fly.xccms.util.dao.TQOrder;
import edu.cqu.fly.xccms.util.dao.TQRestriction;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("excellentIndividualService")
@Transactional
public class ExcellentIndividualServiceImpl extends CommonServiceImpl implements ExcellentIndividualService{
	@SuppressWarnings("restriction")
	@Resource(name="commonDao")
	protected CommonDao commonDao;
	public Pager queryExcellentIndividualByPage(
			ExcellentIndividualQuery excellentIndividual, int pagesize,
			int currentpage) {
		Pager page = new Pager();
		TypedQueryBuilder<ExcellentIndividual> tqBuilder = new TypedQueryBuilder<ExcellentIndividual>(ExcellentIndividual.class,"e");
		if(!excellentIndividual.getName().equals("")&&excellentIndividual.getName() !=null){
			tqBuilder.addRestriction(new TQRestriction( "name", "like", Arrays.asList("%"+excellentIndividual.getName()+"%")));
		}
		
		if(excellentIndividual.getUser() != null){
			TypedQueryBuilder<BsUserRole> tqBuilder_role= new TypedQueryBuilder<BsUserRole>(BsUserRole.class,"e");
			tqBuilder_role.addRestriction(new TQRestriction( "userid", "in", Arrays.asList(excellentIndividual.getUser().getUserid())));
		    List<BsUserRole> role = queryByParas(tqBuilder_role);
		 
		    //按角色过滤
		 if(role.get(0).getRoleid() != 1){
			 //××××××××××××××××××××××××××××××××××
			 List<Integer> orgs = new ArrayList<Integer>();
			 for(BsUserOrg userorg : excellentIndividual.getUserorgs()){
				 orgs.add(userorg.getOrgid());
			 }
			 tqBuilder.addRestriction(new TQRestriction( "uploadorgid", "in",orgs));
			 //××××××××××××××××××××××××××××××××××
		 }
		}
		
		int startrecord = (currentpage - 1) * pagesize;
		List<ExcellentIndividual> list = commonDao.selectObjectForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = queryByParas(tqBuilder).size();
		page.setRows(list);
		page.setTotal(totalCount);
		return page;
	}
	public Map queryExcellentIndividualForPage(
			ExcellentIndividual excellentIndividual, int pagesize,
			int currentpage) {
		Map map = new HashMap();
		TypedQueryBuilder<ExcellentIndividual> tqBuilder = new TypedQueryBuilder<ExcellentIndividual>(ExcellentIndividual.class,"e");
		
		 tqBuilder.addOrder(new TQOrder("chooseTime",true));//按日期排序
		
		
			int startrecord = (currentpage - 1) * pagesize;
			List<ExcellentIndividual> list = commonDao.selectObjectForPage(tqBuilder,
					startrecord, pagesize);
			int totalCount = commonDao.queryByParas(tqBuilder).size();
			map.put("excellentindividuals", list);
			map.put(Constant.TOTALCOUNT, totalCount);
			map.put(Constant.TOTALPAGECOUNT,
					ComonUtil.computusTotalPage(totalCount, pagesize));
			return map;
	}

}
