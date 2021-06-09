package in.co.rays.project3.model;



	import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DatabaseException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.util.JDBCDataSource;
import in.co.rays.project3.dto.CourseDTO;



	/**
	 * JDBC Implementation of Course Model
	 * @author Vaishali
	 *
	 */
	public class CourseModelJdbcImp {

		
		//private static Logger log = Logger.getLogger(CourseModel.class);

		

		public Integer nextPk() throws DatabaseException {
		//	log.debug("CourseModel nextPK method started");

			Connection conn = null;
			int pk = 0;
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_COURSE");
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					pk = rs.getInt(1);
				}
				rs.close();
			} catch (Exception e) {
				//log.error("Exception in Database..", e);
				throw new DatabaseException("Exception : Exception in getting Pk");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
			//log.debug("CourseModel nextPK method END");
			return pk + 1;
		}

		public long add(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
			//log.debug("CourseModel Add method END");
			Connection conn = null;
			int pk = 0;
			
			//CourseDTO duplicateCourseName = findByName(dto.getCourse_Name());
			//if(duplicateCourseName!=null){
				//throw new DuplicateRecordException("Course Name already Exist");
		//	}
			try {
				conn = JDBCDataSource.getConnection();
				pk = nextPk();
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_COURSE VALUES(?,?,?,?,?,?,?,?)");
				pstmt.setInt(1, pk);
				pstmt.setString(2, dto.getCourse_Name());
				pstmt.setString(3, dto.getDuration());
				pstmt.setString(4, dto.getDiscription());
				
				pstmt.setString(5, dto.getCreatedBy());
				pstmt.setString(6, dto.getModifiedBy());
				pstmt.setTimestamp(7, dto.getCreatedDatetime());
				pstmt.setTimestamp(8, dto.getModifiedDatetime());
				pstmt.executeUpdate();

				conn.commit();
				pstmt.close();
				conn.close();
			} catch (Exception e) {
			//	log.debug("EXception in Database...", e);
				try {
					conn.rollback();
				} catch (Exception ex) {
					throw new ApplicationException("Exception : add Rollback Exception.." + ex.getMessage());
				}
				throw new ApplicationException("Exception in Course Add method");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("CourseModel Add method END");
			return pk;
		}
		
		
		public void delete(CourseDTO dto) throws ApplicationException {
		//	log.debug("CourseModel Delete Method Started");
			Connection conn = null;
			try {
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_COURSE WHERE ID=?");
				pstmt.setLong(1, dto.getId());
				pstmt.executeUpdate();
				conn.commit();
				pstmt.close();
			} catch (Exception e) {
			//	log.error("Database Exception...", e);
				try {
					conn.rollback();
				} catch (Exception ex) {
					throw new ApplicationException("Exception : Exception in Rollback Method" + ex.getMessage());
				}
				throw new ApplicationException("Exception in Delete Method");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("CourseModel Delete Method End");
		}
		
		

		public void update(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
			Connection conn = null;
			
//			CourseDTO beanExist = findByName(dto.getCourse_Name());
//			if(beanExist !=null && beanExist.getId()!=dto.getId()){
//				throw new DuplicateRecordException("Course Already Exist");
//			}
			try {
				conn = JDBCDataSource.getConnection();
				conn.setAutoCommit(false);
				PreparedStatement pstmt = conn.prepareStatement(
						"UPDATE ST_COURSE SET NAME=?,DURATION=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
				pstmt.setString(1, dto.getCourse_Name());
				pstmt.setString(2, dto.getDuration());
				pstmt.setString(3, dto.getDiscription());
				pstmt.setString(4, dto.getCreatedBy());
				pstmt.setString(5, dto.getModifiedBy());
				pstmt.setTimestamp(6, dto.getCreatedDatetime());
				pstmt.setTimestamp(7, dto.getModifiedDatetime());
				pstmt.setLong(8, dto.getId());
				pstmt.executeUpdate();
				conn.commit();
				pstmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			//	log.debug("Database Exception ...", e);
				try {
					conn.rollback();
				} catch (Exception ex) {
					ex.printStackTrace();
					throw new ApplicationException("Exception : Exception in Rollback.." + ex.getMessage());
				}
				throw new ApplicationException("Exception in Updating the Course Model");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		}
		
		

		public CourseDTO findByName(String name) throws ApplicationException {
		//	log.debug("Course Model FindByName method Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE NAME=?");
			CourseDTO dto = null;
			Connection conn = null;
			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setString(1, name);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					dto = new CourseDTO();
					dto.setId(rs.getLong(1));
					dto.setCourse_Name(rs.getString(2));
					dto.setDuration(rs.getString(3));
					dto.setDiscription(rs.getString(4));
					dto.setCreatedBy(rs.getString(5));
					dto.setModifiedBy(rs.getString(6));
					dto.setCreatedDatetime(rs.getTimestamp(7));
					dto.setModifiedDatetime(rs.getTimestamp(8));
				}
				rs.close();
			} catch (Exception e) {
			//	log.debug("Database Exception ..", e);
				throw new ApplicationException("Exception in Course Model FindByName Method ");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("Course Model FindByName method End");
			return dto;
		}
		
		

		public CourseDTO findByPk(Long pk) throws ApplicationException {
		//	log.debug("CourseModel FindbyPK method Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE ID=?");
			CourseDTO dto = null;
			Connection conn = null;

			try {
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				pstmt.setLong(1, pk);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					dto = new CourseDTO();
					dto.setId(rs.getLong(1));
					dto.setCourse_Name(rs.getString(2));
					dto.setDuration(rs.getString(3));
					dto.setDiscription(rs.getString(4));
					dto.setCreatedBy(rs.getString(5));
					dto.setModifiedBy(rs.getString(6));
					dto.setCreatedDatetime(rs.getTimestamp(7));
					dto.setModifiedDatetime(rs.getTimestamp(8));
				}
				rs.close();
			}catch(Exception e) {
			//	log.error("DatabaseException ... ", e);
				throw new ApplicationException("Exception : Exception in the findbyPk method");
			} finally {
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("CourseModel FindbyPK method End");
			return dto;
		}

		

		public List search (CourseDTO dto) throws ApplicationException{
		return search (dto,0,0);	
		}

		
		public List search(CourseDTO dto, int pageNo, int pageSize) throws ApplicationException {
			
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>. search k ander");
//			log.debug("CourseModel Search Method Started");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE 1=1");
			if(dto!=null){
				if(dto.getId()>0){
					sql.append(" AND id = " + dto.getId());
				}
				/*if(bean.getName()!= null && bean.getName().length()>0){
					sql.append(" AND Name like '" + bean.getName() +"%'");
				}*/
	/*			if(bean.getDescription()!=null && bean.getName().length()>0){
					sql.append(" AND Description like '" + bean.getDescription() + "%'");
				}
				if(bean.getDuration()!=null && bean.getDuration().length() >0){
					sql.append(" AND Duration like '" + bean.getDuration().length() + "%'");
				}*/
			}
			
			// if page size is greater than zero then apply pagination
			if(pageSize>0){
				pageNo = (pageNo-1)*pageSize;
				sql.append(" limit " +pageNo+","+pageSize);	
			}
			ArrayList list = new ArrayList();
			Connection conn = null;
			try{
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				System.out.println(sql);
				ResultSet rs =pstmt.executeQuery();
				while(rs.next()){
					dto=new CourseDTO();
					dto.setId(rs.getLong(1));
					dto.setCourse_Name(rs.getString(2));
					dto.setDuration(rs.getString(3));	
					dto.setDiscription(rs.getString(4));
					dto.setCreatedBy(rs.getString(5));
					dto.setModifiedBy(rs.getString(6));
					dto.setCreatedDatetime(rs.getTimestamp(7));
					dto.setModifiedDatetime(rs.getTimestamp(8));
					list.add(dto);
					
				}
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			//	log.error("Database Exception ,,,," , e);
				throw new ApplicationException("Exception in the Search Method" + e.getMessage());
			
			}finally{
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("CourseModel Search Method End");
			System.out.println("----------------------------------->>>>"+list.size());
			return list;
		}

		
		
		public List list () throws ApplicationException{
			return list(0,0);
		}
		
		
		
		public List list(int pageNo, int pageSize) throws ApplicationException {
		//	log.debug("CourseModel List Method End");
			StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE ");
		// if page size is greater than zero then apply pagination
			if(pageSize>0){
				pageNo = (pageNo-1)*pageSize;
				sql.append(" limit "+ pageNo +" , "+pageSize);
			}
			
			ArrayList list = new ArrayList();
			Connection conn = null;
			
			try{
				conn = JDBCDataSource.getConnection();
				PreparedStatement pstmt =conn.prepareStatement(sql.toString());
				ResultSet rs =pstmt.executeQuery();
				while (rs.next()) {
					CourseDTO dto = new CourseDTO();
					dto.setId(rs.getLong(1));
					dto.setCourse_Name(rs.getString(2));	
					dto.setDuration(rs.getString(3));
					dto.setDiscription(rs.getString(4));
					dto.setCreatedBy(rs.getString(5));
					dto.setModifiedBy(rs.getString(6));
					dto.setCreatedDatetime(rs.getTimestamp(7));
					dto.setModifiedDatetime(rs.getTimestamp(8));
					list.add(dto);
				}
				rs.close();
			}catch(Exception e){
			//	log.error("Database Exception in list ...",e);
				throw new ApplicationException("Exception : Exception in CourseModel List method " + e.getMessage());		
			}finally{
				JDBCDataSource.closeConnection(conn);
			}
		//	log.debug("CourseModel List Method End");
			return list;
		}

	}

