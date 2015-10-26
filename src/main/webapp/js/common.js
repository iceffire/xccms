	function showMask(speed){
		document.onkeydown = function(e){
			return false;
		};
		if ($("#mask").length > 0){
			$("#mask").show(speed);
		}else{
			var mask = document.createElement("div");
			$(mask).attr("id","mask");
			$(mask).addClass("mask");
			$(mask).css("height",$(window).height());
			$(mask).css("width",$(window).width());
			$(mask).html("&nbsp;")
			$("body").append(mask);
			$(mask).show(speed);
		}
	}

	function hideMask(){
		document.onkeydown = function(e){
			return true;
		};
		$("#mask").hide();
	}

	function loading(msg,speed,loadCallBack){
		if ($("#loading").length > 0){
			$("#loading").show(speed,loadCallBack);
		}else{
			var loading = document.createElement("div");
			$(loading).attr("id","loading");
			$(loading).addClass("loading");
			$(loading).css("top",$(window).height()/2-40);
			$(loading).css("left",$(window).width()/2-30);
			$(loading).html(msg)
			$("body").append(loading);
			$(loading).show(speed,loadCallBack);
		}
	}

	function unLoading(){
		$("#loading").hide();
	}
	
	/* 判断是否是浮点数字 */
	function isFloat(f){
		if (f.indexOf('.') == 0) {
			return false;
		}
		var i = parseFloat(f);
		if (f != i){
			return false;
		}else{
			return true;
		}
	}

	/* 判断是否是金额（整数小于等于9位，小数小于2位） */
	function isFund(f){
		if (f.indexOf(" ") != -1){
			return false;
		}
		var i = parseFloat(f);
		if (f != i){
			return false;
		}else{
			if (f.indexOf(".") == -1){
				if (f.length > 9){
					return false;
				}else{
					return true;
				}
			}else{
				var a = f.split("\.")[0];
				var b = f.split("\.")[1];
				if (a.length > 9 || a.length < 1 || b.length > 2 || b.length < 1){
					return false;
				}else{
					return true;
				}
			}
		}
	}

	$(document).ready(function() {
		$("a").each(function(){
			$(this).focus(
				function(){
					$(this).blur();
				}
			);
		});
		$(":button").each(function(){
			$(this).focus(
				function(){
					$(this).blur();
				}
			);
			$(this).hover(
				function(){
					$(this).css("fontWeight","bold");
				},
				function(){
					$(this).css("fontWeight","normal");
				}
			);
		});
	});
