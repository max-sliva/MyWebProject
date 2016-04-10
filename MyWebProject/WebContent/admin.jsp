<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin Page</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="bootstrap.css">

</head>
<body>
	Hello, Admin!
<%
	String userName = null;
	//allow access only if session exists
	if(session.getAttribute("user") == null){
	    response.sendRedirect("index.jsp");
	} else {
		userName = (String) session.getAttribute("user");
		if (!userName.equals("Admin")) response.sendRedirect("index.jsp");
	}	
%>  
	<p>Аккаунты!!!</p>
	<jsp:include page="/UsersListShow" />
	<table	border="1" cellpadding="5" align="center">
            <caption>List of users!</caption>
            <tr>
                <th>Login</th>
                <th>Password</th>
            </tr>
		<c:forEach items="${accounts}" var="account">
	        <tr>
	            <td><c:out value="${account.login}" /></td>
	            <td><c:out value="${account.pass}" /></td>
	        </tr>
    	</c:forEach>
	    <tr>
			<td>
			</td>
			<td>
			<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal">Добавить пользователя</button>
			</td>
        </tr>    
	</table>
	
		<div id="myModal" class="modal fade" role="dialog" charset="UTF-8">
		  <div class="modal-dialog">
		
		    <!-- Modal content-->
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal">&times;</button>
		        <h4 class="modal-title">Новый пользователь</h4>
		      </div>
		      <form action="AddUser" method="post" accept-charset="UTF-8">
			      <div class="modal-body">
			        <p>Введите данные</p>
					<label>ФИО:</label><input name="FIO" required placeholder="Иванов Иван Иванович" type="text" size="25" maxlength="25" 
						accept-charset="UTF-8" autofocus><br />
					<label>Группа:</label> 
					<% //TODO сделать возможность ввода новой группы %>
						<select name="group">
						<c:forEach items="${groupList}" var="group">
							<option value=<c:out value="${group.groupName}" />><c:out value="${group.groupName}" /></option>
		    			</c:forEach> 
					 	</select> <br /> 	
					<label>e-mail:</label> <input name="email" required pattern="\S+@[a-z]+.[a-z]+" type="email" size="15" 
						maxlength="25"><br />
			        <label>Логин:</label> <input id="login" name="login" pattern="[A-Za-z].+" placeholder="Начинается с буквы" type="text" size="15" maxlength="15" required>
						<script>
							$('#login').blur(function() {
							  var text = $(this).val();
							  var obj = "${logins}";
							  var index;
							  //for (index = 0; index < obj.length; ++index) {
							   console.log(obj);
							   var n = obj.indexOf(text);
							   if (n==-1) {console.log("No")}
							   else {
								   console.log("Yes");
								   alert("Такой логин уже есть!!");
								   document.getElementById("login").focus();
							   }
							//	}							  
							})
						</script>
						<% //out.print("Hello");  %>
			        <br />
					<label>пароль:</label> <input name="password" type="password" size="15" maxlength="15" required><br />
			      </div>
			      <div class="modal-footer">
			        <input type="submit" class="btn btn-default" value="Сохранить" > <!-- data-dismiss="modal" onclick="javascript: submit()"> -->
			        <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
			      </div>
		      </form>
		    </div>
		
		  </div>
		</div>
	
</body>
</html>