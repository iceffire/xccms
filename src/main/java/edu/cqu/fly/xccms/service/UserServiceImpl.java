package edu.cqu.fly.xccms.service;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.druid.pool.DruidDataSource;

import edu.cqu.fly.xccms.dao.CommonDao;
import edu.cqu.fly.xccms.dao.UserDao;
import edu.cqu.fly.xccms.pojo.BsUserOrg;
import edu.cqu.fly.xccms.pojo.BsUserRole;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.User;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;
import edu.cqu.fly.xccms.util.dao.TQRestriction;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;
@Service("userService")
@Transactional
public class UserServiceImpl extends CommonServiceImpl implements UserService{
	Logger log = Logger.getLogger(UserServiceImpl.class);
	@SuppressWarnings("restriction")
	@Resource(name="userDao")
	protected UserDao userDao;
	
	@Resource(name="commonDao")
	protected CommonDao commonDao;
	
	@Resource(name="dataSource_yun")
	DruidDataSource dataSource;
	public Map queryUsername(User user) {
		Map map = new HashMap();
		if (ComonUtil.validateEmptyForString(user.getUsername())) {
			map.put(Constant.ACTION_RESULT, Constant.RESULT_FAIL);
			try {
				throw new Exception("用户名字为空!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
			TypedQueryBuilder<User> tqBuilder = new TypedQueryBuilder<User>(User.class,"e");
			tqBuilder.addRestriction(new TQRestriction( "username", "in", Arrays.asList(user.getUsername())));
			tqBuilder.addRestriction(new TQRestriction( "password", "in", Arrays.asList(user.getPassword())));
			if(null != user.getIsadmin()&&!user.getIsadmin().equals("")){
				tqBuilder.addRestriction(new TQRestriction( "isadmin", "in", Arrays.asList(user.getIsadmin())));
			}
			
			List<User> list = queryByParas(tqBuilder);
			if (null != list && list.size() == 1) {
				if (log.isDebugEnabled()) {
					log.debug("数据库里面存在该用户，校验成功");
				}
				map.put(Constant.ACTION_RESULT, Constant.RESULT_SUCCESS);
				map.put(Constant.USER, list.get(0));
			} else {
				if (log.isDebugEnabled()) {
					log.debug("数据库里面不存在该用户，校验失败");
				}
				map.put(Constant.ACTION_RESULT, Constant.RESULT_FAIL);
			}
		}
		return map;
	}
	public Pager queryUserListByPage(User user, int pagesize, int currentpage) {
		Pager page = new Pager();
		Integer orgcode = 0;
		List<String> userids = null;
		if(!user.getUsername().equals("admin")){
			TypedQueryBuilder<BsUserOrg> tqBuilder_org = new TypedQueryBuilder<BsUserOrg>(BsUserOrg.class,"e");
			tqBuilder_org.addRestriction(new TQRestriction( "userid", "in", Arrays.asList(user.getUserid())));
			List<BsUserOrg> orglists = queryByParas(tqBuilder_org);
			if(orglists.size()!=0){
				orgcode = orglists.get(0).getOrgid();
				TypedQueryBuilder<BsUserOrg> tqBuilder_org_t= new TypedQueryBuilder<BsUserOrg>(BsUserOrg.class,"e");
				tqBuilder_org_t.addRestriction(new TQRestriction( "orgid", "in", Arrays.asList(orgcode)));
				List<BsUserOrg> orglists_t = queryByParas(tqBuilder_org_t);
				if(orglists_t.size()!=0){
					userids = new ArrayList<String>();
					for(BsUserOrg org : orglists_t){
						userids.add(org.getUserid());
					}
				}
			}
			
		}
	
		
		TypedQueryBuilder<User> tqBuilder = new TypedQueryBuilder<User>(User.class,"e");
		int startrecord = (currentpage - 1) * pagesize;
		
		
		if(userids !=null ){
			tqBuilder.addRestriction(new TQRestriction( "userid", "in", userids));
		}
		
		
		List<User> list = userDao.selectUserForPage(tqBuilder,
				startrecord, pagesize);
		int totalCount = queryByParas(tqBuilder).size();
		page.setRows(list);
		page.setTotal(totalCount);
		return page;
	}
	public Map createUser(User user, String roleid,String orgid) {
		if(orgid ==null){
			orgid="";
		}
		Map map = new HashMap();
		if (null == user) {
			map.put(Constant.ACTION_RESULT, Constant.RESULT_FAIL);
			try {
				throw new Exception("用户编号为空!");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			TypedQueryBuilder<User> tqBuilder = new TypedQueryBuilder<User>(User.class,"e");
	//		tqBuilder.addRestriction(new TQRestriction( "email", "in", Arrays.asList(user.getEmail())));
			//List<User> list = queryByParas(tqBuilder);
			
			try{
				userDao.saveEntity(user);
				
				List<User> userlist = queryByParas(tqBuilder);
				String[] roleidarray = roleid.split(",");
				for (int i = 0; i < roleidarray.length; i++) {
					if (!roleidarray[i].equals("")) {
						BsUserRole bsUserRole = new BsUserRole();
						bsUserRole.setRoleid(Integer.valueOf(roleidarray[i]));
						bsUserRole.setUserid(userlist.get(0).getUserid());
						commonDao.saveEntity(bsUserRole);
					}
				}
				String[] orgidarray = orgid.split(",");
				for (int i = 0; i < orgidarray.length; i++) {
					if (!orgidarray[i].equals("")) {
						BsUserOrg bsUserOrg = new BsUserOrg();
						bsUserOrg.setOrgid(Integer.valueOf(orgidarray[i]));
						bsUserOrg.setUserid(userlist.get(0).getUserid());
						commonDao.saveEntity(bsUserOrg);
					}
				}
				map.put(Constant.ACTION_RESULT, Constant.RESULT_SUCCESS);
			}catch (Exception e){
				map.put(Constant.ACTION_RESULT, Constant.RESULT_FAIL);
					try {
						throw new Exception("注册用户失败!");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					e.printStackTrace();
			}
	

		}
		return map;
	}
	public Map updateUser(User user, String roleid,String orgid) {
		if(orgid ==null){
			orgid="";
		}
		Map map = new HashMap();
		
		
		User res = saveEntity(user);
		// 删除
		TypedQueryBuilder<BsUserRole> tqBuilder = new TypedQueryBuilder<BsUserRole>(BsUserRole.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "userid", "in", Arrays.asList(user.getUserid())));
		List<BsUserRole> userroles = queryByParas(tqBuilder);
		for(BsUserRole userrole : userroles){
			removeEntityById(userrole.getId(),BsUserRole.class);
		}
		TypedQueryBuilder<BsUserOrg> tqBuildero = new TypedQueryBuilder<BsUserOrg>(BsUserOrg.class,"e");
		tqBuildero.addRestriction(new TQRestriction( "userid", "in", Arrays.asList(user.getUserid())));
		List<BsUserOrg> userorgs = queryByParas(tqBuildero);
		for(BsUserOrg userorg : userorgs){
			removeEntityById(userorg.getId(),BsUserOrg.class);
		}
		// 添加
		String[] roleidarray = roleid.split(",");
		for (int i = 0; i < roleidarray.length; i++) {
			if (!roleidarray[i].equals("")) {
				BsUserRole bsUserRole = new BsUserRole();
				bsUserRole.setRoleid(Integer.valueOf(roleidarray[i]));
				bsUserRole.setUserid(user.getUserid());
				addEntity(bsUserRole);
			}
		}
		
		String[] orgidarray = orgid.split(",");
		for (int i = 0; i < orgidarray.length; i++) {
			if (!orgidarray[i].equals("")) {
				BsUserOrg bsUserOrg = new BsUserOrg();
				bsUserOrg.setOrgid(Integer.valueOf(orgidarray[i]));
				bsUserOrg.setUserid(user.getUserid());
				addEntity(bsUserOrg);
			}
		}
		if (res != null) {
			map.put(Constant.ACTION_RESULT, Constant.RESULT_SUCCESS);
			if (log.isDebugEnabled()) {
				log.debug("修改用户信息成功!");
			}
		}  else {
			map.put(Constant.ACTION_RESULT, Constant.RESULT_FAIL);
			if (log.isDebugEnabled()) {
				log.debug("修改用户信息失败");
			}
		}
		return map;
	}
	public List<BsUserRole> queryroleByuserid(String userid) {
		TypedQueryBuilder<BsUserRole> tqBuilder = new TypedQueryBuilder<BsUserRole>(BsUserRole.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "userid", "in", Arrays.asList(userid)));
		return queryByParas(tqBuilder);
	}
	public List<BsUserOrg> queryorgByuserid(String userid) {
		TypedQueryBuilder<BsUserOrg> tqBuilder = new TypedQueryBuilder<BsUserOrg>(BsUserOrg.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "userid", "in", Arrays.asList(userid)));
		return queryByParas(tqBuilder);
	}
	public boolean queryUserByName(String username) {
		TypedQueryBuilder<User> tqBuilder = new TypedQueryBuilder<User>(User.class,"e");
		tqBuilder.addRestriction(new TQRestriction( "username", "in", Arrays.asList(username)));
		List<User> list = queryByParas(tqBuilder);
		if(list.size()>0){
			return false;
		}else{
			return true;
		}
	}
	public void saveUserToOtherDb(User user) {
		try{
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			int isadmin = 0;
			if(user.getIsadmin().equals("yes")){
				isadmin = 1;
			}
			String sql = "insert into user(`account`,`passwords`,`isadmin`)  values('"+user.getUsername()+"','"+user.getPassword()+"',"+isadmin+")";
			stmt.execute(sql);
		}catch (Exception e){
			
		}
		
		
	}
	public void updateUserToOtherDb(User user) {
		try{
			Connection conn = dataSource.getConnection();
			Statement stmt = conn.createStatement();
			int isadmin = 0;
			if(user.getIsadmin().equals("yes")){
				isadmin = 1;
			}
			String sql = "update  user set account='"+user.getUsername()+"',passwords='"+user.getPassword()+"',isadmin='"+isadmin+"' where account='"+user.getUsername()+"'";
			stmt.execute(sql);
		
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}


}
