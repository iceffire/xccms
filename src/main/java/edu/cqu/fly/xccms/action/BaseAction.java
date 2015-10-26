package edu.cqu.fly.xccms.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import edu.cqu.fly.xccms.pojo.BsArticle;
import edu.cqu.fly.xccms.pojo.BsOrg;
import edu.cqu.fly.xccms.pojo.EduVideo;
import edu.cqu.fly.xccms.pojo.ExcellentIndividual;
import edu.cqu.fly.xccms.pojo.NewsContribute;
import edu.cqu.fly.xccms.pojo.OfficeSoftware;
import edu.cqu.fly.xccms.pojo.ResourceTable;
import edu.cqu.fly.xccms.pojo.SciResearchPaper;
import edu.cqu.fly.xccms.pojo.SiteUrl;
import edu.cqu.fly.xccms.pojo.User;
import edu.cqu.fly.xccms.pojo.ZZNews;
import edu.cqu.fly.xccms.service.ArticleService;
import edu.cqu.fly.xccms.service.CommonService;
import edu.cqu.fly.xccms.service.ZZNewsService;
import edu.cqu.fly.xccms.util.CodingCovert;
import edu.cqu.fly.xccms.util.dao.TQOrder;
import edu.cqu.fly.xccms.util.dao.TQRestriction;
import edu.cqu.fly.xccms.util.dao.TypedQueryBuilder;

