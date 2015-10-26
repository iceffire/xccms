<%@page import="org.apache.struts2.components.Include,
                                     edu.cqu.fly.xccms.pojo.NewsType,
                                     java.util.*,
                                     edu.cqu.fly.xccms.cache.Cache
                                     "%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String request_path = request.getContextPath();
	String image_path = request_path + "/images/blue-themes";
	String css_path = request_path + "/css/blue-themes";
	String js_path = request_path + "/js";
	request.setAttribute("request_path", request_path);
	request.setAttribute("image_path", image_path);
	request.setAttribute("css_path", css_path);
	request.setAttribute("js_path", js_path);
	List<NewsType> typelist = (List<NewsType>)Cache.getInstance().get("newstype");
	StringBuilder sb = new StringBuilder();
	sb.append("{");
	
	for(NewsType newsType : typelist){
		sb.append(newsType.getTypecode()+":\""+newsType.getType()+"\"").append(",");
	}
	if(typelist.size()>0){
		sb.delete(sb.length()-1, sb.length());
	}
	sb.append("}");
	String typeJson=sb.toString();
	System.out.println(typeJson);
	request.getSession().setAttribute("typelist", typelist);
	
	
	String imgwidth = (String)Cache.getInstance().get("imgwidth");
	String imgheight = (String)Cache.getInstance().get("imgheight");
%>
<!DOCTYPE html>
<html>
<head>
<base target="_self">
<meta charset="UTF-8">
<title>27基地军事训练信息化管理平台-新闻管理</title>
   <script type="text/javascript" src="${js_path }/jquery.js"></script>
