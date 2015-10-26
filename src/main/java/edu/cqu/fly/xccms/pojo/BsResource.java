package edu.cqu.fly.xccms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_RESOURCE")
public class BsResource {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="RESOURCE_ID")
	private Integer id;

	@Column(name="RESOURCE_URL")
	private String resourceUrl;
	
	@Column(name="RESOURCE_NAME")
	private String resouceName;

	@Column(name="RESOURCE_TYPE")
	private String resourceType;
	
	@Column(name="MENU_ID")
	private Long menuid;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl == null ? null : resourceUrl.trim();
	}

	public String getResouceName() {
		return resouceName;
	}

	public void setResouceName(String resouceName) {
		this.resouceName = resouceName == null ? null : resouceName.trim();
	}


	public String getResourceType() {
		return resourceType;
	}


	public void setResourceType(String resourceType) {
		this.resourceType = resourceType == null ? null : resourceType.trim();
	}


	public Long getMenuid() {
		return menuid;
	}

	public void setMenuid(Long menuid) {
		this.menuid = menuid;
	}
}