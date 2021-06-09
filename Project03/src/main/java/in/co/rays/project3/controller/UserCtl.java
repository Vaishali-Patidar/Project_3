package in.co.rays.project3.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project3.dto.BaseDTO;
import in.co.rays.project3.dto.UserDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.ModelFactory;
import in.co.rays.project3.model.RoleModelInt;
import in.co.rays.project3.model.UserModelHibImp;
import in.co.rays.project3.model.UserModelInt;
import in.co.rays.project3.util.DataUtility;
import in.co.rays.project3.util.DataValidator;
import in.co.rays.project3.util.PropertyReader;
import in.co.rays.project3.util.ServletUtility;

/**
 * * User functionality Controller. Performs operation for add, update and get
 * User
 * 
 * @author Vaishali 
 */
@WebServlet(name = "UserCtl", urlPatterns = { "/ctl/UserCtl" })
public class UserCtl extends BaseCtl {

	private static final int serialVersionUID = 1;

	/** The log. */
	private static Logger log = Logger.getLogger(UserCtl.class);

	/* (non-Javadoc)
	 * @see in.co.rays.ors.controller.BaseCtl#preload(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected void preload(HttpServletRequest request) {
		RoleModelInt roleModel = ModelFactory.getInstance().getRoleModel();
		try {
			List list = roleModel.list();
			request.setAttribute("roleList", list);
		} catch (ApplicationException e) {
			e.printStackTrace();
			log.error(e);
		}

	}

	/* (non-Javadoc)
	 * @see in.co.rays.ors.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("UserCtl Method validate Started");
		boolean pass = true;
		String fname = request.getParameter("firstName");
		String lname = request.getParameter("lastName");
		String mobile = request.getParameter("mobileNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String role = request.getParameter("role");
		if (DataValidator.isNull(fname)) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		}else if(!DataValidator.isName(fname)){
			request.setAttribute("firstName", "");
            pass=false;
		}
		if (DataValidator.isNull(lname)) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		}else if(!DataValidator.isName(lname)){
			request.setAttribute("firstName", "Please Enter Proper Name");
			pass=false;
		}
		if (DataValidator.isNull(mobile)) {
			request.setAttribute("mobileNumber", PropertyReader.getValue("error.require", "Mobile Number"));
			pass = false;
		}else if(!DataValidator.isPhoneNo(mobile)){
			request.setAttribute("mobileNumber","Please Enter Valid Mobile Number");
			pass=false;
			
		}
		if (DataValidator.isNull(email)) {
			request.setAttribute("email", PropertyReader.getValue("error.require", "Email"));
			pass = false;
		} 
//			else if (!DataValidator.isEmail(email)) {
//		}
		if (DataValidator.isNull(password)) {
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;
		}
//		else if (!DataValidator.isPasswordLength(password)) {
//			request.setAttribute("password", "Password should contain 8 character");
//			pass = false;
		
//		}else if (!DataValidator.isPassword(password)) {
//			request.setAttribute("password", "Password Contain upper case,lower case,number and special char. ");
//			pass = false;
		//}
//		if (DataValidator.isNull(confirmPassword)) {
//			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "ConfirmPassword"));
//			pass = false;
//		}
		if (DataValidator.isNull(gender)) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		if (DataValidator.isNull(dob)) {
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date of Birth"));
			pass = false;
		}/*else if (DataValidator.isValidAge(dob)) {
			request.setAttribute("dob", "Age Must Be Above 18");
			pass = false;
		}*/
		if (DataValidator.isNull(role)) {
			request.setAttribute("role", PropertyReader.getValue("error.require", "Role"));
			pass = false;
		}

//		if (!password.equals(confirmPassword) && !"".equals(confirmPassword)) {
//			ServletUtility.setErrorMessage("Confirm  Password  should not be matched.", request);
//
//			pass = false;
//		}
		log.debug("UserRegistrationCtl Method validate Ended");

		return pass;
	}
	/* (non-Javadoc)
	 * @see in.co.rays.ors.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
	 */
	
	protected BaseDTO populateDTO(HttpServletRequest request) {
		log.debug("UserCtl Method populatebean Started");

		UserDTO dto = new UserDTO();

		dto.setId(DataUtility.getLong(request.getParameter("id")));

		dto.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		dto.setLastName(DataUtility.getString(request.getParameter("lastName")));

		dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNumber")));

		dto.setLogin(DataUtility.getString(request.getParameter("email")));

		dto.setPassword(DataUtility.getString(request.getParameter("password")));

//		dto.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));

		dto.setGender(DataUtility.getString(request.getParameter("gender")));

		dto.setDob(DataUtility.getDate(request.getParameter("dob")));
System.out.println("date of birth >>>>>>>>>>>>>>>>>>>."+request.getParameter("dob"));
		dto.setRoleid(DataUtility.getLong(request.getParameter("role")));

		return dto;
	}
	

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		  log.debug("UserCtl Method doGet Started");
	        String op = DataUtility.getString(request.getParameter("operation"));
	        // get model
	        UserModelInt model = ModelFactory.getInstance().getUserModel();
	        long  id = DataUtility.getInt(request.getParameter("id"));
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            UserDTO dto;
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
	        log.debug("UserCtl Method doGet Ended");
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UserCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long  id = DataUtility.getInt(request.getParameter("id"));
         
		//System.out.println(">>>><<<<>><<><<><<><>**********" +request.getParameter("id"));
		
		UserModelInt model=ModelFactory.getInstance().getUserModel();
	       if (OP_SAVE.equalsIgnoreCase(op)|| OP_UPDATE.equalsIgnoreCase(op)) {
	            UserDTO dto = (UserDTO) populateDTO(request);

	            try {
	                if (id > 0) {
	     
	                	//System.out.println("hi i am in dopost update");
	                	model.update(dto);
	                    ServletUtility.setDto(dto, request);
                        ServletUtility.setSuccessMessage("User is successfully Updated", request);
	                    ServletUtility.forward(getView(), request, response);
		                       
	                } else {
	                	//System.out.println(">>>>><<<<<)))(((()(()(())");
	                    Long pk = model.add(dto);
	                  //  bean.setId(pk);
	                    ServletUtility.setDto(dto, request);

	                    ServletUtility.setSuccessMessage("User is successfully Added", request);
	                    ServletUtility.forward(getView(), request, response);
	                    dto.setId(pk);
	                }
	               /* ServletUtility.setBean(bean, request);
	                ServletUtility.setSuccessMessage("User is successfully saved",
	                        request);*/
	                
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setDto(dto, request);
	                ServletUtility.setErrorMessage("Login id already exists",
	                        request);
	            }
	             } else if (OP_DELETE.equalsIgnoreCase(op)) {

	            UserDTO dto = (UserDTO) populateDTO(request);
	            try {
	                model.delete(dto);
	                ServletUtility.redirect(ORSView.USER_CTL, request,
	                        response);
	                return;
	            } catch (ApplicationException e) {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }

	        } 
	             else if (OP_RESET.equalsIgnoreCase(op)) {
	            	 ServletUtility.redirect(ORSView.USER_CTL, request, response);
	             }
	             
	             else if (OP_CANCEL.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.USER_CTL, request, response);
	            return;
	        }
//	        ServletUtility.forward(getView(), request, response);

	        log.debug("UserCtl Method doPostEnded");
	}

	/* (non-Javadoc)
	 * @see in.co.rays.ors.controller.BaseCtl#getView()
	 */
	@Override
	protected String getView() {
		return ORSView.USER_VIEW;
	}


}
