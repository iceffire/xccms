<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
    <%
String request_path = request.getContextPath();
request.setAttribute("request_path", request_path);
request.setAttribute("image_path", request_path+"/Pages");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <script type="text/javascript">
        function show() {
          
            var txtContent = document.getElementById('txtContent');
            if (txtContent.value == '' || txtContent.value == null) {
                 //alert('菜单名称或排序号不能为空');
                $.ligerDialog.error('内容不能为空');
                username.focus();
                return false;
            } else {

                return true;

            }
        }
</script>

</head>
<body>
<div class="main">
<jsp:include page="header.jsp"></jsp:include>
<s:property value="errmsg"/>
	<form id="form1" action="${request_path}/BBSMessage_addMsg" method="get" onsubmit="return show();">
		<div class="snav">
		 	
			<a href="index.jsp">返回首页</a> 
		</div>
		<div>
		<label>标题</label>
		<input type="text" id="title" name="title"/>
		</div>
		<div class="s_content">
			
		<textarea id="txtContent" cols="100" rows="4" style="width: 99%; height: 300px;"  name="content"></textarea>
			 <script>
		 		 $(function () {CKEDITOR.replace('txtContent',{ height: '300px', width: '100%' })})
		    </script>     
	   </div>   
	   <div class="btnSubmit">
	     <input id="btnSubmit" type="submit" value="提交保存" class="btnSubmit1" />   
	   </div>   
   </form>
   <%@ include file="footer.jsp"%>            
	</div>
	<script type="text/javascript">
	  
	</script>
</body>
</html>