<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
	<title>作品列表</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/production/list.css" media="all" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/toastr.css">
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.1.min.js"></script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
	<div class="layui-inline">
		  <a class="layui-btn layui-btn-danger batchDel" onclick="deleteMore()">批量删除</a>
		</div>
		<div class="layui-inline">
			  <div class="layui-input-inline">
		    	<input type="text" id="keyword2" placeholder="请输入关键字" class="layui-input search_input">
		 	  </div>
		<a class="layui-btn search_btn" onclick="search_cate()">查询</a>
	</div>
	</blockquote>
	<div class=" news_list">
	  	<table class="layui-table">
		    <colgroup>
				<col width="9">
				<col>
				<col width="12%">
				<col width="30%">
				<col width="25%">
				<col width="20%">
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="" onclick="allChoose();" lay-skin="primary" lay-filter="allChoose" id="allChoose"></th>
					<th>分类名称</th>
					<th>分类标题</th>
					<th style="text-align:center;">分类描述</th>
					<th>操作</th>
				</tr> 
		    </thead>
		    <tbody class="categoryli"></tbody>
		</table>
	</div>
	<div class="page-footer">
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/toastr.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/production/form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/category/categoryList.js"></script>
</body>
</html>