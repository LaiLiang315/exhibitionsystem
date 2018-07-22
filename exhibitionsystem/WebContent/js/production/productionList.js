var currentPage = 1;
$(document).ready(function(){
	//获取分类信息
	getProductionTypeInfo();
	//获取作品信息
	getProductionInfo();
});

//首页查询分类信息
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
//获取作品信息
function getProductionInfo(){
	var formData = new FormData();
	formData.append("showAll",'1');
	formData.append("search",$("searchInfo").val());
	formData.append("page",currentPage);
	$.ajax({
		type:'POST',
		data:formData,
		url:'/exhibitionsystem/productionManagement/productionManagement_showPicturesVO',
		cache: false,  
	    processData: false,  
	    contentType: false,
	    success:function(result){
	    	var productionVO = JSON.parse(result);
	    	putProductionInfo(productionVO);
	    }
	})
}
//查询分类下拉菜单
function putType(listCarouselDTO){
	var str="";
	var typeNames= document.querySelector("#selectType");// 定位放入的位置
	var length = listCarouselDTO.length;
	//遍历对象
	for(var i=0;i<length;i++){
		var typeId=listCarouselDTO[i].type.production_type_id;//类型id
		var typeName=listCarouselDTO[i].type.production_type_name;//类别名称
		str+='<option value="'+typeId+'">'+typeName+'</option>';
	}
	typeNames.innerHTML=str;// 插入标签
	
}
//列出产品列表
function putProductionInfo(productionVO){
	//遍历productionVO对象
	console.log("嘟嘟嘟！！！"+JSON.stringify(productionVO))
	var length = productionVO.listProductionDTO.length;
	var productions = productionVO.listProductionDTO;
	var str = '';
	console.log("长度啊！！！"+length)
	var productionInfo= document.querySelector("#productionInfo");// 定位放入的位置
	for(var i=0;i<length;i++){
		console.log("走了循环啊！！！")
		var infoList =  productions[i].listInfo;
		var lengthOfProduction = infoList.length;
		for(var j=0;j<lengthOfProduction;j++){
			var discription=infoList[j].production_info_discription;
			var singnalWord = discription;
			var title=infoList[j].production_info_name;
			var limTitle=title;
			//判断作品标题是否过长
			if(title.length>8){
				//截取前十二个字
				limTitle = title.substr(0,8)+"...";
			}
			//判断作品描述是否过长
			if(discription.length>25){
				//截取前十二个字
				singnalWord = discription.substr(0,25)+"...";
			}
			str+='<tr>'+ 
						'<td style="text-align:center;"><input type="checkbox" name="item" lay-skin="primary" lay-filter="choose" value="'+infoList[j].production_info_id+'"/></td>'+
						'<td style="text-align:center;">'+limTitle+'</td>'+
						'<td style="text-align:center;">'+infoList[j].production_info_author+'</td>'+
						'<td style="text-align:center;">'+singnalWord+'</td>'+
						'<td style="text-align:center;">'+productions[i].type.production_type_name+'</td>'+
						'<td style="text-align:center;">'+
							'<a class="layui-btn layui-btn-mini news_edit"><i class="iconfont icon-edit"></i> 编辑</a>'+
							'<a class="layui-btn layui-btn-danger layui-btn-mini news_del" onclick="article_delete(this)" data_id="'+infoList[j].production_info_id+ '" ><i class="layui-icon">&#xe640;</i> 删除</a>'+
						'</td>'+
					'</tr>';
		}
	}
	productionInfo.innerHTML=str;// 插入标签
}
//全选
function allChoose(){
	var checkal=document.getElementById("allChoose");
	var checkbos=document.getElementsByName("item");
	for(i=0;i<checkbos.length;i++){
		var checkbo=checkbos[i];
		if(checkal.checked){
			checkbo.checked="checked";
		}else{
			checkbo.checked=null;
		}
		
	}		
}			
