<%@page import="in.co.rays.project3.controller.LoginCtl"%>
<%@page import="in.co.rays.project3.util.DataUtility"%>
<%@page import="in.co.rays.project3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

<!-- font-awesome library -->
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
   background-color:#4caf50a1!important;
   border-style: solid;
  border-width: 50px;
}

.paddingclass {
	padding-top: 10px;
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

<% 	String URI = (String)request.getAttribute("uri");%>
	<jsp:useBean id="dto" scope="request"
		class="in.co.rays.project3.dto.UserDTO" />
	<div>
		
		<%@include file="Header.jsp" %>
		
	</div>
	<form action="<%=ORSView.LOGIN_CTL%>" method="post">



		<main> <!--MDB Forms-->
		<div class="container mt-4 ">


			<!-- Grid row -->
			<div class="row">

				<div class="col-md-4"></div>

				<!-- Grid column -->
				<div class="col-md-4">
					<div class="card">
						<div class="card-body">

							<h3 class="text-center default-text py-3"><span class='fa fa-sign-in-alt'></span><b><u> Login</u></b></h3>
							<!--Body-->
							<hr color="white">
							<%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
							<div class=		"alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Success!</strong><%=ServletUtility.getSuccessMessage(request) %>
							</div>
							<%} %>
							<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
							<div class="alert alert-success alert-dismissible fade show">
								<button type="button" class="close" data-dismiss="alert">&times;</button>
								<strong>Error!</strong><font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
							</div>
							<%} %>

						 	 <h6 style="color: #fff">
								<b>Username:</b><font color="red">*</font>
							</h6>
   
							


     					 <div class="input-group" >
                            <div class="input-group-prepend ">
                               <span class="input-group-text " style="background-color:white-space; height: 38px; width: 45px;"><i  class="fa fa-envelope"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Email" class="form-control border" style="height: 15px ; background-color:white" name="login" value="<%=DataUtility.getStringData(dto.getLogin())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("login",request) %> </font>

						
							<h6 class="paddingclass" style="color: #fff">
								<b>Password:</b><font color="red">*</font>
							</h6>


                             <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i  class="fa fa-lock"></i></span>
                                 </div>
                        <input type="password" placeholder="Enter Password" class="form-control border" style="height: 15px ; background-color:white" name="password" value="<%=DataUtility.getStringData(dto.getPassword())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("password",request) %> </font>


<br>
 
 <%-- 
   <h6 ><b>Username:</b><font color="red">*</font></h6>           
         <div class="input-group mb-3">                   
	<div class="form-group input-group">
		<div class="input-group-prepend">
		
		    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
		 </div>
        <input name="login" class="form-control" placeholder="Enter Username" type="text" value="<%=DataUtility.getStringData(dto.getLogin())%>">
    </div>
    <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("login",request) %> </font>
    </div>
     <!-- form-group// --><br>
    
    
     <h6 ><b>Password:</b><font color="red">*</font></h6>           
         <div class="input-group mb-3">                   
	<div class="form-group input-group">
		<div class="input-group-prepend">
		
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		 </div>
        <input name="password" class="form-control" placeholder="Enter Password" type="password" value="<%=DataUtility.getStringData(dto.getLogin())%>">
    </div>
    <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("password",request) %> </font>
    </div>
     <!-- form-group// --><br>
 --%>    



							<div class="text-center">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=LoginCtl.OP_SIGN_IN%>">
										<span class="fa fa-check-square"></span> Signin
									</button>
									 
									
							 <button type="submit" class="btn btn-info" name="operation"
									style="font-size: 13px"	value="<%=LoginCtl.OP_SIGN_UP%>">
										<span class="fa fa-user"></span> Signup
									</button>
							
							</div>
							<div class="text-center" style="color: #20B2AA">
								<br>
								 <font size="4px"> <a
										href="<%=ORSView.FORGET_PASSWORD_CTL%>"><u> Forget password ?</u></a></font></br>
							</div>







						</div>
					</div>
				</div>
				
				
			
             <input type="hidden" name="uri" value="<%=URI%>">
          
				<div class="col-md-3"></div>
			</div>
		</div>
		
	</form>
	<br>
<br>
<br>
<br>
<br>
<br>
</body>

<%@include file="Footer.jsp" %>


</html>

