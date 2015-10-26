<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<%@ include file="../home/header.jsp" %>
<div class="side_content">
		<div class="side_content_title">
			<div class="right"><i></i><a href="">首页</a>> <a href="ExcellentIndividual_queryExcellentList?page=1&rows=10">先进代表</a></div>
		</div><!--/side_content_title-->
		<div class="news-detail">
		
            <h1><s:property value="excellentdetail.name"></s:property></h1>
            <div class="detail-time">浏览次数:<s:property value="excellentdetail.scannum"></s:property>　 更新时间:<s:property value="excellentdetail.chooseTime"></s:property>　发布人：</div>
              
            <div class="detail-img">
              <c:if test="${!empty(excellentdetail.picurl)}">
            <img src="showpic.jsp?pictruename=${excellentdetail.picurl}"/>
            </c:if>
            </div>
            <div class="detail-con clear">
            <c:out value="${excellentdetail.des}" escapeXml="false" />
             
            </div>
        </div>
</div>
<%@ include file="../home/footer.jsp" %>
</body>
</html>