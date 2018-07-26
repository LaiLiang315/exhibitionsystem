var file1 = document.getElementById("file1");
var file2 = document.getElementById("file2");
var file3 = document.getElementById("file3");

$('#file1').on('change', function (e) {
	var file1 = document.getElementById("file1")
	if(file1.files[0]!=null){
	document.getElementById("demo1").innerHTML=file1.files[0].name;
	var a1=$("#file1").val();
	var a2=a1.split('\\'); //分割
	var a3=a2[a2.length-1];//去掉 // 获取图片名
	var a4=a3.lastIndexOf('.'); //获取 . 出现的位置
    var ext = a3.substring(a4, a3.length).toUpperCase();  //切割 . 获取文件后缀
	
	console.log("第一个文件框获取的文件名是===="+a1);
	console.log("第2个文件框获取的文件名是===="+a2);
	console.log("第3个文件框获取的文件名是===="+a3);
	console.log("第4个文件框获取的文件名是===="+a4);
	console.log("第5个文件框获取的文件名是===="+ext);
	}
	
});
$('#file2').on('change', function (e) {
	var file2 = document.getElementById("file2")
	if(file2.files[0]!=null){
	document.getElementById("demo2").innerHTML=file2.files[0].name;
	}
});
$('#file3').on('change', function (e) {
	var file3 = document.getElementById("file3")
	if(file3.files[0]!=null){
	document.getElementById("demo3").innerHTML=file3.files[0].name;
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
		if(file1.files[0] != null &&file2.files[0]!=null&&file3.files[0]!=null&&production_type_name!="" && production_type_title!="" && production_type_discription!=""){
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