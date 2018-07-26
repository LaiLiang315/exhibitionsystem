window.onload = function() {
		console.log("启动getUrlParam");
		show_categoryInfo();
}
var file1 = document.getElementById("file1");
var file2 = document.getElementById("file2");
var file3 = document.getElementById("file3");

//获取第一个文件上传框的所有属性
$('#file1').on('change', function (e) {
	var file1 = document.getElementById("file1")
	if(file1.files[0]!=null){
	/*document.getElementById("demo1").innerHTML=file1.files[0].name;*/
    var animateimg = $("#file1").val(); //获取上传的图片名 带//
    var imgarr=animateimg.split('\\'); //分割
    var myimg=imgarr[imgarr.length-1]; //去掉 // 获取图片名
    var houzui = myimg.lastIndexOf('.'); //获取 . 出现的位置
    var ext = myimg.substring(houzui, myimg.length).toUpperCase();  //切割 . 获取文件后缀
/*	console.log("第1个文件框获取的文件名是===="+animateimg);
	console.log("第2个文件框获取的文件名是===="+imgarr);
	console.log("第3个文件框获取的文件名是===="+myimg);
	console.log("第4个文件框获取的文件名是===="+houzui);
	console.log("第5个文件框获取的文件名是===="+ext);
*/    if(ext !='.PNG' && ext !='.GIF' && ext !='.JPG' && ext !='.JPEG' && ext !='.BMP'){
    	toastr.error("上传文件格式不正确！");
        return false;
    }else{
    	var reader = new FileReader();
        // 将文件以二进制形式进行读入页面
    	reader.readAsDataURL(file1.files[0]);
    	reader.onload=function (e) {
    	document.getElementById('demo1').src=this.result;
    	}
    }
	}
	
});
//获取第二个文件上传框的所有属性
$('#file2').on('change', function (e) {
	var file2 = document.getElementById("file2")
	if(file2.files[0]!=null){
	var animateimg = $("#file2").val(); //获取上传的图片名 带//
    var imgarr=animateimg.split('\\'); //分割
    var myimg=imgarr[imgarr.length-1]; //去掉 // 获取图片名
    var houzui = myimg.lastIndexOf('.'); //获取 . 出现的位置
    var ext = myimg.substring(houzui, myimg.length).toUpperCase();  //切割 . 获取文件后缀
    if(ext !='.PNG' && ext !='.GIF' && ext !='.JPG' && ext !='.JPEG' && ext !='.BMP'){
    	toastr.error("上传文件格式不正确！");
        return false;
    }else{
    	var reader = new FileReader();
        // 将文件以二进制形式进行读入页面
    	reader.readAsDataURL(file2.files[0]);
    	reader.onload=function (e) {
    	document.getElementById('demo2').src=this.result;
    	}}
	}
});
//获取第三个文件上传框的所有属性
$('#file3').on('change', function (e) {
	var file3 = document.getElementById("file3")
	if(file3.files[0]!=null){
	var animateimg = $("#file3").val(); //获取上传的图片名 带//
    var imgarr=animateimg.split('\\'); //分割
    var myimg=imgarr[imgarr.length-1]; //去掉 // 获取图片名
    var houzui = myimg.lastIndexOf('.'); //获取 . 出现的位置
    var ext = myimg.substring(houzui, myimg.length).toUpperCase();  //切割 . 获取文件后缀
    if(ext !='.PNG' && ext !='.GIF' && ext !='.JPG' && ext !='.JPEG' && ext !='.BMP'){
    	toastr.error("上传文件格式不正确！");
        return false;
    }else{
    	var reader = new FileReader();
        // 将文件以二进制形式进行读入页面
    	reader.readAsDataURL(file3.files[0]);
    	reader.onload=function (e) {
    	document.getElementById('demo3').src=this.result;
    	}}
	}
});

//接收URL传过来的类别id
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
var ca_id= GetQueryString("data_id");
console.log("data_id66666===="+ca_id);
var pp_id=ca_id;

