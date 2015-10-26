package edu.cqu.fly.xccms.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.BsRole;
import edu.cqu.fly.xccms.pojo.BsRoleResource;
import edu.cqu.fly.xccms.pojo.MenuAuthority;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.service.RoleService;

public class RoleAction extends BaseAction{
	Logger log = Logger.getLogger(this.getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 6463129488374629381L;

	@Resource(name="roleService")
	RoleService roleService;
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
		String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
		String roleNamequery = request.getParameter("roleNamequery"); 

		try {
			BsRole bsRole  = new BsRole();
			bsRole.setName(roleNamequery);
			Pager pager = roleService.queryBsRoleByPage(bsRole,Integer.valueOf(rows),Integer.valueOf(page));
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 更新角色
	 */
	public  void  update(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		String resourceid = request.getParameter("resourceid"); 
		String menuid = request.getParameter("menuid");
		BsRole role = getBsRole();
		try {
			role.setId(Integer.valueOf(id));
			roleService.updateBsRole(role,resourceid,menuid);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 新增角色
	 */
	public void save(){
		boolean flag =false ;
		BsRole role = getBsRole();
		String resourceid = request.getParameter("resourceid"); 
		String menuid = request.getParameter("menuid");
		try {
			roleService.saveBsRole(role,resourceid,menuid);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除角色
	 */
	public void delete(){
		boolean flag =false ;
		String id = request.getParameter("id"); 
		try {
			roleService.removeEntityById(Integer.valueOf(id),BsRole.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 得到角色
	 * @return
	 */
	public BsRole  getBsRole(){
		String name = request.getParameter("name"); 
		String isactive = request.getParameter("isactive"); 
		BsRole  role=  new BsRole();
		role.setIsactive(isactive);
		role.setName(name);
		return role;
	}
	/**
	 * 删除角色资源
	 */
	public void queryresourceByroleid(){
		String roleid = request.getParameter("roleid"); 
		try {
			List<BsRoleResource>  list =roleService.queryBsRoleResourceByroleid(Integer.valueOf(roleid));
			String json ="";
			if(null!=list&&list.size()>0){
				for(BsRoleResource row:list){
					json = json+row.getResourceid()+",";
				}
				json = json.substring(0, json.length()-1);
				log.info(json);
			}
			super.reponseWriter(json);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void querymenuByroleid(){
		String roleid = request.getParameter("roleid"); 
		try {
			List<MenuAuthority>  list =roleService.queryMenuAuthorityByroleid(Integer.valueOf(roleid));
			String json ="";
			if(null!=list&&list.size()>0){
				for(MenuAuthority row:list){
					json = json+row.getMenuid()+",";
				}
				json = json.substring(0, json.length()-1);
				log.info(json);
			}
			super.reponseWriter(json);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
