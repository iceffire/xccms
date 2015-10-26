var wait=60;
/**
 * 60秒倒计时
 * @param o
 */
function time(o) {
        if (wait == 0) {
            o.removeAttribute("disabled");            
            o.value="发送验证邮件";
            wait = 60;
        } else { // www.jbxue.com
            o.setAttribute("disabled", true);
            o.value="重新发送(" + wait + ")";
            wait--;
            setTimeout(function() {
                time(o);
            },
            1000);
        }
    }
/**
 * 选择tab
 * @param name
 * @param cursel
 * @param n
 */
function setTab(name, cursel, n) {
	for (var i = 1; i <= n; i++) {
		var menu = document.getElementById(name + i);
		var con = document.getElementById("con_" + name + "_" + i);
		menu.className = i == cursel ? "hover" : "";
		con.style.display = i == cursel ? "block" : "none";
	}
}
/**
 * 发送邮件
 */
function sendmail() {
	
	var email= $('#findpasswd_email').val();
	
	var checkcode= $('#checkcode').val();
	
	if (email == '') {
		jAlert("邮箱不能为空!",'找回密码提示');
		return;
	}else{
		var obj =document.getElementById('findpasswd_email');
		if(checkEmail(obj.value))
		{
			jAlert("E-mail格式不正确，请检查!",'找回密码提示');
			obj.focus();
			return false;
		}
	}
	if(checkcode==''){
		jAlert("验证码不能为空!",'找回密码提示');
		return;
	}
	$.ajax({
		type : 'post',
		url : 'Login_sendmail',
		data : {
			email : email,
			checkcode : checkcode
		},
		success : function(data) {
			if(data=='1'){
				jAlert('发送邮件成功，请登录邮箱['+email+']验证！','找回密码提示');
				time(document.getElementById("sendmail_btn"));
			}else if(data=='2'){
				jAlert('验证码不正确！','找回密码提示');
			}else {
				jAlert('发送邮件失败！你输入的邮箱号不存','找回密码提示');
			}
		},
		error : function() {
			jAlert('系统错误，请联系管理员','找回密码提示');
		}
	});
}