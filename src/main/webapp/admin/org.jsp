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
<title>27基地军事训练信息化管理平台-机构管理</title>
</head>
<body>
	<div style="margin: 10px 0;">
	 <a href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="true" onclick="editorg()">编辑</a>
			 <a href="javascript:void(0)"
			class="easyui-linkbutton"  iconCls="icon-save" plain="true"  onclick="saveorg()">保存</a> <a
			href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-no" plain="true" 
			onclick="cancelorg()">取消</a>
	</div>
	<table title="机构管理" class="easyui-treegrid" id="orgtg"
		data-options="url: '${request_path}/Org_query',method: 'get',rownumbers: true,idField: 'id', fit:true,treeField: 'orgname',iconCls: 'icon-ok',animate: true, fitColumns: true,onContextMenu: onContextOrg">
		<thead>
			<tr>
				
				<th data-options="field:'orgname',editor:'text'" width="100" >机构名称</th>
				<th data-options="field:'orgcode', editor:'text'" width="220">机构编码</th>
				<th data-options="field:'parentid',editor:'numberbox'" width="150">父机构id</th>
			</tr>
		</thead>
	</table>
	<div id="orgmm" class="easyui-menu" style="width: 120px;">
		<div onclick="appendorg()" data-options="iconCls:'icon-add'">添加</div>
		<div onclick="removeItorg()" data-options="iconCls:'icon-remove'">删除</div>
		<div class="menu-sep"></div>
		<div onclick="collapseorg()">收缩</div>
		<div onclick="expandorg()">展开</div>
	</div>
	<script type="text/javascript">
	var editingorgId;
	function editorg() {
		if (editingorgId != undefined) {
			$('#orgtg').treegrid('select', editingorgId);
			return;
		}
		var row = $('#orgtg').treegrid('getSelected');
		if (row) {
			editingorgId = row.id;
			$('#orgtg').treegrid('beginEdit', editingorgId);
		}
	}

	function saveorg() {
		if (editingorgId != undefined) {
			var t = $('#orgtg');
			t.treegrid('endEdit', editingorgId);
			editingorgId = undefined;

			var rows = t.treegrid('getChildren');
			var ids = 0;
			for ( var i = 0; i < rows.length; i++) {
				var id = parseInt(rows[i].id);
				var orgcode = rows[i].orgcode;
				var orgname = rows[i].orgname;
				var parentid = parseInt(rows[i].parentid);
			
				$.ajax({
					type : 'post',
					url : 'Org_updateoradd',
					data : {
						id : id,
						orgcode : orgcode,
						orgname : orgname,
						parentid : parentid
					},
					success : function(data) {
						if (data == 'false') {
							ids = 1;
						}
					},
					error : function() {
						ids = 1;
					}
				});
			}
			if (ids == 1) {
				jAlert('系统错误，请联系管理员', '错误提示');
			} else {
				$('#tt').tree('reload');
				jAlert('恭喜你，保存成功!', '错误提示');
			}
		}
	}
	function cancelorg() {
		if (editingorgId != undefined) {
			$('#orgtg').treegrid('cancelEdit', editingorgId);
			editingorgId = undefined;
		}
	}

	function formatOrgid(value, rowData, rowIndex) {
		var s = '<a href="javascript:void(0)" class="easyui-linkbutton" onclick="showemail('
				+ rowData.id + ')">邮件详细内容</a>';
		return s;
	}
	function onContextOrg(e, row) {
		e.preventDefault();
		$(this).treegrid('select', row.id);
		$('#orgmm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});
	}
	var idIndexorg = 100;
	function appendorg() {
		idIndexorg++;
		var d2 = new Date();
		var orgname = 'New Org' + idIndexorg;
		d2.setMonth(d2.getMonth() + 1);
		var node = $('#orgtg').treegrid('getSelected');
		$.ajax({
			type : 'post',
			url : 'Org_updateoradd',
			data : {
				id : "",
				orgname : orgname,
				orgcode : "",
				parentid : node.id
			},
			success : function(data) {
				if (data == 'true') {
					$('#orgtg').treegrid('append', {
						parent : node.id,
						data : [ {
							id : "",
							orgname : orgname,
							orgcode : "",
							parentid : node.id
						} ]
					});
					$('#tt').tree('reload');
				} else {
					jAlert('系统错误，请联系管理员', '错误提示');
				}
				;
			},
			error : function() {
				jAlert('系统错误，请联系管理员', '错误提示');
			}
		});

	}
	function removeItorg() {
		var node = $('#orgtg').treegrid('getSelected');
		if (node) {
			$('#orgtg').treegrid('remove', node.id);
			$.ajax({
				type : 'post',
				url : 'Org_deleteOrg',
				data : {
					id : node.id,
				},
				success : function(data) {
					if (data == 'false') {
						jAlert('系统错误，请联系管理员', '错误提示');
					} else {
						$('#tt').tree('reload');
						jAlert('恭喜你，删除成功!', '菜单提示');
					}
				},
				error : function() {
					jAlert('系统错误，请联系管理员', '错误提示');
				}
			});
		}
	}
	function collapseorg() {
		var node = $('#orgtg').treegrid('getSelected');
		if (node) {
			$('#orgtg').treegrid('collapse', node.id);
		}
	}
	function expandorg() {
		var node = $('#orgtg').treegrid('getSelected');
		if (node) {
			$('#orgtg').treegrid('expand', node.id);
		}
	}

	</script>
</body>
</html>