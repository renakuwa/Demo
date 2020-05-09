<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>title</title>
	  <link rel="stylesheet" href="resources/css/style.css">
</head>
<body>
	<form action="LoginServlet" method="post">
		<input type="text" name="user" placeholder="username">
		<input type="password" name="password" placeholder="password">
		
		<% String error = (String)request.getAttribute("errorMsg");
		//届くデータがある＝エラーの時
		if(error != null){ %>
		<p style="color:rgb(172, 24, 24); text-align:center; "><%=error%></p>
		<%} %>
		
		<button type="submit">Login</button>
	</form>
</body>
</html>