package edu.cqu.fly.xccms.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="XCCMS_SCIRESEARCHPAPER")
public class SciResearchPaper {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SCIRESEARCHPAPER_ID")
	private Long id;
	
	@Column(name="SCIRESEARCHPAPER_TITLE")
	private String title;
	
	@Column(name="SCIRESEARCHPAPER_SUMMARY")
	private String summary;
	
	@Column(name="SCIRESEARCHPAPER_SAVEPATH")
	private String savepath;
	
	@Column(name="SCIRESEARCHPAPER_URL")
	private String url;
	
	@Column(name="SCIRESEARCHPAPER_UOLOADTIME")
	private Date uploadtime;

	@Column(name="SCIRESEARCHPAPER_UPLOADUSERID")
	private String uploaduserid;
	
	@Column(name="SCIRESEARCHPAPER_UPLOADORGID")
	private Long uploadorgid;
	
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSavepath() {
		return savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}

	public String getUploaduserid() {
		return uploaduserid;
	}

	public void setUploaduserid(String uploaduserid) {
		this.uploaduserid = uploaduserid;
	}

	public Long getUploadorgid() {
		return uploadorgid;
	}

	public void setUploadorgid(Long uploadorgid) {
		this.uploadorgid = uploadorgid;
	}

	
	
	
}
