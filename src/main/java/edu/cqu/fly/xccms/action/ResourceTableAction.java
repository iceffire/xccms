package edu.cqu.fly.xccms.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.ResourceTable;
import edu.cqu.fly.xccms.service.ResourceTableService;
import edu.cqu.fly.xccms.util.Constant;

public class ResourceTableAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4085530272353669830L;

	@Resource(name="resourceTableService")
	ResourceTableService resourceTableService;
	
	protected List<ResourceTable> resourcetables;
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
			ResourceTable resourceTable = new ResourceTable();
			Pager pager = resourceTableService.queryResourceTableByPage(resourceTable,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询常用表格列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  String  queryResourceTableList(){
	    String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
	    currentPage=Integer.parseInt(page);
		prePage = currentPage-1;
		 nextPage = currentPage+1;
		try {
			ResourceTable  resourceTable = new ResourceTable();
	
			Map map =resourceTableService.queryResourceTableForPage(resourceTable, Integer.valueOf(rows),Integer.valueOf(page));
		//	if(ComonUtil.validateMapResult(map)){
				
			resourcetables = (List<ResourceTable>) map.get("resourcetables");
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
		return "resourcetables_result";
	}
	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		ResourceTable resourceTable = getResourceTable();
		try {
			resourceTable.setId(Integer.valueOf(id));
			resourceTableService.saveEntity(resourceTable);
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
		ResourceTable resourceTable = getResourceTable();
	
		try {
			resourceTableService.saveEntity(resourceTable);
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
			resourceTableService.removeEntityById(Integer.valueOf(id),ResourceTable.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ResourceTable  getResourceTable(){
		String type = request.getParameter("type"); 
		String des = request.getParameter("des");
		String savepath = request.getParameter("savepath"); 
		ResourceTable  resourceTable=  new ResourceTable();
		resourceTable.setType(type);
		resourceTable.setDes(des);
		resourceTable.setSavepath(savepath);
		resourceTable.setUploadtime(new Date());
		return resourceTable;
	}
	public List<ResourceTable> getResourcetables() {
		return resourcetables;
	}
	public void setResourcetables(List<ResourceTable> resourcetables) {
		this.resourcetables = resourcetables;
	}
	
	
}
