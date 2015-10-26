<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<div class="main">
	<%@ include file="header.jsp"%>
	<div id="bbs">
	<div class="sent_bbs" style="display:block;">
		<div class="sent_new">
			<a href="BBSMessage_sentbbs">发帖</a>
		</div>
		<div class="m-page m-page-sr m-page-sm">
            <a href="#" class="first pageprv z-dis" onclick="skipUrl('BBSMessage_queryBBSMessageList?page=${prePage }&rows=30','${totalPageCount }','${prePage }')"><span class="pagearr">&lt;</span>上一页</a>
                     <s:iterator var="pages" value="totalpages" status="st">
              <a href="BBSMessage_queryBBSMessageList?page=${pages }&rows=30">${pages }</a>
            </s:iterator>
            <a href="#" class="last pagenxt" onclick="skipUrl('BBSMessage_queryBBSMessageList?page=${nextPage }&rows=30','${totalPageCount }','${nextPage }')">下一页<span class="pagearr">&gt;</span></a>
        </div><!--7-->
		
	</div>
	<div class="content_nav">
		<h3>===27基地军训网讨论区===</h3>
		<div class="choose_type">
			<table>
			   <tbody class="title">
					<tr>
					<th>
						<ul >
							<li>主题:</li>
							<li><a href="BBSMessage_queryBBSMessageList"><span>全部</span></a></li>
							<li class="current"><a class="filter" href="BBSMessage_queryBBSMessageList?isessence=1"><span>精华</span></a></li>
							<li class="pipe">|</li>
							<li>时间:</li>
							<li><a href="BBSMessage_queryBBSMessageList?timeperiod=ONE_DAY"><span>一天</span></a></li>
							<li><a  href="BBSMessage_queryBBSMessageList?timeperiod=TWO_DAY"><span>两天</span></a></li>
							<li><a  href="BBSMessage_queryBBSMessageList?timeperiod=ONE_WEEK"><span>周</span></a></li>
							<li><a  href="BBSMessage_queryBBSMessageList?timeperiod=ONE_MONTH"><span>月</span></a></li>
							<li class="pipe">|</li>
							<li><a href="">热门</a></li>
						</ul>
					</th>
					<td class="author"><a href="#">发表时间</a></td>
					<td class="nums"><a href="#" class="order ">回复</a>&nbsp;
					<a href="#" class="order">查看</a></td>
					<td class="lastpost"><cite><a href="">最后发表</a></cite></td>
					</tr>
					
				</tbody>
				<c:if test="${empty(bbsmessagelist)}">
							<tbody>
								<tr>
									<th class="subject_common"><label>没有记录！！！</label></th>

								</tr>

							</tbody>
						</c:if>		
			<s:iterator var="bbsmessage" value="bbsmessagelist" status="st">
									
									<tbody>
					<tr>
						<th class="subject_common">
							
	 					<a href="BBSMessage_queryBBSMessageDetail?id=${bbsmessage.id}">${bbsmessage.title}</a>
						</th>
						<td class="author">${bbsmessage.createTime}</td>
						<td class="nums"><strong>${bbsmessage.replyNum}</strong>/<em>${bbsmessage.scannum}</em></td>
						<td class="lastpost">
							<em><a href="#">${bbsmessage.lastcommenttime}</a></em>
						</td>
				  </tr>
				
				</tbody>
									</s:iterator>
				</table>
		</div>
	
	</div>
	<div class="sent_bbs">
		<div class="sent_new">
			<a href="BBSMessage_sentbbs">发帖</a>
		</div>
		<div class="m-page m-page-sr m-page-sm">
            <a href="#" class="first pageprv z-dis" onclick="skipUrl('BBSMessage_queryBBSMessageList?page=${prePage }&rows=30','${totalPageCount }','${prePage }')"><span class="pagearr">&lt;</span>上一页</a>
            <s:iterator var="pages" value="totalpages" status="st">
              <a href="BBSMessage_queryBBSMessageList?page=${pages }&rows=30">${pages }</a>
            </s:iterator>
         
            <a href="#" class="last pagenxt" onclick="skipUrl('BBSMessage_queryBBSMessageList?page=${nextPage }&rows=30','${totalPageCount }','${nextPage }')">下一页<span class="pagearr">&gt;</span></a>
        </div><!--7-->
		
	</div>
	</div>
	<div id="search" class="search" style="display:none">
		<div class="header"><a href="#">27基地</a>-> 搜索</div>
		<div class="s_content">
			<div class="m_search">
				<label class="label"><span>搜索</span><strong>帖子</strong></label>
					<form  action ="BBSMessage_queryBBSMessageList" method="get"  id="searchform" accept-charset="utf-8">
					<p>
					<input type="text" id="title" name="title"  size="45" maxlength="40" value="" class="txt" tabindex="1" />
					<script type="text/javascript">$('srchtxt').focus();</script>
					<button  name="searchsubmit" id="searchsubmit">搜索</button>
				
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
</script>
</body>
</html>