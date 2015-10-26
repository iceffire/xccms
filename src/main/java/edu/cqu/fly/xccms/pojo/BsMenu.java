package edu.cqu.fly.xccms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_MENU")
public class BsMenu {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MENU_ID")
	private Long id;
	
	@Column(name="MENU_TEXT")
	private String text;
	
	@Column(name="MENU_LEVEL")
	private Integer menulevel;

	@Column(name="MENU_URL")
	private String menuurl;

	@Column(name="MENU_ORDER")
	private Integer menuorder;

	@Column(name="MENU_PARENT")
	private Integer menuparent;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text == null ? null : text.trim();
	}


	public Integer getMenulevel() {
		return menulevel;
	}

	
	public void setMenulevel(Integer menulevel) {
		this.menulevel = menulevel;
	}

	
	public String getMenuurl() {
		return menuurl;
	}

	public void setMenuurl(String menuurl) {
		this.menuurl = menuurl == null ? null : menuurl.trim();
	}


	public Integer getMenuorder() {
		return menuorder;
	}

	public void setMenuorder(Integer menuorder) {
		this.menuorder = menuorder;
	}

	public Integer getMenuparent() {
		return menuparent;
	}


	public void setMenuparent(Integer menuparent) {
		this.menuparent = menuparent;
	}
	
	
}