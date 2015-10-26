package edu.cqu.fly.xccms.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_BBSMESSAGE")
public class BBSMessage {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="BBSMESSAGE_ID")
	private Long  id;
	
	@Column(name="BBSMESSAGE_TITLE")
	private String title;
	
	@Column(name="BBSMESSAGE_USERID")
	private String  userid;
	
	@Column(name="BBSMESSAGE_CREATETIME")
	private Date createTime;
	
	@Lob
	@Column(name="BBSMESSAGE_CONTENT")
	private String content;

	@Column(name="BBSMESSAGE_SCANNUM")
	private Long scannum;
	
	@Column(name="BBSMESSAGE_ISESSENCE")
	private Integer isessence;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public Long getScannum() {
		return scannum;
	}

	public void setScannum(Long scannum) {
		this.scannum = scannum;
	}

	public Integer getIsessence() {
		return isessence;
	}

	public void setIsessence(Integer isessence) {
		this.isessence = isessence;
	}
	
	
}
