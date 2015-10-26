jQuery(function() {
	jQuery(".fixedBox ul.fixedBoxList li.fixeBoxLi").hover(function() {
		jQuery(this).find('span.fixeBoxSpan').addClass("hover");
		jQuery(this).addClass("hover");
	}, function() {
		jQuery(this).find('span.fixeBoxSpan').removeClass("hover");
		jQuery(this).removeClass("hover");
	});
	var oDate = new Date();
	var iHour = oDate.getHours();
	if (iHour > 8 && iHour < 22) {
		jQuery(".Service").addClass("startWork");
		jQuery(".Service").removeClass("Commuting");
	} else {
		jQuery(".Service").addClass("Commuting");
		jQuery(".Service").removeClass("startWork");
	}
	;
});

jQuery(function() {
	jQuery('.listLeftMenu dl dt').click(function() {
		var but_list = jQuery(this).attr('rel');
		if (but_list == 'off') {
			jQuery(this).attr('rel', 'on');
			jQuery('.listLeftMenu dl').removeClass('off');
			jQuery(this).parent().addClass('on');
		} else {
			jQuery(this).attr('rel', 'off');
			jQuery(this).parent().removeClass('on');
			jQuery(this).parent().addClass('off');
		}
	});

	$('#ico_pp_a').click(function() {
		$('#pop_ly_id_div').show();
	})
	$('#pop_ly_id_div dl dt span').click(function() {
		$("#pop_ly_id_div").hide();
	})
	$('#msg_sub_button').click(
			function() {
				if ($("#name").val() == ""
						|| $("#name").val() == "请输入您的称呼") {
					jAlert("请输入您的称呼!");
					return false;
				}
				if ($("#messgae").val() == ""
						|| $("#messgae").val() == "请输入留言内容") {
					jAlert("请输入留言内容!");
					return false;
				}
				if ($("#msg_vcode").val() == ""
						|| $("#msg_vcode").val() == "请输入验证码") {
					jAlert("请输入验证码!");
					return false;
				}
				var email =$("#email").val();
				var name =$("#name").val();
				var telephone =$("#telephone").val();
				var message =$("#message").val();
				var msg_vcode =$("#msg_vcode").val();
				$.ajax({
					type : 'post',
					url : 'LeaveMessage_save',
					data : {
						email : email,
						message : message,
						name : name,
						telephone : telephone,
						msg_vcode : msg_vcode
					},
					success : function(data) {
						if (data == "true") {
							jAlert("留言成功！，感谢你的参与!");
						} else {
							jAlert("留言失败！，感谢你的参与!");
						}
						$("#pop_ly_id_div").hide();
						
					},
					error : function() {
						jAlert('系统错误，请联系管理员', '注册提示');
					}
				});
			
			})
});

