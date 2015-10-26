package edu.cqu.fly.xccms.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="XCCMS_NEWSTYPE")
public class NewsType implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7092713181461771688L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NEWSTYPE_ID")
	private Integer id;
	
	@Column(name="NEWSTYPE_TYPECODE")
	private String typecode;
	
	@Column(name="NEWSTYPE_NAME")
	private String type;

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
