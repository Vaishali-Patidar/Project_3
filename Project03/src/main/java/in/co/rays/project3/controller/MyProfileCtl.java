package in.co.rays.project3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.project3.dto.BaseDTO;
import in.co.rays.project3.dto.UserDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.UserModelHibImp;
import in.co.rays.project3.util.DataUtility;
import in.co.rays.project3.util.DataValidator;
import in.co.rays.project3.util.PropertyReader;
import in.co.rays.project3.util.ServletUtility;


/**
 * My Profile functionality Controller. Performs operation for update your
 * Profile
 * @author Vaishali
 *
 */
@WebServlet(name="MyProfileCtl",urlPatterns={"/ctl/MyProfileCtl"})
public class MyProfileCtl extends BaseCtl {
	public static final String OP_CHANGE_MY_PASSWORD = "ChangePassword";

	  //  private static Logger log = Logger.getLogger(MyProfileCtl.class);

	    @Override
	    protected boolean validate(HttpServletRequest request) {

	    //    log.debug("MyProfileCtl Method validate Started");


	        boolean pass = true;

	        String op = DataUtility.getString(request.getParameter("operation"));

	        if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op) || op == null) {

	            return pass;
	        }

	        if (DataValidator.isNull(request.getParameter("firstName"))) {
	            System.out.println("firstName" + request.getParameter("firstName"));
	            request.setAttribute("firstName",
	                    PropertyReader.getValue("error.require", "First Name"));
	            pass = false;
	        }

	        if (DataValidator.isNull(request.getParameter("lastName"))) {
	            request.setAttribute("lastName",
	                    PropertyReader.getValue("error.require", "Last Name"));
	            pass = false;
	        }

	        if (DataValidator.isNull(request.getParameter("gender"))) {
	            request.setAttribute("gender",
	                    PropertyReader.getValue("error.require", "Gender"));
	            pass = false;
	        }
	        if (DataValidator.isNull(request.getParameter("mobileNumber"))) {
	            request.setAttribute("mobileNumber",
	                    PropertyReader.getValue("error.require", "mobileNumber"));
	            pass = false;
	        }

//	        if (DataValidator.isNull(request.getParameter("dob"))) {
//	            request.setAttribute("dob",
//	                    PropertyReader.getValue("error.require", "dob"));
//	            pass = false;
//	        }

	      //  log.debug("MyProfileCtl Method validate Ended");

	        return pass;

	}
	 
	 /* (non-Javadoc)
 	 * @see in.co.rays.controller.BaseCtl#populateDTO(javax.servlet.http.HttpServletRequest)
 	 */
 		
	 
	 /* (non-Javadoc)
 	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
 	 */
	    @Override
		protected BaseDTO populateDTO(HttpServletRequest request) {

			 //   log.debug("MyProfileCtl Method populatebean Started");

		        UserDTO dto = new UserDTO();

		        dto.setId(DataUtility.getLong(request.getParameter("id")));

		        dto.setLogin(DataUtility.getString(request.getParameter("email")));

		        dto.setFirstName(DataUtility.getString(request
		                .getParameter("firstName")));

		        dto.setLastName(DataUtility.getString(request.getParameter("lastName")));

		        dto.setMobileNo(DataUtility.getString(request.getParameter("mobileNumber")));
		        System.out.println("mobileno>>>>>>>>>>>>>>>"+request.getParameter("mobileNumber"));

		        dto.setGender(DataUtility.getString(request.getParameter("gender")));

		      //  dto.setDob(DataUtility.getDate(request.getParameter("dob")));
		       // System.out.println("dob>>>>>>>>>>"+request.getParameter("dob"));


		        return dto;

		 }

		
	    /**
	     * Display Concept for viewing profile page view
	     */
	    protected void doGet(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession(true);
	      //  log.debug("MyprofileCtl Method doGet Started");

	        UserDTO UserBean = (UserDTO) session.getAttribute("user");
	        System.out.println(UserBean.getId());
	       long id = UserBean.getId();
	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model
	        UserModelHibImp model = new UserModelHibImp();
	        if (id > 0 || op != null) {
	            System.out.println("in id > 0  condition");
	            UserDTO dto;
	            try {
	                dto = model.findByPK(id);
	                ServletUtility.setDto(dto, request);
	            } catch (ApplicationException e) {
	        //        log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }
	        ServletUtility.forward(getView(), request, response);

	       // log.debug("MyProfileCtl Method doGet Ended");
	    }

	    /**
	     * Submit Concept
	     */
	    protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession(true);
	      //  log.debug("MyprofileCtl Method doPost Started");

	        UserDTO UserBean = (UserDTO) session.getAttribute("user");
	        long id = UserBean.getId();
	        String op = DataUtility.getString(request.getParameter("operation"));

	        // get model
	        UserModelHibImp model = new UserModelHibImp();

	        if (OP_SAVE.equalsIgnoreCase(op)) {
	        	UserDTO bean = (UserDTO) populateDTO(request);
	            try {
	                if (id > 0) {
	                    UserBean.setFirstName(bean.getFirstName());
	                    UserBean.setLastName(bean.getLastName());
	                    UserBean.setMobileNo(bean.getMobileNo());
	                    UserBean.setGender(bean.getGender());
	                   // UserBean.setDob(bean.getDob());
	                    model.update(UserBean);

	                }
	                ServletUtility.setDto(UserBean, request);
	                ServletUtility.setSuccessMessage(
	                        "Profile has been updated Successfully. ", request);
	            } catch (ApplicationException e) {
	        //        log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } catch (DuplicateRecordException e) {
	                ServletUtility.setDto(UserBean, request);
	                ServletUtility.setErrorMessage("Login id already exists",
	                        request);
	            }
	        } else if (OP_CHANGE_MY_PASSWORD.equalsIgnoreCase(op)) {

	            ServletUtility.redirect(ORSView.CHANGE_PASSWORD_CTL, request,
	                    response);
	            return;

	        }

	        ServletUtility.forward(getView(), request, response);

	      //  log.debug("MyProfileCtl Method doPost Ended");
	    }

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.MY_PROFILE_VIEW;
	}

   
}
