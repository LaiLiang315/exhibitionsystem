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
//删除单个作品
function deleteProduction(object_i){
		layer.confirm('确定删除此作品信息？',{icon:3, title:'提示信息'},function(index){
			var productionId=$(object_i).attr('data_id');//定义id
			var formData = new FormData;
			formData.append("idList", productionId);
			//var arid=JSON.parse(ar);		//转换成json对象
				$.ajax({
					url:'/exhibitionsystem/productionManagement/productionManagement_deleteProduction',
					type:"post",
					data :formData,
					//报错请加入以下三行，则ajax提交无问题
			        cache: false,  
			        processData: false,  
			        contentType: false,
					success:function(result){
						var deleteResult = JSON.parse(result);
						if(deleteResult=="deleteSuccess"){
							toastr.success("作品信息删除成功!");
							setTimeout(function(){
								location.href="http://localhost:8080/exhibitionsystem/skip/skip_intoProductionList";
							},1000);
						}else{
							toastr.error("删除失败!");
						}}
				});
			})
}
//批量删除作品
function batchDeleteProduction(vals){
		layer.confirm('确定删除此作品信息？',{icon:3, title:'提示信息'},function(index){
			var formData = new FormData;
			formData.append("idList", vals);
			//var arid=JSON.parse(ar);		//转换成json对象
				$.ajax({
					url:'/exhibitionsystem/productionManagement/productionManagement_deleteProduction',
					type:"post",
					data :formData,
					//报错请加入以下三行，则ajax提交无问题
			        cache: false,  
			        processData: false,  
			        contentType: false,
					success:function(result){
						var deleteResult = JSON.parse(result);
						if(deleteResult=="deleteSuccess"){
							toastr.success("作品信息删除成功!");
							setTimeout(function(){
								location.href="http://localhost:8080/exhibitionsystem/skip/skip_intoProductionList";
							},1000);
						}else{
							toastr.error("删除失败!");
						}}
				});
			})
}
//批量删除
function batchDelete(){
	var arr = new Array();
    $.each($('input:checkbox:checked'),function(i){
        arr[i] = $(this).val();
    });
    var vals = arr.join(",");
    batchDeleteProduction(vals);
}
//点击帅选触发事件
layui.use('form', function(){
	var form = layui.form; 
	form.render('select');
	form.on('select(userGrade)',function (data) {
	    typeId = data.value;
	    categoryName = data.elem[data.elem.selectedIndex].text;
	    if(typeId!=""&&typeId!=null){
	    	getProductionInfoByType();
	    }else{
	    	getProductionInfo();
	    }
	});
});
//按分类筛选作品
	function putProductionByType(productionVO){
		var length = productionVO.listProductionDTO.length;
		var productionInfo= document.querySelector("#productionInfo");// 定位放入的位置
		//从vo对象中获取dto对象
		var productions = productionVO.listProductionDTO;
		var str = '';
		var changeType= document.querySelector("#productionInfo");// 定位放入的位置
		//遍历vo找到该类别的所有作品
		for(var i=0;i<length;i++){
			if(productions[i].type.production_type_id==typeId){
				var infoList =  productions[i].listInfo;
				var lengthOfProduction = infoList.length;
				productionVO.totalPages=Math.ceil(infoList.length/(productionVO.pageSize));
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
					var typeOne = null;
					if(infoList[j].production_info_isdailywork==1){
						typeOne="平时作业";
					}else{
						typeOne="毕业设计";
					}
					str+='<tr>'+ 
								'<td style="text-align:center;"><input type="checkbox" name="item" lay-skin="primary" lay-filter="choose" value="'+infoList[j].production_info_id+'"/></td>'+
								'<td style="text-align:center;">'+limTitle+'</td>'+
								'<td style="text-align:center;">'+infoList[j].production_info_author+'</td>'+
								'<td style="text-align:center;">'+singnalWord+'</td>'+
								'<td style="text-align:center;">'+typeOne+'</td>'+
								'<td style="text-align:center;">'+productions[i].type.production_type_name+'</td>'+
								'<td style="text-align:center;">'+
									'<a class="layui-btn layui-btn-mini news_edit" href="http://localhost:8080/exhibitionsystem/skip/skip_intoProductionEdit?data_id='+infoList[j].production_info_id+'"><i class="iconfont icon-edit"></i> 编辑</a>'+
									'<a class="layui-btn layui-btn-danger layui-btn-mini news_del" onclick="deleteProduction(this)" data_id="'+infoList[j].production_info_id+ '" ><i class="layui-icon">&#xe640;</i> 删除</a>'+
								'</td>'+
							'</tr>';
				}
			}
		}
		changeType.innerHTML=str;// 插入标签
		//layui再次渲染
		layui.use('form', function(){
			var form = layui.form; 
			form.render();
		});
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
			var typeOne = null;
			if(productions[j].info.production_info_isdailywork==1){
				typeOne="平时作业";
			}else{
				typeOne="毕业设计";
			}
			str+='<tr>'+ 
						'<td style="text-align:center;"><input type="checkbox" name="item" lay-skin="primary" lay-filter="choose" value="'+infoList[j].production_info_id+'"/></td>'+
						'<td style="text-align:center;">'+limTitle+'</td>'+
						'<td style="text-align:center;">'+infoList[j].production_info_author+'</td>'+
						'<td style="text-align:center;">'+singnalWord+'</td>'+
						'<td style="text-align:center;">'+typeOne+'</td>'+
						'<td style="text-align:center;">'+productions[j].type.production_type_name+'</td>'+
						'<td style="text-align:center;">'+
							'<a class="layui-btn layui-btn-mini news_edit" href="http://localhost:8080/exhibitionsystem/skip/skip_intoProductionEdit?data_id='+productions[j].info.production_info_id+ '"><i class="iconfont icon-edit"></i> 编辑</a>'+
							'<a class="layui-btn layui-btn-danger layui-btn-mini news_del" onclick="deleteProduction(this)" data_id="'+productions[j].info.production_info_id+ '" ><i class="layui-icon">&#xe640;</i> 删除</a>'+
						'</td>'+
					'</tr>';
		}
	}
	productionInfo.innerHTML=str;// 插入标签
}
