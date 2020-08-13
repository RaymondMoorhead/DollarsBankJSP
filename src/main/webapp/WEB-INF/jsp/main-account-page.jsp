<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<title>Dollars Bank ${curAccount.name}'s Page</title>
</head>

<body>
	<%@ include file = "header.jsp"%>
	
	<div><a class="button" href="/account-details">Account Details</a></div>
	<table border="1">
		<thead>
			<tr>
				<th>Options</th>
			</tr>
		</thead>
		<tr>
			<td>Deposit</td>
		</tr>
		<tr>
			<td>Withdraw</td>
		</tr>
		<tr>
			<td>Transfer</td>
		</tr>
		<tr>
			<td>View Recent Transactions</td>
		</tr>
	</table>
	
	<%@ include file = "footer.jsp"%>

</body>

</html>