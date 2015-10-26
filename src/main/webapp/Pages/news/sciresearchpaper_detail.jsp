<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<%@ include file="../home/header.jsp" %>
<div class="side_content">
		<div class="side_content_title">
			<div class="right"><i></i><a href="">首页</a>> <a href="SciResearchPaper_querySciResearchPaperList?page=1&rows=10">科研学术</a></div>
		</div><!--/side_content_title-->
	<div class="news-detail">
            <h1><s:property value="sciresearchpaperdetail.title"></s:property></h1>
            <div class="detail-time">浏览次数:　 更新时间:<s:property value="sciresearchpaperdetail.uploadtime"></s:property></div>
              <div class="abstract">
          <h3>【摘要】</h3><c:out value="${sciresearchpaperdetail.summary}" escapeXml="false" />
        </div>
       
            <div class="detail-con clear">
            <c:if test="${!empty(sciresearchpaperdetail.url)}">
             <a href="<s:property value="sciresearchpaperdetail.url"></s:property>">链接论文</a>
            </c:if>
            <c:if test="${!empty(sciresearchpaperdetail.savepath)}">
             <a href="downweb.jsp?filename=<s:property value="sciresearchpaperdetail.savepath"></s:property>">论文下载</a>
             </c:if>
            </div>
            <div class="detail-page">
                     </div>
        </div>
</div>
<%@ include file="../home/footer.jsp" %>
</body>
</html>