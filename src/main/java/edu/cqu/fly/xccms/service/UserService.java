package edu.cqu.fly.xccms.service;

import java.util.List;
import java.util.Map;

import edu.cqu.fly.xccms.pojo.BsUserOrg;
import edu.cqu.fly.xccms.pojo.BsUserRole;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.User;

public interface UserService extends CommonService{

public Map queryUsername(User user);
public Pager queryUserListByPage(User user, int pagesize,int currentpage);
public Map createUser(User user, String roleid,String orgid);
public Map updateUser(User user, String roleid,String orgid);
public List<BsUserRole> queryroleByuserid(String userid);
public List<BsUserOrg> queryorgByuserid(String userid);
public boolean queryUserByName(String username);
public void saveUserToOtherDb(User user);
public void updateUserToOtherDb(User user);
}
