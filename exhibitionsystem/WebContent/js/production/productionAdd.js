window.onload = function() {
	$("#addProduction").click(function() {
		console.log("启动addProduction_ajax");
		addProduction_ajax();
	}
	);
}

function addProduction_ajax() {
	var article_title = $("input[name='article_title']").val();
	var article_state = $("input[name='article_state']").val();
	var user_name = $("input[name='user_name']").val();
	var article_permission = $("select[name='article_permission']").val();
	var article_type = $("select[name='article_type']").val();
	var article_content = $("textarea[name='article_content']").val();
	var formData = new FormData();
	formData.append("article_title", article_title); // 存入后台
	formData.append("article_state", article_state);
	formData.append("user_name", user_name);
	formData.append("article_permission", article_permission);
	formData.append("article_type", article_type);
	formData.append("article_content", article_content);
	/*原生ajax
	 * var xhr = new XMLHttpRequest(); console.log("xhr"); 
	 * xhr.open("POST","/lx/article/article_addArticle"); 
	 * xhr.send(formData);		//发送数据到后台
	 * xhr.onreadystatechange = function() {
	 *  console.log("xhr1"); 
	 *  if (xhr.readyState == 4 && xhr.status == 200) {
	 *  console.log("xhr2"); 
	 *  var response = xhr.responseText; //得到后台响应
	 *  console.log("response"+response); } }
	 */

	if (article_title != "" && article_content != "") {
		$.ajax({
			url : "/lx/article/article_addArticle",
			type : "POST",
			data : formData,
			// 前台发送给后台的数据 //报错请加入以下三行，则ajax提交无问题 cache: false, processData:
			// false,
			processData : false,
			contentType : false,
			dataType:'text',
			success : function(result) {
				if (result == "addArticleSuccess") {
					console.log("===文章添加成功....===");
					toastr.success("文章添加成功!");
					setTimeout(function() {location.href = "/lx/skip/skip_intoArticlelist"}, 1000);
				} else {
					console.log("===文章添加失败....===");
					toastr.error("文章添加失败!");
					setTimeout(function() {
						location.href = "/lx/skip/skip_intoArticlelist"
					}, 1000);
				}
			},
			/*error : function(result) {
				if (result == "addArticleSuccess") {
					console.log("===文章添加成功....===");
					toastr.success("文章添加成功!");
					location.href = "/lx/skip/skip_intoArticlelist";
				} else {
					console.log("===文章添加失败....===");
					toastr.error("文章添加失败!");
					setTimeout(function() {
						window.location.href = "/lx/skip/skip_intoArticlelist"
					}, 1000);
				}
			}*/
		});
	} else {
		toastr.error("指定内容不能为空!");
	}

}