package edu.cqu.fly.xccms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_ROLERESOURCE")
public class BsRoleResource {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROLERESOURCE_ID")
	private Integer id;
	
	@Column(name="ROLE_ID")
	private Integer roleid;
	
	@Column(name="RESOURCE_ID")
	private Integer resourceid;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRoleid() {
		return roleid;
	}

	
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public Integer getResourceid() {
		return resourceid;
	}

	public void setResourceid(Integer resourceid) {
		this.resourceid = resourceid;
	}
}