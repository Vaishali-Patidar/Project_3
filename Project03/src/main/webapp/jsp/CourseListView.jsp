<%@page import="java.util.Iterator"%>
<%@page import="in.co.rays.project3.util.DataUtility"%>
<%@page import="in.co.rays.project3.dto.CourseDTO"%>
<%@page import="in.co.rays.project3.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.rays.project3.controller.CourseListCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.project3.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Course List</title>

<!-- Select All Library -->
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js"></script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>	

<!-- font-awesome library -->
<style type="text/css">
@import url(https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css);
@import url(https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.4.3/css/mdb.min.css);

body {
	background-image: url("/Project03/img/pngtree.jpg");
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
    color: #2BBBAD; 
}
.info-text {
    color: #33b5e5; 
}
.ripe-malinka-gradient {
    background-image: linear-gradient(120deg, #f093fb 0%, #f5576c 100%);
}
.near-moon-gradient {
    background-image: linear-gradient(to bottom, #5ee7df 0%, #b490ca 100%);
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
.button{
border-radius:5px;
width:100px;
 padding:5px;
 color:white;
 font-size:20px;
 background-color:#00cc88
}
.fonta{
font-size: 20px;
}
</style>
</head>
<body class="hm-gradient">
<jsp:useBean id="dto" scope="request" class="in.co.rays.project3.dto.CourseDTO" />
<div>
<%@include file="Header.jsp" %>
</div>
<form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">
 <div class="container-fluid">
        	<div class="card" style="background-color:#28a74582; margin-bottom: 80px; margin-top:30px "  >
        <div class="card-body" >
        

 <div align="center">    
         <H2 style="color: white">  <span class="fa fa-list-alt"></span><b > <u>Course List</u></b> </H2>
         <hr color="white">
       </div>
       
       <div class="text-center" >
       
			<%if(!ServletUtility.getSuccessMessage(request).equals("")){ %>
			<div class="alert alert-success alert-dismissible fade show">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Success!</strong><font color="green"> <%=ServletUtility.getSuccessMessage(request) %></font>
			</div>
			<%} %>

			<%if(!ServletUtility.getErrorMessage(request).equals("")){ %>
			<div class="alert alert-success alert-dismissible fade show">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Error!</strong><font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
			</div>

			<%} %>
			   </div>


                           
            <input type="hidden" name="id" value="<%=dto.getId()%>">
            <input type="hidden" name="createdBy" value="<%=dto.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=dto.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(dto.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(dto.getModifiedDatetime())%>">
	
	
	
	
	
	
	  <%     List list = ServletUtility.getList(request);
	System.out.println(list);
        if (list.size()==0){ %>
			 <table align="center">
				<tr>
					<td>
					 
					  <button type="submit" name="operation" class=" form-control btn btn-warning" 
			     value="<%=CourseListCtl.OP_BACK%>" style=" width: 150px; height: 47px; font-size: 16px; background-color: gray;">
                 <span style="margin-right: 7px;" class="	fa fa-folder-open"></span>  Back </button>
                 
					</td>
			
				</tr>
			</table>
			
			<%}else{ %>
	 
	  
     <%--     <br>
                 <div>
                 <table width="80%"  align="center">
                 <tr>
	<%List listSize=ServletUtility.getList(request); %>
	    <div class="container-fluid text-center">
          
          <div class="form-inline">
        
				<% List courseList=(List)request.getAttribute("courseList");%>
			 <div class="form-group text-center"> 
			 &emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
			
					 <label style="color: white;">Course Name :</label>
					 
					 
					 
					 <%=HTMLUtility.getList("course",String.valueOf(dto.getId()),courseList) %>
					  
					  
					  </div>
			&emsp;
			
			
			
			
				<div class="form-group">
				<button type="submit" name="operation" class=" form-control btn btn-info" style="font-size: 13px"
				 value="<%=CourseListCtl.OP_SEARCH%>">
                <span class="fa fa-search"></span> Search </button>
       
			     <button type="submit" name="operation" class=" form-control btn btn-warning"  style="font-size: 13px"
			     value="<%=CourseListCtl.OP_RESET%>" >
                 <span class="	fa fa-refresh"></span> Reset </button>
        
        </div>
     
        </tr>
        </table>
         </div>
    --%>
    
    <%--  <div class="row no-gutters">
	&emsp;&emsp;&emsp;&emsp;&emsp;&emsp;
	 <div class="col-sm-2"></div>
	 <!--  <label class="form-check-label" for="check2" style = "max-width:72px"> -->
	 <strong style="color: white">Course Name :~</strong>&nbsp;
	 	
				<% List courseList=(List)request.getAttribute("courseList");%>
			
    <div class="col-sm-2"><%=HTMLUtility.getList("course",String.valueOf(dto.getId()),courseList) %></div>
    &emsp;&emsp;&emsp;
	
			

</div>
	
	
	
	   
	  <div class="col-sm-2 pl-4" style="max-width: 120px">
	  <button type="submit" name="operation" class=" form-control btn btn-info" style="font-size: 13px"
				 value="<%=CourseListCtl.OP_SEARCH%>">
             <span class="fa fa-search"></span> Search </button>
        </div>
        &emsp;
      <div class="col-sm-2 pl-1" style="max-width: 100px">     
      <button type="submit" name="operation" class=" form-control btn btn-warning"  style="font-size: 13px"
			     value="<%=CourseListCtl.OP_RESET%>" >
                 <span class="fa fa-refresh"></span>Reset</button>
      </div>
	 
	 
	  </div>
 --%>    
 
  <div class="row no-gutters">
  <div class="col-sm-2"> </div>
	&emsp;&emsp;&emsp;
	 <!--  <label class="form-check-label" for="check2" style = "max-width:72px"> -->
	 <strong style="color: white">Course Name :~</strong>&nbsp;
	 	
				<% List cl=(List)request.getAttribute("courseList");%>
			
    <div class="col-sm-2"><%=HTMLUtility.getList("CourseName",String.valueOf(dto.getId()),cl) %></div>
	&emsp;&emsp;&emsp;
	
	
	
	   
	  <div class="col-sm-2 pl-4" style="max-width: 120px">
	  <button type="submit" name="operation" class=" form-control btn btn-info" style="font-size: 13px"
				 value="<%=CourseListCtl.OP_SEARCH%>">
             <span class="fa fa-search"></span> Search </button>
        </div>
        &emsp;
      <div class="col-sm-2 pl-1" style="max-width: 100px">     
      <button type="submit" name="operation" class=" form-control btn btn-warning"  style="font-size: 13px"
			     value="<%=CourseListCtl.OP_RESET%>" >
                 <span class="fa fa-refresh"></span>Reset</button>
      </div>
	 
	 
	  </div>
 
    
        <hr color="white">
        
        			<table width="100%">
				<tr>
				
					<%--  <td align="left">
  <button type="submit" name="operation" style="font-size: 12px"
	value="<%=CourseListCtl.OP_PREVIOUS%>"<%=(pageNo == 1) ? "disabled" : ""%>   class="btn btn-primary">
	<span class="fa fa-chevron-left"></span> <%=CourseListCtl.OP_PREVIOUS%> </button> 

			</td> --%>
				
				<td align="left">
      <button type="submit" style="font-size: 13px" name="operation" value="<%=CourseListCtl.OP_DELETE%>"
	  class="btn btn-danger"><span class="fa fa-trash"></span> 
	  <%=CourseListCtl.OP_DELETE%> </button>
			</td>	
			
				
						<td align="right">
    <button type="submit" style="font-size: 13px" name="operation" value="<%=CourseListCtl.OP_NEW%>"
	class="btn btn-primary"> <span class="fas fa-plus-square"></span> 
	<%=CourseListCtl.OP_NEW%> </button>
			</td>
			
			
					
 
               <%--   <td align="right">
        <button type="submit" style="font-size: 13px" name="operation" 
        value="<%=CourseListCtl.OP_NEXT%>"<%=(list.size()<pageSize||next==0) ? "disabled" : ""%>  class="btn btn-primary" >
        <%=CourseListCtl.OP_NEXT%>
         <span class="fa fa-chevron-right"></span> 
        </button></div>

			</td> --%>

			</table>
       
       
      <div class="table-responsive" >
     <!--  <table class="table table-striped" width="100%"> -->
  <table  border="3" width="100%"  cellpadding=12px cellspacing=".6">
  
      <thead>
     <tr align="center"  style="background: orange">
      <th class="text-center"><input type="checkbox" id="select_all" name="select"><strong> Select All</strong></th>
                    <th><strong>S.NO</strong></th>
					<th><strong>CourseName</strong></th>
					<th><strong>Duration</strong></th>
					<th><strong>Description</strong></th>
					<th><strong>Edit</strong></th>
      </tr>
      </thead>
      <%
	                int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;
                    int next=DataUtility.getInt(request.getAttribute("nextlist").toString());

                    Iterator<CourseDTO> it = list.iterator();
                    while (it.hasNext()) {
                    	
                        dto = it.next();
               %>
               <tbody>
				<%-- <tr class="<%=((index)%2==0)?"table-info":"table-danger"%>"> --%>
					<tr align="center" style="background:white">
					<td class="text-center"><input type="checkbox" class="checkbox" name="ids" value="<%=dto.getId()%>"></td>
					<td class=""><strong><%=index++%></strong></td>
					<td><strong><%=dto.getCourse_Name()%></strong></td>
					<td><strong><%=dto.getDuration()%></strong></td>
					<td><strong><%=dto.getDiscription()%></strong></td>
					<%-- <td><a href="CourseCtl?id=<%=dto.getId()%>"><strong>Edit</strong></a></td> --%>
				
				 <td style="size: 20%; text-align: center;">
					
					<a href="CourseCtl?id=<%=dto.getId()%>">
					<span class="fa fa-edit"></span></a>
					
					</td>
					 
					 <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
			<input type="hidden" name="pageSize" value="<%=pageSize%>">
				
				
				</tr>
				</tbody>
				<%
                    }
                %>
			</table>
			</div>
			
			
			<table width="100%">
				<tr>
				
					 <td align="left">
  <button type="submit" name="operation" style="font-size: 12px"
	value="<%=CourseListCtl.OP_PREVIOUS%>"<%=(pageNo == 1) ? "disabled" : ""%>   class="btn btn-primary">
	<span class="fa fa-chevron-left"></span> <%=CourseListCtl.OP_PREVIOUS%> </button> 

			</td>
					<%-- <td>
				
      <button type="submit" style="font-size: 13px" name="operation" value="<%=CourseListCtl.OP_DELETE%>"
	  class="btn btn-danger"><span class="fa fa-trash"></span> 
	  <%=CourseListCtl.OP_DELETE%> </button>
			</td>	
			
				
							<td>
    <button type="submit" style="font-size: 13px" name="operation" value="<%=CourseListCtl.OP_NEW%>"
	class="btn btn-primary"> <span class="fas fa-plus-square"></span> 
	<%=CourseListCtl.OP_NEW%> </button>
			</td>
			 --%>
			
					
 
                 <td align="right">
        <button type="submit" style="font-size: 13px" name="operation" 
        value="<%=CourseListCtl.OP_NEXT%>"<%=(list.size()<pageSize||next==0) ? "disabled" : ""%>  class="btn btn-primary" >
        <%=CourseListCtl.OP_NEXT%>
         <span class="fa fa-chevron-right"></span> 
        </button></div>

			</td>

			</table>

			
			<%} %>
			</div>
</form>
</body>

<%@include file="Footer.jsp" %>
</html>