$(document).ready(function(){

	$(".title1").click(function(){
		var con=$(this).html();
		 //#getTitle代表页面中想要得到title的值元素  
		$(".gettitle1").html(con);
		
	})
})