package edu.cqu.fly.xccms.action;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.NewsType;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.service.NewsTypeService;

public class NewsTypeAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1370868736966393914L;
	@Resource(name="newsTypeService")
	NewsTypeService newsTypeService;
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
	NewsType newsType = new NewsType();
			Pager pager = newsTypeService.queryNewsTypeByPage(newsType,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 更新新闻类别
	 */
	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		NewsType newsType = getNewsType();
		try {
			newsType.setId(Integer.valueOf(id));
			newsTypeService.saveEntity(newsType);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增新闻类别
	 */
	public void save(){
		boolean flag =false ;
		NewsType newsType = getNewsType();
	
		try {
			newsTypeService.saveEntity(newsType);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除新闻类别
	 */
	public void delete(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		try {
			newsTypeService.removeEntityById(Integer.valueOf(id),NewsType.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 得到新闻类别
	 * @return
	 */
	public NewsType  getNewsType(){
		String type = request.getParameter("type"); 
		String typecode = request.getParameter("typecode"); 
		NewsType  newsType=  new NewsType();
		newsType.setType(type);
		newsType.setTypecode(typecode);
		return newsType;
	}
}
