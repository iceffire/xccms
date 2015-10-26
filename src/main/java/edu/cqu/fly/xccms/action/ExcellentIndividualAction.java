package edu.cqu.fly.xccms.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.BsUserOrg;
import edu.cqu.fly.xccms.pojo.ExcellentIndividual;
import edu.cqu.fly.xccms.pojo.ExcellentIndividualQuery;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.User;
import edu.cqu.fly.xccms.service.ExcellentIndividualService;
import edu.cqu.fly.xccms.service.OrgService;
import edu.cqu.fly.xccms.service.UserService;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;
import edu.cqu.fly.xccms.util.DateUtil;

public class ExcellentIndividualAction extends BaseAction{
	private static final Logger log = Logger.getLogger(ExcellentIndividualAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 4196723006769538668L;

	@Resource(name="excellentIndividualService")
	ExcellentIndividualService excellentIndividualService;
	
	
	@Resource(name="orgService")
	OrgService orgService;
	
	@Resource(name="userService")
	UserService userService;
	
	protected ExcellentIndividual excellentdetail;
	
	protected List<ExcellentIndividual> excellentlists;
	/**
	 * 
	 * @return
	 */
	public  String    index(){
		return "index";
	}
	public String excellentdetail(){
		String id = request.getParameter("id");
		try {
			excellentdetail =	excellentIndividualService.findEntityById(Long.valueOf(id), ExcellentIndividual.class);
			ExcellentIndividual excellent = excellentIndividualService.findEntityById(Long.valueOf(id), ExcellentIndividual.class);
			excellent.setScannum(excellent.getScannum()+1);
			excellentIndividualService.saveEntity(excellent);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "excellent_detail";
	}
	/**
	 * 查询先进个人列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  String  queryExcellentList(){
	    String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
	    currentPage=Integer.parseInt(page);
		prePage = currentPage-1;
		 nextPage = currentPage+1;
		try {
			ExcellentIndividual  excellentIndividual = new ExcellentIndividual();
	
			Map map =excellentIndividualService.queryExcellentIndividualForPage(excellentIndividual, Integer.valueOf(rows),Integer.valueOf(page));
		//	if(ComonUtil.validateMapResult(map)){
				
				excellentlists = (List<ExcellentIndividual>) map.get("excellentindividuals");
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
		return "excellents_result";
	}
	/**
	 * 查询主页
	 */
	public  void query(){
		String namequery =request.getParameter("namequery"); 
		if(namequery == null ||namequery.equals("")){
			namequery="";
		}
		String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  

		try {
			Map<String, Object> session =context.getSession();
			User sessionuser = (User) session.get("user");
			List<BsUserOrg> userorgs = userService.queryorgByuserid(sessionuser.getUserid());
			ExcellentIndividualQuery excellentIndividual = new ExcellentIndividualQuery();
			excellentIndividual.setName(namequery);
			excellentIndividual.setUserorgs(userorgs);
			excellentIndividual.setUser(sessionuser);
			Pager pager = excellentIndividualService.queryExcellentIndividualByPage(excellentIndividual,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		ExcellentIndividual excellentIndividual = getExcellentIndividual();
		try {
			excellentIndividual.setId(Long.valueOf(id));
			excellentIndividualService.saveEntity(excellentIndividual);
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
		ExcellentIndividual excellentIndividual = getExcellentIndividual();
	
		try {
			excellentIndividual.setScannum((long)0);
			excellentIndividualService.saveEntity(excellentIndividual);
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
			excellentIndividualService.removeEntityById(Long.valueOf(id),ExcellentIndividual.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ExcellentIndividual  getExcellentIndividual(){
		Map<String, Object> session =context.getSession();
		User sessionuser = (User) session.get("user");
		String name = request.getParameter("name");
		String des = request.getParameter("des");
		String chooseTime = request.getParameter("chooseTime");
		String picurl = request.getParameter("picurl");
		String width = request.getParameter("picwidth");
		String height = request.getParameter("picheight");
		if(!width.equals("")&&width!=null&&!height.equals("")&&height!=null){
			try{
				ComonUtil.changeImageSize(new File("xccms/image/"+picurl), Integer.parseInt(width), Integer.parseInt(height));
			}catch(Exception e){
				log.error("修改图片大小异常");
			}
			
		}
		ExcellentIndividual  excellentIndividual=  new ExcellentIndividual();
	    excellentIndividual.setName(name);
	    excellentIndividual.setDes(des);
	    excellentIndividual.setPicurl(picurl);
	    excellentIndividual.setChooseTime(DateUtil.strToDate(chooseTime));
	    excellentIndividual.setUploadorgid(orgService.getOrgCodeByUserId(sessionuser.getUserid()));
		return excellentIndividual;
	}
	public ExcellentIndividual getExcellentdetail() {
		return excellentdetail;
	}
	public void setExcellentdetail(ExcellentIndividual excellentdetail) {
		this.excellentdetail = excellentdetail;
	}
	public List<ExcellentIndividual> getExcellentlists() {
		return excellentlists;
	}
	public void setExcellentlists(List<ExcellentIndividual> excellentlists) {
		this.excellentlists = excellentlists;
	}
	
	
}
