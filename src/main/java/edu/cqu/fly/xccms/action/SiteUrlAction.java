package edu.cqu.fly.xccms.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.SiteUrl;
import edu.cqu.fly.xccms.service.SiteUrlService;
import edu.cqu.fly.xccms.util.Constant;

public class SiteUrlAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2808569736858083991L;

	@Resource(name="siteUrlService")
	SiteUrlService siteUrlService;
	
	
	/**
	 * 
	 * @return
	 */
	public  String    index(){
		return "index";
	}
	/**
	 * 查询主页
	 */
	public  void query(){
		String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  

		try {
			SiteUrl siteUrl = new SiteUrl();
			Pager pager = siteUrlService.querySiteUrlByPage(siteUrl,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询超链接列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  String  queryEduVideoList(){
	    String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
	    currentPage=Integer.parseInt(page);
		prePage = currentPage-1;
		 nextPage = currentPage+1;
		try {
			SiteUrl  siteUrl = new SiteUrl();
	
			Map map =siteUrlService.querySiteUrlForPage(siteUrl, Integer.valueOf(rows),Integer.valueOf(page));
		//	if(ComonUtil.validateMapResult(map)){
				
			siteurllist = (List<SiteUrl>) map.get("siteurllist");
				totalCount = (Integer) map.get(Constant.TOTALCOUNT);
				totalPageCount =(Integer) map.get(Constant.TOTALPAGECOUNT);
				   totalpages = new ArrayList<Integer>();
					for(int i=Integer.valueOf(page);i<=totalPageCount;i++){
						totalpages.add(i);
					}
		//	}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "siteurls_result";
	}
	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		SiteUrl siteUrl = getSiteUrl();
		try {
			siteUrl.setId(Long.valueOf(id));
			siteUrlService.saveEntity(siteUrl);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(){
		boolean flag =false ;
		SiteUrl siteUrl = getSiteUrl();
	
		try {
			siteUrlService.saveEntity(siteUrl);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		try {
			siteUrlService.removeEntityById(Long.valueOf(id),SiteUrl.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SiteUrl  getSiteUrl(){
		String type = request.getParameter("type"); 
		String sitename = request.getParameter("sitename");
		String url = request.getParameter("url"); 
		SiteUrl  siteUrl=  new SiteUrl();
		siteUrl.setType(type);
		siteUrl.setSitename(sitename);
		siteUrl.setUrl(url);
		return siteUrl;
	}
}
