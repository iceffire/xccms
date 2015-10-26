package edu.cqu.fly.xccms.pojo;

import java.util.Date;
import java.util.List;

public class BsArticleQuery extends BsArticle {
	/**
	 * 开始日期
	 */
	private Date startdatacreatenew;
	/**
	 * 结束日期
	 */
	private Date enddatacreatenew;
	
	private List<BsUserOrg> userorgs;
	
	private User user;
	public Date getStartdatacreatenew() {
		return startdatacreatenew;
	}
	public void setStartdatacreatenew(Date startdatacreatenew) {
		this.startdatacreatenew = startdatacreatenew;
	}
	public Date getEnddatacreatenew() {
		return enddatacreatenew;
	}
	public void setEnddatacreatenew(Date enddatacreatenew) {
		this.enddatacreatenew = enddatacreatenew;
	}
	public List<BsUserOrg> getUserorgs() {
		return userorgs;
	}
	public void setUserorgs(List<BsUserOrg> userorgs) {
		this.userorgs = userorgs;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}