<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan system</title>
</head>
<body>
	<!-- write the html code to read user credentials and send it to validateservlet
	    to validate and user servlet's registernewuser method if create new user
	    account is selected
	-->
	  <jsp:include page="header.jsp"/>
<h2>Admin Login</h2>
	<form action="user?action=validate" method="post">
		<% if("failed".equals(request.getParameter("action"))) { %>
		<font color=red>Either user name or password is wrong.</font>
		<% } else if("logout".equals(request.getParameter("action"))) { %>
		<font color=green>You have successfully logged out!</font>
		<% } else {%>
		<font color=blue>Welcome!</font>
		<% } %>
		<div>
			<div><label for="loginid">Enter login Id</label> </div>
			<div><input type="text" id="loginid" name="loginid"> </div>
		</div>
		<div>
			<div><label for="password">Enter password</label> </div>
			<div><input type="text" id="password" name="password"> </div>
		</div>
		<div>
			<div><input type="submit" value="Login"> </div>
		</div>
	</form>
</div>
<hr/>
<div>
	<a href="user?action=register"><button>New User Regisration</button></a>
</div>
<hr/>	
	<jsp:include page="footer.jsp"/>
       
       
      
 


	
</body>
</html>