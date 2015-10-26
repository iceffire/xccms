package edu.cqu.fly.xccms.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.cqu.fly.xccms.dao.CommonDao;
@Service("systemService")
@Transactional
public class SystemServiceImpl extends CommonServiceImpl implements SystemService{

	@Resource(name="commonDao")
	private CommonDao commonDao;

	public void addLog(String logContent, Short loglevel, Short operatetype) {
	}
}
