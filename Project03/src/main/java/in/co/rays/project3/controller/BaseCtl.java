package in.co.rays.project3.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.project3.dto.BaseDTO;
import in.co.rays.project3.dto.MarksheetDTO;
import in.co.rays.project3.dto.UserDTO;
import in.co.rays.project3.util.DataUtility;
import in.co.rays.project3.util.DataValidator;
import in.co.rays.project3.util.ServletUtility;

/**
 * Base controller class of project. It contain (1) Generic operations (2)
 * Generic constants (3) Generic work flow
 @author Vaishali
 */
public abstract class BaseCtl extends HttpServlet{
    public static final String OP_SAVE = "Save";
    public static final String OP_CANCEL = "Cancel";
    public static final String OP_DELETE = "Delete";
    public static final String OP_LIST = "List";
    public static final String OP_SEARCH = "Search";
    public static final String OP_VIEW = "View";
    public static final String OP_NEXT = "Next";
    public static final String OP_PREVIOUS = "Previous";
    public static final String OP_NEW = "New";
    public static final String OP_GO = "Go";
    public static final String OP_BACK = "Back";
    public static final String OP_LOG_OUT = "Logout";
    public static final String OP_UPDATE = "Update";
    public static final String OP_RESET = "Reset";

    /**
     * Success message key constant
     */
    public static final String MSG_SUCCESS = "success";

    /**
     * Error message key constant
     */
    public static final String MSG_ERROR = "error";

   
    protected boolean validate(HttpServletRequest request) {
        return true;
    }

   
    protected void preload(HttpServletRequest request) {
    }
   
//    protected BaseDTO populateBean( HttpServletRequest request) {
//        return null;
//    }

    
    
    protected BaseDTO populateDTO(HttpServletRequest request) {
        return null;
    }

    
    protected BaseDTO populateBean(BaseDTO dto, HttpServletRequest request) {

        String createdBy = request.getParameter("createdBy");
        String modifiedBy = null;

        UserDTO dto1 = (UserDTO) request.getSession()
                .getAttribute("user");

        if (dto1 == null) {
            // If record is created without login
            createdBy = "root";
            modifiedBy = "root";
        } else {

            modifiedBy = dto1.getLogin();

            // If record is created first time
            if ("null".equalsIgnoreCase(createdBy)
                    || DataValidator.isNull(createdBy)) {
                createdBy = modifiedBy;
            }

        }

        dto1.setCreatedBy(createdBy);
        dto1.setModifiedBy(modifiedBy);

        int cdt = DataUtility.getInt(request.getParameter("createdDatetime"));

        if (cdt > 0) {
            dto1.setCreatedDatetime(DataUtility.getTimeStamp(cdt));
        } else {
            dto1.setCreatedDatetime(DataUtility.getCurrentTimeStamp());
        }

        dto1.setModifiedDatetime(DataUtility.getCurrentTimeStamp());

        return dto1;
    }

    protected void service(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        // Load the preloaded data required to display at HTML form
        preload(request);

        String op = DataUtility.getString(request.getParameter("operation"));

        // Check if operation is not DELETE, VIEW, CANCEL, and NULL then
        // perform input data validation

        if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op)
                && !OP_VIEW.equalsIgnoreCase(op)
                && !OP_DELETE.equalsIgnoreCase(op)
        	   && !OP_RESET.equalsIgnoreCase(op))
        {
            // Check validation, If fail then send back to page with error
            // messages

            if (!validate(request)) {
            	BaseDTO dto = (BaseDTO) populateDTO(request);
                ServletUtility.setDto(dto, request);
                ServletUtility.forward(getView(), request, response);
                return;
            }
        }
        super.service(request, response);
        
    }

    
    protected abstract String getView();

	protected void preload() {
		// TODO Auto-generated method stub
		
	}

}
