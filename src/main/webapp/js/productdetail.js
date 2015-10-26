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
		menu.className = i == cursel ? "selected" : "hover";
		//con.style.display = i == cursel ? "block" : "none";
	}
	$("html,body").animate({scrollTop:$("#con_" + name + "_" + cursel).offset().top},1000)
}
$(function() {
	$(window).scroll(function(){
		var scrolltop=$(this).scrollTop();		
		if(scrolltop>=610){		
			$("#tab_usual1").addClass("cc_top");
			$("#tab_addcart").css("display","block");
		}else{
			$("#tab_usual1").removeClass("cc_top");
			$("#tab_addcart").css("display","none");
		}
	});		
});
/**
 * 按虐变灰，点不了
 * 加入购物车特效
 * 数字加一
 * @param e
 */
function addcart(e) {
	var productid = $("#productid").val();
	 $("#confirm_shop_cart").attr("disabled", true) ;
	 $("#confirm_shop_cart").removeClass().addClass("gray_btn");
	 $("#floatOrder").css("display","block");
	 additem(productid);
	 MoveBox(); 
	
}

//移动效果
function MoveBox() {
	var divTop =  $("#floatOrder").offset().top;
	var divLeft =  $("#floatOrder").offset().left;
	 $("#floatOrder").css({
		"position": "absolute",
		"background": "#98bf21",
		"z-index": "500",
		"left": divLeft + "px",
		"top": divTop + "px"
	});
	 $("#floatOrder").animate({
		"left": ($("#cartshop").offset().left - $("#cartshop").width()) + "px",
		"top": ($(document).scrollTop() + 30) + "px",
		"width": "80px",
		"height": "30px"
	},
	500,
	function() {
		 $("#floatOrder").animate({
			"left": $("#cartshop").offset().left + "px",
			"top": $("#cartshop").offset().top + "px",
			"width": "50px",
			"height": "25px"
		},500).fadeTo(0, 0.1).hide(0);
	});
}