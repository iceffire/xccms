package edu.cqu.fly.xccms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.BsMenu;
import edu.cqu.fly.xccms.pojo.BsUserRole;
import edu.cqu.fly.xccms.pojo.MenuAuthority;
import edu.cqu.fly.xccms.pojo.MenuNode;
import edu.cqu.fly.xccms.pojo.User;
import edu.cqu.fly.xccms.util.dao.TQRestriction;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("menuService")
@Transactional
public class MenuServiceImpl extends CommonServiceImpl implements MenuService {

	public String getMenuJsonStr(User user) {
		StringBuilder sb = new StringBuilder();
		sb.append("");
		List<BsMenu> dataList = null;
			TypedQueryBuilder<BsUserRole> tquserrole = new TypedQueryBuilder<BsUserRole>(BsUserRole.class,"e");
			tquserrole.addRestriction(new TQRestriction( "userid", "in", Arrays.asList(user.getUserid()+"")));
			List<BsUserRole> roles = queryByParas(tquserrole);
		    List<Integer> rolesidlist = new ArrayList<Integer>();
		    for(BsUserRole userrole : roles){
		    	rolesidlist.add(userrole.getRoleid());
		    }
			TypedQueryBuilder<MenuAuthority> tqmenuauthority = new TypedQueryBuilder<MenuAuthority>(MenuAuthority.class,"e");
			tqmenuauthority.addRestriction(new TQRestriction( "roleid", "in", rolesidlist));
			List<MenuAuthority> menuAuthoritys = queryByParas(tqmenuauthority);
			List<Long>  menuids = new ArrayList<Long>();
			for(MenuAuthority menuAuthority : menuAuthoritys){
				menuids.add(menuAuthority.getMenuid());
			}
			TypedQueryBuilder<BsMenu> tqBuilder = new TypedQueryBuilder<BsMenu>(BsMenu.class,"e");
			tqBuilder.addRestriction(new TQRestriction( "id", "in", menuids));
			dataList =queryByParas(tqBuilder);
		// 节点列表（散列表，用于临时存储节点对象）
				HashMap<Integer, MenuNode> nodeList = new HashMap<Integer, MenuNode>();
				// 根节点
				MenuNode root = null;
				// 根据结果集构造节点列表（存入散列表）
				for (BsMenu dataRecord : dataList) {
					MenuNode node = new MenuNode();
					node.setId(dataRecord.getId());
					BeanUtils.copyProperties(dataRecord, node);
					nodeList.put(node.getId().intValue(), node);
				}
				// 构造无序的多叉树
				Set entrySet = nodeList.entrySet();
				for (Iterator it = entrySet.iterator(); it.hasNext();) {
					MenuNode node = (MenuNode) ((Map.Entry) it.next()).getValue();
					if (node.getMenuparent() == null || node.getMenuparent().equals("")) {
						root = node;
					} else {
						((MenuNode) nodeList.get(node.getMenuparent())).getChildren()
								.add(node);
					}
				}
				String json = JSON.toJSONString(root);
				return "[" + json + "]";
	}
	/**
	 * 更新菜单或者添加菜单
	 */
	public void updateOrAddMenu(BsMenu bsMenu) {
		if (null == bsMenu.getId() || bsMenu.getId() == 0) {
			bsMenu.setId(null);
			addEntity(bsMenu);
		} else {
			saveEntity(bsMenu);
		}
	}

	/**
	 * 递归删除菜单
	 */
	public void deleteMenu(long id) {
		TypedQueryBuilder<BsMenu> tqBuilder = new TypedQueryBuilder<BsMenu>(BsMenu.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "menuparent", "in", Arrays.asList(Integer.parseInt(id+""))));
		List<BsMenu> menulist = queryByParas(tqBuilder);
		for(BsMenu menu : menulist){
			removeEntityById(menu.getId(),BsMenu.class);
		}
		removeEntityById(Long.parseLong(id+""),BsMenu.class);
	}

}
