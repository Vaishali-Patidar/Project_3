package in.co.rays.project3.controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.impl.SessionImpl;

import in.co.rays.project3.dto.UserDTO;
import in.co.rays.project3.util.HibDataSource;
import in.co.rays.project3.util.JDBCDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;


/**
 * The Class JasperCtl.
 * @author Vaishali
 */

//@WebServlet({"/ctl/JasperCtl"})
@WebServlet (name= "JasperCtl" ,urlPatterns={"/ctl/JasperCtl"})
public class JasperCtl extends BaseCtl {
	
	/**
	 * Instantiates a new jasper ctl.
	 */
	public JasperCtl() {
		super();
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("DoGet Jasper Report");
		try {                                                              
			JasperReport JasperRepor = JasperCompileManager.compileReport("C:\\Users\\Dell\\Downloads\\Project03\\Project03\\src\\main\\webapp\\report\\Cherry_Landscape.jrxml");
			
			HttpSession session = request.getSession(true);
			UserDTO dto = (UserDTO) session.getAttribute("user");
			dto.getFirstName();
			dto.getLastName();
			
			Map<String, Object> map = new HashMap();
			map.put("user", dto.getFirstName() + " " + dto.getLastName());
			Connection conn = null;
			
			ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.project3.bundle.System");
			
			String Database = rb.getString("DATABASE");
			if ("Hibernate".equalsIgnoreCase(Database)) {
				conn = ((SessionImpl) HibDataSource.getSession()).connection();
			}

			if ("JDBC".equalsIgnoreCase(Database)) {
				conn = JDBCDataSource.getConnection();
			}

			JasperPrint jasperPrint = JasperFillManager.fillReport(JasperRepor, map, conn);
			byte[] pdf = JasperExportManager.exportReportToPdf(jasperPrint);
			response.setContentType("application/pdf");
			response.getOutputStream().write(pdf);
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see in.co.rays.controller.BaseCtl#getView()
	 */
	protected String getView() {
		return null;
	}
}

/*package in.co.rays.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;

import in.co.rays.util.HibDataSource;
import in.co.rays.util.JDBCDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

*//** 
 * Jasper functionality Controller. Performs operation for Print pdf of MarksheetMeriteList
 *  
 * @author SUNRAYS Technologies 
 * @version 1.0 
 * @Copyright (c) SUNRAYS Technologies 
 *//* 
@WebServlet(name= "JasperCtl" ,urlPatterns={"/ctl/JasperCtl"})
public class JasperCtl extends BaseCtl {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		Connection con = null;
		JasperReport jr = null;
		JasperDesign jd = null;
		try {
			ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.system");
			String database = rb.getString("DATABASE");
			if ("Hibernate".equals(database)) {
				Session session = HibDataSource.getSession();
				SessionFactoryImplementor factoryImplementor = (SessionFactoryImplementor) session.getSessionFactory();
				ConnectionProvider connectionProvider = factoryImplementor.getConnectionProvider();
				try {
					con =  connectionProvider.getConnection();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			if ("JDBC".equals(database)) {
				con =JDBCDataSource.getConnection();
			}
			Map map = new HashMap();
			
			System.out.println(">>>>>>>!!!!!!!!!!!!!!!!....1");
			String path = getServletContext().getRealPath("/WEB-INF/");
			
			System.out.println(">>>>>>>!!!!!!!!!!!!!!!!....2" + path);
			jd = JRXmlLoader.load(path+"/Cherry_1.jrxml");
			
			System.out.println(">>>>>>>!!!!!!!!!!!!!!!!....3");
			jr= JasperCompileManager.compileReport(jd);
			byte[] bytestrem = JasperRunManager.runReportToPdf(jr, map,con);
			OutputStream os = resp.getOutputStream();
			
			System.out.println(">>>>>>>!!!!!!!!!!!!!!!!....4");
			resp.setContentType("application/pdf");
			resp.setContentLength(bytestrem.length);
			os.write(bytestrem, 0, bytestrem.length);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}
	
	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return null;
	}


}

*/

