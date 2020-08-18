<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>

<head>
<link rel="stylesheet" type="text/css" href="formats.css">
<title>Dollars Bank ${curAccount.name}'s Details</title>
</head>
<body>
	<%@ include file = "header.jsp"%>
	<div><a class="button" href="/main-account-page">Return To Main Page</a></div>
	
	<table border="1">
		<tr>
			<td>UserId</td>
			<td>${curAccount.username}</td>
		</tr>
		<tr>
			<td>Name</td>
			<td>${curAccount.name}</td>
		</tr>
		<tr>
			<td>Address</td>
			<td>${curAccount.address}</td>
		</tr>
		<tr>
			<td>Contact Number</td>
			<td>${curAccount.contactNumber}</td>
		</tr>
	</table>
	
	<table border="1">
		<tr>
			<td><a class="button" href="/delete-account" style="color: red">Delete Account</a></td>
		</tr>
	</table>
	
	<%@ include file = "footer.jsp"%>

</body>

</html>