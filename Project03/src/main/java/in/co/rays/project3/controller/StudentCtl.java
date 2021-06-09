package in.co.rays.project3.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project3.dto.BaseDTO;
import in.co.rays.project3.dto.StudentDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.CollegeModelInt;
import in.co.rays.project3.model.ModelFactory;
import in.co.rays.project3.model.StudentModelInt;
import in.co.rays.project3.model.SubjectModelInt;
import in.co.rays.project3.util.DataUtility;
import in.co.rays.project3.util.DataValidator;
import in.co.rays.project3.util.PropertyReader;
import in.co.rays.project3.util.ServletUtility;

/**
 *  Student functionality Controller. Performs operation for add, update, delete
 * and get Student
 * @author Vaishali
 *
 */
@WebServlet(name="StudentCtl",urlPatterns={"/ctl/StudentCtl"})
public class StudentCtl extends BaseCtl {
	 
 	/** The log. */
 	private static Logger log = Logger.getLogger(StudentCtl.class);
	  
	 /* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
 	 */
 	@Override
	 protected void preload(HttpServletRequest request){
		  CollegeModelInt collegeModel =ModelFactory.getInstance().getCollegeModel();
	        try {
	            List l = collegeModel.list();
	            request.setAttribute("collegeList", l);
	        } catch (ApplicationException e) {
	            log.error(e);
	        }
	        SubjectModelInt subjectModel =ModelFactory.getInstance().getSubjectModel();
	        try {
	            List l1 = subjectModel.list();
	            request.setAttribute("subjectList", l1);
	        } catch (ApplicationException e) {
	            log.error(e);
	        }

	  }
	  
  	/* (non-Javadoc)
  	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
  	 */
  	@Override
	protected boolean validate(HttpServletRequest request) {
		  log.debug("StudentCtl Method validate Started");
          boolean pass = true;
          String op = DataUtility.getString(request.getParameter("operation"));
          String email = request.getParameter("email");
          String dob = request.getParameter("dob");

          if (DataValidator.isNull(request.getParameter("firstName"))) {
              request.setAttribute("firstName",
                      PropertyReader.getValue("error.require", "First Name"));
              pass = false;
          }else if (!DataValidator.isName(request.getParameter("firstName"))) {
              request.setAttribute("firstName","Please Enter Valid Name");
              pass = false;
          }
          if (DataValidator.isNull(request.getParameter("lastName"))) {
              request.setAttribute("lastName",
                      PropertyReader.getValue("error.require", "Last Name"));
              pass = false;
          }else if (!DataValidator.isName(request.getParameter("lastName"))) {
              request.setAttribute("firstName","Please Enter Valid Name");
              pass = false;
          }
          if (DataValidator.isNull(request.getParameter("mobileNo"))) {
              request.setAttribute("mobileNo",
                      PropertyReader.getValue("error.require", "Mobile No"));
              pass = false;
          }else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
              request.setAttribute("mobileNo",
                      "Please Enter Valid Mobile Number");
              pass = false;
          }
          
          
          
          if (DataValidator.isNull(email)) {
              request.setAttribute("email",
                      PropertyReader.getValue("error.require", "Email "));
              pass = false;
          } else if (!DataValidator.isEmail(email)) {
              request.setAttribute("email",
                      PropertyReader.getValue("error.email", "Email "));
              pass = false;
          }
          if (DataValidator.isNull(request.getParameter("collegeId"))) {
              request.setAttribute("collegeId",
                      PropertyReader.getValue("error.require", "College Name"));
              pass = false;
          }
          if (DataValidator.isNull(dob)) {
              request.setAttribute("dob",
                      PropertyReader.getValue("error.require", "Date Of Birth"));
              pass = false;
          } 
//          else if (!DataValidator.isValidAge(dob)) {
//              request.setAttribute("dob",
//            		  "Age Must Be Above 18");
//              pass = false;
//          }

          log.debug("StudentCtl Method validate Ended");

