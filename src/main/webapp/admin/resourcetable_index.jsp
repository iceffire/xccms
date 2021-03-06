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
<title>27基地军事训练信息化管理平台-资料表格管理</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>

</head>
<body>

	<table id="resourcetabledg" title="资料表格列表" class="easyui-datagrid"
		style="width: auto; height: 616px" url="ResourceTable_query"
		toolbar="#resourcetabletoolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" pageSize="20">
		<thead>
			<tr>
				<th field="des" width="50">表格描述</th>
				<th field="savepath" width="50">存储路径</th>
				<th field="type" width="50">类别</th>
			</tr>
		</thead>
	</table>
	<div id="resourcetabletoolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newresourcetable()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editresourcetable()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyresourcetable()">删除</a>
	</div>
	
	

	<div id="resourcetabledlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#resourcetabledlg-buttons">
		<div class="ftitle">资料表格信息</div>
		<form id="resourcetablefm" method="post" novalidate>
		<div class="fitem">
				<label>表格描述</label> <input name="des" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>类别</label> <input name="type" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>存储路径</label> <input name="savepath"  id="savepath" class="easyui-validatebox"
					required="true">
						<input type="file" id="upfile" name="upfile" />
					<a href="javascript:void(0)" class="easyui-linkbutton"
			            iconCls="icon-add" plain="true" onclick="fileupload()">上传</a>
			</div>
		</form>
	</div>
	<div id="resourcetabledlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveresourcetable()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#resourcetabledlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript">
		var url;


		function newresourcetable() {
			$('#resourcetabledlg').dialog('open').dialog('setTitle', '新增表格');
			$('#resourcetablefm').form('clear');
			url = 'ResourceTable_save';
		}
		function editresourcetable() {
			var row = $('#resourcetabledg').datagrid('getSelected');
			if (row) {
				$('#resourcetabledlg').dialog('open').dialog('setTitle', '编辑表格');
				$('#resourcetablefm').form('clear');
				$('#resourcetablefm').form('load', row);
				url = 'ResourceTable_update?id=' + row.id;
			}
		}
		function saveresourcetable() {
		$('#resourcetablefm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					if (result != "true") {
						jAlert('系统错误，请联系管理员', '错误提示');
					} else {
						$('#resourcetabledlg').dialog('close'); // close the dialog
						$('#resourcetabledg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyresourcetable() {
			var row = $('#resourcetabledg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '你想要删除这个表格吗?', function(r) {
					if (r) {
						$.post('ResourceTable_delete', {
							id : row.id
						}, function(result) {
							if (result = "true") {
								$('#resourcetabledg').datagrid('reload'); // reload the user data
							} else {
								jAlert('系统错误，请联系管理员', '错误提示');
							}
						}, 'json');
					}
				});
			}
		}
		function fileupload(){
			ajaxFileUpload();
		}
		function ajaxFileUpload() {
			load();
		    $.ajaxFileUpload
		    (
		        {
		            url: 'Uploader_excuteupLoad', //用于文件上传的服务器端请求地址
		            secureuri: false, //是否需要安全协议，一般设置为false
		            fileElementId: 'upfile', //文件上传域的ID
		            dataType: 'json', //返回值类型 一般设置为json
		            success: function (data, status)  //服务器成功响应处理函数
		            {
		            	disLoad();
		              $('#savepath').val(data.url);
		            },
		            error: function (data, status, e)//服务器响应失败处理函数
		            {
		                alert(e);
		            }
		        }
		    )
		    return false;
		}
		
		//弹出加载层
		function load() { 
		    $("<div class=\"datagrid-mask\"></div>").css({ display: "block", width: "100%", height: $(window).height() }).appendTo("form"); 
		    $("<div class=\"datagrid-mask-msg\"></div>").html("正在上传，请稍候。。。").appendTo("form").css({ display: "block", left: ($(document.form).outerWidth(true)) / 2, top: ($(window).height()-100) / 2 }); 
		}  
		//取消加载层  
		function disLoad() { 
		    $(".datagrid-mask").remove(); 
		    $(".datagrid-mask-msg").remove(); 
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