window.onload = function() {
		console.log("启动getUrlParam");
		show_categoryInfo();
}
//接收URL传过来的类别id
function GetQueryString(name)
{
     var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
     var r = window.location.search.substr(1).match(reg);
     if(r!=null)return  unescape(r[2]); return null;
}
var ca_id= GetQueryString("data_id");
console.log("data_id66666===="+ca_id);

function show_categoryInfo (){
	var formData = new FormData();
	formData.append("productionType.production_type_id",ca_id);
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
	var category_table_info = document.querySelector("#imggg");
	document.getElementsByName("production_type_name")[0].value=vo.type.production_type_name;
	document.getElementsByName("production_type_title")[0].value=vo.type.production_type_title;
	document.getElementsByName("production_type_discription")[0].value=vo.type.production_type_discription;
	
	var str="";
	str="<div class='layui-upload' style='margin-left:40px;'>"+
"<div id='upload1' >"+
 " <button type='button' class='layui-btn' onclick='file1.click()'>点击上传轮播背景图</button>"+
  "<input type='file' class='file' id='file1' name='file1' style='display:none'/><br/>"+
 " <div class='layui-upload-list'>"+
    "<img class='layui-upload-img' src='/exhibitionsystem/productionManagement/productionManagement_IoReadImage?fileFileName="+vo.carousel.carousel_picture+"'  id='demo1' style='width:288px;height:130px;'>"+
 " </div>"+
"</div>"+
/*<!-- ===========分类LOGO图上传======= -->*/
"<div id='upload2-3'>"+
"<div id='upload2' >"+
  "<button type='button' class='layui-btn' onclick='file2.click()'>上传分类LOGO图</button>"+
  "<input type='file' class='file' id='file2' name='file2' style='display:none'/><br/>"+
 " <div class='layui-upload-list'>"+
    "<img class='layui-upload-img'src='/exhibitionsystem/productionManagement/productionManagement_IoReadImage?fileFileName="+vo.type.production_type_logo+"' id='demo2' style='width:115px;height:122px;'>"+
    "<p id='demoText'></p>"+
 " </div>"+
"</div>"+

/*<!-- ==========作品代表图上传======= -->*/
"<div id='upload3'>"+
  "<button type='button' class='layui-btn' onclick='file3.click()'>上传作品代表图</button>"+
  "<input type='file' class='file' id='file3' name='file3' style='display:none'/><br/>"+
 " <div class='layui-upload-list'>"+
    "<img class='layui-upload-img' src='/exhibitionsystem/productionManagement/productionManagement_IoReadImage?fileFileName="+vo.type.production_type_picture+"' id='demo3' style='width:115px;height:122px;'>"+
    "<p id='demoText'></p>"+
  "</div>"+
"</div>"+
"</div>"+
"</div>"
category_table_info.innerHTML=str;
}

