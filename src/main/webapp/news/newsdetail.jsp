<%@ page contentType="text/html; charset=utf-8" language="java"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%
String url = request.getRequestURL().toString();
String uri = request.getRequestURI();
String website =url.substring(0, url.indexOf(uri));
String request_path = website+request.getContextPath();
	String image_path = request_path + "/images/blue-themes";
	String css_path = request_path + "/css/blue-themes";
	String js_path = request_path + "/js";
	request.setAttribute("request_path", request_path);
	request.setAttribute("image_path", image_path);
	request.setAttribute("css_path", css_path);
	request.setAttribute("js_path", js_path);
	

	// 当前导航栏位置
	request.setAttribute("cur_nav", 5);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>新闻中心_${bsArticledetail.title}</title>
<meta http-equiv="Content-Type" name="description"
	content="${bsArticledetail.summary}" />
<link rel="stylesheet" type="text/css" href="${css_path}/subindex.css" />
<link rel="stylesheet" type="text/css" href="${css_path}/common.css" />
<link rel="stylesheet" type="text/css" href="${css_path}/news.css" />
<script language="JavaScript" type="text/javascript"
	src="${js_path}/jquery.js"></script>
<script language="JavaScript" type="text/javascript"
	src="${js_path}/jquery.blockUI.js"></script>
<script language="JavaScript" type="text/javascript"
	src="${js_path}/jquery.cookie.js"></script>
<script language="JavaScript" type="text/javascript"
	src="${js_path}/etUtil.js"></script>
<script type="text/javascript">
$(document).ready(function() {
     $("#fontsize").change(
        function()
        {
            $("#articcontentdetail").css({fontSize:this.value});//dom
        }
      )
});

</script>
</head>

<body>
	<div id="fade" class="black_overlay"></div>
	<div class="container" style="position: relative;">



		<div style="position: relative; margin: 5px 0px 10px 0px;">
			<div id="index_top">

				<div id="news_right_content_detail">
					<div class="one_artic_detail">
						<div class="artic_title_detail" align="center">
							<b style="font-size: 15px; font-family: sans-serif;"><c:out
									value="${bsArticledetail.title}" /> </b>
						</div>
						<div class="artic_author_detail" align="center">
							作者:
							<c:out value="${bsArticledetail.author}" />
							时间:
							<fmt:formatDate value="${bsArticledetail.createdate}" pattern="yyyy/MM/dd  HH:mm:ss" />
							<div class="bdsharebuttonbox" style="text-align: right;">
						
								<label for="fontsize">字体大小:</label> <select id="fontsize">
									<option value="12px">小号</option>
									<option value="14px">较小</option>
									<option value="15px" selected="selected">中号</option>
									<option value="18px">较大</option>
									<option value="24px">大号</option>
								</select>
							</div>
							<script>
								window._bd_share_config = {
									"common" : {
										"bdSnsKey" : {},
										"bdText" : "",
										"bdMini" : "2",
										"bdMiniList" : false,
										"bdPic" : "",
										"bdStyle" : "0",
										"bdSize" : "16"
									},
									"share" : {}
								};
								with (document)
									0[(getElementsByTagName('head')[0] || body)
											.appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='
											+ ~(-new Date() / 36e5)];
							</script>
						</div>

						<div class="normal_summary">
							概要：
							<c:out value="${bsArticledetail.summary}" escapeXml="false" />
						</div>
						<div class="artic_content_detail" id="articcontentdetail">
							<c:out value="${bsArticledetail.content}" escapeXml="false" />
						</div>
					</div>

				</div>

			</div>

		</div>

		<jsp:include page="../bottom.jsp"></jsp:include>

	</div>
</body>
</html>