var article_paginationQuery = {
		"currPage":"1",
}

window.onload = function() {
	console.log("执行article列表ajax");
	show_articleList();
}
//show_articleList_ajax
function show_articleList(iquery_data) {
	var article_paginationQueryAjax = {
		"currentPage" : article_paginationQuery.currPage,
	}
	
	$.ajax({
		url : "/lx/article/article_getArticleList",		//数据传输的目的地址，将在这里对前台数据进行操作
		type : "post",
		data : article_paginationQueryAjax,				//这里是前台传到后台的数据
		success : function(data) {
			if(data.success=true){
				var result = JSON.parse(data);		//转换成json对象
				var r1=JSON.stringify(result);
				var resultlist=JSON.parse(data).list;
				var r2=JSON.stringify(resultlist);
				// 显示article信息列表
				var article_table_info = document.querySelector("#articleli>tbody");		//获取文档元素
				article_table_info.innerHTML;
				var str = "";
				
				// 遍历json集合
				for (var i = 0; i < resultlist.length; i++) {
					// 得到每条数据
			
					var object = resultlist[i];
					// 得到各条数据的某个信息
					str += "<tr>"
					// 遍历是把article_id的值传给checkbox的value(为后期的批量删除)
					str += '<td style="text-align:center;"><input type="checkbox" name="item" lay-skin="primary" lay-filter="choose" value="'
							+ object.article_id + '"/></td>';
					// a链接是调用action的page_staffDetail方法把object.xsjsglxt_staff_id传入staff_id_transfer,在action里把staff_id_transfer存入staff_id，之后跳转到staffDetails.jsp后，该页面得到staff_id(跳转到各自id的Detail页)
					str += '<td style="text-align:center;"><a href="/lx/article/article_staffDetail?staff_id_transfer='
						+ object.article_author
						+ '">' + object.article_title
							+ '</a></td>';
					str += '<td style="text-align:center;"><a href="/lx/article/article_page_staffDetail?staff_id_transfer='
						+ object.article_author
						+ '">' + object.article_author
							+ '</a></td>';
					var ty;
					switch(object.article_type){
					case "1041647d-0f15-45f3-b049-394a892ab418":
						ty="爱情";
						break;
					case "3b2493a9-3444-4b2b-bccb-1c1899cb7ce0":
						ty="悲剧";
						break;
					case "caab7561-f730-4ae2-bc80-b8f97141b990":
						ty="喜剧";
						break;
					case "fe0aa98a-0c01-4c37-a3ca-922d0eaf0c65":
						ty="文艺";
						break;
					
					}
				
					str += '<td style="text-align:center;"><a href="/lx/article/article_page_staffDetail?staff_id_transfer='
						+ object.article_author
						+ '">' +ty
							+ '</a></td>';
					str += '<td style="text-align:center;"><a href="/lx/article/article_page_staffDetail?staff_id_transfer='
						+ object.article_author
						+ '">' + object.article_time
							+ '</a></td>';
					var spm;
					if(object.article_permission=="0"){
						spm="仅自己可见";
					}else{
						spm="所有人可见";
					}
					str += '<td style="text-align:center;"><a href="/lx/article/article_page_staffDetail?staff_id_transfer='
						+ object.article_author
						+ '">' + spm
							+ '</a></td>';
					var stt;
					if(object.article_state=="0"){
						stt="未审核";
					}else{
						stt="审核通过";
					}
					str += '<td style="text-align:center;"><a href="/lx/article/article_page_staffDetail?staff_id_transfer='
						+ object.article_author
						+ '">' + stt
							+ '</a></td>';
					str += '<td>'
						+  '<a class="layui-btn layui-btn-mini news_edit"><i class="iconfont icon-edit"></i> 编辑</a>'
						+  '<a class="layui-btn layui-btn-normal layui-btn-mini news_collect"><i class="layui-icon">&#xe600;</i> 收藏</a>'
						+  '<a class="layui-btn layui-btn-danger layui-btn-mini news_del" onclick="article_delete(this)" data_id="'+ object.article_id + '" ><i class="layui-icon">&#xe640;</i> 删除</a>'
				        +'</td>';
					// 把td加入tr
					// staff_list_tr.innerHTML=str;
					// 把tr加入tbody
					article_table_info.innerHTML = str;
				}
				//分页信息存入article_paginationQuery中
				article_paginationQuery.currPage=result.currentPage;
				article_paginationQuery.totalPage=result.totalPage;
				article_paginationQuery.pageSize=result.pageSize;
				article_paginationQuery.count=result.count;

				// 获取分页器的页面信息
				var page_info = document.querySelector(".page-infomation");
				page_info.innerHTML = "共" + article_paginationQuery.count
						+ "条记录&nbsp;&nbsp;当前" + article_paginationQuery.currPage + "/"
						+ article_paginationQuery.totalPage + "页";
			}else{
				console.log("传值失败");
			}	
		}
	});
}

