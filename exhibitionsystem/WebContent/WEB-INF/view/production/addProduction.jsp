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
	<style type="text/css">
		.layui-form-item .layui-inline{ width:33.333%; float:left; margin-right:0; }
		@media(max-width:1240px){
			.layui-form-item .layui-inline{ width:100%; float:none; }
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.1.min.js"></script>
</head>
<body class="childrenBody">
	<form class="layui-form" style="width:80%;">
		<div class="layui-form-item">
			<div class="layui-inline">
			<label class="layui-form-label">作品名</label>
			<div class="layui-input-block">
				<input type="text" name="production_info_name" class="layui-input userName" lay-verify="required" placeholder="请输入作品名">
			</div>
			</div>
		<div class="layui-inline">
	
			<label class="layui-form-label">作者</label>
			<div class="layui-input-block">
				<input type="text" name="production_info_author" class="layui-input userEmail" lay-verify="email" placeholder="请输入作者">
			</div>
		</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
			    <label class="layui-form-label">作品类型</label>
				<div class="layui-input-block">
					<select name="production_info_type" class="userGrade" lay-filter="userGrade">
						<option value="0">类型1</option>
						<option value="1">类型2</option>
				        <option value="2">类型3</option>
				        <option value="3">类型4</option>
				    </select>
				</div>
		    </div>
		<div class="layui-inline">
	
			<label class="layui-form-label">创建时间</label>
			<div class="layui-input-block">
				<input name="production_info_creationtime" class="layui-input" id="test1" placeholder="yyyy-MM-dd" type="text">
			</div>
		</div>
		</div>
		<div class="layui-form-item">
			
		 
		<div class="layui-form-item">
			<label class="layui-form-label">作品描述</label>
			<div class="layui-input-block">
				<textarea name="" placeholder="请输入作品描述" class="layui-textarea linksDesc"></textarea>
			</div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" id="addProduction" lay-filter="addUser">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div>
	</form>
	<script type="text/javascript" src="${pageContext.request.contextPath }/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/production/form.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/production/productionAdd.js"></script>
</body>
</html>