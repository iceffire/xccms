package edu.cqu.fly.xccms.action;


import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.BsResource;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.service.ResourceService;

public class ResourceAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4891346059234522817L;
	
	@Resource(name="resourceService")
	ResourceService resourceService;
	/**
	 * 主页
	 * @return
	 */
	public  String    index(){
		return "index";
	}
	/**
	 * 查询主页
	 */
	public  void query(){
		String resourceurlquery =request.getParameter("resourceurlquery"); 
		String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
		try {
			BsResource bsBsResource  = new BsResource();
			bsBsResource.setResourceUrl(resourceurlquery);
			Pager pager = resourceService.queryBsResourceByPage(bsBsResource,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 更新资源
	 */
	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		BsResource resource = getBsResource();
		try {
			resource.setId(Integer.valueOf(id));
			resourceService.saveEntity(resource);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增资源
	 */
	public void save(){
		boolean flag =false ;
		BsResource resource = getBsResource();
		try {
			resourceService.saveEntity(resource);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除资源
	 */
	public void delete(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		try {
			resourceService.removeEntityById(Integer.valueOf(id), BsResource.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 得到资源
	 * @return
	 */
	public BsResource  getBsResource(){
		String resourceUrl = request.getParameter("resourceUrl"); 
		String resouceName = request.getParameter("resouceName"); 
		String resourceType = request.getParameter("resourceType"); 
		String menuid = request.getParameter("menuid"); 
		BsResource  resource=  new BsResource();
		resource.setMenuid(Long.valueOf(menuid));
		resource.setResouceName(resouceName);
		resource.setResourceType(resourceType);
		resource.setResourceUrl(resourceUrl);
		return resource;
	}
}
