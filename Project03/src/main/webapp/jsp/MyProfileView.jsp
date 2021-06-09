<%@page import="in.co.rays.project3.controller.MyProfileCtl"%>
<%@page import="in.co.rays.project3.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project3.util.DataUtility"%>
<%@page import="in.co.rays.project3.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>My Profile</title>
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

.card{
   background-color:#28a74582!important;
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
    	changeMonth :true,
		  changeYear :true,
		  yearRange :'-40:-18',
		  dateFormat:'dd/mm/yy',
		  	 
		
    	
		 }) ;
  } );
  </script>
    
    <jsp:useBean id="dto" scope="request" class="in.co.rays.project3.dto.UserDTO" />
<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">
    
    
    
    
    
    <main>
        
        <!--MDB Forms-->
        <div class="container mt-3">

            
            <!-- Grid row -->
            <div class="row">
                <!-- Grid column -->
               <div class="col-md-4"></div>
               
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                        
                        
                       
                           <h3 class="text-center default-text py-3"><u>My Profile</u></h3>
                            <hr color="black"> 
                          
                            <!--Body-->
                            
                            		<%
                            		
                            		List roleList=(List)request.getAttribute("roleList");
                            		
                            		
                            		
                            		if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
    <div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong><%=ServletUtility.getSuccessMessage(request) %>
    </div>
    <%} %>                            
	
	<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
<div class="alert alert-success alert-dismissible fade show">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Success!</strong><%=ServletUtility.getErrorMessage(request) %>
  </div>
<%} %>                            
		
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
            
            
            
            <h6 class="paddingclass" style="color: #fff " >Email<font color="red">*</font></h6>

                           <div class="md-form">
                  		 <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i   class="fa fa-envelope"></i></span>
                                 </div>
                        <input type="text" placeholder="Enter Email" readonly="readonly" class="form-control border" style="height: 19px ; background-color:white" name="email" value="<%=DataUtility.getStringData(dto.getLogin())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("email",request) %> </font>

  </div>
            
                            
                           <h6  style="color: #fff ">First Name<font color="red">*</font></h6>
                           
                           
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




<!-- <h6 class="paddingclass" style="color: #fff " >Email<font color="red">*</font></h6> -->

<!--                            <div class="md-form"> -->
<!--                   		 <div class="input-group"> -->
<!--                             <div class="input-group-prepend"> -->
<!--                                <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i   class="fa fa-envelope"></i></span> -->
<!--                                  </div> -->
<%--                         <input type="text" placeholder="Enter Email" readonly="readonly" class="form-control border" style="height: 19px ; background-color:white" name="email" value="<%=DataUtility.getStringData(dto.getLogin())%>"> --%>
<!--                                 </div> -->
<%-- 							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("email",request) %> </font> --%>

<!--   </div> -->

                           
                   
                   
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

                           
                           
<!--                            <h6 class="paddingclass" style="color: #fff ">Password<font color="red">*</font></h6> -->
<!--                            <div class="md-form"> -->
<!--                   		 <div class="input-group"> -->
<!--                             <div class="input-group-prepend"> -->
<!--                                <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i   class="fa fa-lock"></i></span> -->
<!--                                  </div> -->
<%--                         <input type="password" placeholder="Enter Password" class="form-control border" style="height: 19px ; background-color:white" name="password" value="<%=DataUtility.getStringData(dto.getPassword())%>"> --%>
<!--                                 </div> -->
<%-- 							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("password",request) %> </font> --%>

<!--                            </div> -->

                          
                           
                           
                           <h6 class="paddingclass" style="color: #fff " >Gender<font color="red">*</font></h6>
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


<%--                            <%String role=HTMLUtility.getList("role",String.valueOf(dto.getRoleid()),roleList); %> --%>
                           
                          
<!--                            <h6 class="paddingclass" style="color: #fff " >Role<font color="red">*</font></h6> -->
                          
<!--                           <div class="md-form"> -->

<!-- 								<div class="input-group mb-3"> -->
<!--                              <div class="input-group-prepend"> -->
<!--                              <span class="input-group-text"><i style="width: 17px" class="fa fa-user"></i></span> -->
<!--                                       </div> -->
<%--                            	<%=role%> --%>
<!--                                       </div> -->
<%-- 								<font color="red" class="ml-5"> <%=ServletUtility.getErrorMessage("role", request) %></font> --%>

<!--                            </div> -->

<!--                            <div class="paddingclass"> -->
<!--                            <h6 class="default-text" >Date Of Birth<font color="red">*</font></h6> -->
<!--                            </div> -->
                           
                           
<!--                            <div class="md-form"> -->

<!--                   		 <div class="input-group"> -->
<!--                             <div class="input-group-prepend"> -->
<!--                                <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i   class="fa fa-calendar"></i></span> -->
<!--                                  </div> -->
<%--                         <input type="text" id="datepicker" placeholder="Enter dob" class="form-control border" style="height: 19px ; background-color:white" name="dob" value="<%=DataUtility.getStringData(dto.getDob())%>"> --%>
<!--                                 </div> -->
<%-- 							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("dob",request) %> </font> --%>

<!--    </div> -->

                           
                          <%--  <div class="text-center"  id="defaultForm-email">
                           <input class="button" type="submit"  name="operation" value="<%=MyProfileCtl.OP_SAVE%>">
                           <input class="button" type="submit"  name="operation" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>">
                          
                            </div> --%>
                             <div class="text-center">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=MyProfileCtl.OP_SAVE%>">
										<span class="fa fa-check-square"></span> Save
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>">
										<span class="fa fa-eraser"></span> Change My Password
									</button>
							
							</div>
                          
                           
                        </div>
                    </div>
                </div>
                <div class="col-md-3"></div>
               </div>
               </div>

</form>

<br><br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

<div>

</div>

</body>
<%@include file="Footer.jsp" %>
</html>