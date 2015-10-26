package edu.cqu.fly.xccms.pojo;

import java.util.ArrayList;
import java.util.List;

public class MenuNode extends BsMenu{
	private  List<BsMenu> children = new ArrayList<BsMenu>();

	public List<BsMenu> getChildren() {
		return children;
	}

	public void setChildren(List<BsMenu> children) {
		this.children = children;
	}
	
}