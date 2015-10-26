package edu.cqu.fly.xccms.service;

import edu.cqu.fly.xccms.pojo.BsOrg;

public interface OrgService extends CommonService{
	public String getOrgJsonStr();
	public void updateOrAddOrg(BsOrg bsOrg);
	public void deleteOrg(long id) ;
	public Integer getOrgCodeByUserId(String userId);
}
