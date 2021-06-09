package in.co.rays.project3.model;


    import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.util.JDBCDataSource;
import in.co.rays.project3.dto.CollegeDTO;
import in.co.rays.project3.dto.CourseDTO;
import in.co.rays.project3.dto.FacultyDTO;
import in.co.rays.project3.dto.SubjectDTO;



	/**
	 * JDBC Implementation of Faculty Model
	 * @author Vaishali
	 *
	 */
	public class FacultyModelJdbcImp {
		
		
			
			
			//public static Logger log = Logger.getLogger(FacultyModel.class);

			

			public Integer nextPk() throws ApplicationException {
			//	log.debug("Faculty Model nextPK method Started");
				Connection conn = null;
				int pk = 0;
				try {
					conn = JDBCDataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(id) FROM ST_FACULTY");
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) {
						pk = rs.getInt(1);
					}
					rs.close();
				} catch (Exception e) {
					//log.error("DataBase Exception ..", e);
					throw new ApplicationException("Exception in Getting the PK");
				} finally {
					JDBCDataSource.closeConnection(conn);
				}
				//log.debug("Faculty Model nextPK method End");
				return pk + 1;
			}

			
			public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
			//	log.debug("Model add Started");
				Connection conn = null;
				//System.out.println("gender :- "+bean.getGender());
				int pk = 0;
				//get CollegeName
				CollegeModelInt cModel = ModelFactory.getInstance().getCollegeModel();
		        CollegeDTO collegeDTO = cModel.findByPK(dto.getCollege_id());
		        dto.setCollegeName(collegeDTO.getCollegeName());
		        
		      //  get CourseName
		        CourseModelInt cm =ModelFactory.getInstance().getCourseModel();
		        CourseDTO cd =cm.findByPK(dto.getCourse_id());
		        dto.setCourse_Name(cd.getCourse_Name());
		        
		        //gets subjcetName
		        
		        SubjectModelInt smodel =ModelFactory.getInstance().getSubjectModel();
		        SubjectDTO sd =smodel.findByPK(dto.getSubject_id());
		        dto.setSubject_Name(sd.getSubject_Name());
				
				
//				FacultyBean beanExist = findByEmail(bean.getEmailId());
//				if (beanExist != null) { 
//					  throw new DuplicateRecordException("Email already exists"); 
//					  }
				 

				try {
					conn = JDBCDataSource.getConnection();
					pk = nextPk();
						conn.setAutoCommit(false); // Begin transaction
					PreparedStatement pstmt = conn.prepareStatement("INSERT INTO st_faculty VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					pstmt.setInt(1, pk);
					pstmt.setLong(2, dto.getCollege_id());
					pstmt.setLong(3, dto.getSubject_id());
					pstmt.setLong(4, dto.getCourse_id());
					pstmt.setString(5, dto.getFirst_Name());
					pstmt.setString(6, dto.getLast_Name());
					pstmt.setString(7, dto.getGender());
					pstmt.setDate(8, new java.sql.Date(dto.getDOJ().getTime()));
					pstmt.setString(9, dto.getEmail_id());
					pstmt.setString(10, dto.getMobile_No());
					pstmt.setString(11, dto.getCourse_Name());
					pstmt.setString(12, dto.getCollegeName());
					pstmt.setString(13, dto.getSubject_Name());	
					pstmt.setString(14, dto.getCreatedBy());
					pstmt.setString(15, dto.getModifiedBy());
					pstmt.setTimestamp(16, dto.getCreatedDatetime());
					pstmt.setTimestamp(17, dto.getModifiedDatetime());
					pstmt.executeUpdate();
					conn.commit(); // End transaction
					System.out.println("faculty add close");
					pstmt.close();
				} catch (Exception e) {
					//log.error("Database Exception..", e);
					e.printStackTrace();
					try {
						conn.rollback();
					} catch (Exception ex) {
						ex.printStackTrace();
						throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
					}
					e.printStackTrace();
					throw new ApplicationException("Exception : Exception in add Faculty");
				} finally {
					JDBCDataSource.closeConnection(conn);
				}
			//	log.debug("Model add End");
				return pk;
			}
			
			

			public void delete(FacultyDTO dto) throws ApplicationException {
				//log.debug("Faculty Model Delete method End");
				Connection conn = null;
				try {

					conn = JDBCDataSource.getConnection();
					conn.setAutoCommit(false);
					PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_FACULTY WHERE ID=?");
					pstmt.setLong(1, dto.getId());
					pstmt.executeUpdate();
					conn.commit();
				} catch (Exception e) {
				//	log.error("DATABASE EXCEPTION ", e);
					try {
						conn.rollback();
					} catch (Exception ex) {
						throw new ApplicationException("Exception in Faculty Model rollback" + ex.getMessage());
					}
					throw new ApplicationException("Exception in Faculty Model Delete Method");
				} finally {
					JDBCDataSource.closeConnection(conn);
				}
			//	log.debug("Faculty Model delete method End");
			}	
			
			

			public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
			//	log.debug("Model update Started");
				Connection conn = null;
				CollegeModelInt col = ModelFactory.getInstance().getCollegeModel();
				CollegeDTO coldto = col.findByPK(dto.getCollege_id());
				dto.setCollegeName(coldto.getCollegeName());
				
				
				CourseModelInt cor = ModelFactory.getInstance().getCourseModel();
				CourseDTO coudto = cor.findByPK(dto.getCourse_id());
				dto.setCourse_Name(coudto.getCourse_Name());

