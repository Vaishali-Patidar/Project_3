package in.co.rays.project3.model;


	
	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.util.JDBCDataSource;
import in.co.rays.project3.dto.CourseDTO;
import in.co.rays.project3.dto.SubjectDTO;



	/**
	 * JDBC Implementation of Subject Model
	 * @author Vaishali
	 *
	 */
	public class SubjectModelJdbcImp {


		//private static Logger log = Logger.getLogger(SubjectModel.class);

		

		public Integer nextPk() throws ApplicationException {
			Connection conn = null;
			int pk = 0;

			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(id) FROM ST_SUBJECT");
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					pk = rs.getInt(1);
				}
				rs.close();
			} catch (Exception e) {
				//log.error("database Exception ...", e);
				throw new ApplicationException("Exception in NextPk of subject Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			return pk + 1;
		}

		

	 public long add(SubjectDTO dto) throws ApplicationException,
	            DuplicateRecordException {
	       // log.debug("Model add Started");
	        Connection conn = null;

	        // get Course Name
	        CourseModelInt  cModel = ModelFactory.getInstance().getCourseModel();
	        CourseDTO cd = cModel.findByPK(dto.getCourse_Id());
	       dto.setCourse_Name(cd.getCourse_Name());  

	     //   SubjectBean duplicateName = findByName(bean.getCourseName());
	        int pk = 0;

		/*
		 * if (duplicateName != null) { throw new
		 * DuplicateRecordException("Subject Name already exists"); }
		 */

	        try {
	            conn = JDBCDataSource.getConnection();
	            pk = nextPk();
	            // Get auto-generated next primary key
	            System.out.println(pk + " in ModelJDBC");
	            
	            conn.setAutoCommit(false); // Begin transaction
	            
	            PreparedStatement pstmt = conn
	                    .prepareStatement("INSERT INTO ST_SUBJECT VALUES(?,?,?,?,?,?,?,?,?)");
	            
	            pstmt.setInt(1, pk);
	            pstmt.setString(2, dto.getSubject_Name());
	            pstmt.setLong(3, dto.getCourse_Id());
	            pstmt.setString(4, dto.getCourse_Name());
	            pstmt.setString(5, dto.getDescription());   
	            pstmt.setString(6, dto.getCreatedBy());
	            pstmt.setString(7, dto.getModifiedBy());
	            pstmt.setTimestamp(8, dto.getCreatedDatetime());
	            pstmt.setTimestamp(9, dto.getModifiedDatetime());
	            pstmt.executeUpdate();
	            conn.commit(); // End transaction
	            pstmt.close();
	        } catch (Exception e) {
	           // log.error("Database Exception..", e);
	            try {
	                conn.rollback();
	            } catch (Exception ex) {
	                throw new ApplicationException(
	                        "Exception : add rollback exception " + ex.getMessage());
	            }
	            throw new ApplicationException(
	                    "Exception : Exception in add Subject");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	       // log.debug("Model add End");
	        return pk;
	    }
		
		
		public void delete(SubjectDTO dto) throws ApplicationException {
		//	log.debug("Subject Model Delete method Started");
			Connection conn = null;
			try {
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_SUBJECT WHERE ID=?");
				pstmt.setLong(1, dto.getId());
				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
			//	log.error("database Exception ...", e);

				try {
					conn.rollback();
				} catch (Exception ex) {
					throw new ApplicationException(
							"Exception in Rollback of Delte Method of Subject Model" + ex.getMessage());
				}
				throw new ApplicationException("Exception in Delte Method of Subject Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("Subject Model Delete method End");
		}

		

		 public void update(SubjectDTO dto) throws ApplicationException,
	     DuplicateRecordException {
	 //log.debug("Model update Started");
	 Connection conn = null;

	// SubjectBean beanExist = findByName(bean.getCourseName());

	 // Check if updated id already exist
//	 if (beanExist != null && beanExist.getId() != bean.getId()) {
//	     throw new DuplicateRecordException("Subject Name is already exist");
//	 }

	 // get Course Name
	 CourseModelInt  cModel = ModelFactory.getInstance().getCourseModel();
     CourseDTO cd = cModel.findByPK(dto.getCourse_Id());
    dto.setCourse_Name(cd.getCourse_Name());  

	 try {

	     conn = JDBCDataSource.getConnection();

	     conn.setAutoCommit(false); // Begin transaction
	     PreparedStatement pstmt = conn
	             .prepareStatement("UPDATE ST_SUBJECT SET Subject_Name=?,Course_ID=?,Course_NAME=?,Description=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
	     pstmt.setString(1, dto.getSubject_Name());
	     pstmt.setLong(2, dto.getCourse_Id());
	     pstmt.setString(3, dto.getCourse_Name());
	     pstmt.setString(4, dto.getDescription()); 
	     pstmt.setString(5, dto.getCreatedBy());
	     pstmt.setString(6, dto.getModifiedBy());
	     pstmt.setTimestamp(7, dto.getCreatedDatetime());
	     pstmt.setTimestamp(8, dto.getModifiedDatetime());
	     pstmt.setLong(9, dto.getId());
	     pstmt.executeUpdate();
	     conn.commit(); // End transaction
	     pstmt.close();
	 } catch (Exception e) {
	   //  log.error("Database Exception..", e);
	     try {
	         conn.rollback();
	     } catch (Exception ex) {
	         throw new ApplicationException(
	                 "Exception : Delete rollback exception "
	                         + ex.getMessage());
	     }
	     throw new ApplicationException("Exception in updating Subject ");
	 } finally {
	     JDBCDataSource.closeConnection(conn);
	 }
	// log.debug("Model update End");
	}

		

		public SubjectDTO findByName(String name) throws ApplicationException {
			//log.debug("Subject Model findByName method Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE SUBJECT_NAME=?");
			Connection conn = null;
			SubjectDTO dto = null;

			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, name);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					dto = new SubjectDTO();
					
					dto.setId(rs.getLong(1));
					dto.setSubject_Name(rs.getString(2));	
					dto.setCourse_Id(rs.getLong(3));
					dto.setCourse_Name(rs.getString(4));
					dto.setDescription(rs.getString(5));
					dto.setCreatedBy(rs.getString(6));
					dto.setModifiedBy(rs.getString(7));
					dto.setCreatedDatetime(rs.getTimestamp(8));
					dto.setModifiedDatetime(rs.getTimestamp(9));
				}
				rs.close();
			} catch (Exception e) {
			//	log.error("database Exception....", e);
				e.printStackTrace();
				//throw new ApplicationException("Exception in findByName Method of Subject Model");
			} finally {
				JDBCDataSource.closeConnection(conn);

			}
		//	log.debug("Subject Model findByName method End");
			return dto;
		}

		
		public SubjectDTO findByPk(long pk) throws ApplicationException {
		//	log.debug("Subject Model findBypk method Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE ID=?");
			Connection conn = null;
			SubjectDTO dto = null;

			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setLong(1, pk);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					dto = new SubjectDTO();
					
					dto.setId(rs.getLong(1));
					dto.setSubject_Name(rs.getString(2));	
					dto.setCourse_Id(rs.getLong(3));
					dto.setCourse_Name(rs.getString(4));
					dto.setDescription(rs.getString(5));
					dto.setCreatedBy(rs.getString(6));
					dto.setModifiedBy(rs.getString(7));
					dto.setCreatedDatetime(rs.getTimestamp(8));
					dto.setModifiedDatetime(rs.getTimestamp(9));
				}
				rs.close();
			} catch (Exception e) {
			//	log.error("database Exception....", e);
				throw new ApplicationException("Exception in findByPk Method of Subject Model");
			} finally {
				JDBCDataSource.closeConnection(conn);

			}
		//	log.debug("Subject Model findByPk method End");
			return dto;
		}
		
		
		
		
		
		public List search(SubjectDTO dto) throws ApplicationException{
			return search(dto,0,0);
		}
		
		


		public List search(SubjectDTO dto, int pageNo, int pageSize) throws ApplicationException {
		//	log.debug("Subject Model search method Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE 1=1 ");
			System.out.println("model search" + dto.getId());
			
			if(dto!= null){
				if(dto.getId() > 0){
					sql.append(" AND id = " + dto.getId()); 
				}
				if(dto.getCourse_Id() > 0){
					sql.append(" AND Course_ID = " + dto.getCourse_Id()); 
				}
			
				if (dto.getSubject_Name() != null && dto.getSubject_Name().length() >0 ) {
					sql.append(" AND Subject_Name like '" +dto.getSubject_Name() + "%'");
				}
				if (dto.getCourse_Name() !=null && dto.getCourse_Name().length() >0 ) {
					sql.append(" AND Course_Name like '" + dto.getCourse_Name() + "%'");
				}
				if (dto.getDescription() !=null && dto.getDescription().length() >0 ) {
					sql.append(" AND description like '" + dto.getDescription() + " % ");
				}
				
				
			}
			
			// Page Size is greater then Zero then aplly pagination 
			if(pageSize>0){
				pageNo = (pageNo-1)*pageSize;
				sql.append(" limit "+pageNo+ " , " + pageSize);
			}
			System.out.println("sql is"+sql);
			Connection conn = null;
			ArrayList list = new ArrayList();
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();
			
				while(rs.next()){
					dto = new SubjectDTO();
				
					dto.setId(rs.getLong(1));
					dto.setSubject_Name(rs.getString(2));
					dto.setCourse_Id(rs.getLong(3));
					dto.setCourse_Name(rs.getString(4));
					dto.setDescription(rs.getString(5));	
					dto.setCreatedBy(rs.getString(6));
					dto.setModifiedBy(rs.getString(7));
					dto.setCreatedDatetime(rs.getTimestamp(8));
					dto.setModifiedDatetime(rs.getTimestamp(9));
					list.add(dto);
				}
				rs.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			//	log.error("database Exception....", e);
				throw new ApplicationException("Exception in search Method of Subject Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("Subject Model search method End");		
			return list;
		}	
		
		
		public List list() throws ApplicationException{
			return list(0,0);
		}

		
		public List list(int pageNo, int pageSize) throws ApplicationException {
		//	log.debug("Subject Model list method Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT ");
			
			// Page Size is greater then Zero then aplly pagination 
			if (pageSize>0) {
				pageNo = (pageNo-1)*pageSize;
				sql.append(" limit "+ pageNo+ " , " + pageSize);
			}
			
			Connection conn = null;
			ArrayList list = new ArrayList();
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()){
					SubjectDTO dto = new SubjectDTO();
					
					dto.setId(rs.getLong(1));
					dto.setSubject_Name(rs.getString(2));
					dto.setCourse_Id(rs.getLong(3));
					dto.setCourse_Name(rs.getString(4));
					dto.setDescription(rs.getString(5));	
					dto.setCreatedBy(rs.getString(6));
					dto.setModifiedBy(rs.getString(7));
					dto.setCreatedDatetime(rs.getTimestamp(8));
					dto.setModifiedDatetime(rs.getTimestamp(9));
					list.add(dto);
				}
				rs.close();
			} catch (Exception e) {
			//	log.error("database Exception....", e);
				throw new ApplicationException("Exception in list Method of Subject Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			//log.debug("Subject Model list method End");		
			return list;
		}
	}
