<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page import="com.iiht.evaluation.eloan.model.ApprovedLoan" %>
<%@ page import="com.iiht.evaluation.eloan.model.LoanDetails" %>
<%@ page import="com.iiht.evaluation.eloan.model.LoanInfo" %>
<%@ page import="com.iiht.evaluation.eloan.dao.ConnectionDao" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Details</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<hr/>

<font color="blue">Welcome <b>Loan</b> </font>You are in Loan List page
<% 

ConnectionDao connDao = (ConnectionDao) request.getAttribute("connDao");
LoanDetails loan = new LoanDetails();
LoanInfo aloan = new LoanInfo();
List<ApprovedLoan> loand=loan.getApprovedLoan();


%>
			<table border="1" cellspacing="5px" cellpadding="5px">
				<tr>
					<th>Application Id</th>
					<th>Purpose</th>
					<th>Amount Requested</th>
					<th>Date Of Application</th>	
					<th>Business Structure</th>
					<th>Business Indicator</th>
					<th>Address</th>		
					<th>Email</th>
					<th>Mobile</th>
					<th>Amount Sanctioned</th>
					<th>Loan Term</th>
					<th>EMI</th>
					<th>PSD</th>
					<th>LCD</th>
					
				</tr>
				<% for(ApprovedLoan loani: loand) { 		
				%>
					<tr>
						<td><%=aloan.getApplno() %></td>
						<td><%=aloan.getPurpose() %></td>
						<td><%=aloan.getAmtrequest() %></td>
						<td><%=aloan.getDoa() %></td>
						<td><%=aloan.getBstructure() %></td>
						<td><%=aloan.getBindicator() %></td>
						<td><%=aloan.getAddress() %></td>
						<td><%=aloan.getEmail() %></td>
						<td><%=aloan.getMobile() %></td>
						<td><%=aloan.getStatus() %></td>
						
					</tr> 
				<% 	} %>
			</table>
	<a href="admin?action=list">Track loan</a>
	<form action="admin?action=logout" method="post">
		<input type="submit" value="Logout" >
	</form>

<hr/>	
	<jsp:include page="footer.jsp"/>
</body>
</html>

</body>
</html>