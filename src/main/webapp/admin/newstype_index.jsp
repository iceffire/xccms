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
<title>27基地军事训练信息化管理平台-新闻类别管理</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>

</head>
<body>

	<table id="newsTypedg" title="新闻类别列表" class="easyui-datagrid"
		style="width: auto; height: 616px" url="NewsType_query"
		toolbar="#newsTypetoolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" pageSize="20">
		<thead>
			<tr>
			<th field="typecode" width="50">类别编码</th>
				<th field="type" width="50">新闻类别</th>
				
			</tr>
		</thead>
	</table>
	<div id="newsTypetoolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newnewsType()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editnewsType()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroynewsType()">删除</a>
	</div>
	
	

	<div id="newsTypedlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#newsTypedlg-buttons">
		<div class="ftitle">新闻类别信息</div>
		<form id="newsTypefm" method="post" novalidate>
		<div class="fitem">
				<label>编码</label> <input name="typecode" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>类别</label> <input name="type" class="easyui-validatebox"
					required="true">
			</div>
		</form>
	</div>
	<div id="newsTypedlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="savenewsType()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#newsTypedlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript">
		var url;


		function newnewsType() {
			$('#newsTypedlg').dialog('open').dialog('setTitle', '新增类别');
			$('#newsTypefm').form('clear');
			url = 'NewsType_save';
		}
		function editnewsType() {
			var row = $('#newsTypedg').datagrid('getSelected');
			if (row) {
				$('#newsTypedlg').dialog('open').dialog('setTitle', '编辑类别');
				$('#newsTypefm').form('clear');
				$('#newsTypefm').form('load', row);
				url = 'NewsType_update?id=' + row.id;
			}
		}
		function savenewsType() {
		$('#newsTypefm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					if (result != "true") {
						jAlert('系统错误，请联系管理员', '错误提示');
					} else {
						$('#newsTypedlg').dialog('close'); // close the dialog
						$('#newsTypedg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroynewsType() {
			var row = $('#newsTypedg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '你想要删除这个类别吗?', function(r) {
					if (r) {
						$.post('NewsType_delete', {
							id : row.id
						}, function(result) {
							if (result = "true") {
								$('#newsTypedg').datagrid('reload'); // reload the user data
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