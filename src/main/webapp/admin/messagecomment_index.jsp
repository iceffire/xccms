<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>27基地军事训练信息化管理平台-BBS会话评论管理</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>

</head>
<body>

	<table id="commentdg" title="BBS评论列表" class="easyui-datagrid"
		style="width: auto; height: 616px" url="MessageComment_query?messageid=${messageid }"
		toolbar="#commenttoolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" pageSize="20">
		<thead>
			<tr>
				<th field="messageid" width="50">帖子ID</th>
				<th field="commentctx" width="50">评论</th>
				<th field="commenttime" width="50" formatter="formatterdate">评论时间</th>
				<th field="userid" width="50" >用户ID</th>
			</tr>
		</thead>
	</table>
	<div id="commenttoolbar">
		 <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroycomment()">删除</a>
	</div>
	
	<script type="text/javascript">
		var url;
		
		function destroycomment() {
			var row = $('#commentdg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '你想要删除这条评论吗?', function(r) {
					if (r) {
						$.post('MessageComment_delete', {
							id : row.id
						}, function(result) {
							if (result = "true") {
								$('#commentdg').datagrid('reload'); // reload the user data
							} else {
								jAlert('系统错误，请联系管理员', '错误提示');
							}
						}, 'json');
					}
				});
			}
		}
	
	</script>
	<style type="text/css">
#rolefm {
	margin: 0;
	padding: 10px 30px;
}

.ftitle {
	font-size: 14px;
	font-weight: bold;
	padding: 5px 0;
	margin-bottom: 10px;
	border-bottom: 1px solid #ccc;
}

.fitem {
	margin-bottom: 5px;
}

.fitem label {
	display: inline-block;
	width: 80px;
}
</style>
</body>
</html>