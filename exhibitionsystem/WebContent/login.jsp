<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>	
<head>
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<meta name="keywords" content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />
<link href="${pageContext.request.contextPath }/css/loginstyle.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/toastr.css">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.1.min.js" ></script>
</head>
<body>
 <!--SIGN UP-->
 <h1>管理员后台登陆</h1>
<div class="login-form">
	<!--管理员登陆头像-->
	<div class="avtar">
		<img src="${pageContext.request.contextPath }/images/login/avtar.png" />
	</div>
	
	<!--表单提交-->
	<form>
			<input type="text"  name="manager_acount" placeholder="请输入账户" >
				
			<input type="password" name="manager_password" placeholder="请输入密码">
				
	
	<div class="signin">
		<input type="button" class="layui-btn" value="Login" >
	</div>
	</form>
</div>


<!--版权声明-->
 <div class="copy-rights">
	<p>Copyright @ 2018-2019 PingXiangXueYuan All Right Reserve </p>
	<p><span>萍乡学院版权所有</span></p>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath }/js/toastr.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/login/login.js"></script>
</body>
</html>