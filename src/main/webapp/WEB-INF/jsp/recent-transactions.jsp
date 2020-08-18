<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>Dollars Bank ${curAccount.name}'s Recent Transactions</title>
</head>

<body>
	<%@ include file = "header.jsp"%>
	<div><a class="button" href="/main-account-page">Return To Main Page</a></div>
	
	<table border="1">
		<c:forEach items="${curAccount.transactions}" var="transaction">
			<tr>
				<td>${transaction.amountAsString}</td>
				<td>${transaction.message}</td>
				<td>${transaction.date}</td>
			</tr>
		</c:forEach>
	</table><br>
	
	<table border="1" class="center">
		<tr>
			<td>Current Balance:</td>
			<td>${balance}</td>
		</tr>
	</table>
	
	<%@ include file = "footer.jsp"%>

</body>

</html>