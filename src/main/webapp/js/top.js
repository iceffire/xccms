//弹出隐藏层
function ShowDiv(show_div, bg_div) {
	document.getElementById(show_div).style.display = 'block';
	document.getElementById(bg_div).style.display = 'block';
	var bgdiv = document.getElementById(bg_div);
	bgdiv.style.width = document.body.scrollWidth;
	$("#" + bg_div).height($(document).height());
};

// 关闭弹出层
function CloseDiv(show_div, bg_div) {
	document.getElementById(show_div).style.display = 'none';
	document.getElementById(bg_div).style.display = 'none';
};

function login() {
	var idNumber = $('#idNumber').val();
	var password = $('#password').val();
	if (idNumber == '') {
		jAlert("用户名不能为空!", '登录提示');
		return;
	}
	if (password == '') {
		jAlert("密码不能为空!", '登录提示');
		return;
	}
	$.ajax({
		type : 'post',
		url : 'Login_login',
		data : {
			idNumber : idNumber,
			password : password
		},
		success : function(data) {
			if (data == "0") {
				location.href = 'Login_init';
			} else {
				jAlert('登录失败！用户名或者密码有误！', '登录提示');
			}
		},
		error : function() {
			jAlert('系统错误，请联系管理员', '错误提示');
		}
	});
}

// 检查邮箱是否合法
function checkEmail(el)// 用正则表达式判断
{
	var regu = "^(([0-9a-zA-Z]+)|([0-9a-zA-Z]+[_.0-9a-zA-Z-]*[0-9a-zA-Z-]+))@([a-zA-Z0-9-]+[.])+([a-zA-Z]|net|NET|asia|ASIA|com|COM|gov|GOV|mil|MIL|org|ORG|edu|EDU|int|INT|cn|CN|cc|CC)$";
	var re = new RegExp(regu);
	if (el.search(re) == -1) {
		return true; // 非法
	}
	return false;// 正确
}
// 检查手机号码是否合法
function isMobil(mobile) {
	if (!/^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i.test(mobile)) {
		return true;
	} else {
		return false;
	}
}
function reg() {
	var email = $('#email').val();
	var mobilephone = $('#mobilephone').val();
	var firstpassword = $('#firstpassword').val();
	var secondpassword = $('#secondpassword').val();
	if (email == '') {
		jAlert("邮箱不能为空!", '注册提示');
		return;
	} else {
		var obj = document.getElementById('email');
		if (checkEmail(obj.value)) {
			jAlert("E-mail格式不正确，请检查!", '注册提示');
			obj.focus();
			return false;
		}
	}
	if (mobilephone == '') {
		jAlert("手机号不能为空!", '注册提示');
		return;
	} else {
		var obj = document.getElementById('mobilephone');
		if (isMobil(obj.value)) {
			jAlert("手机格式不正确，请检查!", '注册提示');
			obj.focus();
			return false;
		}
	}
	if (firstpassword == '') {
		jAlert("输入密码不能为空!", '注册提示');
		return;
	}
	if (secondpassword == '') {
		jAlert("输入密码不能为空!", '注册提示');
		return;
	}
	if (firstpassword != secondpassword) {
		jAlert("两次输入密码不一致!", '注册提示');
		return;
	}
	$.ajax({
		type : 'post',
		url : 'Login_regUser',
		data : {
			email : email,
			mobilephone : mobilephone,
			firstpassword : firstpassword,
			secondpassword : secondpassword
		},
		success : function(data) {
			if (data == 'true') {
				jAlert('恭喜你,注册成功!', '注册提示');
			} else {
				jAlert('系统错误，请联系管理员', '注册提示');
			}
		},
		error : function() {
			jAlert('系统错误，请联系管理员', '注册提示');
		}
	});
	CloseDiv('reguser', 'fade');
}
function slideDown(id, time) {
	if (getCookie("isfirst") != "no") {
		SetCookie("isfirst", "no");
		$(id).slideDown(time);
		setTimeout("slideUp('" + id + "'," + time + ")", time * 2);
	}
}
function slideUp(id, time) {
	$(id).slideUp(time);
}
function SetCookie(name, value)// 两个参数，一个是cookie的名子，一个是值
{
	var Days = 30; // 此 cookie 将被保存 30 天
	var exp = new Date(); // new Date("December 31, 9998");
	exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();
}
function getCookie(name)// 取cookies函数
{
	var arr = document.cookie
			.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null)
		return unescape(arr[2]);
	return null;
}
function showlogindiv() {
	ShowDiv('popupLogin', 'fade');
}
function showreguserdiv() {
	ShowDiv('reguser', 'fade');
}
function showhidecartshopdiv() {
	if (document.getElementById('gwc').style.display = 'none') {
		ShowDiv('gwc', 'fade');
	} else {
		CloseDiv('gwc', 'fade');
	}

}
var  shopcar_no="<div id=\"item_product_0\"> "
	+" 	<table cellpadding=\"0\" cellspacing=\"0\" class=\"gwc_tb2\" > "
	+ " <tr> "
	+ " <td class=\"tb2_td3\" width=\"100%\"  height=\"200px\" colspan=\"6\" align=\"center\">购物车暂无商品</td> "
	+ " </tr> "
	+ " </table> </div> ";
