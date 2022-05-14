
$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 
// SAVE ============================================
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateEmployeeForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hididemployeeSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "EmployeeAPI", 
 type : type, 
 data : $("#formEmp").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onEmployeeSaveComplete(response.responseText, status); 
 } 
 }); 
});

// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hididemployeeSave").val($(this).data("idemployee")); 
 $("#employeeNumber").val($(this).closest("tr").find('td:eq(0)').text()); 
 $("#employeeName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#employeeArea").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#employeePnumber").val($(this).closest("tr").find('td:eq(3)').text());
 $("#employeeMail").val($(this).closest("tr").find('td:eq(4)').text()); 
});

$(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "EmployeeAPI", 
 type : "DELETE", 
 data : "idemployee=" + $(this).data("employeeid"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onEmployeeDeleteComplete(response.responseText, status); 
 } 
 }); 
});
// CLIENT-MODEL================================================================
function validateEmployeeForm() 
{ 
// Number
if ($("#employeeNumber").val().trim() == "") 
 { 
 return "Insert Employee Number."; 
 } 
 // is numerical value
var empNumber = $("#employeeNumber").val().trim(); 
if (!$.isNumeric(empNumber)) 
 { 
 return "Insert a numerical value for employee number."; 
 } 
// convert to decimal price
 $("#employeeNumber").val(parseFloat(empNumber).toFixed(2)); 
// DESCRIPTION------------------------

// NAME
if ($("#employeeName").val().trim() == "") 
 { 
 return "Insert Employee Name."; 
 } 
// Area-------------------------------
if ($("#employeeArea").val().trim() == "") 
 { 
 return "Insert Area."; 
 } 
if ($("#employeePnumber").val().trim() == "") 
 { 
 return "Insert employee Phone number."; 
 }
 if ($("#employeeMail").val().trim() == "") 
 { 
 return "Insert employee mail."; 
 } 
return true; 
}

function onEmployeeSaveComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully saved."); 
 $("#alertSuccess").show(); 
 $("#divEmployeeGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while saving."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while saving.."); 
 $("#alertError").show(); 
 } 
 $("#hidEmployeeIDSave").val(""); 
 $("#formItem")[0].reset(); 
}


function onEmployeeDeleteComplete(response, status) 
{ 
if (status == "success") 
 { 
 var resultSet = JSON.parse(response); 
 if (resultSet.status.trim() == "success") 
 { 
 $("#alertSuccess").text("Successfully deleted."); 
 $("#alertSuccess").show(); 
 $("#divEmployeeGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error") 
 { 
 $("#alertError").text(resultSet.data); 
 $("#alertError").show(); 
 } 
 } else if (status == "error") 
 { 
 $("#alertError").text("Error while deleting."); 
 $("#alertError").show(); 
 } else
 { 
 $("#alertError").text("Unknown error while deleting.."); 
 $("#alertError").show(); 
 } 
}




