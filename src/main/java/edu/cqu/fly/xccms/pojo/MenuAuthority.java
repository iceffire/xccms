package edu.cqu.fly.xccms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_MENUAUTHORITY")
public class MenuAuthority {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MENUAUTHORITY_ID")
	private Long id;
	
	@Column(name="MENUAUTHORITY_MENUID")
	private Long menuid;
	
	@Column(name="MENUAUTHORITY_ROLEID")
	private Integer roleid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMenuid() {
		return menuid;
	}

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
	
	
	
	
}
