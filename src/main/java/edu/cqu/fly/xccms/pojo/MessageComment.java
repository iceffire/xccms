package edu.cqu.fly.xccms.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="XCCMS_MESSAGECOMMENT")
public class MessageComment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="MESSAGECOMMENT_ID")
	private Long id;
	
	@Column(name="MESSAGECOMMENT_MESSAGEID")
	private Long messageid;
	
	@Column(name="MESSAGECOMMENT_COMMENTCTX")
	private String commentctx;
	
	@Column(name="MESSAGECOMMENT_COMMENTTIME")
	private Date commenttime;
	
	@Column(name="MESSAGECOMMENT_USERID")
	private String userid;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMessageid() {
		return messageid;
	}

	public void setMessageid(Long messageid) {
		this.messageid = messageid;
	}

	public String getCommentctx() {
		return commentctx;
	}

	public void setCommentctx(String commentctx) {
		this.commentctx = commentctx;
	}

	public Date getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(Date commenttime) {
		this.commenttime = commenttime;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	
	
}
