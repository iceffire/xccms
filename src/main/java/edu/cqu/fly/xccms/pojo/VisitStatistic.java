package edu.cqu.fly.xccms.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="XCCMS_VISITSTATISTIC")
public class VisitStatistic {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="VISITSTATISTIC_ID")
	private Integer id;
	
	@Column(name="VISITSTATISTIC_COUNT")
	private Long count;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}


	
	
}
