package edu.cqu.fly.xccms.pojo;

public class BsSystem {


	private Long id;

	private String systemKey;

	private String systemValue;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getSystemKey() {
		return systemKey;
	}

	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey == null ? null : systemKey.trim();
	}

	public String getSystemValue() {
		return systemValue;
	}


	public void setSystemValue(String systemValue) {
		this.systemValue = systemValue == null ? null : systemValue.trim();
	}
}