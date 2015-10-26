<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<%@ include file="../home/header.jsp" %>
<div class="news_de">
	<div class="side_content">
		<div class="side_content_title">
			<div class="right"><i></i><a href="index">首页</a>> <a href="">科研学术</a></div>
		</div><!--/side_content_title-->
		 <div class="sub-news-table">
			 <ul>
			 	<s:iterator var="sciresearchpaper" value="sciresearchpaperlist" status="st">
			 	<li><a href="${sciresearchpaper.url}">${sciresearchpaper.title}</a><span>${sciresearchpaper.uploadtime}</span></li>
				</s:iterator>
			 
			 </ul>
	       
	    </div><!--sub-news-table-->
	   
		<div class="m-page m-page-sr m-page-sm">
            <a href="#" class="first pageprv z-dis"  onclick="skipUrl('SciResearchPaper_querySciResearchPaperList?page=${prePage }&rows=10','${totalPageCount }','${prePage }')"><span class="pagearr">&lt;</span>上一页</a>
           <s:iterator var="pages" value="totalpages" status="st">
              <a href="SciResearchPaper_querySciResearchPaperList?page=${pages }&rows=10">${pages }</a>
            </s:iterator>
            <a href="#" onclickclass="last pagenxt" onclick="skipUrl('SciResearchPaper_querySciResearchPaperList?page=${nextPage }&rows=10','${totalPageCount }','${nextPage }')">下一页<span class="pagearr">&gt;</span></a>
        </div><!--7-->
	</div><!--/side_content-->
</div>
<%@ include file="../home/footer.jsp" %>
</body>
</html>