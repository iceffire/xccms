package edu.cqu.fly.xccms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.dao.CommonDao;
import edu.cqu.fly.xccms.pojo.OfficeSoftware;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;
import edu.cqu.fly.xccms.util.dao.TQOrder;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("officeSoftwareService")
@Transactional
public class OfficeSoftwareServiceImpl  extends CommonServiceImpl implements OfficeSoftwareService{

	@SuppressWarnings("restriction")
	@Resource(name="commonDao")
	protected CommonDao commonDao;
	public Pager queryOfficeSoftwareByPage(OfficeSoftware officeSoftware,
			int pagesize, int currentpage) {
		Pager page = new Pager();
		TypedQueryBuilder<OfficeSoftware> tqBuilder = new TypedQueryBuilder<OfficeSoftware>(OfficeSoftware.class,"e");
		
		int startrecord = (currentpage - 1) * pagesize;
		List<OfficeSoftware> list = commonDao.selectObjectForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = queryByParas(tqBuilder).size();
		page.setRows(list);
		page.setTotal(totalCount);
		return page;
	}
	public Map queryOfficeSoftwareForPage(OfficeSoftware officeSoftware,
			int pagesize, int currentpage) {
		Map map = new HashMap();
		TypedQueryBuilder<OfficeSoftware> tqBuilder = new TypedQueryBuilder<OfficeSoftware>(OfficeSoftware.class,"e");
		
		 tqBuilder.addOrder(new TQOrder("uploadtime",true));//按日期排序
		
		
			int startrecord = (currentpage - 1) * pagesize;
			List<OfficeSoftware> list = commonDao.selectObjectForPage(tqBuilder,
					startrecord, pagesize);
			int totalCount = commonDao.queryByParas(tqBuilder).size();
			map.put("officesoftwares", list);
			map.put(Constant.TOTALCOUNT, totalCount);
			map.put(Constant.TOTALPAGECOUNT,
					ComonUtil.computusTotalPage(totalCount, pagesize));
			return map;
	}

}
