package in.co.rays.project3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project3.dto.BaseDTO;
import in.co.rays.project3.dto.MarksheetDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.MarksheetModelInt;
import in.co.rays.project3.model.ModelFactory;
import in.co.rays.project3.model.StudentModelInt;
import in.co.rays.project3.util.DataUtility;
import in.co.rays.project3.util.DataValidator;
import in.co.rays.project3.util.PropertyReader;
import in.co.rays.project3.util.ServletUtility;


/**
 * /**
 * Marksheet functionality Controller. Performs operation for add, update,
 * delete and get Marksheet
 
 * @author Vaishali
 *
 */
@WebServlet(name="MarksheetCtl",urlPatterns={"/ctl/MarksheetCtl"})
public class MarksheetCtl extends BaseCtl{
	private static Logger log = Logger.getLogger(MarksheetCtl.class);
	
	@Override
	protected void preload(HttpServletRequest request){
		StudentModelInt model =ModelFactory.getInstance().getStudentModel();
		try {
			List studentList = model.list();
			request.setAttribute("studentList", studentList);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}
	
@Override
protected boolean validate(HttpServletRequest request){
	log.debug("MarksheetCtl Method validate Started");
	boolean pass = true;
	if (DataValidator.isNull(request.getParameter("rollNo"))) {
		request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll Number"));
		pass = false;
	}
    if( DataValidator.isNull(request.getParameter("physics"))){
		
		request.setAttribute("physics",PropertyReader.getValue("error.require", "physics") );
	pass=false;
	}else

	if (DataValidator.isNotNull(request.getParameter("physics"))&&!DataValidator.isInteger(request.getParameter("physics"))) 
	{
		request.setAttribute("physics", PropertyReader.getValue("error.integer", "Marks"));
		pass = false;
	}else

	if (DataValidator.isNotNull(request.getParameter("physics"))&&DataUtility.getInt(request.getParameter("physics")) > 100) {
		request.setAttribute("physics", "Marks can not be greater than 100");
		pass = false;
	} 

    if( DataValidator.isNull(request.getParameter("chemistry"))){
		
		request.setAttribute("chemistry",PropertyReader.getValue("error.require", "chemistry") );
	pass=false;
	}else

	if (DataValidator.isNotNull(request.getParameter("chemistry"))&&!DataValidator.isInteger(request.getParameter("chemistry"))) 
	{
		request.setAttribute("chemistry", PropertyReader.getValue("error.integer", "Marks"));
		pass = false;
	}else

	if (DataValidator.isNotNull(request.getParameter("chemistry"))&&DataUtility.getInt(request.getParameter("chemistry")) > 100) {
		request.setAttribute("chemistry", "Marks can not be greater than 100");
		pass = false;
	} 

    if( DataValidator.isNull(request.getParameter("maths"))){
		
		request.setAttribute("maths",PropertyReader.getValue("error.require", "maths") );
	pass=false;
	}else

	if (DataValidator.isNotNull(request.getParameter("maths"))&&!DataValidator.isInteger(request.getParameter("maths"))) 
	{
		request.setAttribute("maths", PropertyReader.getValue("error.integer", "Marks"));
		pass = false;
	}else

	if (DataValidator.isNotNull(request.getParameter("maths"))&&DataUtility.getInt(request.getParameter("maths")) > 100) {
		request.setAttribute("maths", "Marks can not be greater than 100");
		pass = false;
	} 


	if (DataValidator.isNull(request.getParameter("studentId"))) {
		request.setAttribute("studentId", PropertyReader.getValue("error.require", "Student Name"));
		pass = false;
	}

	log.debug("MarksheetCtl Method validate Ended");

	return pass;
}

/* (non-Javadoc)
 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
 */
@Override
protected BaseDTO populateDTO(HttpServletRequest request){

   // log.debug("MarksheetCtl Method populatebean Started");
	MarksheetDTO dto = new MarksheetDTO();
	dto.setId(DataUtility.getLong(request.getParameter("id")));

	dto.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
	


	dto.setPhysics(DataUtility.getInt(request.getParameter("physics")));

	dto.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));

	dto.setMaths(DataUtility.getInt(request.getParameter("maths")));

	dto.setStudentId(DataUtility.getLong(request.getParameter("studentId")));
	System.out.println("Subjectid+++++++++++++++++"+request.getParameter("studentId"));
	populateBean(dto, request);
	log.debug("MarksheetCtl Method populatebean Ended");
	return dto;

}

private void populateBean(MarksheetDTO dto, HttpServletRequest request) {
	// TODO Auto-generated method stub
	
}


protected void doGet(HttpServletRequest request ,HttpServletResponse response) throws IOException, ServletException{
//    log.debug("MarksheetCtl Method doGet Started");
String op =DataUtility.getString(request.getParameter("operation"));
MarksheetModelInt model=ModelFactory.getInstance().getMarksheetModel();
long id =DataUtility.getInt(request.getParameter("id"));
System.out.println("inside doget>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");

if(id>0){
	MarksheetDTO dto ;
	try{
		dto=model.findByPK(id);
		System.out.println("StudentNAme>>>>>>>>>>"+dto.getName());
		ServletUtility.setDto(dto, request);
		
	}catch(ApplicationException e){
		//log.error(e);
		ServletUtility.handleException(e, request, response);
		return;
	}
}
ServletUtility.forward(getView(), request, response);
//log.debug("MarksheetCtl Method doGet Ended");

}

protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
   // log.debug("MarksheetCtl Method doPost Started");
	//log.debug("MarksheetCtl Method doPost Started");

	String op = DataUtility.getString(request.getParameter("operation"));
	MarksheetModelInt model=ModelFactory.getInstance().getMarksheetModel();
	long id = DataUtility.getInt(request.getParameter("id"));
	MarksheetDTO dto = (MarksheetDTO) populateDTO(request);
	
	// get model
	//MarksheetModel model = new MarksheetModel();

	if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
		try {
			if (id > 0) {
				model.update(dto);
				System.out.println("student");
				ServletUtility.setDto(dto, request);
				ServletUtility.setSuccessMessage("Marksheet is Successfully Updated ", request);

			} else {
				long pk = model.add(dto);
				ServletUtility.setDto(dto, request);
				ServletUtility.setSuccessMessage("Marksheet is Successfully Added ", request);
		
			}
			

		} catch (ApplicationException e) {
			//log.error(e);
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		} catch (DuplicateRecordException e) {
			ServletUtility.setDto(dto, request);
			ServletUtility.setErrorMessage("Roll no already exists", request);
		}

	} else if (OP_RESET.equalsIgnoreCase(op)) {

		ServletUtility.redirect(ORSView.MARKSHEET_CTL, request, response);
		return;
	} else if (OP_CANCEL.equalsIgnoreCase(op)) {

		ServletUtility.redirect(ORSView.MARKSHEET_LIST_CTL, request, response);
		return;
	}
	ServletUtility.setDto(dto, request);
	ServletUtility.forward(getView(), request, response);

	//log.debug("MarksheetCtl Method doPost Ended");
}


	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.MARKSHEET_VIEW;
	}

}