          return pass;
}
	  
	  /* (non-Javadoc)
  	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
  	 */
  	@Override
	  protected BaseDTO populateDTO(HttpServletRequest request){
		  log.debug("StudentCtl Method populatebean Started");
		  System.out.println(">>>>>>>>>>>>>>>>POPULATE");
		  StudentDTO dto=new StudentDTO();
		    dto.setId(DataUtility.getLong(request.getParameter("id")));
            dto.setFirstName(DataUtility.getString(request.getParameter("firstName")));
            dto.setLastName(DataUtility.getString(request.getParameter("lastName")));
            dto.setDob(DataUtility.getDate(request.getParameter("dob")));
            dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
            dto.setEmail(DataUtility.getString(request.getParameter("email")));
            
            dto.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
            System.out.println("collegeName>>>>>>>>>>>>>>>>>"+request.getParameter("collegeId"));
            
            log.debug("StudentCtl Method populatebean Ended");
            return dto;
	  }

	  
	  
	  /* (non-Javadoc)
  	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
  	 */
  	@Override

	  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    log.debug("StudentCtl Method doGet Started");
    System.out.println("do get");

    String op = DataUtility.getString(request.getParameter("operation"));
    long id = DataUtility.getLong(request.getParameter("id"));

   StudentModelInt model=ModelFactory.getInstance().getStudentModel();
    if (id > 0 || op != null) {
      StudentDTO dto;
      try {
        dto = model.findByPK(id);
        ServletUtility.setDto(dto, request);
    } catch (ApplicationException e) {
        log.error(e);
        ServletUtility.handleException(e, request, response);
        return;
    }
}
ServletUtility.forward(getView(), request, response);
log.debug("StudentCtl Method doGett Ended");
}

	  
	  
	  
	  /* (non-Javadoc)
  	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
  	 */
  	@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.debug("StudentCtl Method doPost Started");

        String op = DataUtility.getString(request.getParameter("operation"));
System.out.println("OPERATION>>>>>>>>>>>>>>>>>>>>>>"+op);
        // get model

        StudentModelInt model =ModelFactory.getInstance().getStudentModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) {

            StudentDTO dto = (StudentDTO)populateDTO(request);
            try {
            	if (id > 0) {
            		System.out.println("UPDATE>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    model.update(dto);
                    ServletUtility.setDto(dto, request);
                   
                    ServletUtility.setSuccessMessage("Data is successfully Updated",  request);
                   ServletUtility.forward(getView(), request, response);

                    
                } else {
                    long pk = model.add(dto);
                    ServletUtility.setDto(dto, request);
               
                    ServletUtility.setSuccessMessage("Data is successfully Saved",
                            request);
                    ServletUtility.forward(getView(), request, response);

                   dto.setId(pk);
                }
//                ServletUtility.setDto(dto, request);
                

            } catch (ApplicationException e) {
                e.printStackTrace();
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            } catch (DuplicateRecordException e) {
                ServletUtility.setDto(dto, request);
                ServletUtility.setErrorMessage("Student EmailId already exists",
                        request);
            }

        }   else if (OP_DELETE.equalsIgnoreCase(op)) {

            StudentDTO dto = (StudentDTO) populateDTO(request);
            try {
                model.delete(dto);
                ServletUtility.redirect(ORSView.STUDENT_LIST_CTL, request,
                        response);
                return;

            } catch (ApplicationException e) {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        } 
        else if (OP_RESET.equalsIgnoreCase(op)) {
       	 ServletUtility.redirect(ORSView.STUDENT_CTL, request, response);
        

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {

            ServletUtility
                    .redirect(ORSView.STUDENT_LIST_CTL, request, response);
            return;

        }
        //ServletUtility.forward(getView(), request, response);

        log.debug("StudentCtl Method doPost Ended");
    }


	  /* (non-Javadoc)
  	 * @see in.co.rays.controller.BaseCtl#getView()
  	 */
  	@Override
	  protected String getView() {

	return ORSView.STUDENT_VIEW;
}


}
 