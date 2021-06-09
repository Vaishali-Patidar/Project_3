package in.co.rays.project3.model;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project3.dto.CourseDTO;
import in.co.rays.project3.dto.SubjectDTO;
import in.co.rays.project3.dto.TimeTableDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.util.HibDataSource;

/**
 * Hibernate Implementation of TimeTable Model

 * @author Vaishali
 *
 */
public  class TimeTableModelHibImpl implements TimeTableModelInt {

	
	
	/** The log. */
	private static Logger log = Logger.getLogger(TimeTableModelHibImpl.class);
	
	
	
	
	public TimeTableDTO timeTableDuplicacy(long courseId, String semester, Date examDate) throws ApplicationException {
		Session session = null;
		TimeTableDTO dto = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria cr = session.createCriteria(TimeTableDTO.class);
			cr.add(Restrictions.eq("Course_Id", courseId));
			cr.add(Restrictions.eq("Semester", semester));
			cr.add(Restrictions.eq("Exam_Date", examDate));
			list = cr.list();
			Iterator itr = list.iterator();
			while (itr.hasNext()) {
				dto = (TimeTableDTO) itr.next();
			}
            System.out.println(dto);
		} catch (HibernateException hb) {
			hb.printStackTrace();
			throw new ApplicationException("Exception in TimeTableDuplicacy of Timetable Model " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#timeTableDuplicacy(long, long, java.util.Date)
	 */
	public TimeTableDTO timeTableDuplicacy(long courseId, long subjectId, Date examDate) throws ApplicationException {
		Session session = null;
		TimeTableDTO dto = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria cr = session.createCriteria(TimeTableDTO.class);
			cr.add(Restrictions.eq("Course_Id", courseId));
			cr.add(Restrictions.eq("Subject_Id", subjectId));
			cr.add(Restrictions.eq("Exam_Date", examDate));
			list = cr.list();
			Iterator itr = list.iterator();
			while (itr.hasNext()) {
				dto = (TimeTableDTO) itr.next();
			}
			  System.out.println(dto);
		} catch (HibernateException hb) {
			hb.printStackTrace();
			throw new ApplicationException("Exception in TimeTableDuplicacy of Timetable Model " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#timeTableDuplicacy(long, java.lang.String, long)
	 */
	public TimeTableDTO timeTableDuplicacy(long courseId, String semester, long subjectId) throws ApplicationException {
		Session session = null;
		TimeTableDTO dto = null;
		List list = null;
		try {
			session = HibDataSource.getSession();
			Criteria cr = session.createCriteria(TimeTableDTO.class);
			cr.add(Restrictions.eq("Course_Id", courseId));
			cr.add(Restrictions.eq("Semester", semester));
			cr.add(Restrictions.eq("Subject_Id", subjectId));
			list = cr.list();
			Iterator itr = list.iterator();
			while (itr.hasNext()) {
				dto = (TimeTableDTO) itr.next();
			}
			  System.out.println("hello"+dto);
		} catch (HibernateException hb) {
			hb.printStackTrace();
			throw new ApplicationException("Exception in TimeTableDuplicacy of Timetable Model " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}
		return dto;
	}


	
	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#add(in.co.rays.dto.TimeTableDTO)
	 */
	public long add(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException {
		long pk = 0;
		Session session = null;
		Transaction tx = null;
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
			TimeTableDTO duplicatename = timeTableDuplicacy(dto.getCourse_Id(), dto.getSemester(), dto.getExam_Date());

			TimeTableDTO duplicatename1 = timeTableDuplicacy(dto.getCourse_Id(), dto.getSemester(), dto.getSubject_Id());

			TimeTableDTO duplicatename2 = timeTableDuplicacy(dto.getCourse_Id(), dto.getSubject_Id(), dto.getExam_Date());
			if (duplicatename1 != null || duplicatename != null || duplicatename2 != null) {
				throw new DuplicateRecordException("Time Table already exist");

			}

			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.save(dto);
			tx.commit();
		} catch (HibernateException hb) {
			hb.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			HibDataSource.closeSession(session);
		}
		return pk;

	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#findByPK(long)
	 */
	public TimeTableDTO findByPK(long pk) throws ApplicationException {
		TimeTableDTO dto = null;
		Session session = null;
		try {
			session = HibDataSource.getSession();
			dto = (TimeTableDTO) session.get(TimeTableDTO.class, pk);
		} catch (HibernateException hb) {
			hb.printStackTrace();
			throw new ApplicationException("Exception in findByPk of TimeTableModel " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}
		return dto;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#update(in.co.rays.dto.TimeTableDTO)
	 */
	public void update(TimeTableDTO dto) throws ApplicationException, DuplicateRecordException  {
		Session session = null;
		Transaction tx = null;
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
			TimeTableDTO duplicatename = timeTableDuplicacy(dto.getCourse_Id(), dto.getSemester(), dto.getExam_Date());

			TimeTableDTO duplicatename1 = timeTableDuplicacy(dto.getCourse_Id(), dto.getSemester(), dto.getSubject_Id());

			TimeTableDTO duplicatename2 = timeTableDuplicacy(dto.getCourse_Id(), dto.getSubject_Id(), dto.getExam_Date());
			if (duplicatename1 != null || duplicatename != null || duplicatename2 != null) {
				throw new DuplicateRecordException("Time Table already exist");

			}

			session = HibDataSource.getSession();
			tx = session.beginTransaction();
			session.update(dto);
			tx.commit();

		} catch (HibernateException hb) {
			hb.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw new ApplicationException("Exception in update of timetable Model " + hb.getMessage());
		} finally {
			HibDataSource.closeSession(session);
		}
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#delete(in.co.rays.dto.TimeTableDTO)
	 */
	public void delete(TimeTableDTO dto) throws ApplicationException {
		Session session=HibDataSource.getSession();
		Transaction transaction=null;
		try {
            transaction = session.beginTransaction();
            session.delete(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("5");
            throw new ApplicationException("Exception in TimeTable Add "
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model add End");	
		
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#name(java.lang.String)
	 */
	public TimeTableDTO name(String emailId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#search(in.co.rays.dto.TimeTableDTO, int, int)
	 */
	/*public TimeTableDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		Session session=null;
		TimeTableDTO dto=null;
		try{
			session=HibDataSource.getSession();
			dto=(TimeTableDTO)session.get(TimeTableDTO.class,pk);
		}catch(HibernateException e){
			log.error("Database Exception..", e);
            throw new ApplicationException("Exception : Exception in getting User by pk");
		}finally {
			session.close();
		}
		return dto;

	}
*/
	public List search(TimeTableDTO dto, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		  Session session=null;
		  List list=null;
		  try{
			  session=HibDataSource.getSession();
			  Criteria criteria=session.createCriteria(TimeTableDTO.class);
			  if (dto.getId() > 0) {
	                criteria.add(Restrictions.eq("id", dto.getId()));
	            }
	            if (dto.getCourse_Id() > 0) {
	                criteria.add(Restrictions.eq("Course_Id", dto.getCourse_Id()));
	            }

	            if (dto.getSubject_Id() > 0) {
	                criteria.add(Restrictions.eq("Subject_Id", dto.getSubject_Id()));
	            }
	           if(dto.getExam_Date()!=null){
	        	   
	        	   System.out.println("===============...>>>>"+dto.getExam_Date());
	            //	Date date = new Date(dto.getExam_Date().getTime());
	            	criteria.add(Restrictions.eq("Exam_Date",dto.getExam_Date()));
	            }
	            if(dto.getCourse_Name()!=null&&dto.getCourse_Name().length()>0){
					criteria.add(Restrictions.like("Course_Name",dto.getCourse_Name()+"%"));
				
				}
	            if(dto.getSubject_Name()!=null&&dto.getSubject_Name().length()>0){
					criteria.add(Restrictions.like("Subject_Name",dto.getSubject_Name()+"%"));
				
				}

	            
	            if(pageSize>0){
	            	criteria.setFirstResult((pageNo-1)*pageSize);
	            	criteria.setMaxResults(pageSize);
	            }
	            list=criteria.list();
	            }
	            catch (HibernateException e) {
	                log.error("Database Exception..", e);
	                throw new ApplicationException("Exception in user search");
	            } finally {
	                session.close();
	            }

	            log.debug("Model search End");

		  
		return list;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#search(in.co.rays.dto.TimeTableDTO)
	 */
	public List search(TimeTableDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#list()
	 */
	public List list() throws ApplicationException {
		
		return list(0, 0);
	}

	/* (non-Javadoc)
	 * @see in.co.rays.model.TimeTableModelInt#list(int, int)
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		Session session=null;
		List list=null;
		try{
			session=HibDataSource.getSession();
			Criteria criteria=session.createCriteria(TimeTableDTO.class);
			
			// if page size is greater than zero then apply pagination
            if (pageSize > 0) {
                pageNo = ((pageNo - 1) * pageSize) + 1;
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in  Users list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
		
		return list;

	}

}
