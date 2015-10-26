package edu.cqu.fly.xccms.pojo;

import java.util.List;

public class SciResearchPaperQuery extends SciResearchPaper{

	private User user;
	
	private List<BsUserOrg> userorgs;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<BsUserOrg> getUserorgs() {
		return userorgs;
	}

	public void setUserorgs(List<BsUserOrg> userorgs) {
		this.userorgs = userorgs;
	}
	
	
}
