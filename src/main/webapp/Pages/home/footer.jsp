<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@page import="org.apache.struts2.components.Include,
                                     edu.cqu.fly.xccms.pojo.SiteUrl,
                                     edu.cqu.fly.xccms.cache.Cache,
                                     java.util.*
                                     "%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%
List<SiteUrl> siteurls = (List<SiteUrl>)Cache.getInstance().get("siteurl");
request.setAttribute("siteurls", siteurls);
%>
<body >
<%-- <div class="link">
	<span>友情链接：</span>
	
	<ul>
		<li>全军网站：
			<select onchange="openurl('this.value')">
			<c:forEach var="siteurl"  items="${siteurls }">
			<c:if test="${siteurl.type==0 }">
			<option value="${siteurl.url}">${siteurl.sitename}</option>
			</c:if>
			</c:forEach>
			</select>
		
		</li>
		<li>总装网站：
			<select onchange="openurl(this.value)">
			<c:forEach var="siteurl"  items="${siteurls }">
			<c:if test="${siteurl.type==1 }">
			<option value="${siteurl.url}">${siteurl.sitename}</option>
			</c:if>
			</c:forEach>
			</select></li>
		<li>基地网站：
			<select>
		<c:forEach var="siteurl"  items="${siteurls }">
			<c:if test="${siteurl.type==2 }">
			<option value="${siteurl.url}">${siteurl.sitename}</option>
			</c:if>
			</c:forEach>
			</select></li>
		<li>军训网站：
			<select>
			<c:forEach var="siteurl"  items="${siteurls }">
			<c:if test="${siteurl.type==3 }">
			<option value="${siteurl.url}">${siteurl.sitename}</option>
			</c:if>
			</c:forEach>
			</select></li>
		
	</ul>
</div> --%>
<script type="text/javascript">
function openurl(url){
	window.location.href=url;
}
</script>
<div class="footer">
<br/>
<p>版权所有  郑州大学新闻编辑处
<p>Copyright 2015
</div>
</body>
</html>