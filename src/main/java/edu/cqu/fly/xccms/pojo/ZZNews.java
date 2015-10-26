package edu.cqu.fly.xccms.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_ZZNEWS")
public class ZZNews {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ZZNEWS_ID")
	private Long id;
	
	@Column(name="ZZNEWS_TITLE")
	private String title;
	
	@Column(name="ZZNEWS_NEWSURL")
	private String newsurl;
	
	@Column(name="ZZNEWS_CREATETIME")
	private Date createtime;
	
	@Column(name="ZZNEWS_PICURL")
	private String picurl;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNewsurl() {
		return newsurl;
	}

	public void setNewsurl(String newsurl) {
		this.newsurl = newsurl;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	

	
	
	
}
