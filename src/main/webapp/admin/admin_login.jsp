<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt'%>
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
<title>登陆_27基地军事训练信息化管理平台</title>
<link rel="stylesheet" type="text/css"
	href="${css_path}/admin/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/icon.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/demo.css">
<link rel="stylesheet" type="text/css" href="${css_path}/admin/adminlogin.css">
<link rel="shortcut icon" href="favicon.ico" />
<script language="JavaScript" type="text/javascript" src="${js_path}/jquery.js" ></script>
<script type="text/javascript"
	src="${js_path}/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript">
	function reset(){
	     $('#loginForm').reset();
	}
</script>
</head>

<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="147" background="${image_path}/top02.gif"></td>
  </tr>
</table>
<table width="562" border="0" align="center" cellpadding="0" cellspacing="0" class="right-table03">
  <tr>
    <td width="221"><table width="95%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
      
      <tr>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="0" class="login-text01">
          <tr>
            <td align="center"><img src="${image_path}/ico13.gif" width="107" height="97" /></td>
          </tr>
          <tr>
            <td height="40" align="center">&nbsp;</td>
          </tr>
          
        </table></td>
        <td><img src="${image_path}/line01.gif" width="5" height="292" /></td>
      </tr>
    </table></td>
    <td>
    <form action="Admin_dologin" method="post" name="loginForm" id="loginForm">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
     <tr>
     <!--    <td height="35" class="login-text02">用户类型：</td>
        <td><select id="usertype" name="usertype" ><option value ="no">普通用户</option> <option value ="yes">管理员</option></select></td> -->
        <input type="hidden" id="usertype" name="usertype" value="yes">
      </tr>
      <tr>
        <td width="31%" height="35" class="login-text02">用户名称：<br /></td>
        <td width="69%"><input id="idNumber" name="idNumber"  type="text" size="30" /></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">密　码：<br /></td>
        <td><input type="password" id="password" name="password" size="30" /></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">验证图片：<br /></td>
        <td>	<img width="80" height="20" id="randimage" src="${request_path}/common/validate_img.jsp" align="absMiddle" border="0" alt="看不清楚?请点击刷新" style="cursor : pointer;" onclick="this.src='${request_path}/common/validate_img.jsp?'+ Math.random()"/></td>
      </tr>
      <tr>
        <td height="35" class="login-text02">请输入验证码：</td>
        <td><input id="checkcode" name="checkcode" type="text" size="30" /></td>
      </tr>
      <tr>
        <td height="35">&nbsp;</td>
        <td><input name="Submit2" type="submit" class="right-button01" value="确认登陆"  />
          <input  type="button" class="right-button02" onclick="reset()" value="重 置" /></td>
      </tr>
       <tr>
        <td colspan="2" style="padding-left: 100px;padding-top: 20px;color: red;font-size: 14px; " ><c:out value="${showMessage}" /></td>
      </tr>
    </table>
    </form>
    </td>
  </tr>
</table>
</body>
</html>