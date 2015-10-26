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
<title>27基地军事训练信息化管理平台-BBS会话管理</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>

</head>
<body>

	<table id="bbsmessagedg" title="BBS会话列表" class="easyui-datagrid"
		style="width: auto; height: 616px" url="BBSMessage_query"
		toolbar="#bbsmessagetoolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" pageSize="20">
		<thead>
			<tr>
				<th field="title" width="50">标题</th>
				<th field="userid" width="50">发帖人ID</th>
				<th field="createTime" width="50" formatter="formatterdate">发帖时间</th>
				<th field="id" width="50" formatter="formattermess">预览</th>
				<th field="isessence" width="50" formatter="formatteressence">精华贴</th>
				<th field="content" width="50" formatter="formattercomm">查看评论</th>
			</tr>
		</thead>
	</table>
	<div id="bbsmessagetoolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newbbsmessage()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editbbsmessage()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroybbsmessage()">删除</a>
	</div>
	
	

	<div id="bbsmessagedlg" class="easyui-dialog"
		style="width: 860px; height: auto; padding: 10px 20px" closed="true"
		buttons="#bbsmessagedlg-buttons">
		<div class="ftitle">BBS会话信息</div>
		<form id="bbsmessagefm" method="post" novalidate>
		<div class="fitem">
		       <label>标题</label>
		       <input name="title" class="easyui-validatebox" required="true" size="50px;">
		</div>
		 <div  style="display: none;">
           		 <textarea name="content" id="content" class="easyui-validatebox"  required="true" style="width: 500px;height: 100px;"/>
            </div>
             <div class="fitem" style="">
                <label>文章内容:</label>
                <br/>
                    <!--style给定宽度可以影响编辑器的最终宽度-->
				<script type="text/plain" id="myEditormessage" name="myEditormessage" style="width:750px;height:240px;">
    				<p>这里我可以写一些输入提示</p>
				</script>
            </div>
		
			
		
		</form>
		
	</div>
	<div id="dlg" ></div>
	<div id="bbsmessagedlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="savebbsmessage()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#bbsmessagedlg').dialog('close')">取消</a>
	</div>
	
	<script type="text/javascript">
	
		var url;
		
		function newbbsmessage() {
			$('#bbsmessagedlg').dialog('open').dialog('setTitle', '新增留言');
			$('#bbsmessagefm').form('clear');
	            UM.getEditor('myEditormessage').setContent('', false);
			url = 'BBSMessage_save';
		}
		function editbbsmessage() {
			var row = $('#bbsmessagedg').datagrid('getSelected');
			if (row) {
				$('#bbsmessagedlg').dialog('open').dialog('setTitle', '编辑留言');
				$('#bbsmessagefm').form('clear');
				   UM.getEditor('myEditormessage').setContent(row.content, false);
				$('#bbsmessagefm').form('load', row);
				$('#createTime').datebox('setValue', timeStamp2String(row.createTime));
				url = 'BBSMessage_update?id=' + row.id;
			}
		}
		function savebbsmessage() {
		  	$("#content").val(UM.getEditor('myEditormessage').getContent());
		$('#bbsmessagefm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					if (result != "true") {
						jAlert('系统错误，请联系管理员', '错误提示');
					} else {
						$('#bbsmessagedlg').dialog('close'); // close the dialog
						$('#bbsmessagedg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroybbsmessage() {
			var row = $('#bbsmessagedg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '你想要删除这条留言吗?', function(r) {
					if (r) {
						$.post('BBSMessage_delete', {
							id : row.id
						}, function(result) {
							if (result = "true") {
								$('#bbsmessagedg').datagrid('reload'); // reload the user data
							} else {
								jAlert('系统错误，请联系管理员', '错误提示');
							}
						}, 'json');
					}
				});
			}
		}
		 function formattermess(value,rowData,rowIndex){
	        	var  s ="<a href=\"#\" onclick=\"javascript:$.messager.alert('提示','已推荐');\">预览</a>";
	      return s;
	        }
		
		 function formattercomm(value,rowData,rowIndex){
			 
	        	var  s ="<a href=\"javascript:void(0)\" onclick=\"opendialog('"+rowData.id+"')\">查看评论</a>";
	           return s;
	        }
		 function opendialog(id){
			 $('#dlg').dialog({
				    title: 'My Dialog',
				    width: 800,
				    height: 650,
				    closed: false,
				    cache: false,
				    href: 'MessageComment_index?messageid='+id,
				    modal: true
				});
		 }
		 
		 function formatteressence(value,rowData,rowIndex){
				var s="";
	        	if(value == "1"){
	        		s ="<a href=\"#\" onclick=\"javascript:$.messager.alert('提示','已标记为精华贴');\"><font style=\"color:green;\">精华帖</font></a>";
	        	}else{
	        		s ="<a href=\"#\" onclick=\"essence('"+rowData.id+"')\">精华标记</a>";
	        	}
	      return s;
	        }
		 function createFrame(url){
	        	var s ='<iframe name="tabiframe" id="tabiframe" scrolling="yes" frameborder="0" src="'+url+
	        	             '" style="width:100%;height:99.5%;border:0px none;></iframe>';
	        	 return s;
	        }
		 function refresh(){
	        	var currTab = $('#right_tab').tabs('getSelected');
	        	var url = $(currTab.panel('options').content).attr('src');
	        	
	           $('#right_tab').tabs('update', {
	        	   tab : currTab,
	        	   options :{
	        		   content : createFrame(url)
	        	   }
	           });
	        }
		   function essence(rowDataid){
	        	$.post('BBSMessage_essence',{
	        		id:rowDataid
	        	},
	        	function(data,status){
	        		$.messager.alert('提示',data);
	        		refresh();
	        	});
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