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
<title>27基地军事训练信息化管理平台-资源管理</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>

</head>
<body>
	<div id="resourcetb" style="padding: 5px; height: auto">
		<div>
			资源url:<input type="text" id="resourceurlquery"> <a
				href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" onclick="queryResource()">搜索</a>
		</div>
	</div>
	<table id="resourcedg" title="资源列表" class="easyui-datagrid"
		style="width: auto; height: 616px" url="Resource_query"
		toolbar="#resourcetoolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" pageSize="20">
		<thead>
			<tr>
				<th field="resourceUrl" width="50">资源url</th>
				<th field="resouceName" width="50">资源名字</th>
				<th field="resourceType" width="50">资源类型</th>
				<th field="menuid" width="70">所属菜单</th>
			</tr>
		</thead>
	</table>
	<div id="resourcetoolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newResource()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editResource()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyResource()">删除</a>
	</div>

	<div id="resourcedlg" class="easyui-dialog"
		style="width: 400px; height: 380px; padding: 10px 20px" closed="true"
		buttons="#resourcedlg-buttons">
		<div class="ftitle">资源信息</div>
		<form id="resourcefm" method="post" novalidate>
			<div class="fitem">
				<label>资源URL</label> <input name="resourceUrl" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>资源名字:</label> <input name="resouceName" class="easyui-validatebox"
					required="true">
			</div>
			
			<div class="fitem">
				<label>资源类型:</label> <input name="resourceType"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>所属菜单:</label> 
				<select id="menuid" name ="menuid" class="easyui-combotree" style="width:200px;"
                    data-options="url:'Menu_query',required:true">
</select>

			</div>
		</form>
	</div>
	<div id="resourcedlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveResource()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#resourcedlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript">
		var url;

		function queryResource() {
			var resourceurlquery = $('#resourceurlquery').val();
			$('#resourcedg').datagrid('load', {
				resourceurlquery : resourceurlquery
			});
		}
		function newResource() {
			$('#resourcedlg').dialog('open').dialog('setTitle', '新增资源');
			$('#resourcefm').form('clear');
			url = 'Resource_save';
		}
		function editResource() {
			var row = $('#resourcedg').datagrid('getSelected');
			if (row) {
				$('#resourcedlg').dialog('open').dialog('setTitle', '编辑资源');
				$('#resourcefm').form('clear');
				$('#resourcefm').form('load', row);
				url = 'Resource_update?id=' + row.id;
			}
		}
		function saveResource() {
			$('#resourcefm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					if (result != "true") {
						jAlert('系统错误，请联系管理员', '错误提示');
					} else {
						$('#resourcedlg').dialog('close'); // close the dialog
						$('#resourcedg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyResource() {
			var row = $('#resourcedg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '你想要删除这个资源吗?', function(r) {
					if (r) {
						$.post('Resource_delete', {
							id : row.id
						}, function(result) {
							if (result = "true") {
								$('#resourcedg').datagrid('reload'); // reload the user data
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
#resourcefm {
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