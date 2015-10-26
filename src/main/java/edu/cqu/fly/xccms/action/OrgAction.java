package edu.cqu.fly.xccms.action;

import java.io.IOException;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.BsOrg;
import edu.cqu.fly.xccms.service.OrgService;

public class OrgAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6839900741849644430L;

	@Resource(name="orgService")
	OrgService orgService;
	public void query() throws IOException{
		String jsonstr = "";
		try {
			jsonstr = orgService.getOrgJsonStr();
			super.reponseWriter(jsonstr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * org显示
	 * @return
	 */
	public  String show(){
		return "org_show";
	}
	/**
	 * 更新或者删除org
	 */
	public  void updateoradd(){
		boolean flag = false;
		try {
			String id = request.getParameter("id");
			String orgcode = request.getParameter("orgcode");
			String orgname = request.getParameter("orgname");
			String parentid = request.getParameter("parentid");
			BsOrg  bsOrg = new BsOrg();
			if(null!=id&&!id.equals("NaN")&&!id.trim().equals("")){
				bsOrg.setId(Long.valueOf(id));
			}else{
				bsOrg.setId(null);
			}
		
			if(null!=parentid&&!parentid.equals("NaN")&&!parentid.trim().equals("")){
				bsOrg.setParentid(Long.parseLong(parentid));
			}else{
				bsOrg.setParentid(null);
			}
			if(null!=orgcode&&!orgcode.equals("NaN")&&!orgcode.trim().equals("")){
				bsOrg.setOrgcode(orgcode);
			}else{
				bsOrg.setOrgcode(null);
			}
			
			if(null!=orgname&&!orgname.equals("NaN")&&!orgname.trim().equals("")){
				bsOrg.setOrgname(orgname);
			}else{
				bsOrg.setOrgname(null);
			}
			orgService.updateOrAddOrg(bsOrg);
			flag=true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除菜单
	 */
	public  void  deleteOrg(){
		boolean flag = false;
		try {
			String id = request.getParameter("id");
			orgService.deleteOrg(Long.valueOf(id));
			flag=true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
