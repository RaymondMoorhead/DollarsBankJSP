<html>

<head>
<title>Dollars Bank Login</title>
</head>

<body>
	<font color="red">${errorMessage}</font>
	
	<form method = "post">
		<table border="1">
  			<tr>
    			<td>Username:</td>
   				<td>
      				<input type = "text" name = "username" maxlength="${maxUsernameLength}" size = "${highestMaxLength}">
    			</td>
  			</tr>
 			 <tr>
    			<td>Password:</td>
    			<td>
      				<input type = "password" name = "password" maxlength="${maxPasswordLength}" size = "${highestMaxLength}">
   				</td>
 			 </tr>
	</table>
	<p><input type = "submit" value = "submit" name = "b1"></p>
	</form>
	
	<a href="register">Don't have an account? Click Here!</a>
</body>

</html>