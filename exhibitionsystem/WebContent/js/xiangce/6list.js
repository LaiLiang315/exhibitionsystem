$(document).ready(function() {
	console.log("启动list6production_ajax");
	list6production_ajax();
});

function list6production_ajax() {
	var pr_name=new Array();
	var pr_type=new Array();
	var pr_author=new Array();
	var pr_createtime=new Array();
	var pr_description=new Array();
	for(var i=0;i<6;i++){
		pr_name[i]=document.querySelectorAll(".card__title")[i];
		pr_type[i]=document.querySelectorAll(".card__subtitle")[i];
		pr_author[i]=document.querySelectorAll(".meta__author")[i];
		pr_createtime[i]=document.querySelectorAll(".meta__date")[i];
		pr_description[i]=document.querySelectorAll(".pr_description")[i];
	}
	var str="大桥久未";
	//pr_name[0].innerHTML=str;
	console.log("执行添加inner");
	
	jQuery.ajax({
		url : "/exhibitionsystem/productionManagement/productionManagement_querrySixProduction", // 数据传输的目的地址，将在这里对前台数据进行操作
		type : "post",
		data : "", // 这里是前台传到后台的数据
		processData : false,
		contentType : false,
		success : function(data) {
			if (data.success = true) {
				console.log("data=====" + data);
			}
			var typelength = data.listPictureInfoDTO.length;
			console.log("遍历出的数据数量为==="+typelength);
			for (i = 0; i < typelength; i++) {
				var votypee = vo.listProductionDTO[i].type;
				var typename = votypee.production_type_name;
				var typetitle = votypee.production_type_title;
				var typediscription = votypee.production_type_discription;
				var limitypetitle = typetitle;
				var limitypediscription = typediscription;
				if (limitypetitle.length > 6) {
					limitypetitle = limitypetitle.substr(0, 6) + "...";
				}
				if (limitypediscription.length > 25) {
					limitypediscription = limitypediscription.substr(0,
							8)
							+ "...";
				}
			}
		}
	})
}