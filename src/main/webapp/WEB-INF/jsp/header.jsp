<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Header</title>
</head>
<body>
	<div>
		<h3>Welcome to Dollars Bank!</h3><br>
		<a class="button" href = "/logout">Logout</a><br>
		
		<font color="red">${errorMessage}</font>
		<font color="green">${successMessage}</font>
	</div>
</body>
</html>