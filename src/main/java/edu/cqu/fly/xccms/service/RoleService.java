package edu.cqu.fly.xccms.service;

import java.util.List;

import edu.cqu.fly.xccms.pojo.BsRole;
import edu.cqu.fly.xccms.pojo.BsRoleResource;
import edu.cqu.fly.xccms.pojo.MenuAuthority;
import edu.cqu.fly.xccms.pojo.Pager;


public interface RoleService extends CommonService{
	public Pager  queryBsRoleByPage(BsRole bsRole, int pagesize,int currentpage);
	public void updateBsRole(BsRole bsRole, String resourceid,String menuid);
	public void saveBsRole(BsRole bsRole, String resourceid,String menuid);
	public List<BsRoleResource> queryBsRoleResourceByroleid(int roleid);
	public List<MenuAuthority>  queryMenuAuthorityByroleid(int roleid);
}
