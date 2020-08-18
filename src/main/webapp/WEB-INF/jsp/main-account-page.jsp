<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>Dollars Bank ${curAccount.name}'s Page</title>
</head>

<body>
	<%@ include file = "header.jsp"%>
	
	<table border="1">
		<thead>
			<tr>
				<th>Options</th>
			</tr>
		</thead>
		<tr>
			<td><a class="button" href="/deposit">Deposit</a></td>
		</tr>
		<tr>
			<td><a class="button" href="/withdraw">Withdraw</a></td>
		</tr>
		<tr>
			<td><a class="button" href="/transfer">Transfer</a></td>
		</tr>
		<tr>
			<td><a class="button" href="/recent-transactions">View Recent Transactions</a></td>
		</tr>
		<tr>
			<td><a class="button" href="/account-details">Account Details</a></td>
		</tr>
	</table>
	
	<%@ include file = "footer.jsp"%>

</body>

</html>