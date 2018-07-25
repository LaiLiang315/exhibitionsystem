$(document).ready(function(){
	//获取分类信息
	getProductionTypeInfo();
	//加载旧信息
	oldProductionInfo();
});
//放入类型信息
function getProductionTypeInfo() {
	$.ajax({
		type:'POST',
		url:'/exhibitionsystem/carouselManagement/carouselManagement_querryCarousel',
		cache: false,  
	    processData: false,  
	    contentType: false,
	    success:function(result){
	    	var listCarouselDTO = JSON.parse(result);
	    	putType(listCarouselDTO);
	    }
	})
}
//查询分类下拉菜单
function putType(listCarouselDTO){
	var strStart = '<option value=""></option>';
	var str="";
	var typeNames= document.querySelector("#selectTypes");// 定位放入的位置
	var length = listCarouselDTO.length;
	//遍历对象
	for(var i=0;i<length;i++){
		var typeId=listCarouselDTO[i].type.production_type_id;//类型id
		var typeName=listCarouselDTO[i].type.production_type_name;//类别名称
		str+='<option value="'+typeId+'">'+typeName+'</option>';
	}
	var strAll = strStart+str;
	typeNames.innerHTML=strAll;// 插入标签
	//layui再次渲染
	layui.use('form', function(){
		var form = layui.form; 
		form.render();
	});
}
//查询旧信息
function oldProductionInfo(){
	//获取urldata_id的值
	var data_id='data_id';
	var dataId = GetQueryString(data_id);
	var formData = new FormData();
	//放入作品信息
	formData.append("productionInfo.production_info_id",dataId);
	$.ajax({
		type:'POST',
		data:formData,
		url:'/exhibitionsystem/productionManagement/productionManagement_querryOneProduction',
		cache: false,  
	    processData: false,  
	    contentType: false,
	    success:function(result){
	    	var productionThreeFormDTO = JSON.parse(result);
	    	console.log(JSON.stringify(productionThreeFormDTO));
	    	putOneProInfo(productionThreeFormDTO);
	    }
	})
}
//放入旧信息
function putOneProInfo(productionThreeFormDTO){
	document.getElementById("productionInfoName").value = productionThreeFormDTO.productionDTO.info.production_info_name;
	document.getElementById("productionInfoAuthor").value = productionThreeFormDTO.productionDTO.info.production_info_author;
	var select = 'dd[lay-value='+productionThreeFormDTO.productionDTO.info.production_info_type+']';
	$('#selectTypes').siblings("div.layui-form-select").find('dl').find(select).click();
	document.getElementById("test1").value = productionThreeFormDTO.productionDTO.info.production_info_creationtime;
	$("input[name=sex][value=0]").attr("checked", productionThreeFormDTO.productionDTO.info.production_info_isdailywork == 0 ? true : false)
	$("input[name=sex][value=1]").attr("checked", productionThreeFormDTO.productionDTO.info.production_info_isdailywork == 1 ? true : false)
	document.getElementById("proDiscription").value = productionThreeFormDTO.productionDTO.info.production_info_discription;
	
}
//获取url指定参数值
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}