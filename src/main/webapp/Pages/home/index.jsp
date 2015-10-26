<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <script type="text/javascript">
	var index = 0 ;
	Swidth = 920 ;
	function page_pre(dpmain,obj){			
		index++ ;
		if(index>1)
			{
				index = 0 ;
			}
			$("#"+dpmain).stop(true, false).animate({left: -index*Swidth+"px"},600)
	}
	function page_next(dpmain,obj){
		index-- ;
		if(index<0)
		{
			index = 1 ;
		}
		//console.log(index*Swidth+"px");
		$("#"+dpmain).stop(true, false).animate({left: -index*Swidth+"px"},600)	
	}
</script>
<body>
<%
String request_path = request.getContextPath();
String image_path = request_path + "/Pages";
String css_path = request_path + "/css/blue-themes";
String js_path = request_path + "/js";
request.setAttribute("request_path", request_path);
request.setAttribute("image_path", image_path);
request.setAttribute("css_path", css_path);
request.setAttribute("js_path", js_path);
%>
<jsp:include page="header.jsp"></jsp:include>
<div class="news">
	<div class="pic_news">
		<div class="banner-box">
			<div class="m-focus-d ">
		       <div class="switchable-box">
		            <ul class="switchable-content">
		                	<s:iterator var="homebigpicnews" value="homebigpicnewslist" status="st">
										 <li><a href="News_newsdetail?id=${homebigpicnews.id}"  target="_blank"><img src="showpic.jsp?pictruename=${homebigpicnews.headerpicurl}"/></a></li>
									</s:iterator>
		            </ul>
		        </div> 
		    </div>
		    <!--m-focus-d -->
		</div>
	</div>
	<div class="words_news">
		<div class="m_news">
			<ul id="tags">
				<li  class="current" onmousemove="selectTag('tagContent0',this)">部队新闻</li>
				<li onmousemove="selectTag('tagContent1',this)">总装新闻</li>
			</ul>
			<a class="more" href="News_queryNewsList?newtype=ZH_DT&page=1&rows=10" id="tagContentmore0" >更多&gt;&gt;</a>
			<a class="more" href="ZZNews_queryZZNewsList?page=1&rows=10" id="tagContentmore1" style="display: none">更多&gt;&gt;</a>
		</div>
		<div id="tagContent">
			<div class="contain" id="tagContent0" style="display:block">
			
				<ul>
						<s:iterator var="armynews" value="armynewslist" status="st">
		             <li><a href="News_newsdetail?id=${armynews.id}" target="_blank">${armynews.title}<span></span></a></li>
				</s:iterator>
				</ul>
			</div>
			<div class="contain" id="tagContent1">
			
				<ul>
				<s:iterator var="zongzhuangnews" value="zongzhuangnewslist" status="st">
		             <li><a href="${zongzhuangnews.newsurl}" target="_blank">${zongzhuangnews.title}<span></span></a></li>
				</s:iterator>
				</ul>
			</div>
		</div>
	</div>
	<div class="notice">
		<h2>通知公告<a class="more1"  href="News_queryNewsList?newtype=TZ_GG&page=1&rows=10">更多&gt;&gt;</a></h2>
		<div class="notice_list">
		<ul>
			<s:iterator var="notify" value="notifylist" status="st">
		      <li><a href="News_newsdetail?id=${notify.id}" target="_blank" >${notify.title}<span>${notify.createdate}</span></a></li>
		</s:iterator>
				</ul>
		</div>
	</div>