//分页
//首页
function firstPage() {
	console.log("首页"+article_paginationQuery.currPage);
	if (article_paginationQuery.currPage <= 1) {
		toastr.error("已经是第一页了哦!");
	} else {
		article_paginationQuery['result.currentPage'] = 1;
		show_articleList();
	}
}

//上一页
function prePage() {
	console.log("上一页");
	if (article_paginationQuery.currPage <= 1) {
		toastr.error("已经是第一页了哦!");
	} else {
		article_paginationQuery.currPage = --article_paginationQuery.currPage;
		console.log("当前页" + article_paginationQuery.currPage);
		show_articleList();
	}
}
//下一页
function nextPage() {
	console.log("下一页");
	if (article_paginationQuery.currPage >= article_paginationQuery.totalPage) {
		toastr.error("没有下一页了哦!");
	} else {

		article_paginationQuery.currPage = ++article_paginationQuery.currPage ;
		console.log("当前页" + article_paginationQuery.currPage );
		show_articleList();
	}
}
//跳页
function goPage() {
	console.log("跳页");
	console.log($("#goInput").val());
	if ($("#go_input").val() <= article_paginationQuery.totalPage && $("#go_input").val() >= 1) {

		article_paginationQuery.currPage = $("#go_input").val();
		show_articleList();
	} else {
		toastr.error("不存在这一页！");
	}
}
//尾页
function lastPage() {
	console.log("尾页");
	if (article_paginationQuery.currPage >= article_paginationQuery.totalPage) {
		toastr.error("没有下一页了哦!");
	} else {
		article_paginationQuery.currPage = article_paginationQuery.totalPage;
		show_articleList();
	}
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
				url:"/lx/article/article_deleteManyArticle?article_id="+check_val+"",
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

$("body").on("click",".news_collect",function(){  //收藏.
	if($(this).text().indexOf("已收藏") > 0){
		layer.msg("取消收藏成功！");
		$(this).html("<i class='layui-icon'>&#xe600;</i> 收藏");
	}else{
		layer.msg("收藏成功！");
		$(this).html("<i class='iconfont icon-star'></i> 已收藏");
	}
})

function allChoose(){
	//全选
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
}			




function article_delete(object_i){
	console.log("=======");
	layer.confirm('确定删除此信息？',{icon:3, title:'提示信息'},function(index){
		var ar=$(object_i).attr('data_id');//定义id
		var formData = new FormData;
		formData.append("article_id", "ar");
		//var arid=JSON.parse(ar);		//转换成json对象
			$.ajax({
				url:"/lx/article/article_deleteArticle?article_id="+ar+"",
				type:"post",
				data :"ar",
				//报错请加入以下三行，则ajax提交无问题
		        cache: false,  
		        processData: false,  
		        contentType: false,
				success:function(result){
					if(result=="delete_success"){
						toastr.success("文章删除成功了哦!");
						setTimeout(function(){
							location.href="/lx/skip/skip_intoArticlelist";
						},1000);
					}else{
						toastr.error("删除失败!");
					}}
			});
		})
}