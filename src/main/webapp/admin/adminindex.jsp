<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
<%
	String request_path = request.getContextPath();
	String image_path = request_path + "/images/blue-themes";
	String css_path = request_path + "/css/blue-themes";
	String js_path = request_path + "/js";
	request.setAttribute("request_path", request_path);
	request.setAttribute("image_path", image_path);
	request.setAttribute("css_path", css_path);
	request.setAttribute("js_path", js_path);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>27基地军事训练信息化管理平台</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<link rel="stylesheet" type="text/css"
	href="${css_path}/jquery.alerts.css" />
<link rel="stylesheet" type="text/css" href="${css_path}/comments.css" />

<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript" src="${js_path}/ajaxfileupload.js"></script>
<%-- 
<script type="text/javascript" src="${js_path}/org.js"></script> --%>
<script type="text/javascript" src="${js_path}/menu.js"></script>
<script type="text/javascript" src="${js_path}/jquery.json-2.4.min.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${js_path}/jquery.alerts.js"></script>
<link href="${request_path}/umeditor/themes/default/css/umeditor.css"
	type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8"
	src="${request_path}/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${request_path}/umeditor/umeditor.min.js"></script>
<script type="text/javascript"
	src="${request_path}/umeditor/lang/zh-cn/zh-cn.js"></script>
<script language="JavaScript" type="text/javascript"
	src="${js_path}/footer.js" charset="UTF-8"></script>
</head>
<script type="text/javascript">
	$(function() {
		$('#tt').tree(
				{
					onClick : function(node) {
						if ($.trim(node.menuurl) == ""
								|| $.trim(node.menuurl) == "undefined"
								|| $.trim(node.menuurl) == null) {

						} else {
							var url = "${request_path}/" + node.menuurl;
							open1(node.text, url); // alert node text property when clicked
						}
					}
				});
	});

	function open1(name, url) {
		if ($('#right_tab').tabs('exists', name)) {
			$('#right_tab').tabs('select', name);
		} else {
			var iframestr=" <iframe  src="+url+" style=\"border: 0; width: 100%; height: 99%;\"></iframe>";
			if (url.indexOf("html") >= 0|| url.indexOf("monitoring")>= 0) {
				$('#right_tab').tabs('add', {
					title : name,
					content : iframestr,
					closable : true
				});
				//$("#tab_iframe_div").html(iframestr);
			} else {
				$('#right_tab').tabs('add', {
					title : name,
					href : url,
					closable : true

				});
			}

		}
	}
</script>
<body>
	<div></div>
	<div class="easyui-layout" style="width: auto; height: 790px;">
		<div data-options="region:'north'"
			style="height: 80px;background: url(${image_path}/body_bg.png) repeat-x scroll center 0 rgba(0, 0, 0, 0);">
			<font style="font-size: 30px; line-height: 75px; font-style: italic;">27基地军事训练信息化管理平台</font>
			<div style="float: right; margin-bottom: 5px; margin-top: 50px;">

				<c:if test="${!empty(user)}">
				管理员：<c:out value="${user.realname}" />[<c:out value="${user.username}" />]
					<a href="Admin_logout">[退出]</a>
				&nbsp;|&nbsp;
			</c:if>

				 <a href="${request_path}"
					target="_blank">网站前台</a> 
			</div>
		</div>
		<div data-options="region:'west',split:true" title="系统菜单"
			style="width: auto; max-width: 177px;">
			<div class="easyui-accordion" data-options="fit:true,border:false">
				<ul id="tt" class="easyui-tree"
					data-options="url:'${request_path}/Menu_query',method:'get',animate:true,dnd:true">
				</ul>
			</div>
		</div>
		<div data-options="region:'center',title:'',iconCls:'icon-ok'"
			style="width: auto;">
			<div id="right_tab" class="easyui-tabs"
				data-options="fit:true,border:false,plain:true">
				<div title="新闻管理" id="tab_iframe_div"
					data-options="href:'${request_path}/News_index'">
					
				</div>

			</div>
		</div>
	</div>
	<div id="bottom"><jsp:include page="../bottom.jsp"></jsp:include></div>
</body>
</html>