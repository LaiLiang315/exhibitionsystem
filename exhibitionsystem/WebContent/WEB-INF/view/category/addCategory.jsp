<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>作品添加</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/toastr.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/category/add.css">
	<style type="text/css">
		.layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
		@media(max-width:1240px){
			.layui-form-item .layui-inline{ width:100%; float:none; }
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.1.min.js"></script>
</head>
<body class="childrenBody">
	<form class="layui-form" style="width:100%;">
	<div style="width:50%;">
		<div class="layui-form-item">
			<div class="layui-inline">
			<label class="layui-form-label">类别名称</label>
			<div class="layui-input-block">
				<input type="text" name="production_info_name" class="layui-input userName" lay-verify="required" placeholder="请输入作品名">
			</div>
			</div>
		<div class="layui-inline">
			<label class="layui-form-label">类别标题</label>
			<div class="layui-input-block">
				<input type="text" name="production_info_author" class="layui-input userEmail" lay-verify="email" placeholder="请输入作者">
			</div>
		</div>
		</div>

		<div class="layui-form-item">
			<label class="layui-form-label">类别描述</label>
			<div class="layui-input-block">
				<textarea name="" placeholder="请输入作品描述" class="layui-textarea linksDesc"></textarea>
			</div>
		</div>
	</div>	
<!-- ================================控制首页轮播图模块上传==================================== -->
<!-- ===========轮播背景图上传======= -->
<div class="layui-upload" style="margin-left:40px;">
<div id="upload1" >
  <button type="button" class="layui-btn" id="test1">点击上传轮播背景图</button>
  <div class="layui-upload-list">
    <img class="layui-upload-img" id="demo1" style="width:288px;height:130px;">
    <p id="demoText"></p>
  </div>
<button type="button" class="layui-btn" id="testListAction1">立即上传</button>
</div>

<!-- ===========分类LOGO图上传======= -->
<div id="upload2-3">
<div id="upload2" >
  <button type="button" class="layui-btn" id="test2">上传分类LOGO图</button>
  <div class="layui-upload-list">
    <img class="layui-upload-img" id="demo2" style="width:115px;height:122px;">
    <p id="demoText"></p>
  </div>
<button type="button" class="layui-btn" id="testListAction2">立即上传</button>
</div>

<!-- ==========作品代表图上传======= -->
<div id="upload3">
  <button type="button" class="layui-btn" id="test3">上传作品代表图</button>
  <div class="layui-upload-list">
    <img class="layui-upload-img" id="demo3" style="width:115px;height:122px;">
    <p id="demoText"></p>
  </div>
<button type="button" class="layui-btn" id="testListAction3">立即上传</button>  
</div>
</div>

</div>
	<div class="layui-form-item" style="margin-top:40px;">
			<div class="layui-input-block">
				<button class="layui-btn" id="addProduction" lay-filter="addUser">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
	</div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath }/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/category/form.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/category/upload.js"></script>
</body>
</html>