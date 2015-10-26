<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<%@ include file="../home/header.jsp" %>
<div class="side_content">
		<div class="side_content_title">
			<div class="right"><i></i><a href="index">首页</a>> <a href="News_queryNewsList?newtype=<s:property value="bsArticledetail.type"></s:property>&page=1&rows=10">${newstypename }</a></div>
		</div><!--/side_content_title-->
		<div class="news-detail">
            <h1><s:property value="bsArticledetail.title"></s:property></h1>
            <div class="detail-time">浏览次数:<s:property value="bsArticledetail.scannum"></s:property> 　 更新时间:<s:property value="bsArticledetail.updatedate"></s:property>　发布人：<s:property value="bsArticledetail.author"></s:property></div>
              <div class="abstract">
          <h3></h3><c:out value="${bsArticledetail.summary}" escapeXml="false" />
        </div>
        
            <div class="detail-img">
            <c:if test="${!empty(bsArticledetail.headerpicurl)}">
              <img src="showpic.jsp?pictruename=${bsArticledetail.headerpicurl}"/>
            </c:if>
          </div>
            <div class="detail-con clear">
            <c:out value="${bsArticledetail.content}" escapeXml="false" />
            <c:if test="${!empty(bsArticledetail.appendix)}">
            <label>附件下载:</label> 
             <a href="downweb.jsp?filename=<s:property value="bsArticledetail.appendix"></s:property>">附件</a>
             </c:if>
          
            </div>
            <div class="detail-page">
                     </div>
        </div>
</div>

<div  style="height:30px;"></div>
<div ><%@ include file="../home/footer.jsp" %></div>

</body>
</html>