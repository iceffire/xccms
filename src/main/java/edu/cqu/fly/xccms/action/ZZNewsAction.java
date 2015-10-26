package edu.cqu.fly.xccms.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.ZZNews;
import edu.cqu.fly.xccms.service.ZZNewsService;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;

public class ZZNewsAction extends BaseAction{
	private static final Logger log = Logger.getLogger(ZZNewsAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource(name="zzNewsService")
	ZZNewsService zzNewsService;
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
			ZZNews zzNews = new ZZNews();
			Pager pager = zzNewsService.queryZZNewsByPage(zzNews,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询先进个人列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  String  queryZZNewsList(){
	    String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
	    currentPage=Integer.parseInt(page);
		prePage = currentPage-1;
		 nextPage = currentPage+1;
		try {
			ZZNews  zzNews = new ZZNews();
	
			Map map =zzNewsService.queryZZNewsForPage(zzNews, Integer.valueOf(rows),Integer.valueOf(page));
		//	if(ComonUtil.validateMapResult(map)){
				
			zongzhuangnewslist = (List<ZZNews>) map.get("zznewslist");
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
		return "zznews_result";
	}
	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		ZZNews zzNews = getZZNews();
		try {
			zzNews.setId(Long.valueOf(id));
			zzNewsService.saveEntity(zzNews);
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
		ZZNews homeBigPicNews = getZZNews();
	
		try {
			zzNewsService.saveEntity(homeBigPicNews);
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
			zzNewsService.removeEntityById(Long.valueOf(id),ZZNews.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ZZNews  getZZNews(){
		String title = request.getParameter("title"); 
		String newsurl = request.getParameter("newsurl"); 
		String picurl = request.getParameter("picurl");
		String width = request.getParameter("picwidth");
		String height = request.getParameter("picheight");
		ZZNews  zzNews=  new ZZNews();
		zzNews.setTitle(title);
		zzNews.setNewsurl(newsurl);
		zzNews.setPicurl(picurl);
		zzNews.setCreatetime(new Date());
		if(!width.equals("")&&width!=null&&!height.equals("")&&height!=null){
			try{
				ComonUtil.changeImageSize(new File("xccms/image/"+picurl), Integer.parseInt(width), Integer.parseInt(height));
			}catch(Exception e){
				log.error("修改图片大小异常");
			}
			
		}
		return zzNews;
	}
}
