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
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/toastr.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/production/list.css" media="all" />
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/layui/layui.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/production/form.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/production/productionList.js"></script>
</head>
<body class="childrenBody">
	<blockquote class="layui-elem-quote news_search">
			<div class="layui-inline layui-form-item layui-form">
			    <label class="layui-form-label">作品类型</label>
				<div class="layui-input-block">
					<select name="userGrade" id="selectType" class="userGrade" lay-filter="userGrade">
				    </select>
				</div>
		   </div>
	<div class="layui-inline">
		  <a class="layui-btn layui-btn-danger batchDel" onclick="batchDelete()")>批量删除</a>
		</div>
		<div class="layui-inline">
			  <div class="layui-input-inline">
		    	<input type="text" value="" id="searchInfo" placeholder="请输入关键字" class="layui-input search_input" oninput="getProductionInfo()">
		 	  </div>
		<a class="layui-btn search_btn">查询</a>
	</div>
	</blockquote>
	<div>
	  	<table class="layui-table">
		    <colgroup>
				<col width="9">
				<col width="15%">
				<col width="12%">
				<col width="35%">
				<col width="9%">
				<col width="9%">
				<col width="20%">
		    </colgroup>
		    <thead>
				<tr>
					<th><input type="checkbox" name="ioo" lay-skin="primary" onclick="allChoose();" lay-filter="allChoose"  id="checkboxallChoose"></th>
					<th>作品名</th>
					<th>作者</th>
					<th>作品描述</th>
					<th>一级分类</th>
					<th>二级分类</th>
					<th>操作</th>
				</tr>
		    </thead>
		    <tbody class="news_content" id="productionInfo">
		    	
		    </tbody>
		</table>
	</div>
	<div class="page-footer">
			<div class="page_info">
				&nbsp;&nbsp;&nbsp;&nbsp; <button class="layui-btn" onclick="firstPage()"><i
				class="fa fa-angle-double-left">首页</i></button>&nbsp;&nbsp;&nbsp;&nbsp;
				 <button class="layui-btn"  onclick="prePage()"><i class="layui-icon"></i></button>
   	 			<button class="layui-btn" onclick="nextPage()"><i class="layui-icon"></i></button>
				<button class="layui-btn" onclick="lastPage()">尾页<i
					class="fa fa-angle-double-right"></i></button>&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="text" class="layui-input-xjc" id="go_input"/>&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="layui-btn" onclick="goPage()">GO</button>	</div>
				<p class='page-infomation'></p>					
	</div>
	<div id="page"></div>
	<script type="text/javascript" src="${pageContext.request.contextPath }/js/toastr.js"></script>
</body>
</html>