// JavaScript Document
$(function(){
	/************ PNG ************/
	window.onload = function()
	 {
		DD_belatedPNG.fix(".pngbg");
	 }
	
	$('.j-child-num').each(function(i,e){
			var liNum=$(this).children();
			var li_leng=liNum.length;
			for(var j=0; j<li_leng; j++){
				var li_class=liNum.eq(j).attr("class");
				if(li_class==null){li_class=""}
				j2=j+1;
				var li_str="child-"+j2+" ";
				var li_newclass=li_str + li_class;
				liNum.eq(j).attr("class",li_newclass);
				li_newclass="";
			}
		});
		

	
 $(window).resize(function(){
        Pagesize();
    });
	function Pagesize(){
        if($('body').width() > 1000){//$('body').width()
            $('.m-focus-d .switchable-content li').css({'width':'400px'});
        }else{
            $('.m-focus-d .switchable-content li').css({'width':'400px'});
            $('.m-focus-d .switchable-content li').css({'height':'300px'});
        }
    };
	
    $(function(){
        $('.m-focus-d .switchable-content li').each(function(index, element) {
            var imgSrc=$(this).find('img').attr('src');
            $(this).css('background','url('+imgSrc+') no-repeat scroll 50% 0px transparent');
            $(this).css('background-size','100% 100%');
        });
        Pagesize();
		
        $('.m-focus-d').slide({
			//direction: 'y',
			speed: 'slow',
			navCls: 'pic-btn',
			contentCls: 'switchable-content',
			interval: 4000,
			caption: false,   //是否带有标题
			prevBtnCls:'m-focus-prev',
			nextBtnCls:'m-focus-next',
			evtype: 'mouse',
			effect:'fade'   //'random', 'normal', 'fade', 'scroll', 'fold', 'slice', 'slide', 'shutter', 'grow' 	
		});
		$('.m-focus-d .switchable-nav li').html('●');

    });
	
	$(function() {
		$('.m-product-2 .j-product-scroll').carouFredSel({
			responsive: true,
			direction :	"left",
			prev: '.j-prev-2',
			next: '.j-next-2',
			width: '100%',
			auto:{
				play:true,
				pauseOnHover:true,
				timeoutDuration:3000
				},
			pause:3000,
			scroll: 1,
			items: {
				visible: {
					min: 5,
					max: 5
				}
			}
		});
	});

	
	})