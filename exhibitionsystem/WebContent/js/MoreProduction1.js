var pro_paginationQuery = {
		"currPage":"1",
}
//var rr=JSON.parse(article_paginationQuery);
//console.log("rwwe"+rr);
window.onload = function() {
		console.log("启动MoreProduction1.js");
		show_MoreProduction();
}

function show_MoreProduction (){
	var pr_paginationQueryAjax = {
			"showAllVO.pageIndex" : pro_paginationQuery.currPage,	//showAllVO.pageIndex
		}
	//console.log("pr_currentpageQuery="+JSON.stringify(pr_currentpageQuery));
	$.ajax({
		url : "/exhibitionsystem/productionManagement/productionManagement_querryTenMoreVO",		//数据传输的目的地址，将在这里对前台数据进行操作
		type : "post",
		data :	pr_paginationQueryAjax ,				//这里是前台传到后台的数据
		success : function(result) {
			if(result.success=true){
				console.log("listCarouselDTO---"+result);
				var prinfo = JSON.parse(result);
		    	show_More(prinfo);
			}
	}
	});
}

function show_More(prinfo){
	var str='';
	var pr_table=document.querySelector("#pr_table");
	var object=prinfo.listInfoTypePhotoDTO;
	console.log("object[========="+object.length);
	for(var i=0;i<object.length;i++){
		if(object[i].info.production_info_discription!=null){
			if (object[i].info.production_info_discription.length > 45) {
				object[i].info.production_info_discription = object[i].info.production_info_discription
						.substr(0, 45)
						+ "...";
			}							
		}
		if(object[i].picture==null);
		if(object[i].type==null)object[i].type.production_type_name="";
		str+='<li class="item1"> <img src="/exhibitionsystem/productionManagement/productionManagement_IoReadImage?fileFileName='+object[i].picture.production_pictures_name+'"  width="240" height="152" alt="" /> <strong>'+object[i].info.production_info_name+'</strong> UPTATED:'+object[i].info.production_info_creationtime+''+
    	      '<p> <strong>'+object[i].info.production_info_name+'</strong> <em>类型：'+object[i].type.production_type_name+'<br/>'+
    	       ' </em>'+object[i].info.production_info_discription+'<br/>'+
    	        '<a href="/exhibitionsystem/ProductionDetail.html?data_id='+object[i].info.production_info_id+'" data_id="'+object[i].info.production_info_id+'" class="btn_blue">查看品牌故事</a>  </p>'+
    	    '</li>'
    	        
	}
	pr_table.innerHTML=str;
	pro_paginationQuery.currPage=prinfo.pageIndex;
	pro_paginationQuery.totalPage=prinfo.totalPages;
	pro_paginationQuery.pageSize=prinfo.pageSize;
	pro_paginationQuery.count=prinfo.totalRecords;
	// 获取分页器的页面信息
	var page_info = document.querySelector(".page-infomation");
	page_info.innerHTML = "共" + pro_paginationQuery.count
			+ "条记录&nbsp;&nbsp;当前" + pro_paginationQuery.currPage + "/"
			+ pro_paginationQuery.totalPage + "页";
}

//首页
function firstPage() {
	//console.log("首页"+article_paginationQuery.currPage);
	if (pro_paginationQuery.currPage <= 1) {
		toastr.error("已经是第一页了哦!");
	} else {
		pro_paginationQuery.currPage = 1;
		show_MoreProduction ()
	}
}

//上一页
function prePage() {
	console.log("上一页");
	if (pro_paginationQuery.currPage <= 1) {
		toastr.error("已经是第一页了哦!");
	} else {
		pro_paginationQuery.currPage = --pro_paginationQuery.currPage;
		console.log("当前页" + pro_paginationQuery.currPage);
		show_MoreProduction ()
	}
}
//下一页
function nextPage() {
	console.log("下一页");
	if (pro_paginationQuery.currPage >= pro_paginationQuery.totalPage) {
		toastr.error("没有下一页了哦!");
	} else {

		pro_paginationQuery.currPage = ++pro_paginationQuery.currPage ;
		console.log("当前页" + pro_paginationQuery.currPage );
		show_MoreProduction ()
	}
}

//尾页
function lastPage() {
	console.log("尾页");
	if (pro_paginationQuery.currPage >= pro_paginationQuery.totalPage) {
		toastr.error("没有下一页了哦!");
	} else {
		pro_paginationQuery.currPage = pro_paginationQuery.totalPage;
		show_MoreProduction ()
	}
}

