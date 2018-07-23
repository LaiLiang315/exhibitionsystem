//筛选类型
var typeId = null;
$(document).ready(function(){
	//获取分类信息
	getProductionTypeInfo();
});
//添加作品
function saveProductionInfo(){
	var formData = new FormData();
	//放入作品信息
	formData.append("productionInfo.production_info_name",$("input[name='production_info_name']").val());
	formData.append("productionInfo.production_info_author",$("input[name='production_info_author']").val());
	formData.append("productionInfo.production_info_type",typeId);
	formData.append("productionInfo.production_info_creationtime",$("input[name='production_info_creationtime']").val());
	formData.append("productionInfo.production_info_discription",$("#proDiscription").val());
	//放入图集信息
	
	$.ajax({
		type:'POST',
		data:formData,
		url:'/exhibitionsystem/productionManagement/productionManagement_addProduction',
		cache: false,  
	    processData: false,  
	    contentType: false,
	    success:function(result){
	    	productionVO = JSON.parse(result);
	    	putProductionInfo(productionVO);
	    }
	})
}
//获取选择的分类
layui.use('form', function(){
	var form = layui.form; 
	form.render('select');
	form.on('select(userGrade)',function (data) {
	    typeId = data.value;
	});
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