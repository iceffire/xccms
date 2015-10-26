package edu.cqu.fly.xccms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_USER")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="REC_ID")
	private Long id;

	@Column(name="USER_ID")
	private String userid;


	@Column(name="USER_NAME")
	private String username;

	@Column(name="USER_REALNAME")
    private String realname;
	
	@Column(name="USER_IDCARD")
	private String idcard;

	@Column(name="USER_ISADMIN")
	private String isadmin;

	@Column(name="USER_PASSWORD")
	private String password;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUserid() {
		return userid;
	}

	
	public void setUserid(String userid) {
		this.userid = userid == null ? null : userid.trim();
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username == null ? null : username.trim();
	}


	
	
	public String getRealname() {
		return realname;
	}


	public void setRealname(String realname) {
		this.realname = realname;
	}

	

	public String getIdcard() {
		return idcard;
	}


	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}


	public String getIsadmin() {
		return isadmin;
	}

	public void setIsadmin(String isadmin) {
		this.isadmin = isadmin == null ? null : isadmin.trim();
	}

	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}
}
