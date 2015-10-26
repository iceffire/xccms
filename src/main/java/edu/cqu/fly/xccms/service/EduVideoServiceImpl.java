package edu.cqu.fly.xccms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.dao.CommonDao;
import edu.cqu.fly.xccms.pojo.EduVideo;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;
import edu.cqu.fly.xccms.util.dao.TQOrder;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("eduVideoService")
@Transactional
public class EduVideoServiceImpl extends CommonServiceImpl implements EduVideoService{

	@SuppressWarnings("restriction")
	@Resource(name="commonDao")
	protected CommonDao commonDao;
	public Pager queryEduVideoByPage(EduVideo eduVideo, int pagesize,
			int currentpage) {
		Pager page = new Pager();
		TypedQueryBuilder<EduVideo> tqBuilder = new TypedQueryBuilder<EduVideo>(EduVideo.class,"e");
		
		int startrecord = (currentpage - 1) * pagesize;
		List<EduVideo> list = commonDao.selectObjectForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = queryByParas(tqBuilder).size();
		page.setRows(list);
		page.setTotal(totalCount);
		return page;
	}
	public Map queryEduVideoForPage(EduVideo eduVideo, int pagesize,
			int currentpage) {
		Map map = new HashMap();
		TypedQueryBuilder<EduVideo> tqBuilder = new TypedQueryBuilder<EduVideo>(EduVideo.class,"e");
		
		 tqBuilder.addOrder(new TQOrder("uploadtime",true));//按类型排序
		
		
			int startrecord = (currentpage - 1) * pagesize;
			List<EduVideo> list = commonDao.selectObjectForPage(tqBuilder,
					startrecord, pagesize);
			int totalCount = commonDao.queryByParas(tqBuilder).size();
			map.put("eduvideolist", list);
			map.put(Constant.TOTALCOUNT, totalCount);
			map.put(Constant.TOTALPAGECOUNT,
					ComonUtil.computusTotalPage(totalCount, pagesize));
			return map;
	}

}
