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
<title>27基地军事训练信息化管理平台-网站超链接管理</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>

</head>
<body>

	<table id="siteurldg" title="超链接列表" class="easyui-datagrid"
		style="width: auto; height: 616px" url="SiteUrl_query"
		toolbar="#siteurltoolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" pageSize="20">
		<thead>
			<tr>
				<th field="sitename" width="50">站点名称</th>
				<th field="type" width="50"   formatter="formattertype">类型</th>
				<th field="url" width="50">站点url</th>
			</tr>
		</thead>
	</table>
	<div id="siteurltoolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newsiteurl()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editsiteurl()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroysiteurl()">删除</a>
	</div>
	
	

	<div id="siteurldlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#siteurldlg-buttons">
		<div class="ftitle">站点信息</div>
		<form id="siteurlfm" method="post" novalidate>
		<div class="fitem">
				<label>站点名称</label> <input name="sitename" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>类型</label> 
				<select id="type" name="type"  class="easyui-combobox" panelHeight="auto"
					style="width: 100px">
					<option value='0'>全军网站</option>
					<option value='1'>总装网站</option>
					<option value='2'>基地网站</option>
					<option value='3'>军训网站</option>
					</select>
			</div>
			<div class="fitem">
				<label>站点URL</label> <input name="url"  class="easyui-validatebox"
					required="true">
					
			</div>
		</form>
	</div>
	<div id="siteurldlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="savesiteurl()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#siteurldlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript">
		var url;


		function newsiteurl() {
			$('#siteurldlg').dialog('open').dialog('setTitle', '新增站点');
			$('#siteurlfm').form('clear');
			url = 'SiteUrl_save';
		}
		function editsiteurl() {
			var row = $('#siteurldg').datagrid('getSelected');
			if (row) {
				$('#siteurldlg').dialog('open').dialog('setTitle', '编辑站点');
				$('#siteurlfm').form('clear');
				$('#siteurlfm').form('load', row);
				url = 'SiteUrl_update?id=' + row.id;
			}
		}
		function savesiteurl() {
		$('#siteurlfm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					if (result != "true") {
						jAlert('系统错误，请联系管理员', '错误提示');
					} else {
						$('#siteurldlg').dialog('close'); // close the dialog
						$('#siteurldg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroysiteurl() {
			var row = $('#siteurldg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '你想要删除这个站点吗?', function(r) {
					if (r) {
						$.post('SiteUrl_delete', {
							id : row.id
						}, function(result) {
							if (result = "true") {
								$('#siteurldg').datagrid('reload'); // reload the user data
							} else {
								jAlert('系统错误，请联系管理员', '错误提示');
							}
						}, 'json');
					}
				});
			}
		}
        
        function formattertype(value,rowData,rowIndex){
        	var s="";
        	if(value == "0"){
        		s ="全军网站";
        	}else if(value=="1"){
        		s ="总装网站";
        	}else if(value=="2"){
        		s ="基地网站";
        	}else if(value=="3"){
        		s ="军训网站";
        	}
        	return s;
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