</div>
<div class="news_detail">
<div class="news">
<div class="news_detail_header">
<h2>各单位新闻</h2>
</div>
<div class="news_detail_content">
	<div class="news_detail_nav">
		<ul id="News">
		<s:iterator value="unitnewsmaps" status="st">
		   <s:if test="#st.index < 11">
			<li onmousemove="selectNews('NewsContent<s:property value="#st.index"></s:property>',this)"><s:property value="key.orgname"></s:property></li>
			</s:if>
		</s:iterator>
	
		</ul>
	</div>
	
		<s:iterator value="unitnewsmaps" status="st">
		<s:if test="#st.first">
			<div class="news_title" id="NewsContent<s:property value="#st.index"></s:property>"  style="display:block;">
		<div class="news_pic">
			
			<s:iterator var="unitnews" value="value" status="sti">
			<s:if test="#sti.first">
			<div class="news_img">
			<c:if test="${!empty(unitnews.headerpicurl)}">
				<img src="showpic.jsp?pictruename=${unitnews.headerpicurl }" style="height:110px;width:170px;"/>
			</c:if>
			</div>
			<div class="news_pic_detail">
			<a class="title" href="#"></a><br/>
			<span style="width:200px;overflow:hidden;text-overflow:ellipsis; ">${unitnews.title}</span>
			<a class="detail" href="News_newsdetail?id=${unitnews.id}">详细&gt;&gt;</a>
			</div>
			</s:if>
			
			</s:iterator>
			<ul>
				<s:iterator var="unitnews" value="value" status="sti">
				<s:if test="!#sti.first">
				  <s:if test="#sti.index < 4">
				<li>·<a href="News_newsdetail?id=${unitnews.id}" target="_blank">${unitnews.title}</a></li>
				</s:if>
				</s:if>
				</s:iterator>
			
			</ul>
		</div>
		
			<div class="right_content">
		<ul>
			<s:iterator var="unitnews" value="value" status="sti">
				
				  <s:if test="#sti.index >= 4">
				<li>·<a href="News_newsdetail?id=${unitnews.id}" target="_blank">${unitnews.title}</a></li>
				</s:if>
				</s:iterator>
	
		</ul>
	</div>
	</div>
		</s:if>
	<s:if test="!#st.first">
		<div class="news_title" id="NewsContent<s:property value="#st.index"></s:property>"  style="display:none;">
		<div class="news_pic">
			
				<s:iterator var="unitnews" value="value" status="sti">
			<s:if test="#sti.first">
			<div class="news_img">
			
				<c:if test="${!empty(unitnews.headerpicurl)}">
				<img src="showpic.jsp?pictruename=${unitnews.headerpicurl }" style="height:110px;width:170px;"/>
			</c:if>
			
			</div>
			<div class="news_pic_detail">
			<a class="title" href="#"></a><br/>
			${unitnews.title}
			<a class="detail" href="News_newsdetail?id=${unitnews.id}">详细&gt;&gt;</a>
			</div>
			</s:if>
			
			</s:iterator>
			
			<ul>
				<s:iterator var="unitnews" value="value" status="sti">
				<s:if test="!#sti.first">
				  <s:if test="#sti.index < 4">
				<li>·<a href="News_newsdetail?id=${unitnews.id}" target="_blank">${unitnews.title}</a></li>
				</s:if>
				</s:if>
				</s:iterator>
			</ul>
		</div>
		
			<div class="right_content">
		<ul>
			<s:iterator var="unitnews" value="value" status="sti">
				
				  <s:if test="#sti.index >= 4">
				<li>·<a href="News_newsdetail?id=${unitnews.id}" target="_blank">${unitnews.title}</a></li>
				</s:if>
				</s:iterator>
		</ul>
	</div>
	</div>
	</s:if>
			
		</s:iterator>
			
<div style="height:35px"></div>
<div class="news_detail_nav_down">
	<ul id="News">
<s:iterator value="unitnewsmaps" status="st">

		   <s:if test="#st.index >=11">
			<li onmousemove="selectNews('NewsContent<s:property value="#st.index"></s:property>',this)"><s:property value="key.orgname"></s:property></li>
			</s:if>
		</s:iterator>
	</ul>
