package edu.cqu.fly.xccms.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.BsUserOrg;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.SciResearchPaper;
import edu.cqu.fly.xccms.pojo.SciResearchPaperQuery;
import edu.cqu.fly.xccms.pojo.User;
import edu.cqu.fly.xccms.service.OrgService;
import edu.cqu.fly.xccms.service.SciResearchPaperService;
import edu.cqu.fly.xccms.service.UserService;
import edu.cqu.fly.xccms.util.Constant;

public class SciResearchPaperAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5568818576607694716L;

	@Resource(name="sciResearchPaperService")
	SciResearchPaperService sciResearchPaperService;
	
	
	@Resource(name="orgService")
	OrgService orgService;
	
	@Resource(name="userService")
	UserService userService;
	
	protected SciResearchPaper sciresearchpaperdetail;
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
			SciResearchPaperQuery sciResearchPaper = new SciResearchPaperQuery();
			Map<String, Object> session =context.getSession();
			User sessionuser = (User) session.get("user");
			List<BsUserOrg> userorgs = userService.queryorgByuserid(sessionuser.getUserid());
			sciResearchPaper.setUser(sessionuser);
			sciResearchPaper.setUserorgs(userorgs);
			Pager pager = sciResearchPaperService.querySciResearchPaperByPage(sciResearchPaper,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询科研论文列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  String  querySciResearchPaperList(){
	    String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
	    currentPage=Integer.parseInt(page);
		prePage = currentPage-1;
		 nextPage = currentPage+1;
		try {
			SciResearchPaper  sciResearchPaper = new SciResearchPaper();
	
			Map map =sciResearchPaperService.querySciResearchPaperForPage(sciResearchPaper, Integer.valueOf(rows),Integer.valueOf(page));
		//	if(ComonUtil.validateMapResult(map)){
				
			sciresearchpaperlist = (List<SciResearchPaper>) map.get("paperlist");
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
		return "sciresearchpapers_result";
	}
	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		SciResearchPaper sciResearchPaper = getSciResearchPaper();
		try {
			sciResearchPaper.setId(Long.valueOf(id));
			sciResearchPaperService.saveEntity(sciResearchPaper);
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
		SciResearchPaper sciResearchPaper = getSciResearchPaper();
	
		try {
			sciResearchPaperService.saveEntity(sciResearchPaper);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String querySciResearchPaperdetail(){
		
		String id = request.getParameter("id");
		sciresearchpaperdetail = sciResearchPaperService.findEntityById(Long.parseLong(id), SciResearchPaper.class);
		return "sciresearchpaper_detail";
	}
	public void delete(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		try {
			sciResearchPaperService.removeEntityById(Long.valueOf(id),SciResearchPaper.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public SciResearchPaper  getSciResearchPaper(){
		Map<String, Object> session =context.getSession();
		User sessionuser = (User) session.get("user");
		String title = request.getParameter("title"); 
		String summary = request.getParameter("summary");
		String savepath = request.getParameter("savepath"); 
		String url = request.getParameter("url"); 
		SciResearchPaper  sciResearchPaper=  new SciResearchPaper();
		sciResearchPaper.setTitle(title);
		sciResearchPaper.setSummary(summary);
		sciResearchPaper.setSavepath(savepath);
		sciResearchPaper.setUploadtime(new Date());
		sciResearchPaper.setUrl(url);
		sciResearchPaper.setUploaduserid(sessionuser.getUserid());
		sciResearchPaper.setUploadorgid(orgService.getOrgCodeByUserId(sessionuser.getUserid()).longValue());
		return sciResearchPaper;
	}
	public SciResearchPaper getSciresearchpaperdetail() {
		return sciresearchpaperdetail;
	}
	public void setSciresearchpaperdetail(SciResearchPaper sciresearchpaperdetail) {
		this.sciresearchpaperdetail = sciresearchpaperdetail;
	}
	
	
}
