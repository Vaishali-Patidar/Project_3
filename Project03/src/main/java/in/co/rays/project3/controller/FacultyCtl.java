package in.co.rays.project3.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project3.dto.BaseDTO;
import in.co.rays.project3.dto.FacultyDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DatabaseException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.CollegeModelInt;
import in.co.rays.project3.model.CourseModelInt;
import in.co.rays.project3.model.FacultyModelInt;
import in.co.rays.project3.model.ModelFactory;
import in.co.rays.project3.model.SubjectModelInt;
import in.co.rays.project3.util.DataUtility;
import in.co.rays.project3.util.DataValidator;
import in.co.rays.project3.util.PropertyReader;
import in.co.rays.project3.util.ServletUtility;

/**
 * Faculty functionality Controller. Performs operation for add, update, delete
 * and get Faculty
 * @author Vaishali
 *
 */
@WebServlet(name = "FacultyCtl", urlPatterns = { "/ctl/FacultyCtl" })
public class FacultyCtl extends BaseCtl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The log. */
	private static Logger log = Logger.getLogger(FacultyCtl.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rays.controller.BaseCtl#preload(javax.servlet.http.
	 * HttpServletRequest)
	 */
@Override
	protected void preload(HttpServletRequest request) {
		CollegeModelInt collegeModel = ModelFactory.getInstance().getCollegeModel();
		CourseModelInt courseModel = ModelFactory.getInstance().getCourseModel();
		SubjectModelInt subjectModel = ModelFactory.getInstance().getSubjectModel();
		try {
			List collegeList = collegeModel.list();
			request.setAttribute("collegeList", collegeList);

			List courseList = courseModel.list();
			request.setAttribute("courseList", courseList);

			List subjectList = subjectModel.list();
			request.setAttribute("subjectList", subjectList);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rays.controller.BaseCtl#validate(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("firstName"))) {
			request.setAttribute("firstName", "Please Enter Valid Name");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("lastName"))) {
			request.setAttribute("firstName", "Please Enter Valid Name");

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("gender"))) {
			request.setAttribute("gender", PropertyReader.getValue("error.require", "Gender"));
			pass = false;
		}
		/*
		 * if (DataValidator.isNull(request.getParameter("joiningDob"))) {
		 * request.setAttribute("joiningDob", PropertyReader.getValue("error.require",
		 * "Joining Date Require")); pass = false; } if
		 * (DataValidator.isNull(request.getParameter("qualification"))) {
		 * request.setAttribute("qualification",
		 * PropertyReader.getValue("error.require", "Qualification")); }
		 */
		if (DataValidator.isNull(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login Id"));
			pass = false;
		} else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.email", "Login Id"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "mobileno"));
			pass = false;
		} else if (!DataValidator.isPhoneNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", "Please Enter Valid Mobile Nubmer");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("college"))) {
			request.setAttribute("college", PropertyReader.getValue("error.require", "College"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("course"))) {
			request.setAttribute("course", PropertyReader.getValue("error.require", "Coure Name"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("subject"))) {
			request.setAttribute("subject", PropertyReader.getValue("error.require", "Subject Name"));
			pass = false;
		}
		log.debug("FacultyCtl Method validate Ended");

		System.out.println("validate method end of facultyModel" + pass);
		return pass;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rays.controller.BaseCtl#populateDTO(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected BaseDTO populateDTO(HttpServletRequest request) {
		log.debug("FacultyCtl Method populatebean Started");
		FacultyDTO dto = new FacultyDTO();
		dto.setId(DataUtility.getLong(request.getParameter("id")));
		dto.setFirst_Name(DataUtility.getString(request.getParameter("firstName")));
		dto.setLast_Name(DataUtility.getString(request.getParameter("lastName")));
		dto.setGender(DataUtility.getString(request.getParameter("gender")));
		dto.setDOJ(DataUtility.getDate(request.getParameter("joiningDob")));
		dto.setQualification(DataUtility.getString(request.getParameter("qualification")));
		dto.setMobile_No(DataUtility.getString(request.getParameter("mobileNo")));
		dto.setEmail_id(DataUtility.getString(request.getParameter("login")));
		dto.setCollege_id(DataUtility.getInt(request.getParameter("college")));
		dto.setCourse_id(DataUtility.getInt(request.getParameter("course")));
		dto.setSubject_id(DataUtility.getInt(request.getParameter("subject")));

		log.debug("FacultyCtl Method populatebean Ended");
		return dto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FacultyCtl Method doGet Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (id > 0 || op != null) {
			FacultyDTO dto;
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
		log.debug("FacultyCtl method doGet ended");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FacultyCtl doPost Started");
		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));
		FacultyModelInt model = ModelFactory.getInstance().getFacultyModel();
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			FacultyDTO dto = (FacultyDTO) populateDTO(request);

			try {
				if (id > 0) {
					model.update(dto);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setDto(dto, request);
					
					ServletUtility.forward(getView(), request, response);

				} else {
					long pk = model.add(dto);
					ServletUtility.setSuccessMessage("Data is successfully save", request);
					ServletUtility.setDto(dto, request);
					
					ServletUtility.forward(getView(), request, response);

				}

			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				//ServletUtility.setDto(dto, request);
				//ServletUtility.setErrorMessage("Faculty  already exists", request);
				return;
			}  catch (DuplicateRecordException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ServletUtility.setDto(dto, request);
				ServletUtility.getErrorMessage("Faculty already exists",request);
				ServletUtility.forward(getView(), request, response);
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else
		{ if (OP_DELETE.equalsIgnoreCase(op)) {
			FacultyDTO dto = (FacultyDTO) populateDTO(request);
			try {
				model.delete(dto);
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
				return;
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		}else if(OP_RESET.equalsIgnoreCase(op)){
			
			ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
		}
			}
		
		//ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPostEnded");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.rays.controller.BaseCtl#getView()
	 */
	protected String getView() {
		return ORSView.FACULTY_VIEW;
	}
}
