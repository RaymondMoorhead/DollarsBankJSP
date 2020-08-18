<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>Dollars Bank Transfer From ${curAccount.name}</title>
</head>

<body>
	<%@ include file = "header.jsp"%>
	<div><a class="button" href="/main-account-page">Return To Main Page</a></div>

	<form method = "post">
		<table border="1">
  			<tr>
    			<td>To:</td>
   				<td>
      				<input type = "text" name = "username" size = "30" required>
    			</td>
  			</tr>
  			<tr>
    			<td>Amount:</td>
   				<td>
      				<input type = "number" name = "balance" size = "30" step='0.01' value='0.00' placeholder='0.00' required>
    			</td>
  			</tr>
 			 <tr>
    			<td>Memo (Optional):</td>
    			<td>
      				<input type = "text" name = "memo" size = "30">
   				</td>
 			 </tr>
	</table>
	<p><input type = "submit" value = "submit" name = "b1"></p>
	</form>
	
	<%@ include file = "footer.jsp"%>
</body>

</html>