</div>
	
	</div>
	</div>
	<div class="info_all">
		<div class="title"><h2><a  href="News_querynewscontribute"><img alt="" src="${image_path }/images/star.jpg"/>信息统计</a></h2></div>
		<div class="info_detail">
		<span class="title1">投稿采用排行</span>
		
			<ul>
			<s:iterator var="newscontribute" value="newscontributelist" status="st">
			<s:if test="#st.index <7">
			  <li><span class="top"><s:property value="#st.index+1"></s:property></span>&nbsp;&nbsp;${newscontribute.orgname }<span>${newscontribute.newscount }</span></li>
		      
			</s:if>
		    </s:iterator>
			</ul>
		</div>
	</div>
</div>
<div class="tp">
	<div class="train">
		<div class="title">
			<h2>部队训练信息 <a href="News_queryNewsList?newtype=BD_XL&page=1&rows=10">更多&gt;&gt;</a></h2>
		</div>
		<div class="content">
			<div class="bgg"></div>
				<ul>
			<s:iterator var="trainnews" value="trainnewslist" status="st">
		      <li><a href="News_newsdetail?id=${trainnews.id }" target="_blank">${trainnews.title}<span></span></a></li>
		      </s:iterator>
				</ul>
				<div class="bg"></div>
				<div style="height:10px"></div>
		</div>
	</div>
	<div class="plan">
		<div class="title">
			<h2>军事等级评定 <a href="News_queryNewsList?newtype=GZ_JH&page=1&rows=10">更多&gt;&gt;</a></h2>
		</div>
		<div class="content">
			<div class="bgg"></div>
				<ul>
				<s:iterator var="workplan" value="workplanlist" status="st">
		      <li><a href="News_newsdetail?id=${workplan.id}" target="_blank">${workplan.title}<span></span></a></li>
		      </s:iterator>
				</ul>
				<div class="bg"></div>
				<div style="height:10px"></div>
		</div>
	</div>
<div class="weather">
		<h2>天气预报<a class="more1"  href="#">更多&gt;&gt;</a></h2>
		
		<table  border=0 cellSpacing=1 cellPadding=0 width=220 bgColor=#a4daee>
		  <TBODY>
		  <TR>
		    <TD width="40%" colSpan=3>
		      <DIV align=center>
		      <FONT color=#cc0000>西&nbsp;&nbsp;昌</DIV></FONT></TD></TR>
		  <TR>
		    <TD bgColor=#e7f7fe>
		      <DIV align=center>云</DIV></TD>
		    <TD bgColor=#e7f7fe colSpan=2>
		      <DIV align=center>晴间多云</DIV></TD></TR>
		  <TR>
		    <TD bgColor=#e7f7fe>
		      <DIV align=center>气 温</DIV></TD>
		    <TD bgColor=#e7f7fe colSpan=2>
		      <DIV align=center>7-25℃</DIV></TD></TR>
		  <TR>
		    <TD bgColor=#e7f7fe rowSpan=2 width="20%">
		      <DIV align=center>天气现象</DIV></TD>
		    <TD bgColor=#e7f7fe width="10%">
		      <DIV align=center>晚上</DIV></TD>
		    <TD bgColor=#e7f7fe width="30%">
		      <DIV align=center>无 </DIV></TD></TR>
		  <TR>
		    <TD bgColor=#e7f7fe>
		      <DIV align=center>白天</DIV></TD>
		    <TD bgColor=#e7f7fe>
		      <DIV align=center>无 </DIV></TD></TR>
		  <TR>
		    <TD width="40%" colSpan=3>
		      <DIV align=center><FONT color=#cc0000>文&nbsp;&nbsp;昌</FONT></DIV></TD></TR>
		  <TR>
		  <TR>
		    <TD bgColor=#e7f7fe>
		      <DIV align=center>云</DIV></TD>
		    <TD bgColor=#e7f7fe colSpan=2>
		      <DIV align=center>多云</DIV></TD></TR>
		  <TR>
		    <TD bgColor=#e7f7fe>
		      <DIV align=center>气 温</DIV></TD>
		    <TD bgColor=#e7f7fe colSpan=2>
		      <DIV align=center>21-24℃</DIV></TD></TR>
		  <TR>
		    <TD bgColor=#e7f7fe rowSpan=2 width="20%">
		      <DIV align=center>天气现象</DIV></TD>
		    <TD bgColor=#e7f7fe width="10%">
		      <DIV align=center>晚上</DIV></TD>
		    <TD bgColor=#e7f7fe width="30%">
		      <DIV align=center>小雨 </DIV></TD></TR>
		  <TR>
		    <TD bgColor=#e7f7fe>
		      <DIV align=center>白天</DIV></TD>
		    <TD bgColor=#e7f7fe>
		      <DIV align=center>零星小雨 </DIV></TD></TR></TBODY></TABLE>
		      
	</div>
