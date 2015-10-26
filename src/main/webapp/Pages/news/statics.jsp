<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<%@ include file="../home/header.jsp" %>
<div class="side_content">
		<div class="side_content_title">
			<div class="right"><i></i><a href="">首页</a>> <a href="ExcellentIndividual_queryExcellentList?page=1&rows=10">投稿排行</a></div>
		</div><!--/side_content_title-->
			<div class="news-detail">
		
            <h1>投稿排行</h1>
            <div class="detail-time"></div>
              <table width="100%">
              <tr>
              <td>排名</td>
              <td>单位</td>
              <td>投稿数</td>
              </tr>
                 <s:iterator var="newscontribute" value="newscontributelist" status="st">
		         <tr>
		         <td> <s:property value="#st.index+1"></s:property></td>
		        <td>${newscontribute.orgname }</td>
		        <td>${newscontribute.newscount }</td>
		  </s:iterator>
              </table>
         
        </div>
		
</div>
<%@ include file="../home/footer.jsp" %>
</body>
</html>