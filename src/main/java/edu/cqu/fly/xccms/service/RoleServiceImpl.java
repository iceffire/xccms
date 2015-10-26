package edu.cqu.fly.xccms.service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.dao.CommonDao;
import edu.cqu.fly.xccms.dao.RoleDao;
import edu.cqu.fly.xccms.pojo.BsMenu;
import edu.cqu.fly.xccms.pojo.BsRole;
import edu.cqu.fly.xccms.pojo.BsRoleResource;
import edu.cqu.fly.xccms.pojo.MenuAuthority;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.util.dao.TQRestriction;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("roleService")
@Transactional
public class RoleServiceImpl extends CommonServiceImpl implements RoleService{

	@SuppressWarnings("restriction")
	@Resource(name="roleDao")
	protected RoleDao roleDao;
	
	@Resource(name="commonDao")
	protected CommonDao commonDao;
	
	public Pager queryBsRoleByPage(BsRole bsRole, int pagesize,
			int currentpage) {
		Pager page = new Pager();
		TypedQueryBuilder<BsRole> tqBuilder = new TypedQueryBuilder<BsRole>(BsRole.class,"e");
		
		if (null != bsRole.getName()
				&& !bsRole.getName().equals("")) {
			tqBuilder.addRestriction(new TQRestriction( "name", "like", Arrays.asList("%" + bsRole.getName() + "%")));
		}
		int startrecord = (currentpage - 1) * pagesize;
		List<BsRole> list = roleDao.selectBsRoleForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = queryByParas(tqBuilder).size();
		page.setRows(list);
		page.setTotal(totalCount);
		return page;
	}
	public void updateBsRole(BsRole bsRole, String resourceid,String menuid) {
		saveEntity(bsRole);
		// 先删除
		TypedQueryBuilder<BsRoleResource> tqBuilder = new TypedQueryBuilder<BsRoleResource>(BsRoleResource.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "roleid", "in", Arrays.asList(bsRole.getId())));
		List<BsRoleResource> rolesources =  queryByParas(tqBuilder);
		for(BsRoleResource rolereource : rolesources){
			removeEntityById(rolereource.getId(),BsRoleResource.class);
		}
		// 先删除
				TypedQueryBuilder<MenuAuthority> tqBuilder2 = new TypedQueryBuilder<MenuAuthority>(MenuAuthority.class,"e");
				tqBuilder2.addRestriction(new TQRestriction( "roleid", "in", Arrays.asList(bsRole.getId())));
				List<MenuAuthority> menuAuthoritys =  queryByParas(tqBuilder2);
				for(MenuAuthority menuAuthority : menuAuthoritys){
					removeEntityById(menuAuthority.getId(),MenuAuthority.class);
				}
		// 后新增
		BsRoleResource bsRoleResource = new BsRoleResource();
		String[] resourceidarray = resourceid.split(",");
		for (int i = 0; i < resourceidarray.length; i++) {
			if (!resourceidarray[i].equals("")) {
				bsRoleResource.setResourceid(Integer
						.valueOf(resourceidarray[i]));
				bsRoleResource.setRoleid(bsRole.getId());
				addEntity(bsRoleResource);
			}
		}
	
		String[] menuidarray = menuid.split(",");
		Set<String> treeset = new TreeSet<String>();
		for(String s : menuidarray){
			treeset.add(s);
			
			while(true){
				String parentid = commonDao.readEntityById(Long.parseLong(s), BsMenu.class).getMenuparent()+"";
				if(!parentid.equals("")&&!parentid.equals("null")){
					treeset.add(parentid);
				}else{
					break;
				}
				s = parentid;
			}
			
		}
		for(String s : treeset){
			MenuAuthority menuAuthority = new MenuAuthority();
			if(!s.equals("")){
				menuAuthority.setMenuid(Long.parseLong(s));
				menuAuthority.setRoleid(bsRole.getId());
				addEntity(menuAuthority);
			}
		}
	}
	
	public void saveBsRole(BsRole bsRole, String resourceid,String menuid) {
		addEntity(bsRole);
		TypedQueryBuilder<BsRole> tqBuilder = new TypedQueryBuilder<BsRole>(BsRole.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "isactive", "in", Arrays.asList(bsRole.getIsactive())));
		tqBuilder.addRestriction(new TQRestriction( "name", "in", Arrays.asList(bsRole.getName())));
		List<BsRole> bsRoleparam = queryByParas(tqBuilder);
		BsRoleResource bsRoleResource = new BsRoleResource();
		String[] resourceidarray = resourceid.split(",");
		for (int i = 0; i < resourceidarray.length; i++) {
			if (!resourceidarray[i].equals("")) {
				bsRoleResource.setResourceid(Integer
						.valueOf(resourceidarray[i]));
				bsRoleResource.setRoleid(bsRole.getId());
				bsRoleResource.setRoleid(bsRoleparam.get(0).getId());
				addEntity(bsRoleResource);
			}
		}
		
		String[] menuidarray = menuid.split(",");
		Set<String> treeset = new TreeSet<String>();
		for(String s : menuidarray){
			treeset.add(s);
			
			while(true){
				String parentid = commonDao.readEntityById(Long.parseLong(s), BsMenu.class).getMenuparent()+"";
				if(!parentid.equals("")&&!parentid.equals("null")){
					treeset.add(parentid);
				}else{
					break;
				}
				s = parentid;
			}
			
		}
		for(String s : treeset){
			MenuAuthority menuAuthority = new MenuAuthority();
			if(!s.equals("")){
				menuAuthority.setMenuid(Long.parseLong(s));
				menuAuthority.setRoleid(bsRole.getId());
				addEntity(menuAuthority);
			}
		}
		
		
	}
	public List<BsRoleResource> queryBsRoleResourceByroleid(int roleid) {
		TypedQueryBuilder<BsRoleResource> tqBuilder = new TypedQueryBuilder<BsRoleResource>(BsRoleResource.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "roleid", "in", Arrays.asList(roleid)));
		return queryByParas(tqBuilder);
	}
	public List<MenuAuthority> queryMenuAuthorityByroleid(int roleid) {
		TypedQueryBuilder<MenuAuthority> tqBuilder = new TypedQueryBuilder<MenuAuthority>(MenuAuthority.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "roleid", "in", Arrays.asList(roleid)));
		return queryByParas(tqBuilder);
	}
	

}
