<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
    <%
String request_path = request.getContextPath();
request.setAttribute("request_path", request_path);
request.setAttribute("image_path", request_path+"/Pages");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${request_path }/plugins/bbs/bbs.css"/>
<link href="${request_path }/plugins/bbs/ui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" />
<script src="${request_path }/plugins/bbs/JQuery/jquery-1.3.2.min.js"></script>
<script src="${request_path }/plugins/bbs/JQuery/jquery.validate.min.js"></script>
<script src="${request_path }/plugins/bbs/JQuery/messages_cn.js"></script>
<script src="${request_path }/plugins/bbs/ui/js/ligerBuild.min.js"></script>
 <script src="${request_path }/plugins/bbs/ckeditor/ckeditor.js"></script>
 
<script src="${request_path }/plugins/bbs/ui/js/commonutil.js"></script>
<title>27基地讨论区欢迎您</title>
<script>
function selectNews(showContent,displayContet,selfObj){
	// 操作标签（banner部分的切换）
	var tag = document.getElementById("News").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.className = "current";
	// 操作内容
	document.getElementById(displayContet).style.display = "none";	
	document.getElementById(showContent).style.display = "block";	
}
</script>
</head>
<body>
<div class="header">
		<img src="${image_path }/images/header1.jpg"/>
	</div>
	<div class="nav">
		<div class="umenu">
		<c:if test="${!empty(user)}">
		当前在线：<c:out value="${user.realname}" />[<c:out value="${user.username}" />]
		<a href="BBSMessage_logout">[注销]</a>
		</c:if>
				<c:if test="${empty(user)}">
		<a href="${request_path }/Pages/bbs/login.jsp">登录</a>
			<a href="${request_path }/Pages/bbs/reg.jsp">注册</a>
		</c:if>
			
		</div>
		<div class="menu">
			<ul id="News">
				<li class="current" onClick="selectNews('bbs','search',this)">论坛</li>
				<li onClick="selectNews('search','bbs',this)">搜索</li>
			</ul>
		</div>
	</div>

</body>
</html>