function deletecartitem(id){
	$.ajax({
		type : 'post',
		url : 'ShopCart_deleteItem',
		data : {
			productid : id
		},
		success : function(data) {
			if(data=='true'){
				var num =parseInt($("#shopcartnum").html())-1;
				$("#shopcartnum").html(num);
				$("#item_product_"+id).remove();
				if(num==0){
					$("#shop_cart_items").html(shopcar_no);
				}
			}else{
				jAlert('删除失败', '提示');
			}
		},
		error : function() {
			jAlert('系统错误，请联系管理员', '提示');
		}
	});
}
//添加购物车
function additem(productid){
	 $.ajax({
		type : 'post',
		url : 'ShopCart_addItem',
		data : {
			productid : productid
		},
		success : function(data) {
			 queryshopcart();
		},
		error : function() {
			jAlert('系统错误，请联系管理员', '购物车提示');
		}
	});
}

function setProductnum(id,productnumber){
	 $.ajax({
			type : 'post',
			url : 'ShopCart_setProductnum',
			data : {
				productid : id,
				productnumber : productnumber
			},
			success : function(data) {
				
			},
			error : function() {
				jAlert('系统错误，请联系管理员', '购物车提示');
			}
		});
}
/**
 * 加数量
 * @param id
 * @param price
 */
function addproductnum(id,price){
	
	var t = $("#text_box_"+id);
	t.val(parseInt(t.val()) + 1);
	setTotal(id,price);
	GetCount();
	setProductnum(id,t.val());
}
/**
 * --数量
 * @param id
 * @param price
 */
function minproductnum(id,price){
	var t = $("#text_box_"+id);
	t.val(parseInt(t.val()) - 1);
	setTotal(id,price);
	GetCount();
	setProductnum(id,t.val());
}
/**
 * 
 * @param id
 */
function updateCheck(id,checkflag){
	$.ajax({
		type : 'post',
		url : 'ShopCart_updateCheckbox',
		data : {
			productid : id,
			checkflag :checkflag
		},
		success : function(data) {
			if(data=='true'){
				//
			}else{
				jAlert('更新失败', '提示');
			}
		},
		error : function() {
			jAlert('系统错误，请联系管理员', '提示');
		}
	});
}
function itemcheck(id) {
	if ($("#newslist-"+id+"").attr("checked")) {
		updateCheck(id,"yes");
		if(isAllCheck()){
			$("#invert").attr("checked",true);
		}
	} else {
		updateCheck(id,"no");
		$("#invert").attr("checked",false);
	}
	GetCount();
}
/**
 * 检查所有的checkbox，触发计算操作
 */
function checkallbox(){
	$(".gwc_tb2 input[name=newslist]").each(function () {
		if($(this).attr("checked")){
			var id = $(this).attr("id");
			var productid =id.substring(9,id.length);
			itemcheck(productid);
		}
	});
}
function  isAllCheck(){
	var flag =true;
	$(".gwc_tb2 input[name=newslist]").each(function () {
		if($(this).attr("checked")){
			flag = true;
		}else{
			flag = false;
			return false;
		}
	});
	return flag;
}
function  isHaveCheck(){
	var flag =false;
	$(".gwc_tb2 input[name=newslist]").each(function () {
		if($(this).attr("checked")){
			flag = true;
			return true;
		}
	});
	return flag;
}
function GetCount() {
	var conts = 0;
	var aa = 0;
	$(".gwc_tb2 input[name=newslist]").each(function () {
		if ($(this).attr("checked")) {
			for (var i = 0; i < $(this).length; i++) {
				conts += parseFloat($(this).val());
				aa += 1;
			}
		}
	});
	$("#shuliang").text(aa);
	$("#zong1").html((conts).toFixed(2));
	if(aa>0){
		$("#jz1").css("display", "none");
		$("#jz2").css("display", "block");
	}else{
		$("#jz1").css("display", "block");
		$("#jz2").css("display", "none");
	}
}
function setTotal(id,price){
	var t = $("#text_box_"+id);
	$("#total_"+id).html((parseInt(t.val()) * price).toFixed(2));
	$("#newslist-"+id).val(parseInt(t.val()) *price);
}



