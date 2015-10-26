package edu.cqu.fly.xccms.service;

import edu.cqu.fly.xccms.pojo.BsMenu;
import edu.cqu.fly.xccms.pojo.User;

public interface MenuService extends CommonService{

	public String getMenuJsonStr(User user);
	public void updateOrAddMenu(BsMenu bsMenu);
	public void deleteMenu(long id) ;
}
