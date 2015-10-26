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
			<div class="right"><i></i><a href="index">首页</a>> <a href="">常用表格</a></div>
		</div><!--/side_content_title-->
		 <div class="sub-news-table">
			 <ul>
			 	<s:iterator var="resourcetable" value="resourcetables" status="st">
			 	<li><a href="ExcellentIndividual_excellentdetail?id=${resourcetable.id}">${resourcetable.des}</a><span>${resourcetable.uploadtime}</span></li>
				</s:iterator>
			 
			 </ul>
	       
	    </div><!--sub-news-table-->
	   
		<div class="m-page m-page-sr m-page-sm">
            <a href="#" class="first pageprv z-dis" onclick="skipUrl('ResourceTable_queryResourceTableList?page=${prePage }&rows=10','${totalPageCount }','${prePage }')"><span class="pagearr">&lt;</span>上一页</a>
        <s:iterator var="pages" value="totalpages" status="st">
              <a href="ResourceTable_queryResourceTableList?page=${pages }&rows=10">${pages }</a>
            </s:iterator>
            <a href="#" class="last pagenxt" onclick="skipUrl('ResourceTable_queryResourceTableList?page=${nextPage }&rows=10','${totalPageCount }','${nextPage }')">下一页<span class="pagearr">&gt;</span></a>
        </div><!--7-->
	</div><!--/side_content-->
</div>
<%@ include file="../home/footer.jsp" %>
</body>
</html>