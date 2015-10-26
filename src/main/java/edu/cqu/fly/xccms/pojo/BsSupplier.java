package edu.cqu.fly.xccms.pojo;

import java.io.Serializable;

public class BsSupplier implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8760846085304180672L;

	private Integer id;

	private String supplierCode;

	private String supplierName;

	private String supplierInfo;

	private String supplierUrl;

	private String contactname;
	
	private String contactemail;

	private String contactphone;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getSupplierCode() {
		return supplierCode;
	}


	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode == null ? null : supplierCode.trim();
	}

	
	public String getSupplierName() {
		return supplierName;
	}


	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName == null ? null : supplierName.trim();
	}


	public String getSupplierInfo() {
		return supplierInfo;
	}


	public void setSupplierInfo(String supplierInfo) {
		this.supplierInfo = supplierInfo == null ? null : supplierInfo.trim();
	}


	public String getSupplierUrl() {
		return supplierUrl;
	}


	public void setSupplierUrl(String supplierUrl) {
		this.supplierUrl = supplierUrl == null ? null : supplierUrl.trim();
	}


	public String getContactname() {
		return contactname;
	}


	public void setContactname(String contactname) {
		this.contactname = contactname == null ? null : contactname.trim();
	}


	public String getContactemail() {
		return contactemail;
	}

	public void setContactemail(String contactemail) {
		this.contactemail = contactemail == null ? null : contactemail.trim();
	}


	public String getContactphone() {
		return contactphone;
	}


	public void setContactphone(String contactphone) {
		this.contactphone = contactphone == null ? null : contactphone.trim();
	}
}