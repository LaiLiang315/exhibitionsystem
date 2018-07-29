window.onload = function() {
		console.log("启动getUrlParam");
		show_productionDetil();
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
var pp_id=ca_id;

function show_productionDetil (){
	var formData = new FormData();
	formData.append("productionInfo.production_info_id",pp_id);
	$.ajax({
		url : "/exhibitionsystem/productionManagement/productionManagement_querryOneProduction",		//数据传输的目的地址，将在这里对前台数据进行操作
		type : "post",
		data : formData,				//这里是前台传到后台的数据
		processData: false,  
	    contentType: false,
		success : function(result) {
			if(result.success=true){
				var prinfo = JSON.parse(result);
		    	//console.log("listCarouselDTO---"+prinfo);
		    	show_info(prinfo);
			}
	}
	});
}

function show_info(prinfo){
	//console.log("kkk"+prinfo.type.production_type_picture);
	var prp = document.querySelector("#prp");
	var detil_name = document.querySelector("#detil_name");
	var detil_author = document.querySelector("#detil_author");
	var detil_time = document.querySelector("#detil_time");
	var detil_description = document.querySelector("#detil_description");
	var detil_pics = document.querySelector("#detailed");
	var detil_pictrue1=document.querySelector("#detil_pictrue1");
	//console.log("kkk"+prinfo.productionDTO.info.production_info_name);
	var strp=prinfo.productionDTO.info.production_info_name;
	var str1="作品："+prinfo.productionDTO.info.production_info_name+"";
	var str2="作者："+prinfo.productionDTO.info.production_info_author+"";
	var str3="创作时间："+prinfo.productionDTO.info.production_info_creationtime+"";
	var str4=""+prinfo.productionDTO.info.production_info_discription+"";
	detil_name.innerHTML=str1;
	detil_author.innerHTML=str2;
	detil_time.innerHTML=str3;
	detil_description.innerHTML=str4;
	prp.innerHTML=strp;
	var object=prinfo.listPicture;
	var str6='<img src="/exhibitionsystem/productionManagement/productionManagement_IoReadImage?fileFileName='+object[0].production_pictures_name+'" width="180" height="120"  />';
	var str5='';
	var plength=prinfo.listPicture.length;
	console.log("plength="+plength);
//	str5='<img alt="" src="images/1.png"  style="height: 499px; width: 700px;" />
//				<img alt="" src="images/1.png" />
//				<img alt="" src="images/1.png" tppabs="http://mc18.eatdou.com/uploads/allimg/131102/1-1311021T227.png" />'
	for(i=0;i<plength;i++){
		str5+='<img alt="" src="/exhibitionsystem/productionManagement/productionManagement_IoReadImage?fileFileName='+object[i].production_pictures_name+'"  style="height: 499px; width: 700px;" />';
	}
	detil_pics.innerHTML=str5;
	detil_pictrue1.innerHTML=str6;
}