public class BaseAction extends SystemAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3096269886351722578L;
	
	@Resource(name="commonService")
	CommonService commonService;
	
	@Resource(name="zzNewsService")
	ZZNewsService zzNewsService;
	
	ActionContext context = ActionContext.getContext();  
    protected HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST);  
    protected  HttpServletResponse response = (HttpServletResponse) context.get(ServletActionContext.HTTP_RESPONSE);  
	
    protected User user;
	protected String idNumber;
	/** 显示信息 */
	protected String showMessage;
	protected String password;
	HttpSession httpsession = request.getSession(); 
	private Map<String, Object> session ;
	protected String suppliercode;
	protected String checkcode;
	protected String  usertype;
	@Resource(name="articleService")
	ArticleService articleService;
	protected int currentPage;//当前页数
	protected int prePage;//前一页
	protected int nextPage;//下一页
	protected int totalPageCount;//总页数
	protected int totalCount;//总记录数
	protected List<Integer> totalpages;
	protected int onlineNum;//在线人数
	protected long hisvisitor;//网站总访问量
	
	/**
	 * 首页大图片新闻
	 */
	protected List<BsArticle> homebigpicnewslist;
	/**
	 * 部队新闻
	 */
	protected List<BsArticle>  armynewslist;
	/**
	 * 总装新闻
	 */
	protected List<ZZNews>  zongzhuangnewslist;
	/**
	 * 通知公告
	 */
	protected List<BsArticle>  notifylist;
	/**
	 * 各单位新闻
	 */
	protected  List<BsArticle> unitnewslist;
	/**
	 * 部队训练信息
	 */
	protected List<BsArticle>  trainnewslist;
	/**
	 * 工作计划
	 */
	protected List<BsArticle>  workplanlist;
	/**
	 * 业务指导
	 */
	protected List<BsArticle>  servicelist;
	/**
	 * 先进个人
	 */
	protected List<ExcellentIndividual> excellentindividuallist;
	
	/**
	 * 常用表格
	 */
	protected List<ResourceTable> resourcetablelist;
	/**
	 * 工作软件
	 */
	protected List<OfficeSoftware> officesoftwarelist;
	/**
	 * 教学视频
	 */
	protected List<EduVideo> eduvideolist;
	/**
	 * 军事基础
	 */
	protected List<BsArticle> militarybaselist;
	/**
	 * 上岗考核
	 */
	protected List<BsArticle> sgkhlist;
	/**
	 * 军队法规
	 */
	protected List<BsArticle> jdfglist;
	/**
	 * 体系标准
	 */
	protected List<BsArticle> txbzlist;
	/**
	 * 学历教育
	 */
	protected List<BsArticle> xljylist;
	/**
	 * 投稿排行
	 */
	protected List<NewsContribute> newscontributelist;
	/**
	 * 公共的reponseWriter
	 * @param jsonstr
	 */
	/**
	 * 各单位新闻
	 */
	protected Map<BsOrg,List<BsArticle>> unitnewsmaps;
	/**
	 * 科研论文
	 */
	protected List<SciResearchPaper> sciresearchpaperlist;
	
	/**
	 * 超链接站点
	 */
	protected static List<SiteUrl> siteurllist ;

	public void commonQuery(){
		
		homebigpicnewslist = articleService.queryHomePageBigPicnews();
		 armynewslist = queryArticleByType("ZH_DT",0,8);
		 
		 zongzhuangnewslist =queryZZNews(0,8);
		 notifylist = queryArticleByType("TZ_GG",0,5);
		 
		 trainnewslist = queryArticleByType("BD_XL",0,8);
		workplanlist = queryArticleByType("GZ_JH",0,8);
		militarybaselist = queryArticleByType("JS_JC",0,10);
		 sgkhlist = queryArticleByType("SG_KH",0,10);
	     jdfglist = queryArticleByType("JD_FG",0,10);
		 txbzlist = queryArticleByType("TX_BZ",0,10);
		 xljylist = queryArticleByType("XL_JY",0,10);
		eduvideolist = commonService.selectObjectPage(new TypedQueryBuilder<EduVideo>(EduVideo.class,"e"), 0, 10);
	    excellentindividuallist = commonService.queryByParas(new TypedQueryBuilder<ExcellentIndividual>(ExcellentIndividual.class,"e"));
	    resourcetablelist = commonService.selectObjectPage(new TypedQueryBuilder<ResourceTable>(ResourceTable.class,"e"), 0, 8);
		 newscontributelist = articleService.queryNewsContributes(5);
		 officesoftwarelist = commonService.selectObjectPage(new TypedQueryBuilder<OfficeSoftware>(OfficeSoftware.class,"e"), 0, 8);
		 unitnewsmaps = articleService.queryUnitArticles(0,11);
	 sciresearchpaperlist = commonService.selectObjectPage(new TypedQueryBuilder<SciResearchPaper>(SciResearchPaper.class,"e"), 0, 10);
		 siteurllist = commonService.findAllEntities(SiteUrl.class);
	}
	public List<ZZNews> queryZZNews(int start,int end){
		TypedQueryBuilder<ZZNews> tqBuilder = new TypedQueryBuilder<ZZNews>(ZZNews.class,"e");
		tqBuilder.addOrder(new TQOrder("createtime",false));
		return commonService.selectObjectPage(tqBuilder, start, end);
	}
	public List<BsArticle> queryArticleByType(String type,int start,int end){
		TypedQueryBuilder<BsArticle> tqBuilder = new TypedQueryBuilder<BsArticle>(BsArticle.class,"e");
		tqBuilder.addOrder(new TQOrder("createdate",false));
		 tqBuilder.addRestriction(new TQRestriction( "type", "in", Arrays.asList(type)));
		 return articleService.selectObjectPage(tqBuilder, start, end);
	}
	public List<BsArticle> queryArticleByTypeAndUnit(String type,String unit){
		TypedQueryBuilder<BsArticle> tqBuilder = new TypedQueryBuilder<BsArticle>(BsArticle.class,"e");
		tqBuilder.addOrder(new TQOrder("createdate",false));
		 tqBuilder.addRestriction(new TQRestriction( "type", "in", Arrays.asList(type)));
		 tqBuilder.addRestriction(new TQRestriction( "createorgid", "in", Arrays.asList(unit)));
		 return articleService.queryByParas(tqBuilder);
	}
	public  void reponseWriter(String jsonstr){
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().print(jsonstr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public User getUser() {
		user = (User) ActionContext.getContext().getSession().get("user");
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Map<String, Object> getSession() {
		session=ActionContext.getContext().getSession();
		return session;
	}


	public void setSession(Map<String, Object> session) {
		this.session = session;
	}


	public String getIdNumber() {
		return idNumber;
	}


	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}


	public String getShowMessage() {
		return showMessage;
	}


	public void setShowMessage(String showMessage) {
		this.showMessage = showMessage;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = CodingCovert.getMD5(password);
	}


	public String getSuppliercode() {
		return suppliercode;
	}


	public void setSuppliercode(String suppliercode) {
		this.suppliercode = suppliercode;
	}


	public String getCheckcode() {
		return checkcode;
	}


	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}


	public String getUsertype() {
		return usertype;
	}


	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	



	public List<BsArticle> getNotifylist() {
		return notifylist;
	}


	public void setNotifylist(List<BsArticle> notifylist) {
		this.notifylist = notifylist;
	}



	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getTotalPageCount() {
		return totalPageCount;
	}


	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}


	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public List<BsArticle> getHomebigpicnewslist() {
		return homebigpicnewslist;
	}
	public void setHomebigpicnewslist(List<BsArticle> homebigpicnewslist) {
		this.homebigpicnewslist = homebigpicnewslist;
	}
	public List<BsArticle> getArmynewslist() {
		return armynewslist;
	}

	public void setArmynewslist(List<BsArticle> armynewslist) {
		this.armynewslist = armynewslist;
	}

	
	public List<ZZNews> getZongzhuangnewslist() {
		return zongzhuangnewslist;
	}
	public void setZongzhuangnewslist(List<ZZNews> zongzhuangnewslist) {
		this.zongzhuangnewslist = zongzhuangnewslist;
	}
	public List<BsArticle> getUnitnewslist() {
		return unitnewslist;
	}
	public void setUnitnewslist(List<BsArticle> unitnewslist) {
		this.unitnewslist = unitnewslist;
	}
	public List<BsArticle> getTrainnewslist() {
		return trainnewslist;
	}

	public void setTrainnewslist(List<BsArticle> trainnewslist) {
		this.trainnewslist = trainnewslist;
	}

	public List<BsArticle> getWorkplanlist() {
		return workplanlist;
	}

	public void setWorkplanlist(List<BsArticle> workplanlist) {
		this.workplanlist = workplanlist;
	}

	public List<BsArticle> getServicelist() {
		return servicelist;
	}

	public void setServicelist(List<BsArticle> servicelist) {
		this.servicelist = servicelist;
	}

	public List<ExcellentIndividual> getExcellentindividuallist() {
		return excellentindividuallist;
	}

	public void setExcellentindividuallist(
			List<ExcellentIndividual> excellentindividuallist) {
		this.excellentindividuallist = excellentindividuallist;
	}

	public List<ResourceTable> getResourcetablelist() {
		return resourcetablelist;
	}

	public void setResourcetablelist(List<ResourceTable> resourcetablelist) {
		this.resourcetablelist = resourcetablelist;
	}

	public List<OfficeSoftware> getOfficesoftwarelist() {
		return officesoftwarelist;
	}

	public void setOfficesoftwarelist(List<OfficeSoftware> officesoftwarelist) {
		this.officesoftwarelist = officesoftwarelist;
	}
	
	
	
	public List<EduVideo> getEduvideolist() {
		return eduvideolist;
	}
	public void setEduvideolist(List<EduVideo> eduvideolist) {
		this.eduvideolist = eduvideolist;
	}
	
	
	public List<NewsContribute> getNewscontributelist() {
		return newscontributelist;
	}
	public void setNewscontributelist(List<NewsContribute> newscontributelist) {
		this.newscontributelist = newscontributelist;
	}
	
	
	
	public List<BsArticle> getSgkhlist() {
		return sgkhlist;
	}
	public void setSgkhlist(List<BsArticle> sgkhlist) {
		this.sgkhlist = sgkhlist;
	}
	public List<BsArticle> getJdfglist() {
		return jdfglist;
	}
	public void setJdfglist(List<BsArticle> jdfglist) {
		this.jdfglist = jdfglist;
	}
	public List<BsArticle> getTxbzlist() {
		return txbzlist;
	}
	public void setTxbzlist(List<BsArticle> txbzlist) {
		this.txbzlist = txbzlist;
	}
	public List<BsArticle> getXljylist() {
		return xljylist;
	}
	public void setXljylist(List<BsArticle> xljylist) {
		this.xljylist = xljylist;
	}
	public List<BsArticle> getMilitarybaselist() {
		return militarybaselist;
	}
	public void setMilitarybaselist(List<BsArticle> militarybaselist) {
		this.militarybaselist = militarybaselist;
	}
	public List<SciResearchPaper> getSciresearchpaperlist() {
		return sciresearchpaperlist;
	}
	public void setSciresearchpaperlist(List<SciResearchPaper> sciresearchpaperlist) {
		this.sciresearchpaperlist = sciresearchpaperlist;
	}
	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public List<Integer> getTotalpages() {
		return totalpages;
	}
	public void setTotalpages(List<Integer> totalpages) {
		this.totalpages = totalpages;
	}
	public int getOnlineNum() {
		return onlineNum;
	}
	public void setOnlineNum(int onlineNum) {
		this.onlineNum = onlineNum;
	}
	
	
	
	public long getHisvisitor() {
		return hisvisitor;
	}
	public void setHisvisitor(long hisvisitor) {
		this.hisvisitor = hisvisitor;
	}
	public Map<BsOrg, List<BsArticle>> getUnitnewsmaps() {
		return unitnewsmaps;
	}
	public void setUnitnewsmaps(Map<BsOrg, List<BsArticle>> unitnewsmaps) {
		this.unitnewsmaps = unitnewsmaps;
	}
	public List<SiteUrl> getSiteurllist() {
		return siteurllist;
	}
	public void setSiteurllist(List<SiteUrl> siteurllist) {
		this.siteurllist = siteurllist;
	}
	

}
