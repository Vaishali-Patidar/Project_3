package in.co.rays.project3.controller;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.project3.util.ServletUtility;

// TODO: Auto-generated Javadoc
/**
 * Welcome functionality Controller. Performs operation for Show Welcome page
 *  
 * @author Vaishali
 */
 
@WebServlet(name="WelcomeCtl",urlPatterns={"/WelcomeCtl"})
public class WelcomeCtl extends BaseCtl {
 
    /** The log. */
    private static Logger log = Logger.getLogger(WelcomeCtl.class);
 
   
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        log.debug("WelcomeCtl Method doGet Started");
 
        ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
 
        log.debug("WelcomeCtl Method doGet Ended");
    }
 
    /* (non-Javadoc)
     * @see in.co.rays.controller.BaseCtl#getView()
     */
    @Override
    protected String getView() {
        return ORSView.WELCOME_VIEW;
    }
 
}
