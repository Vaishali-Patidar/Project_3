<%@page import="in.co.rays.project3.controller.RoleCtl"%>
<%@page import="in.co.rays.project3.util.DataUtility"%>
<%@page import="in.co.rays.project3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- font-awesome library -->
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
.form-white .md-form label {
  color: #fff; 
}

.card{
   background-color:#28a74582!important;
}


.paddingclass{
padding-top: 10px;
}

</style>
<style type="text/css">
.setForm{
padding-top: 5%;
padding-left: 25%;
width: 130%
}
.textfield{
border: 1px solid #808083b;height: 38px; padding-left: 6px;
}


.button{
border-radius:10px;padding:10px;color:white;font-size:20px;background-color:#00cc88
}
</style>
</head>

<body >
<jsp:useBean id="dto" scope="request" class="in.co.rays.project3.dto.RoleDTO" />
<div>
<%@include file="Header.jsp" %>
</div>
<form action="<%=ORSView.ROLE_CTL%>" method="post">



    
    
    <main>
        
        <!--MDB Forms-->
        <div class="container mt-3">

            
            <!-- Grid row -->
            <div class="row">
            <div class="col-md-4"></div>
                <!-- Grid column -->
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                        <%long id=DataUtility.getLong(request.getParameter("id")); %>
                            <h3 class="text-center default-text py-3"><u><%=(id==0)? "Add Role": "Update Role" %></u></h3>
                           <hr color="black"> 
                            <!--Body-->

                            		<%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
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





                          
                            
                          <h6  style="color: #fff ">Role:<font color="red">*</font></h6>  
                            <div class="md-form">
                          <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text" style="background-color:white-space; height: 38px; width: 45px;"><i class="fa fa-user"></i></span>
                                 </div>
                          <input type="text" placeholder="Enter role"  class="form-control border"  style="height: 19px ;background-color:white;" name="role" value="<%=DataUtility.getStringData(dto.getName())%>">
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("role",request) %> </font>
                            
                            </div>
                            
                            
                            
                            <h6 style="color:#fff ">Description<font color="red">*</font></h6>
                            <div class="md-form">
                               
                               <div class="input-group">
                            <div class="input-group-prepend">
                               <span class="input-group-text"><i style="width: 17px"  class="fa fa-user"></i></span>
                                 </div>
                          <textarea placeholder="Enter description" class="form-control border" name="description" value="<%=DataUtility.getStringData(dto.getName())%>"></textarea>
                                </div>
							<font color="red" class="ml-5"><%=ServletUtility.getErrorMessage("description",request) %> </font>
                               
                               
                               </div>
                          
                          
                          
                          
                          
                          
                                               <%if (id>0){ %>
                           <div class="text-center">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=RoleCtl.OP_UPDATE%>">
										<span class="fa fa-check-square"></span> Update
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=RoleCtl.OP_CANCEL%>">
										<span class="fa fa-refresh"></span> Cancel
									</button>
							
							</div>
                          
                          
                          <%}else{ %> 
                           <div class="text-center">
							
								 <button type="submit" class="btn btn-success" name="operation"
									style="font-size: 13px"	value="<%=RoleCtl.OP_SAVE%>">
										<span class="fa fa-check-square"></span> Save
									</button>
									 
									
							 <button type="submit" class="btn btn-warning" name="operation"
									style="font-size: 13px"	value="<%=RoleCtl.OP_RESET%>">
										<span class="fa fa-refresh"></span> Reset
									</button>
							
							</div>
                          
                             <%} %>
       
                        </div>
                    
                    </div>
                     </div>

<div class="col-md-3"></div>
</div>               
</div>
</form>

</body>
<%@include file="Footer.jsp" %>
</html>