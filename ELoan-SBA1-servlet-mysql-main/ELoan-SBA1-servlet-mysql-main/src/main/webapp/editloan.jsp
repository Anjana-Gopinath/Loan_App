<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Loan Application</title>
</head>
<body>
	<h3>Welcome User!</h3>
<hr />
<h4>New User Registration</h4>
	<form action="user?action=placeloan" method="post">
		<table>
			<tr>
				<div>
					<td><label>Application ID</label></td>
					<td><input type="text" name="applno" required}/></td>
				</div>	
			</tr>
			<tr>
				<div>
					<td><label>Amount Requested</label></td>
					<td><input type="number" name="amtrequested" required /></td>
				</div>	
			</tr>
			<tr>
				<div>
					<td><label>Purpose Of Loan</label></td>
					<td><input type="text" name="purpose" required /></td>
				</div>	
			</tr>
			<tr>
				<div>
					<td><label>Date of Application</label></td>
					<td><input type="text" name="dao" required /></td>
				</div>	
			</tr>	
			<tr>
				<div>
					<td><label>Business Structure</label></td>
					<td><input type="text" name="bstructure" required /></td>
				</div>	
			</tr>	
			<tr>
				<div>
					<td><label>Business Indicator</label></td>
					<td><input type="text" name="bindicator" required /></td>
				</div>	
			</tr>	
			<tr>
				<div>
					<td><label>Address</label></td>
					<td><input type="text" name="address" required /></td>
				</div>	
			</tr>	
			<tr>
				<div>
					<td><label>Email</label></td>
					<td><input type="text" name="email" required /></td>
				</div>	
			</tr>	
			<tr>
				<div>
					<td><label>Mobile</label></td>
					<td><input type="text" name="mobile" required /></td>
				</div>	
			</tr>	
			<tr>
				<div>
					<td><label>Status</label></td>
					<td><input type="text" name="status" required /></td>
				</div>	
			</tr>	
			
		</table>
		<button>Update Details</button>		
	</form>

<hr/>	

</body>
</html>