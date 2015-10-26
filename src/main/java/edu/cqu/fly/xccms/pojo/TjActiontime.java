package edu.cqu.fly.xccms.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="XCCMS_TIJACTIONTIME")
public class TjActiontime {


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="ACTION_ID")
	private Long id;

	@Column(name="ACTION_EXCUTETIME")
	private Long excuteTime;

	@Column(name="ACTION_METHOD")
	private String actionMethod;

	@Column(name="ACTION_CREATETIME")
	private Date createtime;

	@Column(name="ACTION_NAME")
	private String actionName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getExcuteTime() {
		return excuteTime;
	}

	public void setExcuteTime(Long excuteTime) {
		this.excuteTime = excuteTime;
	}


	public String getActionMethod() {
		return actionMethod;
	}

	public void setActionMethod(String actionMethod) {
		this.actionMethod = actionMethod == null ? null : actionMethod.trim();
	}

	public Date getCreatetime() {
		return createtime;
	}


	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getActionName() {
		return actionName;
	}


	public void setActionName(String actionName) {
		this.actionName = actionName == null ? null : actionName.trim();
	}
}