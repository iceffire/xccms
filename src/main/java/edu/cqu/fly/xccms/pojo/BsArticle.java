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
@Table(name="XCCMS_ARTICLE")
public class BsArticle {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ARTICLE_ID")
	private Long id;

	@Column(name="ARTICLE_AUTHOR")
	private String author;
	
	@Column(name="ARTICLE_AUTHORID")
	private String authorid;
	
	@Column(name="ARTICLE_CREATEDATE")
	private Date createdate;
	
	@Column(name="ARTICLE_TYPE")
	private String type;
	
	@Column(name="ARTICLE_SUMMARY")
	private String summary;
	
	@Lob
	@Column(name="ARTICLE_CONTENT")
	private String content;
	
	@Column(name="ARTICLE_UPDATEDATE")
	private Date updatedate;
	
	@Column(name="ARTICLE_TITLE")
	private String title;

	@Column(name="ARTICLE_CREATEORGID")
	private Integer createorgid;
	
	@Column(name="ARTICLE_ISRECOMMEND")
	private Integer isrecommend;
	
	@Column(name="ARTICLE_HASHEADERPIC")
	private Integer hasheaderpic;
	
	@Column(name="ARTICLE_HEADERPICURL")
	private String headerpicurl;
	
	@Column(name="ARTICLE_SCANNUM")
	private Long scannum;
	
	@Column(name="ARTICLE_HASAPPENDIX")
	private Integer hasappendix; 
	
	@Column(name="ARTICLE_APPENDIX")
	private String appendix;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}

	
	public String getAuthor() {
		return author;
	}

	
	public void setAuthor(String author) {
		this.author = author == null ? null : author.trim();
	}

	
	
	public String getAuthorid() {
		return authorid;
	}


	public void setAuthorid(String authorid) {
		this.authorid = authorid;
	}


	public Date getCreatedate() {
		return createdate;
	}

	
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}


	public String getType() {
		return type;
	}

	
	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}


	public String getSummary() {
		return summary;
	}

	
	public void setSummary(String summary) {
		this.summary = summary == null ? null : summary.trim();
	}

	
	public String getContent() {
		return content;
	}

	
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	
	public Date getUpdatedate() {
		return updatedate;
	}

	
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}

	
	public String getTitle() {
		return title;
	}

	
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}







	public Integer getCreateorgid() {
		return createorgid;
	}


	public void setCreateorgid(Integer createorgid) {
		this.createorgid = createorgid;
	}


	public Integer getIsrecommend() {
		return isrecommend;
	}


	public void setIsrecommend(Integer isrecommend) {
		this.isrecommend = isrecommend;
	}


	public Integer getHasheaderpic() {
		return hasheaderpic;
	}


	public void setHasheaderpic(Integer hasheaderpic) {
		this.hasheaderpic = hasheaderpic;
	}


	public String getHeaderpicurl() {
		return headerpicurl;
	}


	public void setHeaderpicurl(String headerpicurl) {
		this.headerpicurl = headerpicurl;
	}


	public Long getScannum() {
		return scannum;
	}


	public void setScannum(Long scannum) {
		this.scannum = scannum;
	}


	public Integer getHasappendix() {
		return hasappendix;
	}


	public void setHasappendix(Integer hasappendix) {
		this.hasappendix = hasappendix;
	}


	public String getAppendix() {
		return appendix;
	}


	public void setAppendix(String appendix) {
		this.appendix = appendix;
	}




	
}