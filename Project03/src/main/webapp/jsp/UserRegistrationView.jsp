<%@page import="in.co.rays.project3.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project3.controller.UserRegistrationCtl"%>
<%@page import="in.co.rays.project3.util.DataUtility"%>
<%@page import="in.co.rays.project3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Registration</title>
<!-- date picker library -->


<style type="text/css">
@import
	url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css)
	;

@import
	url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.3/css/mdb.min.css)
	;

 body {
	background-image:url("/Project03/img/pngtree.jpg");
	background-size: cover;
	background-repeat: no-repeat;
	background-attachment: fixed;
	background-position: center;
}
.darken-grey-text {
	color: #2E2E2E;
}

.danger-text {
	color: #ff3547;
}

.default-text {
	color: #fff;
}

.info-text {
	color: #33b5e5;
}

.card{
   background-color:#28a74582!important;
  }
.paddingclass {
	padding-top: 10px;
}

#textfield {
	border: 2px solid #8080803b;
}
</style>
<style type="text/css">
.setForm {
	padding-top: 5%;
	padding-left: 25%;
	width: 130%
}

.button {
	border-radius: 10px;
	padding: 10px;
	color: white;
	font-size: 20px;
	background-color: #00cc88
}

.textfield {
	border: 1px solid #8080803b;
	height: 38px;
	padding-left: 6px;
}
</style>

</head>
<body>
	<jsp:useBean id="dto" scope="request" class="in.co.rays.project3.dto.UserDTO" />

	<div>
		<%@include file="Header.jsp"%>
	</div>



<script>
$( function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true,
	  yearRange:'1980:2030',
	  dateFormat:'dd-mm-yy'
    });
  } );

  </script>


	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">



		<main> <!--MDB Forms-->
		<div class="container mt-3">
			<span class="rounded"></span>

			<!-- Grid row -->
			<div class="row">

				<div class="col-md-4"></div>
				<!-- Grid column -->
				<div class="col-md-4">
					<div class="card ">
						<div class="card-body">
							<h3 class="text-center default-text py-1"><u>User Registration</u>
							</h3>
							<hr color="black">
							<!--Body-->
							<%
		
		long id=DataUtility.getLong(request.getParameter("id"));
		
		if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Success!</strong><%=ServletUtility.getSuccessMessage(request) %>
							</div>
							<%} %>

							<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Error!</strong><font color="red"> <%=ServletUtility.getErrorMessage(request) %></font>
							</div>
							<%} %>

							<input type="hidden" name="id" value="<%=dto.getId()%>">
							<input type="hidden" name="createdBy"
								value="<%=dto.getCreatedBy()%>"> <input type="hidden"
								name="modifiedBy" value="<%=dto.getModifiedBy()%>"> <input
								type="hidden" name="createdDatetime"
								value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
							<input type="hidden" name="modifiedDatetime"
								value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">







							<h6 style="color: #fff">
								First Name<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i 
											class="fa fa-user"></i></span>
									</div>
									<input type="text" name="firstName"
										value="<%=DataUtility.getStringData(dto.getFirstName())%>"
										class="form-control border" style="height: 19px ; background-color:white" placeholder="Enter First Name">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("firstName", request) %></font>
							</div>







							<h6 style="color: #fff" class="paddingclass">
								Last Name<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;" ><i style="width: 17px"
											class="fa fa-user"></i></span>
									</div>
									<input type="text" name="lastName"
										value="<%=DataUtility.getStringData(dto.getLastName())%>"
										class="form-control border" style="height: 19px ; background-color:white" placeholder="Enter Last Name">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("lastName", request) %></font>
							</div>




							<h6 style="color: #fff" class="paddingclass">
								Email<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i 
											class="fa fa-envelope"></i></span>
									</div>
									<input type="text" name="email"
										value="<%=DataUtility.getStringData(dto.getLogin())%>"
										class="form-control border" style="height: 19px ; background-color:white" placeholder="Enter Email">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("email", request) %></font>
							</div>




							<h6 style="color: #fff" class="paddingclass">
								Mobile Number<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i
											class="fa fa-mobile"></i></span>
									</div>
									<input type="text" maxlength="10" name="mobileNumber"
										value="<%=DataUtility.getStringData(dto.getMobileNo())%>"
										class="form-control border" style="height: 19px ; background-color:white" placeholder="Enter Mobile Number">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("mobileNumber", request) %></font>
							</div>


							<h6 style="color: #fff" class="paddingclass">
								Password<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i
											class="fa fa-lock"></i></span>
									</div>
									<input type="password" name="password"
										value="<%=DataUtility.getStringData(dto.getPassword())%>"
										class="form-control border" style="height: 19px ; background-color:white" placeholder="Enter Password">
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("password", request) %></font>
							</div>


							<%HashMap<String,String> map=new HashMap<String,String>();
                           map.put("male", "male");
                           map.put("female","female");
                           String gender=HTMLUtility.getList("gender",dto.getGender(), map);
                           %>

							<h6 style="color: #fff" class="paddingclass">
								Gender<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" ><i 
											class="fa fa-venus-mars"></i></span>
									</div>
									<%=gender%>
								</div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("gender", request) %></font>
							</div>




							<h6 style="color: #fff" class="paddingclass">
								Date of Birth<font color="red">*</font>
							</h6>
							<div class="md-form">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i
											class="fa fa-calendar"></i></span>
									</div>
									<input type="text" id="datepicker" readonly="readonly"
										name="dob"
										value="<%=DataUtility.getDateString(dto.getDob())%>"
										class="form-control border" style="height: 19px ; background-color:white" placeholder="Enter dob">


								</div>
								<span> <font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("dob", request) %></font></span>
							</div>





							<%-- <div class="text-center paddingclass" id="defaultForm-email">
								<input class="btn btn-success btn-sm" style="font-size: 13px"
									type="submit" name="operation"
									value="<%=UserRegistrationCtl.OP_SIGN_UP%>"> <input
									class="btn btn-warning btn-sm" style="font-size: 13px"
									type="submit" name="operation"
									value="<%=UserRegistrationCtl.OP_RESET%>">

							</div> --%>
							
								<%-- <div class="form-group" align="center">
								<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
									<br>
									<button type="submit" class="btn btn-success" name="operation"
										value="<%=UserRegistrationCtl.OP_SIGN_UP%>">
										<span class="fa fa-check-square"></span> Save
									</button>
									&emsp;
									<button type="submit" class="btn btn-warning" name="operation"
										value="<%=UserRegistrationCtl.OP_RESET%>">
										<span class="fa fa-refresh"></span> Reset
									</button>


								</div>
							</div>
 --%>							
							
								<div class="text-center">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=LoginCtl.OP_SIGN_UP%>">
										<span class="fa fa-check-square"></span> Save
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=LoginCtl.OP_RESET%>">
										<span class="fa fa-refresh"></span> Reset
									</button>
							
							</div>
							
							
						</div>
					</div>
				</div>

				<div class="col-md-3"></div>
			</div>
		</div>
		<br>
		<br>
	</form>

	<%@include file="Footer.jsp"%>


</body>
</html>