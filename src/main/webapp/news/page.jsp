<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<script>
	var curPage = '${currentPage}';
	var totalPageCount = '${totalPageCount}';
	
	function firstPage(){
		var cpage = parseInt(curPage);
		var tpc = parseInt(totalPageCount);
		if(cpage == 1){
			jAlert('已经是第一页啦.','提示');
		}else if(cpage>0 && tpc >0){
			newsSearch(1);
		}else{
			return -1;
		}
	}
	function prevPage(){
		var cpage = parseInt(curPage);
		var tpc = parseInt(totalPageCount);
		if(cpage == 1){
			jAlert('已经是第一页啦.','提示');
		}else if(cpage>=2 && tpc>0){
			newsSearch((cpage-1));
		}else{
			return -1;
		}
	}
	function nextPage(){
		var cpage = parseInt(curPage);
		var tpc = parseInt(totalPageCount);
		if(cpage<tpc && tpc>0){
			newsSearch((cpage+1));
		}else if(cpage == tpc){
			jAlert('已经是最后一页啦.','提示');
			//newsSearch(cpage);
		}else{
			return -1;
		}
	}
	function lastPage(){
		var cpage = parseInt(curPage);
		var tpc = parseInt(totalPageCount);
		if(cpage<tpc && tpc>0){
			newsSearch(tpc);
		}else if(cpage=tpc){
			jAlert('已经是最后一页啦.','提示');				
		}else{
			return -1;
		}
	}
</script>
<!-- 分页 -->
<div id="pagebar" style="margin-top:10px;">
	<table style="width:100%;text-align:center;height:30px;background-color:#EFEFEF">
		<tr>
			<td style="text-align:right">当前记录总数&nbsp;&nbsp;共${totalCount}条记录</td>
			<td width="50px"><a href="javascript:firstPage()" >首页</a>&nbsp;</td>
			<td width="50px">&nbsp;<a href="javascript:prevPage()" >上一页</a>&nbsp;</td>
			<td width="50px">&nbsp;${currentPage}/${totalPageCount}&nbsp;</td>
			<td width="50px">&nbsp;<a href="javascript:nextPage()" >下一页</a>&nbsp;</td>
			<td width="50px">&nbsp;<a href="javascript:lastPage()" >末页</a></td>
		</tr>
	</table>
</div>
<!-- /分页 -->
