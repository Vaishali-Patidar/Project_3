 <%@page import="java.util.List"%>
<%@page import="in.co.rays.project3.controller.UserCtl"%>
<%@page import="in.co.rays.project3.util.DataUtility"%>
<%@page import="in.co.rays.project3.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">



<title>Add User</title>
<!-- date picker library -->


<style type="text/css">
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);
@import url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.3/css/mdb.min.css);

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
    color: #ff3547; }
.default-text {
    color: #fff; 
}
.info-text {
    color: #33b5e5; 
}

.card{
   background-color:#28a74582!important;
  }

.form-white .md-form label {
  color: #fff; 
}
.paddingclass{
padding-top: 10px;
}

</style>
<style type="text/css">
/* .setForm{
padding-top: 5%;
padding-left: 25%;
width: 130%
}
 */
 .button{
border-radius:10px;padding:10px;color:white;font-size:20px;background-color:#00cc88
}

.textfield{
border: 1px solid #8080803b;height: 38px; padding-left: 6px;
}
</style>


</head>
<body >
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

<jsp:useBean id="dto" scope="request" class="in.co.rays.project3.dto.UserDTO" />
<form action="<%=ORSView.USER_CTL%>" method="post">
<!-- getting role list for preload -->
<%List list= (List)request.getAttribute("roleList"); %>

    
    
    <main>
        
        <!--MDB Forms-->
        <div class="container mt-3">

            
            <!-- Grid row -->
            <div class="row">
                <!-- Grid column -->
               <div class="col-md-4"></div>
               
                <div class="col-md-4">
                    <div class="card" >
                        <div class="card-body" >
                        
                        <%long id=DataUtility.getLong(request.getParameter("id")); %>
                       
                           <h3 class="text-center default-text py-3"> <u><%=(id==0)? "Add User": "Update User" %></u></h3>
                          <hr color="black">  
                          
                            <!--Body-->
                            
                            		<%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
    <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong><%=ServletUtility.getSuccessMessage(request) %>
    </div>
    <%} %>                            
	
	<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
<div class="alert alert-denger alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error!</strong><font><%=ServletUtility.getErrorMessage(request) %></font>
  </div>
<%} %>                            
		
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
                            
                           <h6 style="color: #fff ">First Name<font color="red">*</font></h6>
                           
                           <div class="md-form">
              		 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i  class="fa fa-user"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter First Name" class="form-control border" style="height: 19px ; background-color:white" name="firstName" value="<%=DataUtility.getStringData(dto.getFirstName())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("firstName",request) %> </font>
                       </div>



                           <h6 class="paddingclass" style="color: #fff " >Last Name<font color="red">*</font></h6>
                           <div class="md-form">
                           <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i  class="fa fa-user"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Last Name" class="form-control border" style="height: 19px;background-color: white " name="lastName" value="<%=DataUtility.getStringData(dto.getLastName())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("lastName",request) %> </font>                     </div>






                           <h6 class="paddingclass" style="color: #fff " >Email<font color="red">*</font></h6>

                           <div class="md-form">
                  		 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i   class="fa fa-envelope"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Email" class="form-control border" style="height: 19px ; background-color:white" name="email" value="<%=DataUtility.getStringData(dto.getLogin())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("email",request) %> </font>

  </div>
                           
                           
                   
                   
                           <h6 class="paddingclass" style="color: #fff " >Mobile Number<font color="red">*</font></h6>                           
                           <div class="md-form" >
                  		 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i   class="fa fa-mobile"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Mobile Number" class="form-control border" style="height: 19px ; background-color:white" name="mobileNumber" value="<%=DataUtility.getStringData(dto.getMobileNo())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("mobileNumber",request) %> </font>

  </div>
                           
                           
                           <h6 class="paddingclass" style="color: #fff ">Password<font color="red">*</font></h6>
                           <div class="md-form">
                  		 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i   class="fa fa-lock"></i></span>
                                 </div>
                        <input type="password" placeholder="Enter Password" class="form-control border" style="height: 19px ; background-color:white" name="password" value="<%=DataUtility.getStringData(dto.getPassword())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("password",request) %> </font>

                           </div>
                           
                           
