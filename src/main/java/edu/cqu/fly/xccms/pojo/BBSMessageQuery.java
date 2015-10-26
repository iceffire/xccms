package edu.cqu.fly.xccms.pojo;

import java.util.Date;

public class BBSMessageQuery extends BBSMessage{

	protected Integer replyNum;//回复数

	protected Date lastcommenttime;//最新评论时间
	
	protected String queryPeriod;//查询周期
	public Integer getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public Date getLastcommenttime() {
		return lastcommenttime;
	}

	public void setLastcommenttime(Date lastcommenttime) {
		this.lastcommenttime = lastcommenttime;
	}

	public String getQueryPeriod() {
		return queryPeriod;
	}

	public void setQueryPeriod(String queryPeriod) {
		this.queryPeriod = queryPeriod;
	}

	
	
	
}
