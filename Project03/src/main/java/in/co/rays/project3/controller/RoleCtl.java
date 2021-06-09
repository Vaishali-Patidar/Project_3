package in.co.rays.project3.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project3.dto.BaseDTO;
import in.co.rays.project3.dto.RoleDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.ModelFactory;
import in.co.rays.project3.model.RoleModelInt;
import in.co.rays.project3.util.DataUtility;
import in.co.rays.project3.util.DataValidator;
import in.co.rays.project3.util.PropertyReader;
import in.co.rays.project3.util.ServletUtility;
/**
 * Role functionality Controller. Performs operation for add, update and get
 * Role
 * @author Vaishali
 *
 */
@WebServlet(name="RoleCtl",urlPatterns={"/ctl/RoleCtl"})
public class RoleCtl  extends BaseCtl{
	
	/** The log. */
	private static Logger log = Logger.getLogger(RoleCtl.class);
	
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
	boolean pass=true;
	
	String role= request.getParameter("role");
	String description= request.getParameter("description");
	
	if (DataValidator.isNull(role)) {
		request.setAttribute("role", PropertyReader.getValue("error.require", "Role"));
		pass=false;
	}
//	if(!DataValidator.isName(role)){
//		request.setAttribute("role", "Please Enter Valid Role");
//		pass=false;
//	}
	
	if (DataValidator.isNull(description)) {
		request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
		pass=false;
	}
	System.out.println("validate");
	return pass;
	}
	
	
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
	RoleDTO dto=new RoleDTO();
	
	dto.setId(DataUtility.getLong(request.getParameter("id")));
	System.out.println(DataUtility.getLong(request.getParameter("id")));
	dto.setName(DataUtility.getString(request.getParameter("role")));
	System.out.println(DataUtility.getString(request.getParameter("description")));
	dto.setDescription(DataUtility.getString(request.getParameter("description")));
	
	System.out.println("populate");
	return dto;
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     String op=DataUtility.getString(request.getParameter("operation"));
     Long id=DataUtility.getLong(request.getParameter("id"));

     RoleModelInt model=ModelFactory.getInstance().getRoleModel();
     RoleDTO dto;
     
     if(id>0||op!=null){
     try {
		dto=model.findByPK(id);
	    ServletUtility.setDto(dto, request);
	
	} catch (ApplicationException e) {
		e.printStackTrace();
		ServletUtility.handleException(e, request, response);
		return;
	}
     }
     ServletUtility.forward(getView(), request, response);
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("RoleCtl Method doGet Started");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		RoleModelInt model = ModelFactory.getInstance().getRoleModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)||OP_UPDATE.equalsIgnoreCase(op)) {
			RoleDTO dto = (RoleDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
					
					ServletUtility.setSuccessMessage("Data is successfully updated", request);
					ServletUtility.setDto(dto, request);	
					ServletUtility.forward(getView(), request, response);
				} else {
					model.add(dto);
					ServletUtility.setSuccessMessage("Data is successfully saved", request);
					
					ServletUtility.setDto(dto, request);
					ServletUtility.forward(getView(), request, response);
				}
					
				
	
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			} catch (DuplicateRecordException e) {
			
				ServletUtility.setDto(dto, request);
				ServletUtility.setErrorMessage("Role already exists", request);
				ServletUtility.forward(getView(), request, response);
			}
		} else {
			if (OP_DELETE.equalsIgnoreCase(op)) {
				RoleDTO bean = (RoleDTO) populateDTO(request);
				try {
					model.delete(bean);
					ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
					return;
				} catch (ApplicationException e) {
					log.error(e);
					ServletUtility.handleException(e, request, response);
					return;
				}
			} else if (OP_CANCEL.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
				return;

			} else if (OP_RESET.equalsIgnoreCase(op)) {

				ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
				return;

			}


			/*ServletUtility.forward(getView(), request, response);*/

			log.debug("RoleCtl Method doPOst Ended");
		}


	}
	
	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ROLE_VIEW;
	}
}

