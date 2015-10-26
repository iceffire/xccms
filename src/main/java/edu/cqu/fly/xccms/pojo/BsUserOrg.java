package edu.cqu.fly.xccms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_USERORG")
public class BsUserOrg {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="USERORG_ID")
	private Integer id;

	@Column(name="USER_ID")
	private String userid;

	@Column(name="ORG_ID")
	private Integer orgid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}
	
	
}