</div>
<div class="pic_one">
	<img src="${image_path }/images/pic_one.jpg"/>
</div>
<div class="business">
	<div><img src="${image_path }/images/work_tech.jpg"/></div>
	<div class="content">
		<div class="content_nav">
			<ul id="Work">
				<li class="current" onmousemove="selectWork('WorkContent0',this)">教学视频</li>
				<li onmousemove="selectWork('WorkContent1',this)">科研学术</li>
				<li onmousemove="selectWork('WorkContent2',this)">军事基础</li>
				<li onmousemove="selectWork('WorkContent3',this)">上岗考核</li>
				<li onmousemove="selectWork('WorkContent4',this)">军队法规</li>
				<li onmousemove="selectWork('WorkContent5',this)">体系标准</li>
				<li onmousemove="selectWork('WorkContent6',this)">学历教育</li>
			</ul>
		</div>
		<div class="content_detail" id="WorkContent0" style="display:block">
			<div class="left">
				<ul>
					<s:iterator var="eduvideo" value="eduvideolist" status="st">
				    <s:if test="#st.index < 5">
				    <li><a href="${eduvideo.savepath }">${eduvideo.des }</a></li>
				    </s:if>
					
		      </s:iterator>
				
				</ul>
			</div>
			<div class="right">
				<ul>
					<s:iterator var="eduvideo" value="eduvideolist" status="st">
					 <s:if test="#st.index >=5 ">
				    <li><a href="${eduvideo.savepath }">${eduvideo.des }</a></li>
				    </s:if>
		      </s:iterator>
				</ul>
			</div>
		</div>
		<div class="content_detail" id="WorkContent1" style="display:none">
			<div class="left">
				<ul>
					<s:iterator var="sciresearchpaper" value="sciresearchpaperlist" status="st">
					 <s:if test="#st.index < 5 ">
					<li><a href="SciResearchPaper_querySciResearchPaperdetail?id=${sciresearchpaper.id }">${sciresearchpaper.title }</a></li>
					</s:if>
		      </s:iterator>
				</ul>
			</div>
			<div class="right" >
				<ul>
						<s:iterator var="sciresearchpaper" value="sciresearchpaperlist" status="st">
						 <s:if test="#st.index >=5 ">
					<li><a href="SciResearchPaper_querySciResearchPaperdetail?id=${sciresearchpaper.id }">${sciresearchpaper.title }</a></li>
					</s:if>
		      </s:iterator>
				</ul>
			</div>
		</div>
			<div class="content_detail" id="WorkContent2" style="display:none">
			<div class="left">
				<ul>
					<s:iterator var="militarybase" value="militarybaselist" status="st">
					 <s:if test="#st.index < 5 ">
					<li><a href="News_newsdetail?id=${militarybase.id}">${militarybase.title }</a></li>
					</s:if>
		      </s:iterator>
				
				</ul>
			</div>
			<div class="right">
				<ul>
					<s:iterator var="militarybase" value="militarybaselist" status="st">
					 <s:if test="#st.index >=5 ">
					<li><a href="News_newsdetail?id=${militarybase.id}">${militarybase.title }</a></li>
					</s:if>
		      </s:iterator>
				</ul>
			</div>
		</div>
			<div class="content_detail" id="WorkContent3" style="display:none">
			<div class="left">
				<ul>
					<s:iterator var="sgkh" value="sgkhlist" status="st">
					 <s:if test="#st.index < 5 ">
					<li><a href="News_newsdetail?id=${sgkh.id }">${sgkh.title }</a></li>
					</s:if>
		      </s:iterator>
				</ul>
			</div>
			<div class="right" >
				<ul>
							<s:iterator var="sgkh" value="sgkhlist" status="st">
					 <s:if test="#st.index >= 5 ">
					<li><a href="News_newsdetail?id=${sgkh.id }">${sgkh.title }</a></li>
					</s:if>
		      </s:iterator>
				</ul>
			</div>
		</div>
			<div class="content_detail" id="WorkContent4" style="display:none">
			<div class="left">
				<ul>
					<s:iterator var="jdfg" value="jdfglist" status="st">
					 <s:if test="#st.index < 5 ">
					<li><a href="News_newsdetail?id=${jdfg.id }">${jdfg.title }</a></li>
					</s:if>
		      </s:iterator>
				</ul>
			</div>
			<div class="right" >
				<ul>
							<s:iterator var="jdfg" value="jdfglist" status="st">
					 <s:if test="#st.index >= 5 ">
					<li><a href="News_newsdetail?id=${jdfg.id }">${jdfg.title }</a></li>
					</s:if>
		      </s:iterator>
				</ul>
			</div>
		</div>
			<div class="content_detail" id="WorkContent5" style="display:none">
				<div class="left">
				<ul>
					<s:iterator var="txbz" value="txbzlist" status="st">
					 <s:if test="#st.index < 5 ">
					<li><a href="News_newsdetail?id=${txbz.id }">${txbz.title }</a></li>
					</s:if>
		      </s:iterator>
				</ul>
			</div>
			<div class="right" >
				<ul>
							<s:iterator var="txbz" value="txbzlist" status="st">
					 <s:if test="#st.index >= 5 ">
					<li><a href="News_newsdetail?id=${txbz.id }">${txbz.title }</a></li>
					</s:if>
		      </s:iterator>
				</ul>
			</div>
		</div>
			<div class="content_detail" id="WorkContent6" style="display:none">
				<div class="left">
				<ul>
					<s:iterator var="xljy" value="xljylist" status="st">
					 <s:if test="#st.index < 5 ">
					<li><a href="News_newsdetail?id=${xljy.id }">${xljy.title }</a></li>
					</s:if>
		      </s:iterator>
				</ul>
			</div>
			<div class="right" >
				<ul>
							<s:iterator var="xljy" value="xljylist" status="st">
					 <s:if test="#st.index >= 5 ">
					<li><a href="News_newsdetail?id=${xljy.id }">${xljy.title }</a></li>
					</s:if>
		      </s:iterator>
				</ul>
			</div>
		</div>
	</div>
	<div class="communicate">
		<div class="title"><h2>交流互动</h2></div>
		<div class="choose">
			<div class="talking">
				<h3><a href="BBSMessage_queryBBSMessageList?page=1&rows=30"  target="_blank">建言献策</a></h3>
				<h3><a href="#"  target="_blank">军训处信箱</a></h3>
			</div>
		</div>
	</div>
