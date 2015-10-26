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
<title>27基地军事训练信息化管理平台-用户管理</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<script type="text/javascript" src="${js_path}/jquery.js"></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>

</head>
<body>
	<div id="usertb" style="padding: 5px; height: auto">
		<div>
			是否管理员:<select
				id="isadminquery" name="isadminquery" class="easyui-combobox"
				panelHeight="auto" style="width: 100px">
				<option value="yes">管理员</option>
				<option value="no">普通用户</option>
			</select> <a href="javascript:void(0)" class="easyui-linkbutton"
				iconCls="icon-search" onclick="queryuser()">搜索</a>
		</div>
	</div>
	<table id="userdg" title="用户列表" class="easyui-datagrid"
		style="width: auto; height: 616px" url="User_query"
		toolbar="#usertoolbar" pagination="true" rownumbers="true"
		fitColumns="true" singleSelect="true" pageSize="20">
		<thead>
			<tr>
				<th field="username" width="50">登陆账号</th>
				<th field="realname" width="50">姓名</th>
				<th field="isadmin" width="50" formatter="formatterIsAdmin">是否为管理员</th>
			</tr>
		</thead>
	</table>
	<div id="usertoolbar">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-add" plain="true" onclick="newUser()">新增</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editUser()">编辑</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-remove" plain="true" onclick="destroyUser()">删除</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-print" plain="true" onclick="exportUser()">导出Excel</a>
	</div>

	<div id="userdlg" class="easyui-dialog"
		style="width: 400px; height: 420px; padding: 10px 20px" closed="true"
		buttons="#userdlg-buttons">
		<div class="ftitle">用户信息</div>
		<form id="userfm" method="post" novalidate>
			<div class="fitem">
				<label>登陆账号:</label> <input name="username"
					class="easyui-validatebox" required="true">
			</div>
		<div class="fitem">
				<label>姓名:</label> <input name="realname"
					class="easyui-validatebox" required="true">
			</div>
			<div class="fitem">
				<label>密码:</label> <input id="password" name="password"
					validType="length[4,32]" class="easyui-validatebox" required="true"
					type="password" value="" />
			</div>
		
			<div class="fitem">
				<label>是否为管理员:</label> <select id="isadmin" name="isadmin"
					class="easyui-combobox" panelHeight="auto" style="width: 100px">
					<option value="yes">管理员</option>
					<option value="no">普通用户</option>
				</select>
			</div>
			
		<!-- 
			<div class="fitem">
				<label>角色管理:</label><input name="roleid" id="roleid"
					class="easyui-validatebox" style="display: none;"> <select
					class="easyui-combogrid" id="rolecombox" name="rolecombox"
					style="width: 150px"
					data-options="
            panelWidth: 500,
            multiple: true,
            singleSelect:false, 
            idField: 'id',
            textField: 'name',
            url: 'Role_query',
            method: 'get',
              pageSize: 10,//每页显示的记录条数，默认为10  
             pageList: [10,20],//可以设置每页记录条数的列表 
              pagination : true,//是否分页  
               rownumbers:true,//序号  
            columns: [[
                {field:'ck',checkbox:true},
                {field:'name',title:'角色名字',width:80,align:'right'},
                {field:'isactive',title:'是否激活',width:120},
            ]],
            fitColumns: true
        ">
				</select>
			</div> -->
			
				<!-- <div class="fitem">
			 	<label>机构管理:</label><input name="orgid"  id="orgid"
					class="easyui-validatebox" style="display: none;"> 
					  <input id="orgtree" class="easyui-combotree" url="Org_query" required="true"  multiple="true"  value="1" > 
		
					<select class="easyui-combotree" id="orgcombox" name="orgcombox"
					   style="width:150px"
					   data-options="
					   url: 'Org_query',
					   method: 'get',
					   rownumbers: true,
					   idField: 'id',
					    fit:true,
					    
					    iconCls: 'icon-ok',
					    animate: true, 
					    text:'orgname',
					    fitColumns: true,
					   "
					>
					
				</select>
			</div> -->
			
			
			<input type="hidden" name="userid"  id="userid">
		</form>
	</div>
	<div id="userdlg-buttons">
		<a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-ok" onclick="saveUser()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#userdlg').dialog('close')">取消</a>
	</div>
	<script type="text/javascript">
		var url;
		function formatterIsAdmin(val, row) {
			if (val == "yes") {
				return "管理员";
			} else if (val == "no") {
				return "普通会员";
			} else {
				return val;
			}
		}
		function queryuser() {
			var usermail = $('#usermailto').val();
			var isadminquery = $('#isadminquery').combobox('getValue');
			$('#userdg').datagrid('load', {
				usermail : usermail,
				isadminquery : isadminquery
			});
		}
		function exportUser() {
			 	var options = $('#userdg').datagrid('getPager').data("pagination").options;  ;
			 	var page = options.pageNumber;
			 	var rows = options.pageSize;
				var usermail = $('#usermailto').val();
				var isadminquery = $('#isadminquery').combobox('getValue');
			    var url = 'User_ExportExcel?usermail='+encodeURIComponent(usermail)+'&isadminquery='+encodeURIComponent(isadminquery)+'&page='+page+'&rows='+rows;  
			    window.location.href = url;   
			   
		}
		function newUser() {
			$('#userdlg').dialog('open').dialog('setTitle', '新增用户');
			$('#userfm').form('clear');
			url = 'User_save';
		}
		function editUser() {
			var row = $('#userdg').datagrid('getSelected');
			if (row) {
				$('#userdlg').dialog('open').dialog('setTitle', '编辑用户');
				$('#userfm').form('clear');
				$('#userfm').form('load', row);
				url = 'User_update?id=' + row.id;
				$.ajax({
					type : 'post',
					url : 'User_queryroleByuserid',
					data : {
						userid : row.userid,
					},
					success : function(data) {
						$('#rolecombox').combogrid('setValues', data);
						
					},
					error : function() {
						jAlert('系统错误，请联系管理员', '错误提示');
					}
				});
				$.ajax({
					type : 'post',
					url : 'User_queryorgByuserid',
					data : {
						userid : row.userid,
					},
					success : function(data) {
						
						$('#orgtree').combotree('setValues', data);
						
					},
					error : function() {
						jAlert('系统错误，请联系管理员', '错误提示');
					}
				});
			}
		}
		function saveUser() {
			$('#roleid').val($('#rolecombox').combogrid('getValues'));
		$('#orgid').val($('#orgtree').combotree('getValues'));
			$('#userfm').form('submit', {
				url : url,
				onSubmit : function() {
					return $(this).form('validate');
				},
				success : function(result) {
					if (result != "true") {
						jAlert('系统错误，请联系管理员', '错误提示');
					} else {
						$('#userdlg').dialog('close'); // close the dialog
						$('#userdg').datagrid('reload'); // reload the user data
					}
				}
			});
		}
		function destroyUser() {
			var row = $('#userdg').datagrid('getSelected');
			if (row) {
				$.messager.confirm('确认', '你想要删除这个用户吗?', function(r) {
					if (r) {
						$.post('User_delete', {
							id : row.id
						}, function(result) {
							if (result = "true") {
								$('#userdg').datagrid('reload'); // reload the user data
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
#userfm {
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