//				FacultyBean beanExist = findByEmail(bean.getEmailId());
//				// Check if updated EmailId already exist
//				if (beanExist != null && !(beanExist.getId() == bean.getId())) {
//					throw new DuplicateRecordException("EmailId is already exist");
//				}

				
				try {
					conn = JDBCDataSource.getConnection();
					conn.setAutoCommit(false);
					PreparedStatement pstmt = conn.prepareStatement(
							"UPDATE ST_FACULTY SET COLLEGE_ID=?,SUBJECT_ID=?,COURSE_ID=?, FIRST_NAME=?, LAST_NAME=?, GENDER=?, DOB=?,  EMAIL_ID=?, MOBILE_NO=? , COURSE_NAME=?,  COLLEGE_NAME=?, SUBJECT_NAME=?, CREATED_BY=? , MODIFIED_BY=? , CREATED_DATETIME=? , MODIFIED_DATETIME=? WHERE ID=? ");
					
					pstmt.setLong(1, dto.getCollege_id());
					pstmt.setLong(2, dto.getSubject_id());
					pstmt.setLong(3, dto.getCourse_id());
					pstmt.setString(4, dto.getFirst_Name());
					pstmt.setString(5, dto.getLast_Name());
					pstmt.setString(6, dto.getGender());
					pstmt.setDate(7, new java.sql.Date(dto.getDOJ().getTime()));
					pstmt.setString(8, dto.getEmail_id());
					pstmt.setString(9, dto.getMobile_No());
					pstmt.setString(10, dto.getCourse_Name());
					pstmt.setString(11,dto.getCollegeName());
					pstmt.setString(12, dto.getSubject_Name());
					pstmt.setString(13, dto.getCreatedBy());
					pstmt.setString(14, dto.getModifiedBy());
					pstmt.setTimestamp(15, dto.getCreatedDatetime());
					pstmt.setTimestamp(16, dto.getModifiedDatetime());
					pstmt.setLong(17, dto.getId());

					pstmt.executeUpdate();

					conn.commit();
					pstmt.close();
				} catch (Exception e) {
					e.printStackTrace();
				//	log.error("DATABASE EXCEPTION ...", e);
					try {
						conn.rollback();
					} catch (Exception ex) {
						throw new ApplicationException("Exception in rollback faculty model .." + ex.getMessage());
					}
					throw new ApplicationException("Exception in faculty model Update Method..");
				} finally {
					JDBCDataSource.closeConnection(conn);
				}
			//	log.debug("Faculty Model update method End");
			}
			
					
			public FacultyDTO findByEmail(String EmailId) throws ApplicationException {
				
				System.out.println("faculty add find by name");
			//	log.debug("Faculty Model findByName method Started");
				StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE EMAIL_ID=?");
				Connection conn = null;
				FacultyDTO bean = null;
				
				System.out.println(" faculty  find by name 1");
				try {
					conn = JDBCDataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());
					
					System.out.println("prepared");
					pstmt.setString(1, EmailId);
					System.out.println("resultset"+EmailId);
					ResultSet rs = pstmt.executeQuery();
					System.out.println(" faculty  find by name 1 while");
					while (rs.next()) {
						bean = new FacultyDTO();
						bean.setId(rs.getLong(1));
						bean.setCollege_id(rs.getLong(2));
						bean.setSubject_id(rs.getLong(3));
						bean.setCourse_id(rs.getLong(4));	
						bean.setFirst_Name(rs.getString(5));
						bean.setLast_Name(rs.getString(6));
						bean.setGender(rs.getString(7));
						bean.setDOJ(rs.getDate(8));
						bean.setEmail_id(rs.getString(9));
						bean.setMobile_No(rs.getString(10));
						bean.setCourse_Name(rs.getString(11));
						bean.setCollegeName(rs.getString(12));
						bean.setSubject_Name(rs.getString(13));
						bean.setCreatedBy(rs.getString(14));
						bean.setModifiedBy(rs.getString(15));
						bean.setCreatedDatetime(rs.getTimestamp(16));
						bean.setModifiedDatetime(rs.getTimestamp(17));
			System.out.println(" faculty  find by name 3");
					}
					rs.close();
				} catch (Exception e) {
				//	log.error("database exception ..." , e);
					throw new ApplicationException("Exception : Exception in faculty model in findbyName method");
				} finally {
					JDBCDataSource.closeConnection(conn);
				}
				System.out.println(" faculty  find by name 4");
			//	log.debug("Faculty Model findbyName method End");
				return bean;
			}
			
			
			public FacultyDTO findByPk(long pk) throws ApplicationException {
			//	log.debug("Faculty Model findByPK method Started");
				StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE ID=?");
				Connection conn = null;
				FacultyDTO dto = null;
				try {
					conn = JDBCDataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());
					pstmt.setLong(1, pk);
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) {
						dto = new FacultyDTO();
						dto.setId(rs.getLong(1));
						dto.setCollege_id(rs.getLong(2));
						dto.setSubject_id(rs.getLong(3));
						dto.setCourse_id(rs.getLong(4));
						dto.setFirst_Name(rs.getString(5));
						dto.setLast_Name(rs.getString(6));
						dto.setGender(rs.getString(7));
						dto.setDOJ(rs.getDate(8));
						dto.setEmail_id(rs.getString(9));
						dto.setMobile_No(rs.getString(10));
						dto.setCourse_Name(rs.getString(11));
						dto.setCollegeName(rs.getString(12));
						dto.setSubject_Name(rs.getString(13));
						dto.setCreatedBy(rs.getString(14));
						dto.setModifiedBy(rs.getString(15));
						dto.setCreatedDatetime(rs.getTimestamp(16));
						dto.setModifiedDatetime(rs.getTimestamp(17));
		}
					rs.close();
				} catch (Exception e) {
					e.printStackTrace();
				//	log.error("database exception ..." , e);
					throw new ApplicationException("Exception : Exception in findByPK in faculty model");
				} finally {
					JDBCDataSource.closeConnection(conn);
				}
				//log.debug("Faculty Model FindByPK method end");
				return dto;
			}
			
			
			public List search(FacultyDTO dto) throws ApplicationException{
				return search(dto,0,0);
			}
			
			
			public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException {
			//	log.debug("Faculty Model search  method Started");
				System.out.println("faculty model");
				StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE true");
				if (dto!=null) {
					if (dto.getId()>0) {
						sql.append(" AND id = " + dto.getId());
					}
					if (dto.getCollege_id() > 0) {
						sql.append(" AND college_Id = " + dto.getCollege_id());
					}
					if (dto.getFirst_Name() != null && dto.getFirst_Name().trim().length() > 0) {
						sql.append(" AND FIRST_NAME like '" + dto.getFirst_Name() + "%'");
					}
					if (dto.getLast_Name() != null && dto.getLast_Name().trim().length() > 0) {
						sql.append(" AND LAST_NAME like '" + dto.getLast_Name() + "%'");
					}
					
					if (dto.getEmail_id()!=null && dto.getEmail_id().length()>0) {
						sql.append(" AND Email_Id like '" + dto.getEmail_id() + "%'");
					}
					
					if (dto.getGender()!=null && dto.getGender().length()>0) {
						sql.append(" AND Gender like '" + dto.getGender() + "%'");
					}
			
				
					if (dto.getMobile_No()!=null && dto.getMobile_No().length()>0) {
						sql.append(" AND Mobile_No like '" + dto.getMobile_No() + "%'");
					}
					
					if (dto.getCollegeName()!=null && dto.getCollegeName().length()>0) {
						sql.append(" AND college_Name like '" + dto.getCollegeName() + "%'");
					}
					if (dto.getCourse_id() > 0) {
						sql.append(" AND course_Id = " + dto.getCourse_id());
					}
					if (dto.getCourse_Name()!=null && dto.getCourse_Name().length()>0) {
						sql.append(" AND course_Name like '" + dto.getCourse_Name() + "%'");
					}
					if (dto.getSubject_id() > 0) {
						sql.append(" AND Subject_Id = " + dto.getSubject_id());
					}
					if (dto.getSubject_Name()!=null && dto.getSubject_Name().length()>0) {
						sql.append(" AND subject_Name like '" + dto.getSubject_Name() + "%'");
					}
				}
				
				// if page no is greater then zero then apply pagination 
				System.out.println("model page ........"+pageNo +" "+pageSize);
				if(pageSize>0){
					pageNo = (pageNo-1)*pageSize;
					sql.append(" limit "+pageNo+ " , " + pageSize);
				}
			     System.out.println("final sql  "+sql);
				Connection conn = null;
				ArrayList list = new ArrayList();
				try{
					
					conn = JDBCDataSource.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());
					ResultSet rs = pstmt.executeQuery(); 
					while (rs.next()) {
						
						dto = new FacultyDTO();
						dto.setId(rs.getLong(1));
						dto.setCollege_id(rs.getLong(2));
						dto.setSubject_id(rs.getLong(3));
						dto.setCourse_id(rs.getLong(4));
						dto.setFirst_Name(rs.getString(5));
						dto.setLast_Name(rs.getString(6));
						dto.setGender(rs.getString(7));
						dto.setDOJ(rs.getDate(8));
						dto.setEmail_id(rs.getString(9));
						dto.setMobile_No(rs.getString(10));
						dto.setCourse_Name(rs.getString(11));
						dto.setCollegeName(rs.getString(12));
						dto.setSubject_Name(rs.getString(13));
						dto.setCreatedBy(rs.getString(14));
						dto.setModifiedBy(rs.getString(15));
						dto.setCreatedDatetime(rs.getTimestamp(16));
						dto.setModifiedDatetime(rs.getTimestamp(17));
		System.out.println("out whiile");
						list.add(dto);
						System.out.println("list size ----------->"+list.size());
					}
					rs.close();
					
				}catch(Exception e){
				//	log.error("database Exception .. " , e);
					e.printStackTrace();
			//	throw new ApplicationException("Exception : Exception in Search method of Faculty Model");
				}finally {
					JDBCDataSource.closeConnection(conn);
				}
			//	log.debug("Faculty Model search  method End");
			//	System.out.println("retuen >>>>>>>>>>>>>>>"+list.size());
				return list;
				
			}

			

			public List list() throws ApplicationException{
				return list(0,0);
			}

			
			public List list(int pageNo, int pageSize) throws ApplicationException {
			//	log.debug("Faculty Model List method Started");
				StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY");
				Connection conn = null;
				ArrayList list = new ArrayList();
				
				// if page is greater than zero then apply pagination 
				if (pageSize>0) {
					pageNo = (pageNo-1)*pageSize;
					sql.append(" limit "+ pageNo+ " , " + pageSize);
				}
				try{
						conn = JDBCDataSource.getConnection();
						PreparedStatement pstmt = conn.prepareStatement(sql.toString());
						ResultSet rs = pstmt.executeQuery();
						while (rs.next()) {
							FacultyDTO dto = new FacultyDTO();
							dto.setId(rs.getLong(1));
							dto.setCollege_id(rs.getLong(2));
							dto.setSubject_id(rs.getLong(3));
							dto.setCourse_id(rs.getLong(4));
							dto.setFirst_Name(rs.getString(5));
							dto.setLast_Name(rs.getString(6));
							dto.setGender(rs.getString(7));
							dto.setDOJ(rs.getDate(8));
							dto.setEmail_id(rs.getString(9));
							dto.setMobile_No(rs.getString(10));
							dto.setCourse_Name(rs.getString(11));
							dto.setCollegeName(rs.getString(12));
							dto.setSubject_Name(rs.getString(13));
							dto.setCreatedBy(rs.getString(14));
							dto.setModifiedBy(rs.getString(15));
							dto.setCreatedDatetime(rs.getTimestamp(16));
							dto.setModifiedDatetime(rs.getTimestamp(17));
							list.add(dto);
						}rs.close();
				}catch(Exception e){
				//	log.error("Database Exception ......" , e);
					throw new ApplicationException("Exception in list method of FacultyModel");
				}finally {
				JDBCDataSource.closeConnection(conn);	
				}
			//	log.debug("Faculty Model List method End");
				return list;
			}	

	}
