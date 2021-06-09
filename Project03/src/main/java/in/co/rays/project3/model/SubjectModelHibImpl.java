package in.co.rays.project3.model;
 
import java.util.List;


import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project3.dto.CourseDTO;
import in.co.rays.project3.dto.SubjectDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.util.HibDataSource;


 

/**
 * Hibernate Implementation of Subject Model

 * @author Vaishali
 *
 */
public class SubjectModelHibImpl implements SubjectModelInt{
    
     
   private static Logger log = Logger.getLogger(SubjectModelHibImpl.class);
    
     

	public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Long pk=0L;
    //    SubjectDTO dtoExist=findByName(dto.getSubject_Name());
//		if(dtoExist!=null){
//			throw new DuplicateRecordException("Subject already exist");
//		}
		
//		CollegeModelInt cModel = ModelFactory.getInstance().getCollegeModel();
//        CollegeDTO collegeDTO = cModel.findByPK(dto.getCollegeId());
//        dto.setCollegeName(collegeDTO.getCollegeName());
		
		// get Course Name
       CourseModelInt  cModel = ModelFactory.getInstance().getCourseModel();
       CourseDTO cd = cModel.findByPK(dto.getCourse_Id());
      dto.setCourse_Name(cd.getCourse_Name());  
        Session session=null;
        Transaction transaction=null;
        try{
        	session=HibDataSource.getSession();
        	transaction=session.beginTransaction();
        	session.save(dto);
        	pk = dto.getId();
        	transaction.commit();
        }catch(HibernateException e)
        {	
        	e.printStackTrace();
        log.error("Database Exception..", e);
        if (transaction != null) {
            transaction.rollback();
        }
        throw new ApplicationException("Exception in Subject Add "
                + e.getMessage());
    } finally {
        session.close();
    }

    log.debug("Model add End");
    
    return dto.getId();
	}
	
	 
	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Long pk=0L;
//        SubjectDTO dtoExist=findByName(dto.getSubject_Name());
//		if(dtoExist!=null){
//			throw new DuplicateRecordException("Subject already exist");
//		}
        Session session=null;
        Transaction transaction=null;
        
        CourseModelInt  cModel = ModelFactory.getInstance().getCourseModel();
        CourseDTO cd = cModel.findByPK(dto.getCourse_Id());
       dto.setCourse_Name(cd.getCourse_Name());  
        try{
        	 session = HibDataSource.getSession();
             transaction = session.beginTransaction();
             session.update(dto);
             transaction.commit();
        }catch(HibernateException e){
        	log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
                throw new ApplicationException("Exception in College Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");
	}
	
	 
	public void delete(SubjectDTO dto) throws ApplicationException {
		log.debug("Model delete Started");
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.delete(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in Subject Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");	
	}
	
	 
	public SubjectDTO findByName(String name) throws ApplicationException {
		log.debug("Model findByName Started");
		 Session session = null;
	     SubjectDTO dto = null;
	     try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(SubjectDTO.class);
	            criteria.add(Restrictions.eq("Subject_Name", name));
	            List list = criteria.list();
	            if (list.size() > 0) {
	                dto = (SubjectDTO) list.get(0);
	            }

	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            e.printStackTrace();
//	            throw new ApplicationException(
//	                    "Exception in getting User by Login " + e.getMessage());

	        } finally {
	            session.close();
	        }

	        log.debug("Model findByName End");
		return dto;
	}
	
	 
	public SubjectDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
        Session session = null;
        SubjectDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (SubjectDTO)session.get(SubjectDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting College by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
		return dto;
	}
	
	 
	public List search(SubjectDTO dto, int pageNo, int pageSize) throws ApplicationException {
		Session session=null;
		List list=null;
		try{
			session=HibDataSource.getSession();
			Criteria criteria=session.createCriteria(SubjectDTO.class);
			if(dto.getId()>0){
				criteria.add(Restrictions.eq("id",dto.getId()));
			}
			
			if(dto.getCourse_Id()>0){
				criteria.add(Restrictions.eq("Course_Id",dto.getCourse_Id()));
			}
			if(dto.getSubject_Name()!=null&&dto.getSubject_Name().length()>0){
				criteria.add(Restrictions.like("Subject_Name", dto.getSubject_Name()));
			}
		if(dto.getCourse_Name()!=null&&dto.getCourse_Name().length()>0){
				criteria.add(Restrictions.like("Course_Name", dto.getCourse_Name()));
			}
		
		
if(dto.getDescription()!= null&&dto.getDescription().length()>0){
	criteria.add(Restrictions.eq("Description", dto.getDescription()));
}
			
		    if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception in course search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
		return list;
		}
	
	 

	public List search(SubjectDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}
	
	 

	public List list() throws ApplicationException {
		return list(0, 0);
	}
	
	 

	public List list(int pageNo, int pageSize) throws ApplicationException {
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(SubjectDTO.class);

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
                    "Exception : Exception in  Course list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;
	}

}