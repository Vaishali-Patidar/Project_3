package in.co.rays.project3.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project3.dto.BaseDTO;
import in.co.rays.project3.dto.SubjectDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.CourseModelInt;
import in.co.rays.project3.model.ModelFactory;
import in.co.rays.project3.model.SubjectModelInt;
import in.co.rays.project3.util.DataUtility;
import in.co.rays.project3.util.DataValidator;
import in.co.rays.project3.util.PropertyReader;
import in.co.rays.project3.util.ServletUtility;



/**
 *  Subject functionality Controller. Performs operation for add, update, delete
 * and get Subject
 * @author Vaishali
 *
 */
@WebServlet(name="SubjectCtl",urlPatterns={"/ctl/SubjectCtl"})
public class SubjectCtl extends BaseCtl {

	protected void preload(HttpServletRequest request){
		//private static Logger log=Logger.getLogger(SubjectCtl.class);
		System.out.println("preload enter");
		CourseModelInt courseModel=ModelFactory.getInstance().getCourseModel();
		try {
			List l=courseModel.list();
			request.setAttribute("courseList",l);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			//log.error(e);
		}
	System.out.println("Preload ended");
	}
	@Override
	protected boolean validate(HttpServletRequest request) {
		//log.debug("validate Method of Subject Ctl start");
 	   boolean pass=true;
 	   if (DataValidator.isNull(request.getParameter("subject"))){
 		  request.setAttribute("subject",PropertyReader.getValue("error.require","Subject Name"));
 	  pass=false;
 	  }
 	  if (DataValidator.isNull(request.getParameter("coursename"))){
 		  request.setAttribute("coursename",PropertyReader.getValue("error.require","coursename"));
 	  pass=false;
 	  }
 	  if (DataValidator.isNull(request.getParameter("description"))){
 		  request.setAttribute("description",PropertyReader.getValue("error.require","Description"));
 	  pass=false;
 	  }
 	 // log.debug("SubjectCtl Method validate Ended");
 	  
 	   return pass;
	}
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
 	  // log.debug("SubjectCtl Method populatebean Started");
		
//		System.out.println("populateBean in");
//		bean.setId(DataUtility.getInt(request.getParameter("id")));
//		System.out.println("id>>>>>>>>>>>>>>>>>>.."+request.getParameter("id"));
//		bean.setSubject_Name(DataUtility.getString(request.getParameter("name")));
//	System.out.println("Name>>>>>>>>>>>>>>>>>>>>"+request.getParameter("name"));
//		bean.setDescription(DataUtility.getString(request.getParameter("description")));
//		System.out.println("Descrip>>>>>>"+request.getParameter("description"));
//		bean.setCourse_Id(DataUtility.getInt(request.getParameter("coursename")));
//		System.out.println("coursename>>>>>>>>>>>."+request.getParameter("coursename"));
//		populateDTO(bean, request);
//		//log.debug("PopulatedBean methods subject ctl ended");
//		System.out.println("populatedbean out");
//		
		   SubjectDTO dto=new SubjectDTO();
		   dto.setId(DataUtility.getLong(request.getParameter("id")));
	       dto.setSubject_Name(DataUtility.getString(request.getParameter("subject")));
	       dto.setCourse_Id(DataUtility.getLong(request.getParameter("coursename")));
	      
          //  dto.setCourse_Name(DataUtility.getString(request.getParameter("course")));
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>Course name"+request.getParameter("coursename"));
        dto.setDescription(DataUtility.getString(request.getParameter("description")));
      //  log.debug("SubjectCtl Method populatebean Ended");
    return dto;
}
	/*Contains Display logics */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		//log.debug("Doget method of subject clt start")
		System.out.println("Do get in");
		
		String op=DataUtility.getString(request.getParameter("operation"));
		SubjectModelInt model=ModelFactory.getInstance().getSubjectModel();
		SubjectDTO dto=null;
		
		long id =DataUtility.getInt(request.getParameter("id"));
		if(id > 0||op!=null){
			try {
				dto =model.findByPK(id);
				ServletUtility.setDto(dto, request);
			}catch(ApplicationException e){
				//log.error(e);
				e.printStackTrace();
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		System.out.println("doget out");
		//log.debug("Doget Method subject ctl ended");
		ServletUtility.forward(getView(), request, response);
	}
	/*Contain Submit logic*/
	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		System.out.println("doPost in");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id =DataUtility.getLong(request.getParameter("id"));
		SubjectModelInt model=ModelFactory.getInstance().getSubjectModel();
 if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {	
		SubjectDTO dto = (SubjectDTO)populateDTO(request);
	//	System.out.println("post in operaion save  ");
	
 try{
	 if(id>0){
		 model.update(dto);
		 ServletUtility.setDto(dto, request);
		 ServletUtility.setSuccessMessage("Subject is Succesfully update", request);
		  }else{
			//  System.out.println("in adddd>>>>>"+model.add(dto));
			  long pk =model.add(dto);
			  ServletUtility.setSuccessMessage("Subject is Succesfully add",request);
		  }
	 ServletUtility.setDto(dto, request);
 }
	 catch(ApplicationException e){
		 //log.debug(e);
		 e.printStackTrace();
		 ServletUtility.handleException(e, request, response);
		 return ;
	 
			  } catch (DuplicateRecordException e) {
		// TODO Auto-generated catch block
				  ServletUtility.setDto(dto, request);
					ServletUtility.setErrorMessage("Subject name already Exsist", request);
					}
				}
				else if (OP_RESET.equalsIgnoreCase(op)) {
					ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
					return;
				}
				else if (OP_CANCEL.equalsIgnoreCase(op) ) {
					ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
					return;
				}
		/*		else if (OP_DELETE.equalsIgnoreCase(op)) {
					SubjectBean bean =  populateBean(request);
					try {
						model.delete(bean);
					ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
					return;
					} catch (ApplicationException e) {
						log.error(e);
						ServletUtility.handleException(e, request, response);
						return ; 
					}
				}*/
				
			
				ServletUtility.forward(getView(), request, response);
				//log.debug("Do post Method of Subject Ctl End");
			}
			
			/* (non-Javadoc)
			 * @see in.co.rays.ors.controller.BaseCtl#getView()
			 */
		  
 
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SUBJECT_VIEW;
	}

}
