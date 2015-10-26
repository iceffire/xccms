package edu.cqu.fly.xccms.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.EduVideo;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.service.EduVideoService;
import edu.cqu.fly.xccms.util.Constant;

public class EduVideoAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1875438934254326509L;

	
	@Resource(name="eduVideoService")
	EduVideoService eduVideoService;
	
	
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
			EduVideo eduVideo = new EduVideo();
			Pager pager = eduVideoService.queryEduVideoByPage(eduVideo,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询视频资源列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  String  queryEduVideoList(){
	    String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
	    currentPage=Integer.parseInt(page);
		prePage = currentPage-1;
		 nextPage = currentPage+1;
		try {
			EduVideo  eduVideo = new EduVideo();
	
			Map map =eduVideoService.queryEduVideoForPage(eduVideo, Integer.valueOf(rows),Integer.valueOf(page));
		//	if(ComonUtil.validateMapResult(map)){
				
			eduvideolist = (List<EduVideo>) map.get("eduvideolist");
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
		return "eduvideos_result";
	}
	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		EduVideo eduVideo = getEduVideo();
		try {
			eduVideo.setId(Integer.valueOf(id));
			eduVideoService.saveEntity(eduVideo);
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
		EduVideo eduVideo = getEduVideo();
	
		try {
			eduVideoService.saveEntity(eduVideo);
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
			eduVideoService.removeEntityById(Integer.valueOf(id),EduVideo.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public EduVideo  getEduVideo(){
		String type = request.getParameter("type"); 
		String des = request.getParameter("des");
		String savepath = request.getParameter("savepath"); 
		EduVideo  eduVideo=  new EduVideo();
		eduVideo.setType(type);
		eduVideo.setDes(des);
		eduVideo.setSavepath(savepath);
		eduVideo.setUploadtime(new Date());
		return eduVideo;
	}
}