<!--                            <h6 class="paddingclass" style="color: #fff " >Confirm Password <font color="red">*</font></h6> -->
                           
<!--                            <div class="md-form"> -->

<!--                   		 <div class="input-group"> -->
<!--                             <div class="input-group-prepend"> -->
<!--                                <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i   class="fa fa-lock"></i></span> -->
<!--                                  </div> -->
<%--                         <input type="password" placeholder="Enter Confirm Password" class="form-control border" style="height: 19px ; background-color:white" name="confirmPassword" value="<%=((id==0)? DataUtility.getStringData(dto.getPassword()):DataUtility.getStringData(dto.getPassword()))%>"> --%>
<!--                                 </div> -->
<%-- 							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("confirmPassword",request) %> </font> --%>
<!--                           </div> -->
                           
                           
                           <h6 class="paddingclass" style="color: #fff; width:100px; heihgt:20px  "   >Gender<font color="red">*</font></h6>
                           <%HashMap<String,String> map=new HashMap<String,String>();
                           
                           map.put("Male", "Male");
                           map.put("Female","Female");
                           String gender=HTMLUtility.getList("gender",dto.getGender(), map);
                           %>
                          <div class="md-form">
								<div class="input-group mb-3">
                             <div class="input-group-prepend">
                             <span class="input-group-text"><i style="width: 17px" class="fa fa-venus-mars"></i></span>
                                      </div>
                           	<%=gender%>
                                      </div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("gender", request) %></font>

 </div>



<%--                            <%String role=HTMLUtility.getList("role",String.valueOf(dto.getRoleid()),list); %> --%>
                           
<!--                            <h6 class="default-text" >Role<font color="red">*</font></h6> -->
<!--                           </div> -->
<!--                           <div class="md-form"> -->

<!-- 								<div class="input-group mb-3"> -->
<!--                              <div class="input-group-prepend"> -->
<!--                              <span class="input-group-text"><i style="width: 17px" class="fa fa-user"></i></span> -->
<!--                                       </div> -->
<%--                            	<%=role%> --%>
<!--                                       </div> -->
<%-- 								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("role", request) %></font> --%>

<!--                            </div> -->
                           
                           
                           
                           
                           
                           
                             <h6 class="paddingclass" style="color: #fff" >Role<font color="red">*</font></h6>
                          <%String role= HTMLUtility.getList("role",String.valueOf(dto.getRoleid()),list); %>
                           <div class="md-form">
                          
                         <div class="input-group mb-3">
                             <div class="input-group-prepend">
                             <span class="input-group-text"><i style="width: 17px" class="fa fa-institution"></i></span>
                                      </div>
                           	<%=role%>
                                      </div>
								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("role", request) %></font>
                          
                          
                         </div>
                           
                           
                           
                           
                           
                           
  
                          <h6 class="paddingclass" style ="color: #fff; width:100px; height:20px "> Date Of Birth<font color="red">*</font></h6>
                         <!--   <h6 class="default-text" >Date Of Birth<font color="red">*</font></h6> -->
           
                           
                           <div class="md-form">

                  		 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i   class="fa fa-calendar"></i></span>
                                 </div>
                        <input type="text" id="datepicker" readonly="readonly" placeholder="Enter dob" class="form-control border" style="height: 19px ; background-color:white" name="dob" value="<%=DataUtility.getStringData(dto.getDob())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("dob",request) %> </font>

   </div>


                                                                         
                          <%if (id>0){ %>
                           <%-- <div class="text-center"  id="defaultForm-email">
                           <input  class="btn btn-success btn-sm" style="font-size: 14px" type="submit"  name="operation" value="<%=UserCtl.OP_UPDATE%>">
                           <input  class="btn btn-danger btn-sm" style="font-size: 14px" type="submit"  name="operation" value="<%=UserCtl.OP_CANCEL%>">
                          
                            </div>
                           --%>
                          <div class="text-center">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=UserCtl.OP_UPDATE%>">
										<span class="fa fa-check-square"></span> Update
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=UserCtl.OP_CANCEL%>">
										<span class="fa fa-refresh"></span> Cancel
									</button>
							
							</div>
                          
                          <%}else{ %> 
                          <%--  <div class="text-center"  id="defaultForm-email">
                           <input  class="btn btn-success btn-sm" style="font-size: 14px" type="submit"  name="operation" value="<%=UserCtl.OP_SAVE%>">
                           <input  class="btn btn-info btn-sm" style="font-size: 14px" type="submit"  name="operation" value="<%=UserCtl.OP_RESET%>">
                          
                            </div> --%>
                            
                            <div class="text-center">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=UserCtl.OP_SAVE%>">
										<span class="fa fa-check-square"></span> Save
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=UserCtl.OP_RESET%>">
										<span class="fa fa-refresh"></span> Reset
									</button>
							
							</div>
                          
                            
                             <%} %>
                        </div>
                    </div>
                </div>
                <div class="col-md-4"></div>
               </div>
               </div>

