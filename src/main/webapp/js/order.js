$(function (){
	initComplexArea('seachprov', 'seachcity', 'seachdistrict', area_array, sub_array, '44', '0', '0');
	queryChooseproductCart();
	queryuseraddress()
});
/**
 * 改变地址显示
 */
function showaddressform(){
	$('#new-address-form').css("display",'block');
}
function hideaddressform(){
	$('#new-address-form').css("display","none");
}
//得到地区码
function getAreaID(){
	var area = 0;          
	if($("#seachdistrict").val() != "0"){
		area = $("#seachdistrict").val();                
	}else if ($("#seachcity").val() != "0"){
		area = $("#seachcity").val();
	}else{
		area = $("#seachprov").val();
	}
	return area;
}

function showAreaID() {
	//地区码
	var areaID = getAreaID();
	//地区名
	var areaName = getAreaNamebyID(areaID) ;
	alert("您选择的地区码：" + areaID + "      地区名：" + areaName);            
}
/**
 * 支付
 */
function  pay(){
	var areaID = getAreaID();
	var areaName = getAreaNamebyID(areaID) ;
	$("#address_head").val(areaName);
	var identity=$('input[name="identity"]:checked').val();
	if(identity=="new"){
		if($('#contactname').val()==""){
			jAlert("收货人不能为空!","友情提示");
			return;
		}
		if($('#address_head').val()==""){
			jAlert("所在不能为空!","友情提示");
			return;
		}
		if($('#address_detail').val()==""){
			jAlert("详细地址不能为空!","友情提示");
			return;
		}
		if($('#contacttelephone').val()==""){
			jAlert("手机号不能为空!","友情提示");
			return;
		}
		if($('#address_postcode').val()==""){
			jAlert("邮编不能为空!","友情提示");
			return;
		}
	}else if($.trim(identity)==""){
		jAlert("请选择收货地址!","友情提示");
		return;
	}
	$("#orderpayform").submit();
	
}
//根据地区码查询地区名
function getAreaNamebyID(areaID){
	var areaName = "";
	if(areaID.length == 2){
		areaName = area_array[areaID];
	}else if(areaID.length == 4){
		var index1 = areaID.substring(0, 2);
		areaName = area_array[index1] + " " + sub_array[index1][areaID];
	}else if(areaID.length == 6){
		var index1 = areaID.substring(0, 2);
		var index2 = areaID.substring(0, 4);
		areaName = area_array[index1] + " " + sub_array[index1][index2] + " " + sub_arr[index2][areaID];
	}
	return areaName;
}
/**
 * 查询用户地址
 */
function queryuseraddress(){
	var noCache = Date();
	$.getJSON("Address_queryuserAddres",{"noCache":noCache},
			function(data) {
				var itemhtml ="";
				if(data.total>0){
					$.each(data.rows,function(i, item) {
							itemhtml =itemhtml
							+ "<li><input type=\"radio\" name=\"identity\"  value=\""+item.id+"\" onclick=\"hideaddressform()\"/>"+item.addressHead+" "+item.addressDetail+" "+item.consignee+" "+item.telephone+" "+item.postcode+"</li>";
						}); 
				
					itemhtml =itemhtml+" <li><input type=\"radio\" name=\"identity\"  value=\"new\" onclick=\"showaddressform()\"/>重新填写新地址</li> ";
					$("#consignee-list-ul").html(itemhtml);
				}else{
					itemhtml  ="<li><input type=\"radio\" name=\"identity\" checked=\"checked\" value=\"new\" onclick=\"showaddressform()\"/>重新填写新地址</li>";
					$("#consignee-list-ul").html(itemhtml);
					showaddressform();
				}
			});
}
function queryChooseproductCart(){
	var imageurl = $("#image_path_url").val();
	var noCache = Date();
	$.getJSON("ShopCart_queryShopCart",{"noCache":noCache},
			function(data) {
				$("#shopcartnum").html(data.itemnumber);
				var itemhtml ="";
				if(data.itemnumber>0){
					itemhtml =" <table style=\"border: 1px dashed #ddd; width: 100%\">"
						+" <tbody class=\"review-thead\"><tr><td class=\"fore1\">商品</td><td class=\"fore2\">商城价</td><td class=\"fore3\">数量</td><td class=\"fore4\">小计</td></tr></tbody> ";
					var  totalprice=0 ;
					$.each(data.list,function(i, item) {
						if(item.checkbox){
							var price =item.bsProduct.productPrice.toFixed(2);
							var xiaojiprice =(item.bsProduct.productPrice*item.productnumber).toFixed(2);
							totalprice =totalprice+(item.bsProduct.productPrice*item.productnumber);
							itemhtml =itemhtml
							+"<tr style=\"border-bottom: 1px #ddd dashed;\"> "
							+"	<td class=\"fore1\"> "
							+"								<div class=\"p-goods\"> "
							+"									<div class=\"p-img\"> "
							+"											<a target=\"_blank\" href=\"Ordering_queryProductInfo_"+item.bsProduct.id+".shtm\"> "
							+"												<img width=\"60\" src='"+imageurl+"/"+item.bsProduct.productImageUrl+".png'/> "
							+"											</a> "
							+"										</div> "
							+"										<div class=\"p-detail\"> "
							+"											<div class=\"p-name\"> "
							+"												<a target=\"_blank\" href=\"Ordering_queryProductInfo_"+item.bsProduct.id+".shtm\">"+item.bsProduct.productName+"</a> "
							+"											</div> "
							+"											<div class=\"p-more\">商品编号："+item.bsProduct.id+"</div> "
							+"										</div> "
							+"									</div></td> "
							+"								<td class=\"p-price\"><strong>￥"+price+"</strong> "
							+"								</td> "
							+"								<td class=\"p-promotion\">"+item.productnumber+"</td> "
							+"								<td class=\"fore2\">￥"+xiaojiprice+"</td> "
							+"							</tr>";
						}
						}); 
				
					itemhtml =itemhtml+" </table>";
					$("#order_pay_items").html(itemhtml);
					totalprice ="￥"+totalprice.toFixed(2);
					$("#payPriceId").html(totalprice);
					
				}else{
					$("#order_pay_items").html(shopcar_no);
				}
			});
}