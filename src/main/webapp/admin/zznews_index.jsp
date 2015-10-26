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
<title>27基地军事训练信息化管理平台-总装新闻管理</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>

</head>
<body>

	<table id="zznewsdg" title="总装新闻列表" class="easyui-datagrid"
		style="width: auto; height: 616px" url="ZZNews_query"
		toolbar="#zznewstoolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" pageSize="20">
		<thead>
			<tr>
				<th field="title" width="50">新闻标题</th>
				<th field="newsurl" width="50">新闻URL</th>
			</tr>
		</thead>
	</table>
	<div id="zznewstoolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newzznews()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editzznews()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyzznews()">删除</a>
	</div>
	
	

	<div id="zznewsdlg" class="easyui-dialog"
		style="width: 400px; height: 280px; padding: 10px 20px" closed="true"
		buttons="#zznewsdlg-buttons">
		<div class="ftitle">总装新闻信息</div>
		<form id="zznewsfm" method="post" novalidate>
		<div class="fitem">
				<label>新闻标题</label> <input name="title" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>新闻URL</label> <input name="newsurl"  id="newsurl" class="easyui-validatebox"
					required="true">
					
			</div>
			<div class="fitem">
				<label>存储路径</label> <input name="picurl"  id="picurl" class="easyui-validatebox">
						<input type="file" id="upfile" name="upfile" />
					<a href="javascript:void(0)" class="easyui-linkbutton"
			            iconCls="icon-add" plain="true" onclick="fileupload()">上传</a>
			</div>
			<div class="fitem">
				<label>设置图片尺寸</label> <input name="picwidth"  style="width: 50px">*
						<input name="picheight" style="width: 50px" >
			</div>
		
		</form>
	</div>
	<div id="zznewsdlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="savezznews()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#zznewsdlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript">
		var url;


		function newzznews() {
			$('#zznewsdlg').dialog('open').dialog('setTitle', '新增总装新闻');
			$('#zznewsfm').form('clear');
			url = 'ZZNews_save';
		}
		function editzznews() {
			var row = $('#zznewsdg').datagrid('getSelected');
			if (row) {
				$('#zznewsdlg').dialog('open').dialog('setTitle', '编辑总装新闻');
				$('#zznewsfm').form('clear');
				$('#zznewsfm').form('load', row);
				url = 'ZZNews_update?id=' + row.id;
			}
		}
		function savezznews() {
		$('#zznewsfm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					if (result != "true") {
						jAlert('系统错误，请联系管理员', '错误提示');
					} else {
						$('#zznewsdlg').dialog('close'); // close the dialog
						$('#zznewsdg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyzznews() {
			var row = $('#zznewsdg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '你想要删除这个新闻吗?', function(r) {
					if (r) {
						$.post('ZZNews_delete', {
							id : row.id
						}, function(result) {
							if (result = "true") {
								$('#zznewsdg').datagrid('reload'); // reload the user data
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
		    $.ajaxFileUpload
		    (
		        {
		            url: 'Uploader_excuteupLoad', //用于文件上传的服务器端请求地址
		            secureuri: false, //是否需要安全协议，一般设置为false
		            fileElementId: 'upfile', //文件上传域的ID
		            dataType: 'json', //返回值类型 一般设置为json
		            success: function (data, status)  //服务器成功响应处理函数
		            {
		              $('#picurl').val(data.url);
		            },
		            error: function (data, status, e)//服务器响应失败处理函数
		            {
		                alert(e);
		            }
		        }
		    )
		    return false;
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