</div>
<div class="rl">
<div class="represent">
	<div class="title"><h2>典型代表</h2>
		<a href="ExcellentIndividual_queryExcellentList?page=1&rows=10">更多 &gt;&gt;</a>
	</div>
	<div class="content">
	<div class="warp_c clear">
		
		<div class="m-product-1 j-li-hover">
		    <ul class="j-product-scroll clear">
		  
		         <s:iterator var="excellentindividual" value="excellentindividuallist" status="st">
		         
		         <s:if test="#st.index <1">
		         <li>
		            <div class="u-box hover">
		                      <a class="u-img" href="ExcellentIndividual_excellentdetail?id=${excellentindividual.id }"><img src="showpic.jsp?pictruename=${excellentindividual.picurl }" alt="" /></a>
		                <div class="u-name">
		               		<p class="p1">
		               			<b>${excellentindividual.name}</b>
		               		</p>
		               		
		               </div>
		            </div>
		        </li>
		        </s:if>
		         <s:if test="#st.index >=1">
		        <li>
		            <div class="u-box">
		                <a class="u-img" href="ExcellentIndividual_excellentdetail?id=${excellentindividual.id }"><img src="showpic.jsp?pictruename=${excellentindividual.picurl }" alt="" /></a>
		                <div class="u-name">
		               		<p class="p1">
		               			<b>${excellentindividual.name}</b>
		               		</p>
		               		
		               </div>
		            </div>
		        </li>
		        </s:if>
		      </s:iterator>
		    </ul>
		    <a href="#" class="u-prev j-prev-1"></a>
		    <a href="#" class="u-next j-next-1"></a>
		</div>
		<script type="text/javascript">
			$(function() {
				$('.m-product-1 .j-product-scroll').carouFredSel({
					responsive: true,
					direction :	"left",
					prev: '.j-prev-1',
					next: '.j-next-1',
					width: '100%',
					pagination:'.caropage',
					auto:{
						play:true,
						pauseOnHover:true,
						timeoutDuration:3000
						},
					pause:3000,
					scroll: 1,
					items: {
						visible: {
							min: 3,
							max: 3
						}
					}
				});

				
			});
		</script>
	</div><!--/warp_c-->
   </div>
   