</form>


<div>

</div>
<br><br>
</body>
<%@include file="Footer.jsp" %>
</html> 

<%-- 
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.controller.UserCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<style  >
.divider-text {
    position: relative;
    text-align: center;
    margin-top: 15px;
    margin-bottom: 15px;
}
.divider-text span {
    padding: 7px;
    font-size: 12px;
    position: relative;   
    z-index: 2;
}
.divider-text:after {
    content: "";
    position: absolute;
    width: 100%;
    border-bottom: 1px solid #ddd;
    top: 55%;
    left: 0;
    z-index: 1;
}

.btn-facebook {
    background-color: #405D9D;
    color: #fff;
}
.btn-twitter {
    background-color: #42AEEC;
    color: #fff;
}
</style>
<script>
  $( function() {
    $( "#datepicker" ).datepicker({ 
    	changeMonth :true,
		  changeYear :true,
		  yearRange :'1980:2030',
		  dateFormat:'mm/dd/yy',
		  endDate: '-18y'	 
		
    	
		 }) ;
  } );
  </script>
</head>
<body background="/Project_3/jsp/p3.png">

<% 	String uri = (String)request.getAttribute("uri");%>
<jsp:useBean id="dto" scope="request" class="in.co.rays.dto.UserDTO" />
<%@include file="Header.jsp" %>
<br>
<div class="container">

<div class="card bg-secondary text-white mx-auto" style="max-width: 400px; " >
<article class="card-body mx-auto" style="max-width: 400px;">
	
	
	<form action="<%=ORSView.USER_CTL %>" method="post">
	<!-- getting role list for preload -->
<%List list= (List)request.getAttribute("roleList"); %>
	
	
                        <%long id=DataUtility.getLong(request.getParameter("id")); %>
                       
                           <h3 class="text-center default-text py-3"><%=(id==0)? "Add User": "Update User" %></h3>
                            
                          
                            <!--Body-->
                            
                            		<%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
    <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong><%=ServletUtility.getSuccessMessage(request) %>
    </div>
    <%} %>                            
	
	<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
<div class="alert alert-danger">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Error!</strong><font><%=ServletUtility.getErrorMessage(request) %></font>
  </div>
<%} %>                            
		
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
         
         <h6 style="color: #20B2AA">First Name<font color="red">*</font></h6>
         
         <div class="input-group mb-3">                   
	<div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="firstName" class="form-control" placeholder="Enter first name" type="text" value="<%=DataUtility.getStringData(dto.getFirstName())%>">
    </div>
    <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("firstName",request) %> </font>
    </div>
     <!-- form-group// -->
    
    <h6 style="color: #20B2AA">Last Name<font color="red">*</font></h6>
        
    <div class="input-group mb-3">
    <div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-user"></i> </span>
		 </div>
        <input name="lastName" class="form-control" placeholder="Enter last name" type="text" value="<%=DataUtility.getStringData(dto.getLastName())%>" >
    </div>
    <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("lastName",request) %> </font>
