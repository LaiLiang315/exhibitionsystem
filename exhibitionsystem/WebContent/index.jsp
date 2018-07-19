<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<from action="${pageContext.request.contextPath }/adminlogin/adminLogin_login" method="post">
<input type="username" name="users.username">
<input type="password" name="users.password">
<input type="submit" name="登陆">
						
</body>
</html>