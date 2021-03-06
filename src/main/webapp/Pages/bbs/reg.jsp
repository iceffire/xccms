<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
        function show() {
          
            var username = document.getElementById('username');
            var email = document.getElementById('email');
            var pwd1 = document.getElementById('pwd1');
            var pwd2 = document.getElementById('pwd2');
            var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;  
            if (username.value == '' || username.value == null) {
                 //alert('菜单名称或排序号不能为空');
                $.ligerDialog.error('用户名不能为空');
                username.focus();
                return false;
            }
            else if (email.value == '' || email.value == null) {
                //alert('请输入大于零的整数！');
                $.ligerDialog.error('邮箱不能为空');
                return false;

            }
            else if(pwd1.value == '' || pwd1.value == null ||pwd2.value == '' || pwd2.value == null){
                // alert('密码不能为空');
                $.ligerDialog.error('密码不能为空');
                return false;
            }
            else{
            	if(pwd1.value != pwd2.value){
            		 $.ligerDialog.error('两次输入密码不一致');
                     return false;
            		}
            	else{
            		if(!pattern.test(email.value)){
            			  $.ligerDialog.error('请输入正确的邮箱地址');
            			  email.focus();
                          return false;  
            		}
            		else{return true;}
            		
            	}
            }

                       
        }
    </script>
</head>
<body>
<div class="main">
	<%@ include file="header.jsp"%>
	<div id="bbs">
	<div class="lnav">
		<a href="BBSMessage_queryBBSMessageList?page=1&rows=10">返回首页</a> 
	</div>
	<div class="login">
		<div class="content">
			<div class="l_content">
				<div class="type">用户注册</div>
				<div class="post_box">
					<form id="form1" action="BBSMessage_bbsreg" method="post" onsubmit="return show();">
						<div class="loginform">
							<div class="username">
								<label>用户名：</label>
								<input type="text" id="username" name="username">&nbsp;&nbsp;<label style="color:red">*</label>
							</div>
							<br/>
							<div class="E-mail">
								<label>邮&nbsp;&nbsp;&nbsp;箱：</label>
								<input type="text"  id="email" name="email">&nbsp;&nbsp;<label style="color:red">*</label>
							</div>
							<br/>
							<div class="pwd1">
								<label>新密码：</label>
								<input type="password" id="pwd1" name="pwd">&nbsp;&nbsp;<label style="color:red">*</label>
							</div>
							<br/>
							<div class="pwd2">
								<label>确认密码：</label>
								<input type="password" id="pwd2" name="pwd2">&nbsp;&nbsp;<label style="color:red">*</label>
							</div>
							<br/>
							<div class="submit">
							<input class="submit1" type="submit" name="submit" value="注册">&nbsp;
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div id="search" class="search" style="display:none">
		<div class="header"><a href="#">27基地</a>-> 搜索</div>
		<div class="s_content">
			<div class="m_search">
				<label class="label"><span>搜索</span><strong>帖子</strong></label>
					<form  action ="BBSMessage_queryBBSMessageList" method="get"  id="searchform" accept-charset="utf-8">
					<p>
					<input type="text" id="title" name="title"  size="45" maxlength="40" value="" class="txt" tabindex="1" />
					<script type="text/javascript">$('srchtxt').focus();</script>
					<button  name="searchsubmit" id="searchsubmit">搜索</button>
				
					</p>
						</form>
			</div>
		<hr class="shadowline"/>
		<div class="quick_search">
			<h3>便捷搜索</h3>
			<table cellspacing="4" cellpadding="0" width="100%">
			<tr>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=ONE_HOUR">1 小时以内的新帖</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=FOUR_HOUR">4 小时以内的新帖</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=EIGHT_HOUR">8 小时以内的新帖</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=ONE_DAY">24 小时以内的新帖</a></td>
			</tr>
			<tr>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=ONE_WEEK">1 周内帖子</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=ONE_MONTH">1 月内帖子</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=HALF_YEAR">6 月内帖子</a></td>
				<td><a href="BBSMessage_queryBBSMessageList?timeperiod=ONE_YEAR">1 年内帖子</a></td>
			</tr>
			</table>
		</div>
		</div>
	</div>
	<%@ include file="footer.jsp"%>
</div>
<script type="text/javascript">
$('#searchsubmit').onclick(function(){
	$('#searchform').submit();
});
       
</script>
</body>
</html>