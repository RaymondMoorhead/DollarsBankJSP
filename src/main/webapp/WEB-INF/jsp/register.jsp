<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>Dollars Bank Registration</title>
</head>

<body>
	<%@ include file = "header-external.jsp"%>
	
	<form method = "post">
		<table border="1">
  			<tr>
    			<td>Username:</td>
   				<td>
      				<input type = "text" name = "username" maxlength="${maxUsernameLength}" size = "${highestMaxLength}" required>
    			</td>
  			</tr>
 			 <tr>
    			<td>Password:</td>
    			<td>
      				<input type = "password" name = "password" maxlength="${maxPasswordLength}" size = "${highestMaxLength}" required>
   				</td>
 			 </tr>
 			 <tr>
 				<td>Name:</td>
    			<td>
      				<input type = "text" name = "name" maxlength="${maxNameLength}" size = "${highestMaxLength}" required>
   				</td>
 			 </tr>
 			 <tr>
 				<td>Address:</td>
    			<td>
      				<input type = "text" name = "address" maxlength="${maxAddressLength}" size = "${highestMaxLength}" required>
   				</td>
 			 </tr>
 			 <tr>
 				<td>Contact Number:</td>
    			<td>
      				<input type = "tel" name = "contactNumber" placeholder="(012)-345-6789" required>
   				</td>
 			 </tr>
 			 <tr>
 				<td>Initial Deposit:</td>
    			<td>
      				<input type = "number" name = "balance" step='0.01' value='0.00' placeholder='0.00' maxlength="${maxAddressLength}" size = "${highestMaxLength}" required>
   				</td>
 			 </tr>
	</table>
	<p><input type = "submit" value = "submit" name = "b1"></p>
	</form>
	
	<a href="login">Already have an account? Click Here!</a>
	
	<%@ include file = "footer.jsp"%>
</body>

</html>