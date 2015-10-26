package edu.cqu.fly.xccms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_PAGESTATIC")
public class BsPagestatic {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PAGESTATIC_ID")
	private Long id;

	@Column(name="PAGESTATIC_HTMNAME")
	private String htmname;

	@Column(name="PAGESTATIC_DYNAMICURL")
	private String dynamicurl;

	@Column(name="PAGESTATIC_FILEPATH")
	private String filepath;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getHtmname() {
		return htmname;
	}


	public void setHtmname(String htmname) {
		this.htmname = htmname == null ? null : htmname.trim();
	}


	public String getDynamicurl() {
		return dynamicurl;
	}

	public void setDynamicurl(String dynamicurl) {
		this.dynamicurl = dynamicurl == null ? null : dynamicurl.trim();
	}


	public String getFilepath() {
		return filepath;
	}


	public void setFilepath(String filepath) {
		this.filepath = filepath == null ? null : filepath.trim();
	}
}