</head>
<body>
		<div id="newstb" style="padding: 5px; height: auto;width:auto;">
			<div>
				创建起始日期: <input id="startdatacreatenew" class="easyui-datetimebox" style="width: 80px">
				创建结束日期: <input id="enddatacreatenew" class="easyui-datetimebox" style="width: 80px">
				新闻类型: <select id="newtype" class="easyui-combobox" panelHeight="auto"
					style="width: 100px">
					<option value="">全部</option>
					<c:forEach items="${ typelist}" var="type">
					    
					    <option value="${type.typecode }">${type.type }</option>
					
					</c:forEach>
				
					
				</select>
				作者:<input type="text"  id="newauthor">
				标题:<input type="text"  id="newtitle">
				 <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" onclick="querynews()">搜索</a>
			</div>
		</div>
    <table id="newdg" title="新闻类表" class="easyui-datagrid" style="width:auto;height:616px"
            url="News_query"
            toolbar="#toolbarnew" pagination="true"
            rownumbers="true" fitColumns="true" singleSelect="true" pageSize="20">
        <thead>
            <tr>
                <th field="author" width="30">作者</th>
                <th field="createdate" width="50" formatter="formatterdate">创建日期</th>
                <th field="type" width="30" formatter="formatnewstype">类型</th>
                <th field="title" width="100">标题</th>
                <th field="updatedate" width="50" formatter="formatterdate">更新日期</th>
                <th field="isrecommend" width="30" formatter="formatreccomend">推荐</th> 
                <th field="id" width="30" formatter="formatpreview">预览</th>
            </tr>
        </thead>
    </table>
    <div id="toolbarnew">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newNews()">新增</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editNews()">编辑</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyNews()">删除</a>
    </div>
    
    <div id="newdlg" class="easyui-dialog" style="width:860px;height:auto;padding:10px 20px"
            closed="true" buttons="#newdlg-buttons">
        <div class="ftitle">文章详细信息</div>
        <form id="newsfm" method="post" novalidate>
            <div class="fitem">
                <label>类型:</label>
             <select id="type" name="type"  class="easyui-combobox" panelHeight="auto"
					style="width: 100px">
					<c:forEach items="${ typelist}" var="type">
					    
					    <option value="${type.typecode }">${type.type }</option>
					
					</c:forEach>
				
					
				</select> 
            </div>
            <div class="fitem">
                <label>标题</label>
                <input name="title"  id="title" class="easyui-validatebox" required="true" size="50px;">
            </div>
            <div class="fitem">
                <label>作者:</label>
                <input name="author" id="author" class="easyui-validatebox" required="true"/>
            </div>
         
            <div class="fitem">
                <label>时间:</label>
                <input name="createdate" class="easyui-datetimebox" id="createdate" required="true" />
            </div>
            
              <div class="fitem">
                <label>是否添加大图片:</label>
                <select id="hasheaderpic" name="hasheaderpic" onchange="select(this.value)">
                        <option value='1'>是</option>
                         <option value='0'>否</option>
                </select>
              
            </div>
         <div class="fitem" id="pic">
         
         </div>
                  <div class="fitem">
                <label>是否上传附件:</label>
                <select id="hasappendix" name="hasappendix" onchange="selectapp(this.value)">
                        <option value='1'>是</option>
                         <option value='0'>否</option>
                </select>
              
            </div>
         <div class="fitem" id="appendixflag">
         
         </div>
            <div  style="display: none;">
           		 <textarea name="content" id="content" class="easyui-validatebox"  required="true" style="width: 500px;height: 100px;"/>
            </div>
             <div class="fitem" style="">
                <label>文章内容:</label>
                <br/>
                    <!--style给定宽度可以影响编辑器的最终宽度-->
				<script type="text/plain" id="myEditornews" name="myEditornews" style="width:750px;height:240px;">
    				<p>这里我可以写一些输入提示</p>
				</script>
            </div>
          <span id="addheadpic"></span>
        </form>
        
    </div>
    <div id="newdlg-buttons">
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-ok" onclick="saveNews()">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#newdlg').dialog('close')">取消</a>
    </div>
    
    <script type="text/javascript">
        var url;
        var allBox = $(":checkbox");
        allBox.click(function () {
            allBox.removeAttr("checked");
            $(this).attr("checked", "checked");
        });
      //  window.UEDITOR_HOME_URL="";
       // alert(window.UEDITOR_HOME_URL);
           
        function formatreccomend(value,rowData,rowIndex){
        	var s="";
        	if(value == "1"){
        		s ="<a href=\"#\" onclick=\"javascript:$.messager.alert('提示','已推荐');\"><font style=\"color:green;\">已推荐</font></a>";
        	}else{
        		s ="<a href=\"#\" onclick=\"recommend('"+rowData.id+"')\">推荐</a>";
        	}
        	return s;
        }
        function recommend(rowDataid){
        	$.post('News_reccomend',{
        		id:rowDataid
        	},
        	function(data,status){
        		$.messager.alert('提示',data);
        		refresh();
        	});
        }
        
        function formatpreview(value,rowData,rowIndex) {
    		var s ='<a href="News_newsdetail?id='+rowData.id+'" class="easyui-linkbutton" target="_blank"">预览</a>';
    		return s;
   	    }
        
        function formatnewstype(value,rowData,rowIndex) {
        	var s="";
        	var typeJson = <%=typeJson%>;
   
        	s = typeJson[value];
    		return s;
   	    }
        function querynews(){
        	var startdatacreatenew = $('#startdatacreatenew').datebox('getValue');
        	var enddatacreatenew = $('#enddatacreatenew').datebox('getValue');
        	var newtype =  $('#newtype').combobox('getValue');
        	var newauthor = $('#newauthor').val();
        	var newtitle = $('#newtitle').val();
        	$('#newdg').datagrid('load', {
        		startdatacreatenew:startdatacreatenew,
        		enddatacreatenew:enddatacreatenew,
        		newtype:newtype,
        		newauthor:newauthor,
        		newtitle:newtitle
        	});
        }
        function newNews(){
            $('#newdlg').dialog('open').dialog('setTitle','发表文章');
            $('#newsfm').form('clear');
            UM.getEditor('myEditornews').setContent('', false);
            url = 'News_save';
        }
        function editNews(){
            var row = $('#newdg').datagrid('getSelected');
      
            if (row){
            	url = 'News_update?id='+row.id;
                $('#newdlg').dialog('open').dialog('setTitle','编辑文章');
                $('#newsfm').form('clear');
                UM.getEditor('myEditornews').setContent(row.content, false);
                
                $('#type').combobox('setValue',row.type);
               
                $('#hasheaderpic').val(row.hasheaderpic);
                $('#hasappendix').val(row.hasappendix);
                if(row.hasheaderpic==1){
                 	$('#addheadpic').append('<input type="hidden" id="headerpicurl">'+
                	                                                   '<input type="hidden" id="picwidth">'+
                	                                                   '<input type="hidden" id="picheight">'); 
                	$('#headerpicurl').val(row.headerpicurl);
                	$('#picwidth').val(row.picwidth);
                	$('#picheight').val(row.picheight);
                	
                }
                if(row.hasappendix==1){
                	$('#addheadpic').append('<input type="hidden" id="appendix">');
                	$('#appendix').val(row.appendix);
                }
                $('#author').val(row.author);
                $('#title').val(row.title); 
            
                $('#createdate').datebox('setValue', timeStamp2String(row.createdate));
               $('#newsfm').form('load',row);
               
               
                
                
            }
        }
        function saveNews(){
    		
        	var title = $('#title').val();

            var createdatebox =  $('#createdate').datetimebox('getValue');
            var createdate = "";
      if(createdatebox.split(":").length==1){
            
            	var date = new Date();
        		date.setTime(createdatebox);
        		var y = date.getFullYear();
        		var m = date.getMonth() + 1;
        		var d = date.getDate();
        		var h = date.getHours();
        		var mi = date.getMinutes();
        		var s = date.getSeconds();
        		createdate = m+"/"+d+"/"+y+" "+h+":"+mi+":"+s;
            }else{
            	var createdate = createdatebox;
            }
        	var author = $('#author').val();
   
        	var type = $('#type').combobox('getValue');

        	var summary="";
        	var content = UM.getEditor('myEditornews').getContent()

        	var hasheaderpic = $('#hasheaderpic').val();
        	var picwidth="";
        	var picheight="";
        	var headerpicurl="";
        	if(hasheaderpic == 1){
        		picwidth = $('#picwidth').val();
             picheight = $('#picheight').val(); 
             headerpicurl = $('#headerpicurl').val();
        	}
        	var hasappendix = $('#hasappendix').val();
        	var appendix="";
        	if(hasappendix == 1){
        		appendix = $('#appendix').val();
        	}
        	$.ajax({
        		type:'post',
        		url:url,
        		data:"title="+title+"&"+
        		          "createdate="+createdate+"&"+
        		          "author="+author+"&"+
        		          "type="+type+"&"+
        		         "summary="+summary+"&"+
        		         "content="+content+"&"+
        		         "hasheaderpic="+hasheaderpic+"&"+
        		         "picwidth="+picwidth+"&"+
        		         "picheight="+picheight+"&"+
        		         "headerpicurl="+headerpicurl+"&"+
        		         "hasappendix="+hasappendix+"&"+
        		         "appendix="+appendix,
        		async:false,
        		success:function(data){
        			if(!data){
        				jAlert('系统错误,请联系管理员','错误提示');
        			}else{
        				 $('#newdlg').dialog('close');        // close the dialog
                         $('#newdg').datagrid('reload');    // reload the user data
        			}
        		}
        	});   
        	
 
        }
        function destroyNews(){
            var row = $('#newdg').datagrid('getSelected');
            if (row){
                $.messager.confirm('确认','你想要删除这篇文章吗?',function(r){
                    if (r){
                        $.post('News_deleteArticle',{id:row.id},function(result){
                            if (result="true"){
                                $('#newdg').datagrid('reload');    // reload the user data
                            } else {
                            	jAlert('系统错误，请联系管理员','错误提示');
                            }
                        },'json');
                    }
                });
            }
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
        
        function select(value){
       if(value == 1){
    	   var  imgwidth ="<%=imgwidth%>"; 
    	   var  imgheight ="<%=imgheight%>"; 
    	   document.getElementById('pic').innerHTML=' <div class="fitem">'+
    	                                                                                             '<label>选择图片:</label>'+
    	                                                                                             '<input name="headerpicurl" type="text" class="easyui-datebox" id="headerpicurl" required="true" />'+
    	                                                                                             '<input type="file" id="upfile" name="upfile" />'+
    	                                                                                             ' <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fileupload(\'pic\')">上传</a>'+
    	                                                                                             '</div>'+
    	                                                                                             '	<div class="fitem">'+
				                                                                                     '<label>设置图片尺寸</label> <input name="picwidth"  id="picwidth" style="width: 50px" value="'+imgwidth+'">*'+
						                                                                             '<input name="picheight" id="picheight" style="width: 50px" value="'+imgheight+'">'+
			                                                                                         '</div>' ;
       }else{
    	   document.getElementById('pic').innerHTML='<input type="hidden" name="headerpicurl" id="headerpicurl" value="">';
       }
        }
        function selectapp(value){
            if(value == 1){
         	   document.getElementById('appendixflag').innerHTML=' <div class="fitem">'+
         	                                                                                             '<label>选择文件:</label>'+
         	                                                                                             '<input name="appendix" type="text" class="easyui-datebox" id="appendix" required="true" />'+
         	                                                                                             '<input type="file" id="appendfile" name="appendfile" />'+
         	                                                                                             ' <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="fileupload(\'app\')">上传</a>'+
         	                                                                                             '</div>';
            }else{
         	   document.getElementById('appendixflag').innerHTML='<input type="hidden" name="appendix" id="appendix" value="">';
            }
             }
        
        function fileupload(val){
        	alert(val);
        	ajaxFileUpload(val);
        }
        function ajaxFileUpload(val) {
        	var file="";
        	var fileid="";
        	if(val == "pic"){
        		file="headerpicurl";
        		fileid="upfile";
        	}else{
        		file="appendix";
        		fileid="appendfile";
        	}
            $.ajaxFileUpload
            (
                {
                    url: 'Uploader_excuteupLoad', //用于文件上传的服务器端请求地址
                    secureuri: false, //是否需要安全协议，一般设置为false
                    fileElementId: fileid, //文件上传域的ID
                    dataType: 'json', //返回值类型 一般设置为json
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                      $('#'+file).val(data.url);
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
        #newsfm{
            margin:0;
            padding:10px 30px;
        }
        .ftitle{
            font-size:14px;
            font-weight:bold;
            padding:5px 0;
            margin-bottom:10px;
            border-bottom:1px solid #ccc;
        }
        .fitem{
            margin-bottom:5px;
        }
        .fitem label{
            display:inline-block;
            width:80px;
        }
    </style>
</body>
</html>