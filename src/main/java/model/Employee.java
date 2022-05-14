package model; 
import java.sql.*; 


public class Employee 
{ 
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 con = 
 DriverManager.getConnection( 
 "jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "mysql"); 
 } 
 catch (Exception e) 
 { 
 e.printStackTrace(); 
 } 
 return con; 
 } 





public String readEmployee() 
{ 
 String output = ""; 
try
 { 
 Connection con = connect(); 
 if (con == null) 
 { 
 return "Error while connecting to the database for reading."; 
 } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Employee ID</th><th>Employee Name</th>" + "<th>Employee area</th>"
			+ "<th>Employee Phone Number</th>" + "<th>Employee email </th></tr>";
 String query = "select * from employee"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
	 String employeeNumber = Integer.toString(rs.getInt("employeeNumber"));
		String employeeName = rs.getString("employeeName");
		String employeeArea = rs.getString("employeeArea");
		String employeePnumber = rs.getString("employeePnumber");
		String employeeMail = rs.getString("employeeMail");
		// Add into the html table
		output += "<tr><td>" + employeeNumber + "</td>";
		output += "<td>" + employeeName + "</td>";
		output += "<td>" + employeeArea + "</td>";
		output += "<td>" + employeePnumber + "</td>";
		output += "<td>" + employeeMail + "</td></tr>"; 
 } 
 con.close(); 
 // Complete the html table
 output += "</table>"; 
 } 
catch (Exception e) 
 { 
 output = "Error while reading the employee."; 
 System.err.println(e.getMessage()); 
 } 
return output; 
}






public String insertEmployee(String employeeNumber, String employeeName, String employeeArea,
		String employeePnumber, String employeeMail) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for inserting."; 
		 } 
		 // create a prepared statement
		 String query = " insert into employee (`employeeNumber`,`employeeName`,`employeeArea`,`employeePnumber`,`employeeMail`) values (?, ?, ?, ?, ?)"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, 0);
		 preparedStmt.setString(1, employeeNumber);
		 preparedStmt.setString(2, employeeName);
		 preparedStmt.setString(3, employeeArea);
		 preparedStmt.setString(4, employeePnumber);
		 preparedStmt.setString(5, employeeMail); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newEmployee = readEmployee(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newEmployee + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the employee.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		





public String updateEmployee(String idemployee, String employeeNumber, String employeeName, String employeeArea,
		String employeePnumber, String employeeMail) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for updating."; 
		 } 
		 // create a prepared statement
		 String query = "UPDATE employee SET employeeNumber=?,employeeName=?,employeeArea=?,employeePnumber=?,employeeMail=? WHERE idemployee=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, employeeNumber);
			preparedStmt.setString(2, employeeName);
			preparedStmt.setString(3, employeeArea);
			preparedStmt.setString(4, employeePnumber);
			preparedStmt.setString(5, employeeMail);
			preparedStmt.setInt(6, Integer.parseInt(idemployee)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newEmployee = readEmployee(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newEmployee + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the employee.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		
		
		
		
		
		public String deleteEmployee(String idemployee) 
		 { 
		 String output = ""; 
		 try
		 { 
		 Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for deleting."; 
		 } 
		 // create a prepared statement
		 String query = "delete from employee where idemployee=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(idemployee)); 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newEmployee = readEmployee(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
		 newEmployee + "\"}"; 
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the employee.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
		}


