var flash_index = 0;
var timer;
$(document).ready(function(){
	var $flashLink = $('.flash_link_li');
	$flashLink.each(function(i){
		var $this = $(this);
		$this.hover(
			function(){
				if (timer){
					window.clearInterval(timer);
				}
				flash_index = i;
				switchImg();
			},
			function(){
				timer = window.setInterval(switchImg, 5000);
			}
		);
	});
	switchImg();
	timer = window.setInterval(switchImg, 5000);
	
});

function switchImg(){
	if (flash_index > 3){
		flash_index = 0;
	}
	var $flashLink = $('.flash_link_li');
	$flashLink.removeClass("flash_link_focus");

	$('#flash_link_'+flash_index).addClass("flash_link_focus");
	
	var $cur = $('.flash_image_div:visible');
	if ($cur.length > 0){
		var id = $cur.attr('id').replace('img_','flash_link_');
		$cur.fadeOut(500,function(){
			
		});
		$('#img_'+flash_index).fadeIn(500);
	}else{
		$('#img_'+flash_index).show();
	}
	flash_index++;
	
}

