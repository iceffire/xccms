package edu.cqu.fly.xccms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_SITEURL")
public class SiteUrl {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SITEURL_ID")
	private Long id;
	
	@Column(name="SITEURL_TYPE")
	private String type;
	
	@Column(name="SITEURL_SITENAME")
	private String sitename;
	
	@Column(name="SITEURL_URL")
	private String url;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
