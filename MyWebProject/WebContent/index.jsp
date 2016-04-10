<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--   meta charset="UTF-8"-->
<title>Log&Pass</title>
</head>
<body>
<% 
	String userName = null;
	if(session.getAttribute("user") != null){
%>
<a href="LogoutServlet">Logout!</a>
<% } 
%>

	<form action="Login" method="post">
		<label>логин:</label> 
		<input name="login" type="text"	size="15" maxlength="15" autofocus required><br /> 
		<label>пароль:</label> 
		<input name="pass" type="password"	size="15" maxlength="15" required><br /> 
		<input type="submit" value="войти"><br />
		<br />
	</form>
	
	
</body>
</html>