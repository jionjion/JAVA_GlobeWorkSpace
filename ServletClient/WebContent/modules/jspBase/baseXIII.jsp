<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>forward动作</title>
</head>
<body>
	<h1>forward动作</h1>
	<p>效果与转发相同</p>
	<jsp:forward page="baseXII_01.jsp">
		<jsp:param value="Jion" name="username"/>
		<jsp:param value="123456" name="password"/>
	</jsp:forward>
</body>
</html>