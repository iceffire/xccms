<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@page import="org.apache.struts2.components.Include,
                                     edu.cqu.fly.xccms.cache.Cache
                                     "%> 
<%
String request_path = request.getContextPath();

String image_path = request_path + "/Pages";
String css_path = request_path + "/css/blue-themes";
String js_path = request_path + "/js";
request.setAttribute("request_path", request_path);
request.setAttribute("image_path", image_path);
request.setAttribute("page_path", image_path);
request.setAttribute("css_path", css_path);
request.setAttribute("js_path", js_path);
Integer onlineNum =  (Integer)Cache.getInstance().get("onlineNum");
Long guesscount= (Long)Cache.getInstance().get("guesscount");
request.setAttribute("guesscount", guesscount);
request.setAttribute("onlinenum", onlineNum);
%>
<%@ page import ="java.util.*"%>
<%! public String getDayWeek(int n)
    { String week[]={"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
      return week[n];
    }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>27基地军训网欢迎您</title>
<link rel="stylesheet" type="text/css" href="${request_path }/plugins/homecss/index.css"/>
<link rel="stylesheet" type="text/css" href="${request_path }/plugins/homecss/newspic.css"/>

<link href="${request_path }/plugins/bbs/ui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" />
<script type="text/javascript" src="${request_path }/plugins/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="${request_path }/plugins/js/globle.js"></script>
<script type="text/javascript" src="${request_path }/plugins/js/focus.js"></script>
<link rel="stylesheet" type="text/css" href="${request_path }/plugins/slidestyle/style.css">
<script type="text/javascript" src="${request_path }/plugins/slidestyle/global.js"></script>
<script type="text/javascript" src="${request_path }/plugins/slidestyle/focus.js"></script>
<script type="text/javascript" src="${request_path }/plugins/slidestyle/carouFredSel-6.2.1-min.js"></script>
<link rel="stylesheet" type="text/css" href="${request_path }/plugins/homecss/news.css">
<script src="${request_path }/plugins/bbs/ui/js/ligerBuild.min.js"></script>
<script src="${request_path }/plugins/bbs/ui/js/commonutil.js"></script>
</head>
<script>
$(document).ready(function(){
	$(".navname li").mouseover(function(){
		$(this).find(".inner").show();
		
		}).mouseout(function(){
			$(this).find(".inner").hide();
		})
	});
	
</script>
<SCRIPT type=text/javascript>
function selectNews(showContent,selfObj){
	// 操作标签（banner部分的切换）
	var tag = document.getElementById("News").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.className = "current";
	// 操作内容
	for(i=0; j=document.getElementById("NewsContent"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";	
}
function selectTag(showContent,selfObj){
	// 操作标签（banner部分的切换）
	var tag = document.getElementById("tags").getElementsByTagName("li");
	var taglength = tag.length;
	var ck;
	for(i=0; i<taglength; i++){
		if(selfObj == tag[i]){
			ck = i;
		}
		tag[i].className = "";
		
	}
	selfObj.className = "current";
	// 操作内容
	for(i=0; j=document.getElementById("tagContent"+i); i++){
		j.style.display = "none";
	}
	for(i=0; j=document.getElementById("tagContentmore"+i); i++){
		if(i == ck){
			j.style.display = "";
		} else{
			j.style.display = "none";
		}
		
	}
	document.getElementById(showContent).style.display = "block";	
}
function selectWork(showContent,selfObj){
	// 操作标签（banner部分的切换）
	var tag = document.getElementById("Work").getElementsByTagName("li");
	var taglength = tag.length;
	for(i=0; i<taglength; i++){
		tag[i].className = "";
	}
	selfObj.className = "current";
	// 操作内容
	for(i=0; j=document.getElementById("WorkContent"+i); i++){
		j.style.display = "none";
	}
	document.getElementById(showContent).style.display = "block";	
}



//设为首页
function SetHome(obj,vrl){
        try{
                obj.style.behavior='url(#default#homepage)';obj.setHomePage(vrl);
        }
        catch(e){
                if(window.netscape) {
                        try {
                                netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
                        }
                        catch (e) {
                                alert("此操作被浏览器拒绝！\n请在浏览器地址栏输入“about:config”并回车\n然后将 [signed.applets.codebase_principal_support]的值设置为'true',双击即可。");
                        }
                        var prefs = Components.classes['@mozilla.org/preferences-service;1'].getService(Components.interfaces.nsIPrefBranch);
                        prefs.setCharPref('browser.startup.homepage',vrl);
                 }
        }
}
//加入收藏夹
function Addme() {
    url = document.URL;  //你自己的主页地址
    title = "****";  //你自己的主页名称
    window.external.AddFavorite(url, title);
}
</SCRIPT>
<body>
<center>
<div id="header">
<div class="nav">
<div class="nav_font">
	<label>网站编号:988234343220</label> &nbsp;  &nbsp; 
	<% Calendar calendar=Calendar.getInstance(); //创建一个日历对象。
            calendar.setTime(new Date());//用当前时间初始化日历时间。
   String 年=String.valueOf(calendar.get(Calendar.YEAR)),
          月=String.valueOf(calendar.get(Calendar.MONTH)+1),
          日=String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)),
          星期=getDayWeek(calendar.get(Calendar.DAY_OF_WEEK)-1);
%>
今天是&nbsp; <%=年%>年<%=月%>月<%=日%>日&nbsp; <%=星期%>
&nbsp; 在线人数:${onlinenum }&nbsp; 访问人数:${guesscount }&nbsp;  
&nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  
&nbsp;&nbsp;&nbsp;&nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  
	&nbsp;  &nbsp; &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  
	&nbsp;  &nbsp; &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  
	&nbsp;  &nbsp; &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  
<a href="javascript:Addme();">收藏本站</a>&nbsp;
<a href="javascript:SetHome(this,window.location);">设置主页</a>&nbsp;

<a href="${request_path }/admin/admin_login.jsp">管理登录</a>
</div>
</div>
<img src="${image_path }/images/header.jpg"/>
</div>

<div class="navname">
	<ul >  
    <%-- <li><a href="index">首页</a></li>  
    <li><a href="${page_path }/news/news_home.jsp">新闻动态</a>
    	<ul class="inner"> --%>
   			 <li><a href="News_queryNewsList?newtype=&page=1&rows=10">各单位新闻</a></li>
   			 <li><a href="ZZNews_queryZZNewsList?page=1&rows=10">总装新闻</a></li>
   			 <li><a href="News_queryNewsList?newtype=TZ_GG&page=1&rows=10">通知公告</a></li>
   			 <li><a href="News_queryNewsList?newtype=ZH_DT&page=1&rows=10">部队新闻</a></li>
   	<!-- 	 </ul>
   	</li>   -->
    <!-- <li><a href="#">业务管理</a>
   		 <ul class="inner">
   		 	 <li><a href="News_queryNewsList?newtype=BD_XL&page=1&rows=10">部队训练</a></li>
   			<li><a href="News_queryNewsList?newtype=ZC_FG&page=1&rows=10">政策法规</a></li>
   			<li><a href="News_queryNewsList?newtype=JS_JC&page=1&rows=10">军事基础</a></li>
   			<li><a href="News_queryNewsList?newtype=SG_KH&page=1&rows=10">上岗考核</a></li>
   			<li><a href="News_queryNewsList?newtype=JS_FG&page=1&rows=10">军事法规</a></li>
   			    <li><a href="News_queryNewsList?newtype=TX_BZ&page=1&rows=10">体系标准</a></li>
   			<li><a href="ExcellentIndividual_queryExcellentList?page=1&rows=10">典型代表</a></li>
   		 </ul>
    </li>  -->
    <!-- <li><a href="#">资格认证</a></li>
    <li><a href="#">资源共享</a>
    	 <ul class="inner">
    	<li><a href="SciResearchPaper_querySciResearchPaperList?page=1&rows=10">科研学术</a></li>
   		    <li><a href="EduVideo_queryEduVideoList?page=1&rows=10">教学视频</a></li>
   		
   		    
       </ul>
     </li> -->
     <li><a href="BBSMessage_queryBBSMessageList?page=1&rows=30" target="_blank">建言献策</a>
     	<!-- <ul class="inner">
     		<li><a href="BBSMessage_queryBBSMessageList?page=1&rows=30" target="_blank">建言献策</a></li>
     		<li><a href="#">军训处信箱</a></li>
     	</ul> -->
     </li>
    <!-- <li><a href="#">下载专区</a>
  	  <ul class="inner">
   		 <li><a href="ResourceTable_queryResourceTableList?page=1&rows=10">资料下载</a></li>
  	  </ul>
    </li> -->
    
</ul>  
</div>

</center>

</body>
</html>