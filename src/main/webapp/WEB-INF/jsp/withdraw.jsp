<html>

<head>
<title>Dollars Bank Local Withdrawal For ${curAccount.name}</title>
</head>

<body>
	<%@ include file = "header.jsp"%>
	<div><a class="button" href="/main-account-page">Return To Main Page</a></div>

	<form method = "post">
		<table border="1">
  			<tr>
    			<td>Amount:</td>
   				<td>
      				<input type = "number" name = "balance"size = "30" required>
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