<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
    <%
String request_path = request.getContextPath();
request.setAttribute("request_path", request_path);
request.setAttribute("image_path", request_path+"/Pages");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<div class="main">
<jsp:include page="header.jsp"></jsp:include>
	<div id="bbs">
		<div class="sent_bbs" style="display:block;">
			<div class="sent_new">
				<a href="BBSMessage_sentbbs">发帖</a>
			</div>
			<div class="m-page m-page-sr m-page-sm" style="border:1px solid #ddd">
	            <a href="BBSMessage_queryBBSMessageList?page=1&rows=30" class="first pageprv z-dis">返回列表</a> 
	        </div><!--7-->
			
		</div>
		<div class="content_nav">
			<h3><s:property value="bbsMessageQuery.title"></s:property></h3>
			<div style="padding:20px;">
			<div class="detail1">
			<div class="detail_nav">
				<img src="${image_path }/images/detail_time.PNG"/>
				<label class="label1">发表于 <span><s:property value="bbsMessageQuery.createTime"></s:property></span></label>
			</div>
			<div class="detail_content">
				<div class="detail_title"><s:property value="bbsMessageQuery.title"></s:property></div>
				<div class="article"> <c:out value="${bbsMessageQuery.content}" escapeXml="false" /></div>
			<div style="height:20px;"></div>
			</div>
			</div>
			
			<s:iterator var="comment" value="commentslist" status="st">
			<div class="back">
		<div class="detail_nav">
					<img src="${image_path }/images/detail_time.PNG"/>
					<label class="label1">评论于 <span>${comment.commenttime }</span></label>
	    </div>
		<div class="back_content">${comment.commentctx }</div>
		</div>
			</s:iterator>
		
		
	</div>
		</div>
			<div class="sent_bbs" style="display:block;">
			<div class="sent_new">
				<a href="BBSMessage_sentbbs">发帖</a>
			</div>
			<div class="m-page m-page-sr m-page-sm" style="border:1px solid #ddd">
	            <a href="BBSMessage_queryBBSMessageList?page=1&rows=30" class="first pageprv z-dis">返回列表</a> 
	        </div><!--7-->
			
		</div>
	<div class="tell">
		
			<div class="comment"><label >评论：</label></div>
						<form action="MessageComment_save" method="get" id="subcommentform">
			<div style="padding-left:100px;">

			
			<textarea  style="width:700px;height:100px;" id="commentctx" name="commentctx"> </textarea></div>
	        <input type="hidden" name="messageid" id="messageid" value="<s:property value="bbsMessageQuery.id"></s:property>">
		<div class="button"><button type="submit" name="commentsubmit" id="commentsubmit" value="true" class="fabiao">发表</button></div>
			</form>
	</div>
	</div>
	<div id="search" class="search" style="display:none">
		<div class="header"><a href="#">27基地</a>-> 搜索</div>
		<div class="s_content">
			<div class="m_search">
				<label class="label"><span>搜索</span><strong>帖子</strong></label>
				<form  action ="BBSMessage_queryBBSMessageList" method="get"  id="searchform">
					<p>
					<input type="text" id="title" name="title"  size="45" maxlength="40" value="" class="txt" tabindex="1" />
					<script type="text/javascript">$('srchtxt').focus();</script>
					<button name="searchsubmit" id="searchsubmit">搜索</button>
					
					</p>
					</form>
			</div>
		<hr class="shadowline"/>
		<div class="quick_search">
			<h3>便捷搜索</h3>
			<table cellspacing="4" cellpadding="0" width="100%">
			<tr>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=ONE_HOUR">1 小时以内的新帖</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=FOUR_HOUR">4 小时以内的新帖</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=EIGHT_HOUR">8 小时以内的新帖</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=ONE_DAY">24 小时以内的新帖</a></td>
			</tr>
			<tr>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=ONE_WEEK">1 周内帖子</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=ONE_MONTH">1 月内帖子</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=HALF_YEAR">6 月内帖子</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=ONE_YEAR">1 年内帖子</a></td>
			</tr>
			</table>
		</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</div>
<script type="text/javascript">
$('#searchsubmit').onclick(function(){
	$('#searchform').submit();
});
$('#commentsubmit').onclick(function(){
	$('#subcommentform').submit();
});
</script>
</body>
</html>