</div>     <!-- form-group// -->
    
    <h6 style="color: #20B2AA">Mobile Number<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
     <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-phone"></i> </span>
		</div>
    	<input name="mobileNumber" class="form-control" placeholder="Enter mobile number" type="text"value="<%=DataUtility.getStringData(dto.getMobileNo())%>" >
    </div><font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("mobileNumber",request) %> </font> 
    </div>
    <!-- form-group// -->
    
     <h6 style="color: #20B2AA">Email<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-envelope"></i> </span>
		 </div>
        <input name="email" class="form-control" placeholder="Enter Email address" type="email" value="<%=DataUtility.getStringData(dto.getLogin())%>">
    </div> <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("email",request) %> </font>
    </div>
    <!-- form-group// -->
    
    <h6 style="color: #20B2AA">Password<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
   <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		</div>
        <input name="password" class="form-control" placeholder="Enter password" type="password" value="<%=DataUtility.getStringData(dto.getPassword())%>">
    </div> <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("password",request) %> </font>
    </div>
    <!-- form-group// -->
    
    <h6 style="color: #20B2AA">Confirm Password<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-lock"></i> </span>
		</div>
        <input name="confirmPassword" class="form-control" placeholder="Enter Confirm password" type="password" value="<%=((id==0)? DataUtility.getStringData(dto.getConfirmPassword()):DataUtility.getStringData(dto.getPassword()))%>">
    </div> <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("confirmPassword",request) %> </font>
    </div>
    <!-- form-group// --> 
    
    
    <%HashMap<String,String> map=new HashMap<String,String>();
                           map.put("male", "male");
                           map.put("female","female");
                           String gender=HTMLUtility.getList("gender",dto.getGender(), map);
                           %>
    <h6 style="color: #20B2AA">Gender<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
    <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i  class="fa fa-venus-mars"></i> </span>
		</div>
		<%=gender%>
	</div> <font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("gender", request) %></font>
	</div>
	<!-- form-group end.// -->
    
    <h6 style="color: #20B2AA">Date Of Birth<font color="red">*</font></h6>
       
       <div class="input-group mb-3">
       <div class="form-group input-group">
    	<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-calendar"></i> </span>
		 </div>
		 <%System.out.print(dto.getDob()); %>
        <input name="dob" class="form-control"  placeholder="Enter date of birth" readonly="readonly" type="text" id="datepicker" value="<%=DataUtility.getStringData(dto.getDob())%>">
    </div> 
    <font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("dob",request) %> </font>
</div>
        <!-- form-group// -->
    
    
     <%String role=HTMLUtility.getList("role",String.valueOf(dto.getRoleId()),list); %>
    
    <h6 style="color: #20B2AA">Role<font color="red">*</font></h6>
    
    <div class="input-group mb-3">
    <div class="form-group input-group">
		<div class="input-group-prepend">
		    <span class="input-group-text"> <i class="fa fa-venus"></i> </span>
		 </div>
<%=role%>
    </div> 
    <font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("role", request) %></font>
</div>
        <!-- form-group// -->
    
      <%if (id>0){ %>                                   
    <div class="form-group " align="center">
        <input type="submit" class="bg-success text-white" name="operation" value="<%=UserCtl.OP_UPDATE%>">   
                <input type="submit" class="bg-warning text-white"  name="operation" value="<%=UserCtl.OP_CANCEL%>">
     </div> <!-- form-group// -->  
   
   <%}else{ %> 
   <div class="form-group " align="center">
        <input type="submit" class="bg-success text-white" name="operation" value="<%=UserCtl.OP_SAVE%>">   
                <input type="submit" class="bg-warning text-white"  name="operation" value="<%=UserCtl.OP_RESET%>">
     </div> <!-- form-group// -->  
   <%} %>
        
</form>
</article>
</div> <!-- card.// -->

</div> 
<!--container end.//-->

<br>
<%@include file="Footer.jsp" %>
</body>
</html> --%>