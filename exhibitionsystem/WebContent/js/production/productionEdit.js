var tableNum=0;//当前表格信息数
var oldNum = 0;//当前作品原有图片总数
var productionId = "";//当前作品id
var typeId = null;//筛选类型
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
	productionId = dataId;
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
	var pictrues = productionThreeFormDTO.listPicture;//获取图集
	var pictruesLength = pictrues.length;
	var str="";
	var typeNames= document.querySelector("#pics");// 定位放入的位置
	//遍历图集
	for(var i=0;i<pictruesLength;i++){
		var shortName = "";
		if(pictrues[i].production_pictures_name.length>50){
			shortName = pictrues[i].production_pictures_name.substr(0,50)+"...";
		}else{
			shortName = pictrues[i].production_pictures_name;
		}
		str+='<tr style="color:#5FB878;">'+
    	'<td>'+pictrues[i].production_pictures_sequence+'</td>'+
        '<td>'+shortName+'</td>'+
        '<td>未知大小</td>'+
        '<td>已上传</td>'+
        '<td>'+
          '<button class="layui-btn layui-btn-xs layui-btn-danger" onclick="deletePictrue('+pictrues[i].production_pictures_id+');">删除</button>'+
        '</td>'+
      '</tr>'
	}
	typeNames.innerHTML=str;// 插入标签
	tableNum=pictruesLength+1;
	oldNum = pictruesLength;
	//layui再次渲染
	layui.use('form', function(){
		var form = layui.form; 
		form.render();
	});
}
//删除单个作品的单个图片
function deletePictrue(deleteObj){
	var formData = new FormData();
	//放入作品信息
	formData.append("",deleteObj);
	$.ajax({
		type:'POST',
		data:formData,
		url:'/exhibitionsystem/productionManagement/productionManagement_querryOneProduction',
		cache: false,  
	    processData: false,  
	    contentType: false,
	    success:function(result){
	    	if(deleteResult=="deleteSuccess"){
				toastr.success("删除成功!");
				oldNum = oldNum-1;//每删除一张图片数减一，行数包括表头
				setTimeout(function(){
					location.href="http://localhost:8080/exhibitionsystem/skip/skip_intoProductionEdit?data_id="+productionId+"";
				},1000);
			}else{
				toastr.error("删除失败!");
			}
	    }
	})
}
//判断表单按钮是否为空!$("input[name='sex1']").checked&&!$("input[name='sex2']").checked
function checkNull(){
	var table = document.getElementById("pictrues");
	var ROW = table.rows.length;
	if($("input[name='production_info_name']").val()==""||$("input[name='production_info_name']").val()==null){
		toastr.error("请填写作品名!");
	}else if($("input[name='production_info_author']").val()==""||$("input[name='production_info_author']").val()==null){
		toastr.error("请填写作者!");
	}else if(typeId==null||typeId==""){
		toastr.error("请选择作品类型!");
	}else if($("input[name='production_info_creationtime']").val()==""||$("input[name='production_info_creationtime']").val()==null){
		toastr.error("请选创作时间!");
	}else if($("#proDiscription").val()==null||$("#proDiscription").val()==""){
		toastr.error("请填写作品描述!");
	}else if(ROW<=1){
		toastr.error("请上传作品图片!");
	}else{
		//changeProInfo();
	}
}
//修改作品信息
function changeProInfo(){
	//根据判断是否删除过图片选择是否补充图片信息
	if((tableNum-1)==oldNum){
		//未删除过图片
		console.log("未删除过图片")
	}else{
		//删除过图片
		console.log("删除过图片")
	}
}
//获取url指定参数值
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]); return null;
}
//获取下拉选择框选择的分类
layui.use('form', function(){
	var form = layui.form; 
	form.render('select');
	form.on('select(userGrade)',function (data) {
	    typeId = data.value;
	});
});
//图片上传
var i=1;
var belongId="";
layui.use('upload', function(){
			var $ = layui.jquery
			,upload = layui.upload;
			var demoListView = $('#pics')
			,uploadListIns = upload.render({
		    elem: '#addPic'			//指向容器选择器，如：elem: '#id'
		    ,url: '/exhibitionsystem/productionManagement/productionManagement_uploadAndSavePic'
		    ,accept: 'file'
		    ,data:{'production_picture.production_pictures_belong':belongId}
		    ,multiple: true
		    ,auto: false
		    ,bindAction: '#excuteUpload' //指向一个按钮触发上传，一般配合 auto: false 来使用
		    ,choose: function(obj){   
		      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
		      //读取本地文件
		      obj.preview(function(index, file, result){
		        var tr = $(['<tr id="upload-'+ index +'">'
		        	,'<td>'+ (tableNum++) +'</td>'
		          ,'<td>'+ file.name +'</td>'
		          ,'<td>'+ (file.size/1014).toFixed(1) +'kb</td>'
		          ,'<td>等待上传</td>'
		          ,'<td>'
		            ,'<button class="layui-btn layui-btn-xs demo-reload layui-hide">重传</button>'
		            ,'<button class="layui-btn layui-btn-xs layui-btn-danger demo-delete">删除</button>'
		          ,'</td>'
		        ,'</tr>'].join(''));
		        //单个重传
		        tr.find('.demo-reload').on('click', function(){
		          obj.upload(index, file);
		        });
		        
		        //删除
		        tr.find('.demo-delete').on('click', function(){
		          delete files[index]; //删除对应的文件
		          tr.remove();
		          uploadListIns.config.elem.next()[0].value = ''; //清空 input file 值，以免删除后出现同名文件不可选
		        });
		        
		        demoListView.append(tr);
		      });
		    }
		    ,done: function(res, index, upload){
		      if(res.code == 0){ //上传成功
		        var tr = demoListView.find('tr#upload-'+ index)
		        ,tds = tr.children();
		        tds.eq(3).html('<span style="color: #5FB878;">上传成功</span>');
		        tds.eq(4).html(''); //清空操作
		        return delete this.files[index]; //删除文件队列已经上传成功的文件
		      }
		      this.error(index, upload);
		    }
		    ,error: function(index, upload){
		      var tr = demoListView.find('tr#upload-'+ index)
		      ,tds = tr.children();
		      tds.eq(3).html('<span style="color: #FF5722;">上传失败</span>');
		      tds.eq(4).find('.demo-reload').removeClass('layui-hide'); //显示重传
		    }
		    ,allDone: function(obj){ //当文件全部被提交后，才触发
		        console.log(obj.total); //得到总文件数
		        console.log(obj.successful); //请求成功的文件数
		        console.log(obj.aborted); //请求失败的文件数
		        //如果文件全部上传成功，执行作品添加操作
		    	/*if(obj.total==obj.successful){
		    		saveProductionInfo();
		    	}*/
		    }
		  })	    
});