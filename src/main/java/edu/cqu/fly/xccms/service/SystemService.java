package edu.cqu.fly.xccms.service;

public interface SystemService extends CommonService{
	/**
	 * 日志添加
	 */
	public void addLog(String LogContent, Short loglevel,Short operatetype);
}
