package edu.cqu.fly.xccms.action;

import java.util.Map;

import javax.annotation.Resource;

import edu.cqu.fly.xccms.cache.Cache;
import edu.cqu.fly.xccms.pojo.VisitStatistic;
import edu.cqu.fly.xccms.service.SystemService;

public class MainAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2665290289528505288L;

	@Resource(name="systemService")
	SystemService systemService;
	
	@SuppressWarnings("unused")
	public String init(){
		super.commonQuery();
		Map<String, Object> session =context.getSession();
		/*****************网站访问统计*************************/
		String s = (String)request.getSession().getAttribute("guess");
		if(s ==null||s.equals("")||s.equals("null")){
			request.getSession().setAttribute("guess", "guess");
			
			if(systemService.findAllEntities(VisitStatistic.class).size() == 0){
				VisitStatistic visit = new VisitStatistic();
				visit.setCount((long)1);
				systemService.addEntity(visit);
			}else{
				VisitStatistic visit = systemService.findAllEntities(VisitStatistic.class).get(0);
				visit.setCount(visit.getCount()+1);
				systemService.saveEntity(visit);
			}
		}
		/*****************网站访问统计*************************/
		hisvisitor = systemService.findAllEntities(VisitStatistic.class).get(0).getCount();
		onlineNum = onlineUsers.size();
		Long his = (Long)Cache.getInstance().get("guesscount");
		if(his == null){
			Cache.getInstance().put("guesscount", hisvisitor);
			Cache.getInstance().put("onlineNum", onlineNum);
		}else{
			Cache.getInstance().remove("guesscount");
			Cache.getInstance().remove("onlineNum");
			Cache.getInstance().put("guesscount", hisvisitor);
			Cache.getInstance().put("onlineNum", onlineNum);
		}
		
		
		return "index";
	}

}
