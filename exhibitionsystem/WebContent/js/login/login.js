window.onload=function(){
	$(".layui-btn").click(function(){
		console.log("点击了登陆按钮");
		login_ajax();	//执行login的异步
	});
}

function login_ajax(){
	console.log("此对话显示该js正在执行login_ajax");
	var manager_acount=$("input[name='manager_acount']").val();
	var manager_password=$("input[name='manager_password']").val();
	console.log("manager_acount==="+manager_acount);
/*	var form=Document.getElementById("loginuser");*/
	var formData=new FormData();
	formData.append("adminInfo.username",manager_acount);
	formData.append("adminInfo.password",manager_password);
	
	$.ajax({
		url:"/exhibitionsystem/adminlogin/adminLogin_adminLogin",
		type:"post",
		data:formData,		//前台传给后台的数据
		//报错请加入以下三行，则ajax提交无问题
        cache: false,  
        processData: false,  
        contentType: false,
		success:function(result){
			var dd = JSON.parse(result);		//转换成json对象
			console.log("result----"+result);
			if(dd=="success"){	
				console.log("====");
				toastr.success("用户登陆成功!");
				setTimeout(function(){
					location.href="/exhibitionsystem/skip/skip_intoBackground";
				},1000);
			}else{ 
				if(dd=="error"){
				toastr.error("用户账户或密码输入错误!");
			}
		}}
		
	});
}
function logout(){

	layer.confirm('确认退出吗？',function(){
		console.log("111");
		$.ajax({
			url:"/exhibitionsystem/adminlogin/adminLogin_logout",
			type: "post",
	        //报错请加入以下三行，则ajax提交无问题
	        cache: false,  
	        processData: false,  
	        contentType: false,
	        success: function(result){
	        	console.log("222");	
	        	if(result=="logoutSuccess"){
	        		console.log("010211");
	        		toastr.success("成功退出用户后台管理!");
	        		setTimeout(function() {
	           		 location.href="/exhibitionsystem/skip/skip_intoLogin";
				        }, 500);
	        	}
	        }
		});
	});}
var day=new Date().getDay();
var x="";
switch (day)
{
case 0:
  x="星期天";
  break;
case 1:
  x="星期一";
  break;
case 2:
  x="星期二";
  break;
case 3:
  x="星期三";
  break;
case 4:
  x="星期四";
  break;
case 5:
  x="星期五";
  break;
case 6:
  x="星期六";
  break;
}
var myDate=new Date();
var hello=document.querySelector("#helloday");
if(myDate>5&&myDate<12){
	hello.innerHTML = x+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 早上好呀";
}else if(myDate>12&&myDate<19){
	hello.innerHTML = x+"  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;下午茶时光~";
}else{	
	hello.innerHTML = x+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;晚上好~";
}

