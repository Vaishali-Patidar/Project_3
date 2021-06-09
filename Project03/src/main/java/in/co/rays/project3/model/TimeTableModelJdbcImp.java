package in.co.rays.project3.model;



	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.util.JDBCDataSource;
import in.co.rays.project3.dto.CourseDTO;
import in.co.rays.project3.dto.SubjectDTO;
import in.co.rays.project3.dto.TimeTableDTO;



	/**
	 * JDBC Implementation of TimeTable Model
	 * @author Vaishali
	 *
	 */
	public class TimeTableModelJdbcImp {
		
	//private static Logger log = Logger.getLogger(TimeTableModel.class);
		
		

		public Integer nextPk() throws ApplicationException {
		//	log.debug("Timetable model nextPk method Started ");
			Connection conn = null;
			int pk = 0;

			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(id) FROM ST_TIMETABLE");
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					pk = rs.getInt(1);
				}
				rs.close();
			} catch (Exception e) {
			//	log.error("database Exception ...", e);
				throw new ApplicationException("Exception in NextPk of TIMETABLE Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("TimeTable model nextpk method end");
			return pk + 1;
		}

		

		public Long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {
			//log.debug("TimeTable model Add method End");
			Connection conn = null;
			long pk = 0;

			CourseDTO cDto = null;
			SubjectDTO sDto = null;
			CourseModelHibImpl cModel = new CourseModelHibImpl();
			SubjectModelHibImpl sModel = new SubjectModelHibImpl();
			try {
	              
				cDto = cModel.findByPK(dto.getCourse_Id());
				sDto = sModel.findByPK(dto.getSubject_Id());
				
				
				dto.setCourse_Name(cDto.getCourse_Name());
				dto.setSubject_Name(sDto.getSubject_Name());
				/*semChecker(cDto, dto);*/
				TimeTableDTO duplicatename = checkBycds(dto.getCourse_Id(), dto.getSemester(), dto.getExam_Date());

				TimeTableDTO duplicatename1 = checkBycss(dto.getCourse_Id(), dto.getSubject_Id(), dto.getSemester());

				TimeTableDTO duplicatename2 = checkBycsd(dto.getCourse_Id(), dto.getSubject_Id(), dto.getExam_Date());
				if (duplicatename1 != null || duplicatename != null || duplicatename2 != null) {
					throw new DuplicateRecordException("Time Table already exist");

				}
		
			try {
				pk = nextPk();
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_TIMETABLE VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
				pstmt.setLong(1, pk);
				pstmt.setLong(2, dto.getCourse_Id());
			//	pstmt.setString(3, courseName);
				pstmt.setLong(4, dto.getSubject_Id());
			//	pstmt.setString(5, subjectName);
				pstmt.setString(6, dto.getSemester());
			    pstmt.setDate(7, new java.sql.Date(dto.getExam_Date().getTime()));	
				pstmt.setString(8, dto.getExam_Time());
				pstmt.setString(9, dto.getDescription());
				
				pstmt.setString(10, dto.getCreatedBy());
				pstmt.setString(11, dto.getModifiedBy());
				pstmt.setTimestamp(12, dto.getCreatedDatetime());
				pstmt.setTimestamp(13, dto.getModifiedDatetime());
				pstmt.executeUpdate();
				

				conn.commit();
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			//	log.error("database Exception ...", e);
				try {
					conn.rollback();
				} catch (Exception ex) {
					throw new ApplicationException("Exception in the Rollback of TIMETABLE Model" + ex.getMessage());
				}
				throw new ApplicationException("Exception in Add method of TIMETABLE Model");
			}} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("TimeTable model Add method End");
			return pk;
			}		
		

		private TimeTableDTO checkBycsd(long course_Id, long subject_Id, Date exam_Date) {
			// TODO Auto-generated method stub
			return null;
		}



		

		public void delete(TimeTableDTO dto) throws ApplicationException {
		//	log.debug("TIMETABLE Model Delete method Started");
			Connection conn = null;
			try {
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
				pstmt.setLong(1, dto.getId());
				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
			//	log.error("database Exception ...", e);

				try {
					conn.rollback();
				} catch (Exception ex) {
					throw new ApplicationException("Exception in Rollback of Delte Method of TIMETABLE Model" + ex.getMessage());
				}
				throw new ApplicationException("Exception in Delte Method of TIMETABLE Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("TIMETABLE Model Delete method End");
		}

		

		public void update(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {
		//	log.debug("TIMETABLE Model update method Started");
			Connection conn = null;
			CourseDTO cDto = null;
			SubjectDTO sDto = null;
			CourseModelHibImpl cModel = new CourseModelHibImpl();
			SubjectModelHibImpl sModel = new SubjectModelHibImpl();
			try {

				cDto = cModel.findByPK(dto.getCourse_Id());
				sDto = sModel.findByPK(dto.getSubject_Id());
				
				dto.setCourse_Name(cDto.getCourse_Name());
				dto.setSubject_Name(sDto.getSubject_Name());
			
			TimeTableDTO bean1 = checkBycds(dto.getCourse_Id(), dto.getSemester(),  new java.sql.Date(dto.getExam_Date().getTime()));
			TimeTableDTO bean2 = checkBycss(dto.getCourse_Id(), dto.getSubject_Id(), dto.getSemester());
			 if(bean1 != null || bean2 != null){ 
				 throw new DuplicateRecordException("TimeTable Already Exsist"); 
				 
			 }
			
			try {
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement(
						"UPDATE ST_TIMETABLE SET COURSE_ID=?,COURSE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,SEMESTER=?,EXAM_DATE=?,EXAM_TIME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
				
				pstmt.setLong(1, dto.getCourse_Id());
				//pstmt.setString(2, courseName);
				pstmt.setLong(3, dto.getSubject_Id());
		//		pstmt.setString(4, subjectName);
				pstmt.setString(5, dto.getSemester());
				pstmt.setDate(6, new java.sql.Date(dto.getExam_Date().getTime()));
				pstmt.setString(7, dto.getExam_Time());
				pstmt.setString(8, dto.getCreatedBy());
				pstmt.setString(9, dto.getModifiedBy());
				pstmt.setTimestamp(10, dto.getCreatedDatetime());
				pstmt.setTimestamp(11, dto.getModifiedDatetime());
				pstmt.setLong(12, dto.getId());
				pstmt.executeUpdate();

				conn.commit();
				pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			//	log.error("database Exception....", e);
				try {
					conn.rollback();
				} catch (Exception ex) {
					throw new ApplicationException(
							"Exception in Rollback of Update Method of TimeTable Model" + ex.getMessage());
				}
				throw new ApplicationException("Exception in update Method of TimeTable Model");
			}
			}finally {
				JDBCDataSource.closeConnection(conn);
			
			}
		//	log.debug("TimeTable Model Update method End");
			}
		

		public TimeTableDTO findByName(String name) throws ApplicationException {
		//	log.debug("TimeTable Model findByName method Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE SubjectName = ?");
			Connection conn = null;
			TimeTableDTO dto = null;

			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, name);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					dto = new TimeTableDTO();

					dto.setId(rs.getLong(1));
					dto.setCourse_Id(rs.getLong(2));
					dto.setCourse_Name(rs.getString(3));
					dto.setSubject_Id(rs.getInt(4));
					dto.setSubject_Name(rs.getString(5));
					dto.setSemester(rs.getString(6));
					dto.setExam_Date(rs.getDate(7));
					dto.setExam_Time(rs.getString(8));
					dto.setCreatedBy(rs.getString(9));
					dto.setModifiedBy(rs.getString(10));
					dto.setCreatedDatetime(rs.getTimestamp(11));
					dto.setModifiedDatetime(rs.getTimestamp(12));
				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			//	log.error("database Exception....", e);
				throw new ApplicationException("Exception in findByName Method of TimeTable Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("TimeTable Model findByName method End");
			return dto;
		}

		
		public TimeTableDTO findByPk(long pk) throws ApplicationException {
		//	log.debug("TimeTable Model findBypk method Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE ID=?");
			Connection conn = null;
			TimeTableDTO dto = null;

			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setLong(1, pk);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					dto = new TimeTableDTO();

					dto.setId(rs.getLong(1));
					dto.setCourse_Id(rs.getLong(2));
					dto.setCourse_Name(rs.getString(3));
					dto.setSubject_Id(rs.getInt(4));
					dto.setSubject_Name(rs.getString(5));
					dto.setSemester(rs.getString(6));
					dto.setExam_Date(rs.getDate(7));
					dto.setExam_Time(rs.getString(8));
					dto.setCreatedBy(rs.getString(9));
					dto.setModifiedBy(rs.getString(10));
					dto.setCreatedDatetime(rs.getTimestamp(11));
					dto.setModifiedDatetime(rs.getTimestamp(12));
				}
				rs.close();
			} catch (Exception e) {
			//	log.error("database Exception....", e);
				throw new ApplicationException("Exception in findByPk Method of TimeTable Model");
			} finally {
				JDBCDataSource.closeConnection(conn);

			}
		//	log.debug("TimeTable Model findByPk method End");
			return dto;
		}

		

		public List search(TimeTableDTO dto) throws ApplicationException {
			return search(dto, 0, 0);
		}

		

		public List search(TimeTableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		//	log.debug("TimeTable Model search method Started");

			Connection conn = null;
			ArrayList list = new ArrayList();
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1");
					
			if (dto != null) {
				if (dto.getId() > 0) {
					sql.append(" AND id = " + dto.getId());
				}
				
				if(dto.getCourse_Id() > 0){
					sql.append(" AND Course_ID = " + dto.getCourse_Id()); 
				}
				if(dto.getSubject_Id() > 0){
					sql.append(" AND Subject_ID = " + dto.getSubject_Id()); 
				}
				if (dto.getExam_Date() !=null && dto.getExam_Date().getTime() >0 ){
					
//					System.out.println("===============...>>>>"+bean.getExamDate());
					Date d = new Date(dto.getExam_Date().getTime());
					sql.append(" AND Exam_Date = '" + d + "'");
					System.out.println("sql statement ==="+d);
				}
				
				if (dto.getCourse_Name() !=null && dto.getCourse_Name().length() >0 ) {
					sql.append(" AND Course_Name like '" + dto.getCourse_Name() + "%'");
				}
				
				if (dto.getSubject_Name() !=null && dto.getSubject_Name().length() >0 ) {
					sql.append(" AND Subject_Name like '" + dto.getSubject_Name() + "%'");
				}
				
			}

			// Page Size is greater then Zero then apply pagination
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + " , " + pageSize);
			}

			System.out.println("sql queryy "+sql);

			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					dto = new TimeTableDTO();

					dto.setId(rs.getLong(1));
					dto.setCourse_Id(rs.getLong(2));
					dto.setCourse_Name(rs.getString(3));
					dto.setSubject_Id(rs.getInt(4));
					dto.setSubject_Name(rs.getString(5));
					dto.setSemester(rs.getString(6));
					dto.setExam_Date(rs.getDate(7));
					dto.setExam_Time(rs.getString(8));
					dto.setCreatedBy(rs.getString(9));
					dto.setModifiedBy(rs.getString(10));
					dto.setCreatedDatetime(rs.getTimestamp(11));
					dto.setModifiedDatetime(rs.getTimestamp(12));
					list.add(dto);
				}
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
				//log.error("database Exception....", e);
				throw new ApplicationException("Exception in search Method of TimeTable Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("TimeTable Model search method End");
			return list;
		}

		
		public List list() throws ApplicationException {
			return list(0, 0);
		}

		
		public List list(int pageNo, int pageSize) throws ApplicationException {
		//	log.debug("TimeTable Model list method Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE ");

			// Page Size is greater then Zero then aplly pagination
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				sql.append(" limit " + pageNo + " , " + pageSize);
			}

			System.out.println("------->>>>>>>>>>---"+sql);
			Connection conn = null;
			ArrayList list = new ArrayList();
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {

					TimeTableDTO dto = new TimeTableDTO();

					dto.setId(rs.getLong(1));
					dto.setCourse_Id(rs.getLong(2));
					dto.setCourse_Name(rs.getString(3));
					dto.setSubject_Id(rs.getInt(4));
					dto.setSubject_Name(rs.getString(5));
					dto.setSemester(rs.getString(6));
					dto.setExam_Date(rs.getDate(7));
					dto.setExam_Time(rs.getString(8));
					dto.setCreatedBy(rs.getString(9));
					dto.setModifiedBy(rs.getString(10));
					dto.setCreatedDatetime(rs.getTimestamp(11));
					dto.setModifiedDatetime(rs.getTimestamp(12));
					list.add(dto);
				}
				
				
				
				
				
				rs.close();
			} catch (Exception e) {
				//log.error("database Exception....", e);
				throw new ApplicationException("Exception in list Method of timetable Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			//log.debug("Timetable Model list method End");
			return list;
		}
		
	    
	    public  TimeTableDTO checkBycss(long CourseId , long SubjectId , String semester) throws ApplicationException
	    {
	    	System.out.println("in from css.........................<<<<<<<<<<<>>>> ");
	    	Connection conn = null ; 
	    	TimeTableDTO dto=null;
	  //  	java.util.Date ExamDAte,String ExamTime
	    	StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE Course_ID=? AND Subject_ID=? AND Semester=?");
	    	
	    	try {
				Connection con = JDBCDataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql.toString());
				ps.setLong(1, CourseId);
				ps.setLong(2, SubjectId);
				ps.setString(3, semester);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					dto = new TimeTableDTO();
					dto.setId(rs.getLong(1));
					dto.setCourse_Id(rs.getLong(2));
					dto.setCourse_Name(rs.getString(3));
					dto.setSubject_Id(rs.getInt(4));
					dto.setSubject_Name(rs.getString(5));
					dto.setSemester(rs.getString(6));
					dto.setExam_Date(rs.getDate(7));
					dto.setExam_Time(rs.getString(8));
					dto.setCreatedBy(rs.getString(9));
					dto.setModifiedBy(rs.getString(10));
					dto.setCreatedDatetime(rs.getTimestamp(11));
					dto.setModifiedDatetime(rs.getTimestamp(12));
				}rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			//	log.error("database Exception....", e);
				throw new ApplicationException("Exception in list Method of timetable Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("Timetable Model list method End");
			System.out.println("out from css.........................<<<<<<<<<<<>>>> ");
			return dto;
	    }

	    
	    
	       
	        public TimeTableDTO checkBycds(long CourseId, String Semester ,Date ExamDate) throws ApplicationException
	    {
	        	System.out.println("in from cds.........................<<<<<<<<<<<>>>> ");
	        	StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE Course_ID=? AND Semester=? AND Exam_Date=?");
	        	
	        	Connection conn = null;
	    	TimeTableDTO dto=null;
//	    	Date ExDate = new Date(ExamDAte.getTime());
	    	
	    	
	    	try {
				Connection con = JDBCDataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql.toString());
				ps.setLong(1, CourseId);
				ps.setString(2, Semester);
				ps.setDate(3, (java.sql.Date)ExamDate);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
                    dto = new TimeTableDTO();
					dto.setId(rs.getLong(1));
					dto.setCourse_Id(rs.getLong(2));
					dto.setCourse_Name(rs.getString(3));
					dto.setSubject_Id(rs.getInt(4));
					dto.setSubject_Name(rs.getString(5));
					dto.setSemester(rs.getString(6));
					dto.setExam_Date(rs.getDate(7));
					dto.setExam_Time(rs.getString(8));
					dto.setCreatedBy(rs.getString(9));
					dto.setModifiedBy(rs.getString(10));
					dto.setCreatedDatetime(rs.getTimestamp(11));
					dto.setModifiedDatetime(rs.getTimestamp(12));
				}
				rs.close();
			}catch (Exception e) {
				e.printStackTrace();
			//	log.error("database Exception....", e);
				throw new ApplicationException("Exception in list Method of timetable Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("Timetable Model list method End");
		System.out.println("out from cds.........................<<<<<<<<<<<>>>> ");
			return dto;
	    }
	    
	   
	   public static TimeTableDTO checkBysemester(long CourseId,long SubjectId,String semester,java.util.Date ExamDAte)
	    {
	    	
	    	TimeTableDTO dto=null;
	    	
	    	Date ExDate = new Date(ExamDAte.getTime());
	    	
	    	StringBuffer sql = new StringBuffer("SELECT * FROM TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND"
	    			+ " SEMESTER=?");
	    	
	    	try {
				Connection con = JDBCDataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql.toString());
				ps.setLong(1, CourseId);
				ps.setLong(2, SubjectId);
				ps.setString(3, semester);
				//ps.setDate(4, examDate);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					dto = new TimeTableDTO();
					dto.setId(rs.getLong(1));
					dto.setCourse_Id(rs.getLong(2));
					dto.setCourse_Name(rs.getString(3));
					dto.setSubject_Id(rs.getInt(4));
					dto.setSubject_Name(rs.getString(5));
					dto.setSemester(rs.getString(6));
					dto.setExam_Date(rs.getDate(7));
					dto.setExam_Time(rs.getString(8));
					dto.setCreatedBy(rs.getString(9));
					dto.setModifiedBy(rs.getString(10));
					dto.setCreatedDatetime(rs.getTimestamp(11));
					dto.setModifiedDatetime(rs.getTimestamp(12));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dto;
	    }
	    
	    
	    public static TimeTableDTO checkByCourseName(long CourseId,java.util.Date ExamDate)
	    {
	    	Connection conn = null;
	    	TimeTableDTO dto=null;
	    	
	    	Date Exdate = new Date(ExamDate.getTime());
	    	
	    	StringBuffer sql = new StringBuffer("SELECT * FROM TIMETABLE WHERE COURSE_ID=? "
	    			+ "AND EXAM_DATE=?");
	    	
	    	try {
				Connection con = JDBCDataSource.getConnection();
				PreparedStatement ps = con.prepareStatement(sql.toString());
				ps.setLong(1, CourseId);
				//ps.setDate(2, examdate);
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					dto = new TimeTableDTO();
					dto.setId(rs.getLong(1));
					dto.setCourse_Id(rs.getLong(2));
					dto.setCourse_Name(rs.getString(3));
					dto.setSubject_Id(rs.getInt(4));
					dto.setSubject_Name(rs.getString(5));
					dto.setSemester(rs.getString(6));
					dto.setExam_Date(rs.getDate(7));
					dto.setExam_Time(rs.getString(8));
					dto.setCreatedBy(rs.getString(9));
					dto.setModifiedBy(rs.getString(10));
					dto.setCreatedDatetime(rs.getTimestamp(11));
					dto.setModifiedDatetime(rs.getTimestamp(12));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return dto;
	    }

	}

