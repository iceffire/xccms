package edu.cqu.fly.xccms.pojo;

import java.util.ArrayList;
import java.util.List;

public class OrgNode extends BsOrg{

	private  List<BsOrg> children = new ArrayList<BsOrg>();

	public List<BsOrg> getChildren() {
		return children;
	}

	public void setChildren(List<BsOrg> children) {
		this.children = children;
	}
	
}
