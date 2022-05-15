<%@page import="com.Complaint"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Complaint service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/complaint.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Complaint service</h1>

				<form id="formProduct" name="formProduct" method="post" action="Product.jsp">


					
						
						<br>Inquiry type: <input id="i_type" name="i_type" type="text"
						class="form-control form-control-sm"> 
						
						<br> Message: <input id="message" name="message" type="text"
						class="form-control form-control-sm"> 
						
							<br> Status: <input id="status" name="status" type="text"
						class="form-control form-control-sm"> 
						<br> <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary">
						 <input type="hidden"
						id="hidProjectIDSave" name="hidProjectIDSave" value="">
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divProjectGrid">
					<%
					    Complaint projectObj = new Complaint();
						out.print(projectObj.readcomplaint());
						
					%> 
				</div>
			</div>
		</div>
	</div>
</body>
</html>