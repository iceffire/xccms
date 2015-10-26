package edu.cqu.fly.xccms.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_RESOURCETABLE")
public class ResourceTable {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RESOURCETABLE_ID")
	private Integer id;
	
	@Column(name="RESOURCETABLE_DES")
	private String des;
	
	@Column(name="RESOURCETABLE_SAVEPATH")
	private String savepath;
	
	@Column(name="RESOURCETABLE_TYPE")
	private String type;

	@Column(name="RESOURCETABLE_UPLOADTIME")
	private Date uploadtime;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getSavepath() {
		return savepath;
	}

	public void setSavepath(String savepath) {
		this.savepath = savepath;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getUploadtime() {
		return uploadtime;
	}

	public void setUploadtime(Date uploadtime) {
		this.uploadtime = uploadtime;
	}
	
	
}
