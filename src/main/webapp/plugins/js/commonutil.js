function skipUrl(url,totalpage,page){
	   if(page>totalpage){
		   $.ligerDialog.error('后边没有啦！！！');
	   }else if(page<1){
		   $.ligerDialog.error('前边没有啦！！！');
	   }else{
		   window.location.href=url;
	   }
}