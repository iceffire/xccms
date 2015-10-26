$(document).ready(function() {
	winwidthval();
	$(".h-t-login .l-abous").hover(function(){
		$(this).find(".f-ab").show();	
	},function(){
		$(this).find(".f-ab").hide();		
	});
	$(".h-t-login .l-name").hover(function(){
		$(this).find(".h-t-a").addClass("hover");	
		$(this).find(".l-name-banner-box").show();
	},function(){
		$(this).find(".h-t-a").removeClass("hover");
		$(this).find(".l-name-banner-box").hide();		
	});
	$(".header-box .h-nav-li").hover(function(){
		$(this).addClass("hover");	
		$(this).find(".h-nav-erji").css({"visibility":"visible"});
	},function(){
		$(this).removeClass("hover");	
		$(this).find(".h-nav-erji").css({"visibility":"hidden"});
	});	
});
$(window).load(function(){
	winwidthval();	
});
$(window).resize(function(){
    winwidthval();
});
function winwidthval(){
	var leftpo=0;
	var leftban=0;
	var dangaiw=0;
	var jilivaval=0;
	var winwidth=$(window).width()>1000?$(window).width():1000;
	leftban=Math.ceil((winwidth-1000)/2);
	$(".header-box,.header-box .h-nav-erji,.footer-box").css({"width":winwidth});
	$(".header-box .h-nav-li").each(function(index, element) {
       leftpo=leftban+(142*index); 
	   $(this).find(".h-nav-erji").css({"left":-leftpo});
	   dangaiw=$(this).find(".h-nav-erji-con").width();
	   jilivaval=leftpo-((dangaiw-142)/2);
	   $(this).find(".h-nav-erji-con").css({"left":jilivaval});
    });
}

/*搜索框*/
$(document).ready(function(){
	$("TEXTAREA,input[focucmsg]") .each (function(){
		$(this).val($(this).attr("focucmsg"));
		$(this).val($(this).attr("focucmsg")).css("color","#666666");
		$(this).focus(function(){
			if($(this).val() == $(this).attr("focucmsg")){
				$(this).val('');
				$(this).val('').css("color","#333");
			}
		});
		$(this).blur(function(){
			if(!$(this).val()){
				$(this).val($(this).attr("focucmsg"));
				$(this).val($(this).attr("focucmsg")).css("color","#666666");
			}
		});
	});
});

$(function(){
	$(".WD_left .wd_ul1 li:last").css("borderBottom","0px");
	$(".WD_left .wd_ul2 li,.m_list1 li,.goods_ul li,.goods_ul2 li,.con_Gallery li").hover(function(){
		$(this).addClass("hover");
	},function(){
		$(this).removeClass("hover");
	});

	$(".po-box .li4 a").click(function() {
		$(window).scrollTop();
		$('body').animate({scrollTop:0},500);
		return false;
	});

	$(".youshi_ul li").each(function(index){
		if(index>3){
			$(this).css("marginTop","21px");
		}
		$(this).find("i").css({backgroundPositionX:'-440px',backgroundPositionY:-(20+(index*60))+'px'})
	});

	$(".youshi_ul li").hover(function(){
		$(this).find(".box1").stop(true,true).fadeOut(200);
		$(this).find(".box2").stop(true,true).fadeIn(200);
	},function(){
		$(this).find(".box1").stop(true,true).fadeIn(200);
		$(this).find(".box2").stop(true,true).fadeOut(200);
	});
	$(".con_Gallery li").each(function(index){		
		$(this).find("i").css({backgroundPositionX:'-380px',backgroundPositionY:-(20+(index*60))+'px'})
	});

	$(".m_tab .tab_ul li").click(function(){
		var _LiNum=$(this).index();
		$(this).addClass("hover").siblings("li").removeClass("hover");
		$(this).parents(".m_tab").find(".tab_box").eq(_LiNum).show().siblings(".tab_box").hide();
	});

});



/*banner*/
$(window).resize(function(){
        Pagesize();
    });
    function Pagesize(){
        if($('body').width() > 1000){
            $('.m-focus-d .switchable-content li').css({'width':$('body').width()});
        }else{
            $('.m-focus-d .switchable-content li').css({'width':'1000px'});
        }
    };
$(function(){
    $('.m-focus-d .switchable-content li').each(function(index, element) {
            var imgSrc=$(this).find('img').attr('src');
            $(this).css('background','url('+imgSrc+') no-repeat scroll 50% 0px transparent');
        });
        Pagesize();
    
        $('.m-focus-d').slide({
            //direction: 'y',
            speed: 'slow',
            navCls: 'switchable-nav',
            contentCls: 'switchable-content',
            interval: 4000,
            caption: false,   //是否带有标题
            prevBtnCls:'m-focus-prev',
            nextBtnCls:'m-focus-next',
            evtype: 'mouse',
            effect:'fade'   //'random', 'normal', 'fade', 'scroll', 'fold', 'slice', 'slide', 'shutter', 'grow'     
        });
        $('.m-focus-d .switchable-nav li').html("●");        
    });

$(function(){
	$('.m-focus-classic,.m-focus-classic2').slide({
		direction: 'x',
		speed: 'slow',
		cur: 'current',
		navCls: 'thumbs-list',
		contentCls: 'pic-list',
		caption: true,   //是否带有标题
		prevBtnCls:'ui-prev',
		nextBtnCls:'ui-next',
		evtype: 'mouse',
		effect:'grow'  
	});
});


