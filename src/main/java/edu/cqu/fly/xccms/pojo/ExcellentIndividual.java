package edu.cqu.fly.xccms.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_EXCELLENTINDICIDUAL")
public class ExcellentIndividual {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="EXCELLENTINDICIDUAL_ID")
	private Long id;
	
	
	@Column(name="EXCELLENTINDICIDUAL_NAME")
	private String name;
	
	
	@Column(name="EXCELLENTINDICIDUAL_DES",length=10000)
	private String des;
	
	
	@Column(name="EXCELLENTINDICIDUAL_PICURL")
	private String picurl;

	@Column(name="EXCELLENTINDICIDUAL_CHOOSETIME")
	private Date chooseTime;
	
	@Column(name="EXCELLENTINDICIDUAL_UPLOADORGID")
	private Integer uploadorgid;
	
	@Column(name="EXCELLENTINDICIDUAL_SCANNUM")
	private Long scannum;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDes() {
		return des;
	}


	public void setDes(String des) {
		this.des = des;
	}


	public String getPicurl() {
		return picurl;
	}


	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}


	public Date getChooseTime() {
		return chooseTime;
	}


	public void setChooseTime(Date chooseTime) {
		this.chooseTime = chooseTime;
	}


	public Integer getUploadorgid() {
		return uploadorgid;
	}


	public void setUploadorgid(Integer uploadorgid) {
		this.uploadorgid = uploadorgid;
	}


	public Long getScannum() {
		return scannum;
	}


	public void setScannum(Long scannum) {
		this.scannum = scannum;
	}



	
}
