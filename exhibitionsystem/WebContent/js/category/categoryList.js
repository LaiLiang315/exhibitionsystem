window.onload = function() {
		console.log("启动listCategory_ajax");
		listCategory_ajax();
}
	
function listCategory_ajax(){
	var formData = new FormData();
	formData.append("showAll",1);
	$.ajax({
		url : "/exhibitionsystem/productionManagement/productionManagement_showPicturesVO",		//数据传输的目的地址，将在这里对前台数据进行操作
		type : "post",
		data : formData,				//这里是前台传到后台的数据
		processData: false,  
	    contentType: false,
		success : function(data) {
			if(data.success=true){
				var vo=JSON.parse(data);
				/*console.log("datadatadata"+data);*/
			}
			var typelength=vo.listProductionDTO.length;
			var category_table_info = document.querySelector(".categoryli");
			var str='';
			console.log("vo"+JSON.stringify(vo));
			for(i=0;i<typelength;i++){
				var votypee=vo.listProductionDTO[i].type;
				console.log("votypee"+JSON.stringify(votypee));
				var typename=votypee.production_type_name;
				var typetitle=votypee.production_type_title;
				console.log("typename======="+typename);
				console.log("typetitle======="+typetitle);
				var typediscription=votypee.production_type_discription;
				var limitypetitle=typetitle;
				var limitypediscription=typediscription;
				if(limitypetitle.length>8){
					limitypetitle=limitypetitle.substr(0,8)+"...";
				}
				if(limitypediscription.length>25){
					limitypediscription=limitypediscription.substr(0,8)+"...";
				}
				str+='<tr>'+ 
				'<td style="text-align:center;"><input type="checkbox" name="item" lay-skin="primary" lay-filter="choose" value="'+votypee.production_type_id+'"/></td>'+
				'<td style="text-align:center;">'+typename+'</td>'+
				'<td style="text-align:center;">'+limitypetitle+'</td>'+
				'<td style="text-align:center;">'+limitypediscription+'</td>'+
				'<td style="text-align:center;">'+
					'<a class="layui-btn layui-btn-mini news_edit" onclick="category_edit(this)" data_id="'+votypee.production_type_id+'"><i class="iconfont icon-edit"></i> 编辑</a>'+
					'<a class="layui-btn layui-btn-danger layui-btn-mini news_del" onclick="category_delete(this)" data_id="'+votypee.production_type_id+ '" ><i class="layui-icon">&#xe640;</i> 删除</a>'+
				'</td>'+
			'</tr>';
			}
			category_table_info.innerHTML=str;
			layui.use('form', function(){
				var form = layui.form; 
				form.render();
				});

				
	}
});
}


//批量删除
function deleteMore(){
  //得到选中的值，ajax操作使用  
	layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
		var formData=new FormData();
		var obj = document.getElementsByName("item");
		console.log("obj==="+obj);
		var check_val;
		for(i=0;i<obj.length;i++){
			if(obj[i].checked){
				check_val=obj[i].value
				formData.append("article_id", check_val);		//存到后台
			}else{
				toastr.error("未指定文章!");
			}
			console.log( "checked要删除的id为===="+check_val);
			$.ajax({
				url:"/exhibitionsystem/productionManagement/article_deleteManyArticle?article_id="+check_val+"",
				type:"post",
				data :formData,
				//报错请加入以下三行，则ajax提交无问题
		        cache: false,  
		        processData: false,  
		        contentType: false,
				success:function(result){
					if(result=="delete_success"){
						console.log("文章删除成功了哦!");
						toastr.success("文章删除成功了哦!");
						setTimeout(function(){
							location.href="/lx/skip/skip_intoArticlelist";
						},1000);
					}else{
						toastr.error("删除文章失败!");
						console.log("删除失败!");
					}}
			});
		}
		
      
	
		});
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
	layui.use('form', function(){
		var form = layui.form; 
		form.render();
		});
}			


//单个删除
function category_delete(object_i){
	console.log("=======");
	layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
		var ar=$(object_i).attr('data_id');//定义id
		var formData = new FormData;
		formData.append("idList", ar);
		//var arid=JSON.parse(ar);		//转换成json对象
		console.log("====arar==="+ar);
			$.ajax({
				url:"/exhibitionsystem/productionTypeManagement/productionManagement_deleteProductionType?idList="+ar+"",
				type:"post",
				data :formData,
				//报错请加入以下三行，则ajax提交无问题
		        cache: false,  
		        processData: false,  
		        contentType: false,
				success:function(result){
					console.log("result----==="+result);
					if(result=="deleteSuccess"){
						toastr.success("文章删除成功了哦!");
						setTimeout(function(){
							location.href="/exhibitionsystem/skip/skip_intoCategoryList";
						},1000);
					}else{
						toastr.error("删除失败33131!");
					}}
			});
		})
}

//

//点击编辑
function category_edit(object_i){
	var ar=$(object_i).attr('data_id');//定义id
	location.href="/exhibitionsystem/skip/skip_intoCategoryEdit";
	show_categoryinfo(ar);
}
//单个分类详情页面
function show_categoryinfo(ar){
	$.ajax({
		url : "/exhibitionsystem/productionManagement/productionManagement_showPicturesVO",		//数据传输的目的地址，将在这里对前台数据进行操作
		type : "post",
		data : formData,				//这里是前台传到后台的数据
		processData: false,  
	    contentType: false,
		success : function(data) {
			if(data.success=true){
				var vo=JSON.parse(data);
			}
	}});
}
/*function category_edit(object_i){
	var ar=$(object_i).attr('data_id');//定义id
	var production_type_name = $("input[name='production_type_name']").val();
	var production_type_title = $("input[name='production_type_title']").val();
	var production_type_discription = $("input[name='production_type_discription']").val();
	var formData = new FormData;
	formData.append("production_type_id", ar);
	formData.append("production_type_name", production_type_name);
	formData.append("production_type_title", production_type_title);
	formData.append("production_type_discription", production_type_discription);
	formData.append("production_type_logo", production_type_discription);
	formData.append("production_type_picture", production_type_discription);
	formData.append("production_type_discription", production_type_discription);
	
}*/