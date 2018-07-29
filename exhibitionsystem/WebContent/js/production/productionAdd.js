//筛选类型
var typeId = null;
$(document).ready(function(){
	//获取分类信息
	getProductionTypeInfo();
});
//定义键值对象
function ObjData(key,value){
	this.Key=key;
	this.Value=value;
}
//添加作品
function saveProductionInfo(){
	var array=[];
	var table = document.getElementById("pictrues");
	var ROW = table.rows.length;
	//遍历表格
	for(var i=1;i<ROW;i++){
		console.log(table.rows[i].cells[0].innerHTML)
		var value= table.rows[i].cells[0].innerHTML;//将表格设置为值
		console.log(table.rows[i].cells[1].innerHTML)
		var key=table.rows[i].cells[1].innerHTML;//将文件名定义为键
		var s = new ObjData(key,value);
		array.push(s);
	}
	var pushData = JSON.stringify(array);//将数组转为json字符串
	var formData = new FormData();
	//放入作品信息
	formData.append("productionInfo.production_info_name",$("input[name='production_info_name']").val());
	formData.append("productionInfo.production_info_author",$("input[name='production_info_author']").val());
	formData.append("productionInfo.production_info_isdailywork",$("input[type='radio']:checked").val());
	formData.append("productionInfo.production_info_type",typeId);
	formData.append("productionInfo.production_info_creationtime",$("input[name='production_info_creationtime']").val());
	formData.append("productionInfo.production_info_discription",$("#proDiscription").val());
	formData.append("pictrueMap",pushData);
	$.ajax({
		type:'POST',
		data:formData,
		url:'/exhibitionsystem/productionManagement/productionManagement_addAndComplete',
		cache: false,  
	    processData: false,  
	    contentType: false,
	    success:function(result){
	    	var addResult = JSON.parse(result);
	    	if(addResult=="success"){
				toastr.success("作品添加成功!");
				setTimeout(function(){
					location.href="http://localhost:8080/exhibitionsystem/skip/skip_intoProductionList";
				},500);
			}else{
				toastr.error("作品添加失败!");
			}
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
//判断表单按钮是否为空!$("input[name='sex1']").checked&&!$("input[name='sex2']").checked
function isNull(){
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
	}
}
//图片上传
var i=1;
var belongId="";
layui.use('upload', function(){
		  var $ = layui.jquery
		  ,upload = layui.upload;
		  var demoListView = $('#demoList')
		  ,uploadListIns = upload.render({
		    elem: '#testList'			//指向容器选择器，如：elem: '#id'
		    ,url: '/exhibitionsystem/productionManagement/productionManagement_uploadAndSavePic'
		    ,accept: 'images'
		    ,data:{'production_picture.production_pictures_belong':belongId}
		    ,multiple: true
		    ,auto: false
		    ,bindAction: '#testListAction' //指向一个按钮触发上传，一般配合 auto: false 来使用
		    ,choose: function(obj){   
		      var files = this.files = obj.pushFile(); //将每次选择的文件追加到文件队列
		      //读取本地文件
		      obj.preview(function(index, file, result){
		        var tr = $(['<tr id="upload-'+ index +'">'
		        	,'<td>'+ (i++) +'</td>'
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
		    ,before: function(obj){
		    	if($("input[name='production_info_name']").val()==""||$("input[name='production_info_name']").val()==null){
	    			toastr.error("请填写作品名!");
	    			throw SyntaxError();
	    		}else if($("input[name='production_info_author']").val()==""||$("input[name='production_info_author']").val()==null){
	    			toastr.error("请填写作者!");
	    			throw SyntaxError();
	    		}else if(typeId==null||typeId==""){
	    			toastr.error("请选择作品类型!");
	    			throw SyntaxError();
	    		}else if($("input[name='production_info_creationtime']").val()==""||$("input[name='production_info_creationtime']").val()==null){
	    			toastr.error("请选创作时间!");
	    			throw SyntaxError();
	    		}else if($("#proDiscription").val()==null||$("#proDiscription").val()==""){
	    			toastr.error("请填写作品描述!");
	    			throw SyntaxError();
	    		}
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
		    	if(obj.total==obj.successful){
		    		saveProductionInfo();
		    	}
		    }
		    
});})
