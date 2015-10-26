package edu.cqu.fly.xccms.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import edu.cqu.fly.xccms.pojo.BsArticle;
import edu.cqu.fly.xccms.pojo.BsArticleQuery;
import edu.cqu.fly.xccms.pojo.BsUserOrg;
import edu.cqu.fly.xccms.pojo.Pager;
import edu.cqu.fly.xccms.pojo.User;
import edu.cqu.fly.xccms.service.ArticleService;
import edu.cqu.fly.xccms.service.OrgService;
import edu.cqu.fly.xccms.service.UserService;
import edu.cqu.fly.xccms.util.ComonUtil;
import edu.cqu.fly.xccms.util.Constant;
import edu.cqu.fly.xccms.util.DateUtil;

public class NewsAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5394408517762878512L;
	Logger log = Logger.getLogger(this.getClass());
	/**
	* @Title: toNewsPage 
	* @Description: 跳转到新闻中心
	* @param @return   /news/news.jsp 
	* @return String    返回类型 
	* @throws
	 */
	@Resource(name="articleService")
	ArticleService articleService;
	
	@Resource(name="orgService")
	OrgService orgService;
	
	@Resource(name="userService")
	UserService userService;
	protected List<BsArticle>  bsArticlelist;
	protected List<BsArticle>  notifylist;
	protected List<BsArticle>  medialist;
	protected BsArticle bsArticledetail;
	protected BsArticle preArticle;
	protected BsArticle nextArticle;
	protected String newstype;
	protected String newstypename;
	public String toNewsPage(){
		super.commonQuery();
		return "to_news_index";
	}
	/**
	 * 查询新闻
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String doqueryNews(){
		if(log.isDebugEnabled()){
			log.debug("查询交易信息currentPage>>>>:"+currentPage);
		}
		try {
			BsArticleQuery bsArticle = new BsArticleQuery();
			bsArticle.setType(Constant.ARTICLE_TYPE_NEWS);
			Map map =articleService.queryArticleByTypeForPage(bsArticle, Constant.PAGESIZE,currentPage);
			if(ComonUtil.validateMapResult(map)){
				bsArticlelist = (List<BsArticle>) map.get(Constant.ARTICLE_LIST);
				totalCount = (Integer) map.get(Constant.TOTALCOUNT);
				totalPageCount =(Integer) map.get(Constant.TOTALPAGECOUNT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "news_result";
	}
	/**
	* @Title: newsdetail 
	* @Description: 跳转到新闻详细页面
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public String newsdetail(){
	//	super.commonQuery();
		String id = request.getParameter("id");
		try {
			bsArticledetail =	articleService.findEntityById(Long.valueOf(id), BsArticle.class);
			BsArticle artcile = articleService.findEntityById(Long.valueOf(id), BsArticle.class);
			//artcile=	articleService.findEntityById(Long.valueOf(id), BsArticle.class);
			artcile.setScannum(artcile.getScannum()+1);
			articleService.saveEntity(artcile);
			newstypename =ComonUtil.getNewstypename(artcile.getType());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "news_detail";
	}
	/**
	 * 新闻后台首页
	 * @return
	 */
	public  String index(){
		return "index";
	}
	/**
	 * 查询新闻
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  void query(){
		String startdatacreatenew = request.getParameter("startdatacreatenew");
		String enddatacreatenew = request.getParameter("enddatacreatenew");
		String newtype = request.getParameter("newtype");
		String newauthor = request.getParameter("newauthor");
		String newtitle = request.getParameter("newtitle");
		String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
	    
		try {
			Map<String, Object> session =context.getSession();
			User sessionuser = (User) session.get("user");
			List<BsUserOrg> userorgs = userService.queryorgByuserid(sessionuser.getUserid());
			BsArticleQuery bsArticle = new BsArticleQuery();
			bsArticle.setUserorgs(userorgs);
			bsArticle.setUser(sessionuser);
			if(null!=startdatacreatenew&&!startdatacreatenew.equals("")){
				bsArticle.setStartdatacreatenew(DateUtil.strToDate(startdatacreatenew));
			}
			if(null!=enddatacreatenew&&!enddatacreatenew.equals("")){
				bsArticle.setEnddatacreatenew(DateUtil.strToDate(enddatacreatenew));
			}
			if(null!=newtype&&!newtype.equals("")){
				bsArticle.setType(newtype);
			}
			if(null!=newauthor&&!newauthor.equals("")){
				bsArticle.setAuthor(newauthor);
			}
			if(null!=newtitle&&!newtitle.equals("")){
				bsArticle.setTitle(newtitle);
			}
			Pager pager =new Pager();
			Map map =articleService.queryArticleByTypeForPage(bsArticle, Integer.valueOf(rows),Integer.valueOf(page));
			if(ComonUtil.validateMapResult(map)){
				bsArticlelist = (List<BsArticle>) map.get(Constant.ARTICLE_LIST);
				totalCount = (Integer) map.get(Constant.TOTALCOUNT);
				totalPageCount =(Integer) map.get(Constant.TOTALPAGECOUNT);
				pager.setTotal(totalCount);
				pager.setRows(bsArticlelist);
			}
			super.reponseWriter(JSON.toJSONString(pager));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询新闻列表
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public  String  queryNewsList(){
    	String newtype = request.getParameter("newtype");
	    String page = request.getParameter("page"); // 当前页数  
	    String rows = request.getParameter("rows"); // 每页显示行数  
	    newstype = newtype;
	    newstypename =ComonUtil.getNewstypename(newtype);
	    currentPage=Integer.parseInt(page);
		prePage = currentPage-1;
		 nextPage = currentPage+1;
		try {
			BsArticleQuery bsArticle = new BsArticleQuery();
		
			if(null!=newtype&&!newtype.equals("")){
				bsArticle.setType(newtype);
			}
		
			Map map =articleService.queryArticleByTypeForPage(bsArticle, Integer.valueOf(rows),Integer.valueOf(page));
			if(ComonUtil.validateMapResult(map)){
				bsArticlelist = (List<BsArticle>) map.get(Constant.ARTICLE_LIST);
				totalCount = (Integer) map.get(Constant.TOTALCOUNT);
				totalPageCount =(Integer) map.get(Constant.TOTALPAGECOUNT);
				totalpages = new ArrayList<Integer>();
				for(int i=Integer.valueOf(page);i<=totalPageCount;i++){
					totalpages.add(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "news_result";
	}
	/**
	 * 保存文章
	 */
	public  void save(){
		boolean flag =false;
		BsArticle  bsArticle =getBsArticle();
		try {
			bsArticle.setScannum(Long.parseLong("0"));
			articleService.addEntity(bsArticle);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 编辑文章
	 */
	public  void update(){
		
		boolean flag =false;
		String id =request.getParameter("id");
		BsArticle  bsArticle =getBsArticle();
		
		bsArticle.setId(Long.valueOf(id));
		try {
			articleService.saveEntity(bsArticle);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 公共方法
	 * @return
	 */
	public  BsArticle getBsArticle(){
		Map<String, Object> session =context.getSession();
		User sessionuser = (User) session.get("user");
		String title =request.getParameter("title");
		String createdate = request.getParameter("createdate");
		String author = request.getParameter("author");
		String type = request.getParameter("type");
		String summary = request.getParameter("summary");
		String content = request.getParameter("content");
		String headerpicurl = request.getParameter("headerpicurl");
		String width = request.getParameter("picwidth");
		String height = request.getParameter("picheight");
	
		String hasappendix = request.getParameter("hasappendix");
		String appendix = request.getParameter("appendix");
		
		
		if(height!=null&&width!=null&&!width.equals("")&&!height.equals("")){
			try{
				ComonUtil.changeImageSize(new File("xccms/image/"+headerpicurl), Integer.parseInt(width), Integer.parseInt(height));
			}catch(Exception e){
				log.error("修改图片大小异常");
			}
			
		}
		String hasheaderpic = request.getParameter("hasheaderpic");
		BsArticle  bsArticle = new BsArticle();
		bsArticle.setTitle(title);
		bsArticle.setSummary(summary);
		bsArticle.setType(type);
		bsArticle.setContent(content);
		bsArticle.setUpdatedate(new Date());
		bsArticle.setCreatedate(DateUtil.strToDateMMDDYYYYHHMMSS(createdate));
		bsArticle.setAuthor(author);
		bsArticle.setAuthorid(sessionuser.getUserid());
		bsArticle.setCreateorgid(orgService.getOrgCodeByUserId(sessionuser.getUserid()));
		bsArticle.setHasheaderpic(Integer.parseInt(hasheaderpic));
		bsArticle.setHeaderpicurl(headerpicurl);
		bsArticle.setHasappendix(Integer.parseInt(hasappendix));
		bsArticle.setAppendix(appendix);
		return bsArticle;
	}
	/**
	 * 删除文章
	 */
	public  void  deleteArticle(){
		boolean flag =false;
		String id =request.getParameter("id");
		try {
			articleService.removeEntityById(Long.valueOf(id), BsArticle.class);
			flag = true;
			super.reponseWriter(JSON.toJSONString(flag));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 推荐新闻
	 * @throws IOException 
	 */
	public void reccomend() throws IOException{
		response.setHeader("Content-type", "text/html;chartset=utf8");
		response.setCharacterEncoding("utf8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		try {
			BsArticle article = articleService.findEntityById(Long.valueOf(id), BsArticle.class);
			article.setIsrecommend(1);
			articleService.saveEntity(article);
		out.print("推荐成功");
		} catch (NumberFormatException e) {
			e.printStackTrace();
			out.print("推荐失败");
		} catch (Exception e) {
			e.printStackTrace();
			out.print("推荐失败");
		}finally{
			out.flush();
			out.close();
		}
	}
	
	/**
	 * 查询全部投稿排行
	 * @return
	 */
	public String querynewscontribute(){
		 newscontributelist = articleService.queryNewsContributes(0);
		 return "allnewscontribute";
	}
	public BsArticle getBsArticledetail() {
		return bsArticledetail;
	}
	public void setBsArticledetail(BsArticle bsArticledetail) {
		this.bsArticledetail = bsArticledetail;
	}
	public List<BsArticle> getBsArticlelist() {
		return bsArticlelist;
	}
	public void setBsArticlelist(List<BsArticle> bsArticlelist) {
		this.bsArticlelist = bsArticlelist;
	}
	public BsArticle getPreArticle() {
		return preArticle;
	}
	public void setPreArticle(BsArticle preArticle) {
		this.preArticle = preArticle;
	}
	public BsArticle getNextArticle() {
		return nextArticle;
	}
	public void setNextArticle(BsArticle nextArticle) {
		this.nextArticle = nextArticle;
	}
	public String getNewstype() {
		return newstype;
	}
	public void setNewstype(String newstype) {
		this.newstype = newstype;
	}
	public String getNewstypename() {
		return newstypename;
	}
	public void setNewstypename(String newstypename) {
		this.newstypename = newstypename;
	}
	
}
