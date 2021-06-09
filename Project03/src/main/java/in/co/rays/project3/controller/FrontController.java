package in.co.rays.project3.controller;


import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.co.rays.project3.util.ServletUtility;


/**
 * Main Controller performs session checking and logging operations before
 * calling any application controller. It prevents any user to access
 * application without login.
 * @author Vaishali
 *
 */
@WebFilter(urlPatterns={"/ctl/*","/doc/*"})
public class FrontController implements Filter {
	 


 /* (non-Javadoc)
  * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
  */
 public void doFilter(ServletRequest req, ServletResponse resp,
         FilterChain chain) throws IOException, ServletException {

	 System.out.println("in frontctl");
     HttpServletRequest request = (HttpServletRequest) req;
     HttpServletResponse response = (HttpServletResponse) resp;

     HttpSession session = request.getSession(true);
     String uri = request.getRequestURI();
		request.setAttribute("uri", uri);
		
		System.out.println(uri);
     if (session.getAttribute("user") == null) {
    	 System.out.println("in frontctl 2>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
         request.setAttribute("error",
                 "Your session has been expired. Please re-Login.");
         ServletUtility.forward(ORSView.LOGIN_VIEW, request, response);
         return;
     } else {
    	 System.out.println(uri);
         chain.doFilter(req, resp);
         
     }
 }

	/* (non-Javadoc)
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig conf) throws ServletException {
	    }

 /* (non-Javadoc)
  * @see javax.servlet.Filter#destroy()
  */
 public void destroy() {
		
	}

}