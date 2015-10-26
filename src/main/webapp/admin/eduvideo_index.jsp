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
<title>27基地军事训练信息化管理平台-教学视频管理</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>

</head>
<body>

	<table id="eduvideodg" title="教学视频列表" class="easyui-datagrid"
		style="width: auto; height: 616px" url="EduVideo_query"
		toolbar="#eduvideotoolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" pageSize="20">
		<thead>
			<tr>
				<th field="des" width="50">视频描述</th>
				<th field="savepath" width="50">存储路径</th>
				<th field="type" width="50">类别</th>
			</tr>
		</thead>
	</table>
	<div id="eduvideotoolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="neweduvideo()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editeduvideo()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyeduvideo()">删除</a>
	</div>
	
	

	<div id="eduvideodlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#eduvideodlg-buttons">
		<div class="ftitle">教学视频信息</div>
		<form id="eduvideofm" method="post" novalidate>
		<div class="fitem">
				<label>视频描述</label> <input name="des" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>类别</label> <input name="type" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>视频地址</label> <input name="savepath"  id="savepath" class="easyui-validatebox"
					required="true">
			</div>
		</form>
	</div>
	<div id="eduvideodlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveeduvideo()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#eduvideodlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript">
		var url;


		function neweduvideo() {
			$('#eduvideodlg').dialog('open').dialog('setTitle', '新增视频');
			$('#eduvideofm').form('clear');
			url = 'EduVideo_save';
		}
		function editeduvideo() {
			var row = $('#eduvideodg').datagrid('getSelected');
			if (row) {
				$('#eduvideodlg').dialog('open').dialog('setTitle', '编辑视频');
				$('#eduvideofm').form('clear');
				$('#eduvideofm').form('load', row);
				url = 'EduVideo_update?id=' + row.id;
			}
		}
		function saveeduvideo() {
		$('#eduvideofm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					if (result != "true") {
						jAlert('系统错误，请联系管理员', '错误提示');
					} else {
						$('#eduvideodlg').dialog('close'); // close the dialog
						$('#eduvideodg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyeduvideo() {
			var row = $('#eduvideodg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '你想要删除这个视频吗?', function(r) {
					if (r) {
						$.post('EduVideo_delete', {
							id : row.id
						}, function(result) {
							if (result = "true") {
								$('#eduvideodg').datagrid('reload'); // reload the user data
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