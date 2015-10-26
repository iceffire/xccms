<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@page import="org.apache.struts2.components.Include,
                                     edu.cqu.fly.xccms.pojo.NewsType,
                                     java.util.*,
                                     edu.cqu.fly.xccms.cache.Cache
                                     "%> 
<%
	String request_path = request.getContextPath();
	String image_path = request_path + "/images/blue-themes";
	String css_path = request_path + "/css/blue-themes";
	String js_path = request_path + "/js";
	request.setAttribute("request_path", request_path);
	request.setAttribute("image_path", image_path);
	request.setAttribute("css_path", css_path);
	request.setAttribute("js_path", js_path);
	String imgwidth = (String)Cache.getInstance().get("imgwidth");
	String imgheight = (String)Cache.getInstance().get("imgheight");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>27基地军事训练信息化管理平台-先进个人管理</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>

</head>
<body>
<div id="excellentindicidualtb" style="padding: 5px; height: auto">
		<div>
			姓名:<input type="text" id="namequery">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" onclick="queryexcellentindividual()">搜索</a>
		</div>
	</div>
	<table id="excellentindividualdg" title="先进个人列表" class="easyui-datagrid"
		style="width: auto; height: 616px" url="ExcellentIndividual_query"
		toolbar="#excellentindividualtoolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" pageSize="20">
		<thead>
			<tr>
				<th field="name" width="50">姓名</th>
				<th field="des" width="50"  formatter="formatterdes">事迹描述</th>
				<th field="chooseTime" width="50" formatter="formatterdate">评选时间</th>
				
			</tr>
		</thead>
	</table>
	<div id="excellentindividualtoolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newexcellentindividual()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editexcellentindividual()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyexcellentindividual()">删除</a>
	</div>
	
	

	<div id="excellentindividualdlg" class="easyui-dialog"
		style="width: 860px; height: auto; padding: 10px 20px" closed="true"
		buttons="#excellentindividualdlg-buttons">
		<div class="ftitle">先进个人信息</div>
		<form id="excellentindividualfm" method="post" novalidate>
			<div class="fitem">
				<label>姓名</label> <input name="name" class="easyui-validatebox" 
					required="true">
			</div>
		
			    <div class="fitem">
                <label>评选时间:</label>
                <input name="chooseTime" type="text" class="easyui-datebox" id="chooseTime" required="true" />
            </div>
            
			<div class="fitem">
				<label>上传图片</label> <input name="picurl"  id="picurl" class="easyui-validatebox"
					required="true">
						<input type="file" id="upfile" name="upfile" />
					<a href="javascript:void(0)" class="easyui-linkbutton"
			            iconCls="icon-add" plain="true" onclick="fileupload()">上传</a>
			</div>
			<div class="fitem">
				<label>设置图片尺寸</label>
				        <input type="text" name="picwidth" id="picwidth" style="width: 50px" >*
						<input  type="text"  name="picheight"  id="picheight" style="width: 50px" >
			</div>
		 <div  style="display: none;">
           		 <textarea name="des" id="des" class="easyui-validatebox"  required="true" style="width: 500px;height: 100px;"/>
            </div>
             <div class="fitem" style="">
                <label>事迹描述:</label>
                <br/>
                    <!--style给定宽度可以影响编辑器的最终宽度-->
				<script type="text/plain" id="myEditorexcellent" name="myEditorexcellent" style="width:750px;height:240px;">
    				<p>这里我可以写一些输入提示</p>
				</script>
            </div>
		</form>
	</div>
	<div id="excellentindividualdlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveexcellentindividual()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#excellentindividualdlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript">
		var url;

		function queryexcellentindividual() {
			var name = $('#namequery').val();
		   $('#excellentindividualdg').datagrid('load', {
				namequery : name
			});
		}
		function newexcellentindividual() {
			$('#excellentindividualdlg').dialog('open').dialog('setTitle', '新增先进个人');
			$('#excellentindividualfm').form('clear');
			$('#picwidth').val("<%=imgwidth%>");
			$('#picheight').val("<%=imgheight%>");
			  UM.getEditor('myEditorexcellent').setContent('', false);
			url = 'ExcellentIndividual_save';
		}
		function editexcellentindividual() {
			var row = $('#excellentindividualdg').datagrid('getSelected');
			if (row) {
				$('#excellentindividualdlg').dialog('open').dialog('setTitle', '编辑先进个人');
				$('#excellentindividualfm').form('clear');
				
				$('#excellentindividualfm').form('load', row);
				$('#chooseTime').datebox('setValue', timeStamp2String(row.chooseTime));
				UM.getEditor('myEditorexcellent').setContent(row.des, false);
				url = 'ExcellentIndividual_update?id=' + row.id;
			}
		}
		function saveexcellentindividual() {
			$("#des").val(UM.getEditor('myEditorexcellent').getContent());
		$('#excellentindividualfm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					if (result != "true") {
						jAlert('系统错误，请联系管理员', '错误提示');
					} else {
						$('#excellentindividualdlg').dialog('close'); // close the dialog
						$('#excellentindividualdg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyexcellentindividual() {
			var row = $('#excellentindividualdg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '你想要删除这条记录吗?', function(r) {
					if (r) {
						$.post('ExcellentIndividual_delete', {
							id : row.id
						}, function(result) {
							if (result = "true") {
								$('#excellentindividualdg').datagrid('reload'); // reload the user data
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
		   function formatterdes(value,rowData,rowIndex){
	        	var s ="<a href=\"ExcellentIndividual_excellentdetail?id="+rowData.id+"\" >预览</a>";
	        	
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