function show_categoryInfo (){
	var formData = new FormData();
	formData.append("productionType.production_type_id",pp_id);
	$.ajax({
		url : "/exhibitionsystem/productionTypeManagement/productionManagement_querryProductionType",		//数据传输的目的地址，将在这里对前台数据进行操作
		type : "post",
		data : formData,				//这里是前台传到后台的数据
		processData: false,  
	    contentType: false,
		success : function(productionType) {
			if(productionType.success=true){
				var vo=JSON.parse(productionType);
				console.log("vo======"+productionType);
				show_info(vo);
			}
			layui.use('form', function(){
				var form = layui.form; 
				form.render();
				});
	}
	});
}

function show_info(vo){
	console.log("kkk"+vo.type.production_type_picture);
	var category_table_info1 = document.querySelector("#imggg1");
	var category_table_info2 = document.querySelector("#imggg2");
	var category_table_info3 = document.querySelector("#imggg3");
	document.getElementsByName("production_type_name")[0].value=vo.type.production_type_name;
	document.getElementsByName("production_type_title")[0].value=vo.type.production_type_title;
	document.getElementsByName("production_type_discription")[0].value=vo.type.production_type_discription;
	
	var str1="";
	str1="<img class='layui-upload-img' src='/exhibitionsystem/productionManagement/productionManagement_IoReadImage?fileFileName="+vo.carousel.carousel_picture+"'  id='demo1' style='width:288px;height:130px;'>"
	var str2="";
	str2="<img class='layui-upload-img'src='/exhibitionsystem/productionManagement/productionManagement_IoReadImage?fileFileName="+vo.type.production_type_logo+"' id='demo2' style='width:115px;height:122px;'>"
	var str3="";
	str3="<img class='layui-upload-img' src='/exhibitionsystem/productionManagement/productionManagement_IoReadImage?fileFileName="+vo.type.production_type_picture+"' id='demo3' style='width:115px;height:122px;'>"
	category_table_info1.innerHTML=str1;
	category_table_info2.innerHTML=str2;
	category_table_info3.innerHTML=str3;
}

//修改类型
function upload2(){
	console.log("ca_id"+pp_id);
	var formData=new FormData();
	var production_type_name = $("input[name='production_type_name']").val();
	var production_type_title = $("input[name='production_type_title']").val();
	var production_type_discription = $("textarea[name='production_type_discription']").val();
	console.log("production_type_name"+production_type_name);
	console.log("production_type_title"+production_type_title);
	console.log("production_type_discription"+production_type_discription);
	
			formData.append('file[0]', file1.files[0]);
			formData.append('file[1]', file2.files[0]);
			formData.append('file[2]', file3.files[0]);
			if(file1.files[0]!=null){
				formData.append('fileFileName[0]', file1.files[0].name);
			}
			if(file2.files[0]!=null){
				formData.append('fileFileName[1]', file2.files[0].name);
			}
			if(file3.files[0]!=null){
				formData.append('fileFileName[2]', file3.files[0].name);
			}
			formData.append('production_type_name', production_type_name);
			formData.append('production_type_id', pp_id);
			formData.append('production_type_title', production_type_title);
			formData.append('production_type_discription', production_type_discription);
		if(production_type_name!=""){
			$.ajax({
				url:"/exhibitionsystem/productionTypeManagement/productionManagement_editProductionTypeCarousel",
				type : "POST",
     			data : formData,
     			processData : false,
     			contentType : false,
     			dataType:"text",
     			success : function(result) {
     				var res=result.split(",");	//祛痘大法好啊！！！去他妈的逗号
     				console.log("获取后台的返回结果"+res[0]);
     				if (res[0] == "uploadsuccess") {
     					toastr.success("信息修改成功！");
     					setTimeout(function(){
    						location.href="/exhibitionsystem/skip/skip_intoCategoryList";
    					},500);
     				} else {
     					toastr.error("信息修改失败！");
     				}
     			},
     		});
     	} else {
     		console.log("===为空添加失败....===");
     		toastr.error("必填框不能为空！");
			}
		}
