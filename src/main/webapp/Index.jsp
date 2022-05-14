<%@page import="model.Employee"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
if (request.getParameter("employeeNumber") != null) 
{ 
 Employee empObj = new Employee(); 
 String stsMsg = ""; 
//Insert--------------------------
if (request.getParameter("hididemployeesave") == "") 
 { 
 stsMsg = empObj.insertEmployee(request.getParameter("employeeNumber"), 
 request.getParameter("employeeName"), 
 request.getParameter(" employeeArea"), 
 request.getParameter("employeePnumber"),
 request.getParameter(" employeeMail")); 
 } 
else//Update----------------------
 { 
 stsMsg = empObj.updateEmployee(request.getParameter("hididemployeesave"), 
		 request.getParameter("employeeNumber"), 
		 request.getParameter("employeeName"), 
		 request.getParameter(" employeeArea"), 
		 request.getParameter("employeePnumber"),
		 request.getParameter(" employeeMail")); 
 } 
 session.setAttribute("statusMsg", stsMsg); 
} 
//Delete-----------------------------
if (request.getParameter("hididemployeeDelete") != null) 
{ 
 Employee empObj = new Employee(); 
 String stsMsg = 
 empObj.deleteEmployee(request.getParameter("hididemployeeDelete")); 
 session.setAttribute("statusMsg", stsMsg); 
}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Employee.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Employee Management </h1>
<form id="formEmp" name="formEmp">
 Employee Number: 
 <input id="employeeNumber" name="employeeNumber" type="text" 
 class="form-control form-control-sm">
 <br> Employee Name: 
 <input id="employeeName" name="employeeName" type="text" 
 class="form-control form-control-sm">
 <br>EmployeeArea: 
 <input id="employeeArea" name="employeeArea" type="text" 
 class="form-control form-control-sm">
 <br> Employee Phone Number: 
 <input id="employeePnumber" name="employeePnumber" type="text" 
 class="form-control form-control-sm">
 <br> employeeMail: 
 <input id="employeeMail" name="employeeMail" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hididemployeeSave" 
 name="hididemployeeSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divEmployeeGrid">
 <%
 Employee empObj = new Employee(); 
 out.print(empObj.readEmployee()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
