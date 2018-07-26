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

function upload(){
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
			formData.append('production_type_title', production_type_title);
			formData.append('production_type_discription', production_type_discription);
			console.log("file1.files[0]="+file1.files[0]+"file2.files[0]="+file2.files[0]+"production_type_name="+production_type_name);
		if(file1.files[0] != null &&file2.files[0]!=null&&production_type_name!="" ){
			
			$.ajax({
				url:"/exhibitionsystem/productionTypeManagement/productionManagement_addProductionType",
				type : "POST",
     			data : formData,
     			processData : false,
     			contentType : false,
     			dataType:"text",
     			success : function(result) {
     				var res=result.split(",");	//祛痘大法好啊！！！去他妈的逗号
     				console.log("获取后台的返回结果"+res[0]);
     				if (res[0] == "uploadsuccess") {
     					toastr.success("信息添加成功！");
     					setTimeout(function(){
    						location.href="/exhibitionsystem/skip/skip_intoCategoryList";
    					},500);
     				} else {
     					toastr.error("信息添加失败！");
     				}
     			},
     		});
     	} else {
     		console.log("===为空添加失败....===");
     		toastr.error("必填框不能为空！");
			}
		}