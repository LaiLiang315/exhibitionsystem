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
	<form class="layui-form" style="width:100%;">
	<div style="margin:auto; width:70%;">
		<div class="layui-form-item">
			<div class="layui-inline">
			<label class="layui-form-label">作品名</label>
			<div class="layui-input-block">
				<input type="text" name="production_info_name" class="layui-input userName"  lay-verify="required" placeholder="请输入作品名">
			</div>
			</div>
		<div class="layui-inline">
	
			<label class="layui-form-label">作者</label>
			<div class="layui-input-block">
				<input type="text" name="production_info_author" class="layui-input userEmail"  lay-verify="email" placeholder="请输入作者">
			</div>
		</div>
		</div>

		<div class="layui-form-item">
			<div class="layui-inline">
			    <label class="layui-form-label">作品类型</label>
				<div class="layui-input-block" >
					<select name="production_info_type" id="selectTypes" class="userGrade" lay-filter="userGrade">
						
				    </select>
				</div>
		    </div>
		<div class="layui-inline">
	
			<label class="layui-form-label">创作时间</label>
			<div class="layui-input-block">
				<input name="production_info_creationtime" class="layui-input" id="test1" placeholder="yyyy-MM-dd" type="text">
			</div>
		</div>
		<div class="layui-form-item">
		    <div class="layui-input-block">
		      <input name="sex" value="0" title="毕业设计" checked type="radio">
		      <input name="sex" value="1" title="平时作业" type="radio">
		    </div>
  		</div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">作品描述</label>
			<div class="layui-input-block">
				<textarea id="proDiscription" name="" placeholder="请输入作品描述" class="layui-textarea linksDesc"></textarea>
			</div>
		</div>
		<div class="layui-upload">
		<div class="layui-inline">
		<button type="button" class="layui-btn layui-btn-normal" id="testList">添加作品图集</button>
		</div>
		<div class="layui-inline">
			<div class="layui-form-mid layui-word-aux">温馨提示: 上传作品图集时，上传顺序决定图集排列顺序，请注意！</div>
		</div>
		<div class="layui-upload-list">
			<table class="layui-table" id="pictrues">
				<thead>
					<tr>
						<th>顺序</th>
						<th>文件名</th>
						<th>大小</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody id="demoList"></tbody>
			</table>
		</div>
		<!-- <button type="button" class="layui-btn" id="testListAction">开始上传</button> -->
		
		<div style="margin:auto;width:200px">
		<input type="button" class="layui-btn" id="testListAction" onclick="isNull()" value="提交作品" />
		<button type="reset" class="layui-btn layui-btn-primary">重置信息</button></div>
	</div>
		<!-- <div class="layui-form-item">
			<div class="layui-input-block">
				<button class="layui-btn" id="addProduction" onclick="saveProductionInfo()" lay-filter="addUser">立即提交</button>
				<button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		</div> -->
	</div>
		
		
<!-- ================================作品图集上传==================================== -->
	
	</form>

	<script type="text/javascript" src="${pageContext.request.contextPath }/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/production/form.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/production/productionAdd.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/toastr.js"></script>
	<%-- <script type="text/javascript" src="${pageContext.request.contextPath }/js/production/upload.js"></script> --%>
</body>
</html>