function  queryshopcart(){
	var imageurl = $("#image_path_url").val();
	var noCache = Date();
	$.getJSON("ShopCart_queryShopCart",{"noCache":noCache},
			function(data) {
				$("#shopcartnum").html(data.itemnumber);
				var itemhtml ="";
				if(data.itemnumber>0){
					$.each(data.list,function(i, item) {
						var price =item.bsProduct.productPrice.toFixed(2);
						var xiaojiprice =(item.bsProduct.productPrice*item.productnumber).toFixed(2);
						itemhtml =itemhtml+"<div id=\"item_product_"+item.bsProduct.id+"\"> "
						+" 	<table cellpadding=\"0\" cellspacing=\"0\" class=\"gwc_tb2\" > "
												+ " <tr> "
												+ " <td class=\"tb2_td1\"><input type=\"checkbox\" value=\""+xiaojiprice+"\" name=\"newslist\" id=\"newslist-"+item.bsProduct.id+"\" onclick=\"itemcheck("+item.bsProduct.id+")\" " ;
												if(item.checkbox){
													itemhtml =itemhtml+ " checked=\"checked\" ";
												}
												itemhtml =itemhtml+ " /></td>  "
												+ " <td class=\"tb2_td2\"><a href=\"Ordering_queryProductInfo_"+item.bsProduct.id+".shtm\"><img src='"+imageurl+"/"+item.bsProduct.productImageUrl+".png' /></a></td>  "
												+ " <td class=\"tb2_td3\"><a href=\"#\">"+item.bsProduct.productName+"</a></td> "
												+ " <td class=\"tb1_td4\">"+price+"</td> "
												+ " <td class=\"tb1_td5\"> "
												+ "	<input onclick=\"minproductnum("+item.bsProduct.id+","+item.bsProduct.productPrice+")\" id=\"min_"+item.bsProduct.id+"\" name=\"\"  style=\" width:20px; height:18px;border:1px solid #ccc;\" type=\"button\" value=\"-\" /> "
												+ "	<input id=\"text_box_"+item.bsProduct.id+"\" name=\"\" type=\"text\" value=\""+item.productnumber+"\" style=\" width:30px; text-align:center; border:1px solid #ccc;\" /> "
												+ "	<input onclick=\"addproductnum("+item.bsProduct.id+","+item.bsProduct.productPrice+")\" id=\"add_"+item.bsProduct.id+"\" name=\"\" style=\" width:20px; height:18px;border:1px solid #ccc;\" type=\"button\" value=\"+\" /> "
												+ "	</td> "
												+ "	<td class=\"tb1_td6\"><label id=\"total_"+item.bsProduct.id+"\" class=\"tot\" style=\"color:#ff5500;font-size:14px; font-weight:bold;\">"+xiaojiprice+"</label></td> "
												+ "	<td class=\"tb1_td7\"><a onclick=\"deletecartitem("+item.bsProduct.id+")\">删除</a></td> "
												+ " </tr> "
												+ " </table> </div> ";
												
									});
					$("#shop_cart_items").html(itemhtml);
					
				}else{
					$("#shop_cart_items").html(shopcar_no);
				}
			});
	
}
// 动态读取用户信息
$(document)
		.ready(
				function() {
					$.ajax({
								type : 'post',
								url : 'Login_isUserLogin',
								success : function(data) {
									var html = "";
									if (data != "") {
										html = '欢迎您：'
												+ data
												+ '<a href="Login_logout">[退出]</a>&nbsp;|&nbsp;';
									} else {
										html = '<a href="#" onclick="showlogindiv()">登录</a>&nbsp;|&nbsp;<a href="#" onclick="showreguserdiv()">注册</a>&nbsp;|&nbsp;';
									}
									html = html
											+ '<a href="Mobile_client.shtm" target="_blank">手机客户端</a>&nbsp;|&nbsp;<a href="OpenApi_index.shtm" target="_blank">开放平台</a>';
									$("#user-header-link").prepend(html);
								},
								error : function() {
									jAlert('系统错误，请联系管理员', '注册提示');
								}
							});
					queryshopcart();
					
				});
$(function() {
	$('#cartshop')
			.hover(
					function() {
						$("#carshopdiv").css("display", "block");
						checkallbox();
					}, // 鼠标移入
					function() {
						$("#carshopdiv").css("display", "none");
					} // 鼠标移出

			);
	$('#carshopdiv').hover(function() {
		$("#carshopdiv").css("display", "block");
		
	}, // 鼠标移入
	function() {
		$("#carshopdiv").css("display", "none");
	} // 鼠标移出

	);
	$(window).scroll(function() {
		var scrolltop = $(this).scrollTop();
		if (scrolltop >= 200) {
			$("#elevator_item").show();
		} else {
			$("#elevator_item").hide();
		}
	});
	$("#elevator").click(function() {
		$("html,body").animate({
			scrollTop : 0
		}, 500);
	});
	$(".qr").hover(function() {
		$(".qr-popup").show();
	}, function() {
		$(".qr-popup").hide();
	});
});

/*var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
		: " http://");
document
		.write(unescape("%3Cscript src='"
				+ _bdhmProtocol
				+ "hm.baidu.com/h.js%3F6a248053a41b78ea041a4230f4bee164' type='text/javascript'%3E%3C/script%3E"));*/
