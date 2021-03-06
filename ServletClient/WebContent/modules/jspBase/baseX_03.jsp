<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JavaBean的使用</title>
</head>
<body>

	<h1>使用useBean完成自动匹配,set方法中的setXXX与属性中的字段相同</h1>
	
	<jsp:useBean id="user" type="jspBase.bean.User" class="jspBase.bean.User" scope="page"/>
 	
 	<!-- 跟表单相关,全部匹配,关联传入的参数,完成匹配 -->
	<jsp:setProperty property="*" name="user"/>
	
	<!-- 跟表单相关,部分匹配,只匹配指定属性,关联传入的参数,完成匹配 -->
<%-- <jsp:setProperty property="username" name="user"/> --%>

	<!-- 跟表单无关,自己定义 --> 
<%--<jsp:setProperty property="username" name="user" value="Jion"/>
	<jsp:setProperty property="password" name="user" value="123456"/> --%>
	
	<!-- 通过URL传参赋值,仅限于post方式传参 -->
<!--<jsp:setProperty property="username" name="user" param="URI"/>
	<jsp:setProperty property="password" name="user" param="URI"/> -->	
<%--用户:<%=user.getUsername() %><br>
	密码:<%=user.getPassword() %><br> --%>
	
	
	<!-- 使用getProperty方式获得用户密码,必须jsp:useBean 和 jsp:setProperty,URI不能传入奇怪的参数-->
	用户:<jsp:getProperty property="username" name="user"/> <br>
	密码:<jsp:getProperty property="password" name="user"/><br>
	
</body>
</html>