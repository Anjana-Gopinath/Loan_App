<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  
 
  <h2>Simple Interest</h2>
       <form action="si" method ="POST">
       
          <div>
             <label> Loan Amount </label>
             <input type="number" name="LoanAmount" required/>
          </div>
          <div>
             <label>Interest Rate %(0 to 1)</label>
             <input type="decimal" name="rate" required min=0 max=1 />
             <table>
         <tr><td> Loan Amount </td><td><strong>${loan.loanAmount}</strong></td></tr>
         <tr><td> Rate of Interest % </td><td><strong>${loan.rate }</strong></td></tr>
         <tr><td> Disbursement Date </td><td><strong>${loan.disbursmentDate }</strong></td></tr>
         <tr><td> Interest </td><td><strong>${loan.interest }</strong></td></tr>
         
       </table>
         </div>
        
         <div>
            <button>SUBMIT</button>
         </div>
       </form>

</body>
</html>