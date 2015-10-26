package edu.cqu.fly.xccms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_ROLE")
public class BsRole {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ROLE_ID")
	private Integer id;

	@Column(name="ROLE_ISACTIVE")
	private String isactive;

	@Column(name="ROLE_NAME")
	private String name;


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive == null ? null : isactive.trim();
	}

	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}
}