</div>
<div class="login">
  <form id="form1" action="post">
	  <h2>协同办公系统</h2>
		<div class="notice_list">
		  <ul>
			  <li>用户名：<input type="text" name="username" style="width:150px"></li>
			  <li>密&nbsp;&nbsp;&nbsp;码：<input type="text" name="password" style="width:150px"></li>
			  <li><input type="submit" value="登 录" ></li>
		  </ul>
	  </div>
  </form>
</div>   
<div class="pic_two">
	<img src="${image_path }/images/pic_two.jpg"/>
</div>
<div class="download">
	<div class="title1"><h3>应用服务</h3></div>
	<div class="file">
		<div class="title"><h2>常用表格<a class="more_down"  href="ResourceTable_queryResourceTableList?page=1&rows=10" >&gt;&gt;</a></h2></div>
		
		<div class="content">
			<ul>
		<s:iterator var="resourcetable" value="resourcetablelist" status="st">
		      <li><a href="downweb.jsp?filename=${resourcetable.savepath}" target="_blank">${resourcetable.des}</a></li>
		      </s:iterator>
			</ul>
		</div>
	</div>
	<div class="software">
		<div class="title"><h2>下载软件<a class="more_down"  href="OfficeSoftware_queryOfficeSoftwareList?page=1&rows=10" >&gt;&gt;</a></h2></div>
		<div class="content">
			<ul>
				<s:iterator var="officesoftware" value="officesoftwarelist" status="st">
		      <li><a href="downweb.jsp?filename=${officesoftware.savepath}" target="_blank">${officesoftware.des}</a></li>
		      </s:iterator>
			</ul>
		</div>
	</div>
	<div class="cloud">
		<div class="title"><h2>&nbsp;<a href="http://127.0.0.1:8080/WTF01/Login_AccessYunPlatForm" >云存储</a></h2></div>
		<div class="content">
		<div style="height:10px"></div>
		<a href="http://127.0.0.1:8080/WTF01/Login_AccessYunPlatForm" ><img alt="进入云存储" title="进入云存储" src="${image_path }/images/cloud.jpg"></a></div>
	</div>
</div>
<div class="footer_index"><%@ include file="footer.jsp"%></div>
</body>
</html>