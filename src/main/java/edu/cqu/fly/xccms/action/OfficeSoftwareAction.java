package edu.cqu.fly.xccms.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.OfficeSoftware;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.service.OfficeSoftwareService;
import edu.cqu.fly.xccms.util.Constant;

public class OfficeSoftwareAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1008020878287329850L;

	@Resource(name="officeSoftwareService")
	OfficeSoftwareService officeSoftwareService;
	
	protected List<OfficeSoftware> officesoftwares;
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
			OfficeSoftware officeSoftware = new OfficeSoftware();
			Pager pager = officeSoftwareService.queryOfficeSoftwareByPage(officeSoftware,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询工作软件列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  String  queryOfficeSoftwareList(){
	    String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
	    currentPage=Integer.parseInt(page);
		prePage = currentPage-1;
		 nextPage = currentPage+1;
		try {
			OfficeSoftware  officeSoftware = new OfficeSoftware();
	
			Map map =officeSoftwareService.queryOfficeSoftwareForPage(officeSoftware, Integer.valueOf(rows),Integer.valueOf(page));
		//	if(ComonUtil.validateMapResult(map)){
				
			officesoftwares = (List<OfficeSoftware>) map.get("officesoftwares");
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
		return "officesoftwares_result";
	}
	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		OfficeSoftware officeSoftware = getOfficeSoftware();
		try {
			officeSoftware.setId(Integer.valueOf(id));
			officeSoftwareService.saveEntity(officeSoftware);
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
		OfficeSoftware officeSoftware = getOfficeSoftware();
	
		try {
			officeSoftwareService.saveEntity(officeSoftware);
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
			officeSoftwareService.removeEntityById(Integer.valueOf(id),OfficeSoftware.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public OfficeSoftware  getOfficeSoftware(){
		String type = request.getParameter("type"); 
		String des = request.getParameter("des");
		String savepath = request.getParameter("savepath"); 
		OfficeSoftware  officeSoftware=  new OfficeSoftware();
		officeSoftware.setType(type);
		officeSoftware.setDes(des);
		officeSoftware.setSavepath(savepath);
		officeSoftware.setUploadtime(new Date());
		return officeSoftware;
	}
	public List<OfficeSoftware> getOfficesoftwares() {
		return officesoftwares;
	}
	public void setOfficesoftwares(List<OfficeSoftware> officesoftwares) {
		this.officesoftwares = officesoftwares;
	}

	
}
