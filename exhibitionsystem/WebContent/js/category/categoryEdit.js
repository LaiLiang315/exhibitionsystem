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
	formData.append("idList",ca_id);
	$.ajax({
		url : "/exhibitionsystem/productionManagement/productionManagement_getLroductionType",		//数据传输的目的地址，将在这里对前台数据进行操作
		type : "post",
		data : formData,				//这里是前台传到后台的数据
		processData: false,  
	    contentType: false,
		success : function(productionType) {
			if(productionType.success=true){
				var vo=JSON.parse(productionType);
			}
			console.log("vo-------------"+productionType);
				str+='<tr>'+ 
				'<td style="text-align:center;"><input type="checkbox" name="item" lay-skin="primary" lay-filter="choose" value="'+votypee.production_type_id+'"/></td>'+
				'<td style="text-align:center;">'+typename+'</td>'+
				'<td style="text-align:center;">'+limitypetitle+'</td>'+
				'<td style="text-align:center;">'+limitypediscription+'</td>'+
				'<td style="text-align:center;">'+
					'<a class="layui-btn layui-btn-mini news_edit" href="/exhibitionsystem/skip/skip_intoCategoryEdit?data_id='+votypee.production_type_id+'" data_id="'+votypee.production_type_id+'"><i class="iconfont icon-edit"></i>编辑</a>'+
					'<a class="layui-btn layui-btn-danger layui-btn-mini news_del" onclick="category_delete(this)" data_id="'+votypee.production_type_id+ '" ><i class="layui-icon">&#xe640;</i> 删除</a>'+
				'</td>'+
			'</tr>';
			console.log("fd:"+str)
			category_table_info.innerHTML=str;
			layui.use('form', function(){
				var form = layui.form; 
				form.render();
				});

				
	}
	});
}
