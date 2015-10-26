function clientQQ(obj,clientPosition){
	var $clienttWidth=$(obj).find('.client-title').width();
	var $clienttHiehgt=$(obj).find('.client-title').height();
	var $clientcWidth=$(obj).find('.client-con').width();
	var $clientcHiehgt=$(obj).find('.client-con').height();
	var $clientWidth=null;
	var $clientHieght=null;
	$clientWidth=$clienttWidth+$clientcWidth;
	if($clienttHiehgt>$clientcHiehgt){
		$clientHieght=$clienttHiehgt;
	}else{
		$clientHieght=$clientcHiehgt;
	}
	if(clientPosition == 'left'){
		$(obj).addClass('leftclient').css({'width':$clientWidth,'left':-$clientcWidth-1});
		$(obj).hover(function(){
			$(this).stop(true,false).animate({'left':'0'});
		},function(){
			$(this).stop(true,false).animate({'left':-$clientcWidth-1});
		});
		$(obj).find('.client-title').css({'float':'right'});
		$(obj).find('.client-con').css({'float':'right'});
	}else if(clientPosition == 'right'){
		$(obj).addClass('rightclient').css({'width':$clientWidth,'right':-$clientcWidth-1});
		$(obj).hover(function(){
			$(this).stop(true,false).animate({'right':'0'});
		},function(){
			$(this).stop(true,false).animate({'right':-$clientcWidth-1});
		});
		$(obj).find('.client-title').css({'float':'left'});
		$(obj).find('.client-con').css({'float':'left'});
	}
	if(navigator.userAgent.indexOf('MSIE 6.0')>0){
		$(obj).css({'position':'absolute'});
		$(window).scroll(function(){
			var scrollHeight=$(this).scrollTop()+300;
			$(obj).css({'position':'absolute','top':scrollHeight});
		});
	}
}
