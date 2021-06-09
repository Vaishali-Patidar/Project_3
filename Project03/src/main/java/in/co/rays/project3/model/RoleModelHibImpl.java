package in.co.rays.project3.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.jfree.util.Log;

import in.co.rays.project3.dto.RoleDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DatabaseException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.util.HibDataSource;


/**
 * Hibernate Implementation of Role Model

 * @author Vaishali
 *
 */
public class RoleModelHibImpl implements RoleModelInt {
	
	private static Logger log = Logger.getLogger(RoleModelHibImpl.class);

    

	public long add(RoleDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		//log.dubeg("Model add Started");
		long pk=0;
		//RoleDTO duplicataRole =findByName(dto.getName());
		//check if update role already exist
//		if (duplicataRole!=null){
//			throw new DuplicateRecordException("Role already Exist");
//		}
		Session session =HibDataSource.getSession();
		Transaction transaction=null;
		try{
			transaction = session.beginTransaction();
			session.save(dto);
			pk =dto.getId();
			transaction.commit();
		}catch(HibernateException e){
			e.printStackTrace();
			//log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
 }
            throw new ApplicationException("Exception in User Add" +e.getMessage());
		}finally{
			if(session!= null){
				session.close();
			}
		}
		Log.debug("Model end");
		
		return dto.getId();
	}

	public void update(RoleDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		log.debug("Model Update started");
		Session session = null;
		Transaction transaction =null;
//		RoleDTO duplicateRole =findByName(dto.getName());
//		//check if update is already exist
//		if(duplicateRole !=null){
//			throw new DuplicateRecordException("Role is already exist");
//			}
						try{
			session =HibDataSource.getSession();
			transaction = session.beginTransaction();
			session.update(dto);
			
			transaction.commit();
		}
		
	catch(HibernateException e){
			e.printStackTrace();
			log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
                throw new ApplicationException("Exception in Role Update"
                        + e.getMessage());
		}
		}finally{
			session.close();
		}
		log.debug("MOdel update End");
	}

	public void delete(RoleDTO dto) throws ApplicationException {
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
            e.printStackTrace();
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            throw new ApplicationException("Exception in Role Delete"
//                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");

		
	}

	

	public RoleDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findBypk Started");
        RoleDTO dto = null;
        Session session = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(RoleDTO.class);
            criteria.add(Restrictions.eq("id", pk));
            List list = criteria.list();
            if (list.size() == 1) {
                dto = (RoleDTO) list.get(0);
            } else {
                dto = null;
            }

        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("Exception in getting Role by pk "
                    + e.getMessage());

        } finally {
            session.close();
        }
        log.debug("Model findBypk End");
        return dto;
    }

		
	

	public List search(RoleDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

   
    public List search(RoleDTO dto, int pageNo, int pageSize)
            throws ApplicationException {

        System.out.println("in method search 1-->" + dto.getName());

        log.debug("Model search Started");
        Session session = null;
        List list = null;
        try {
        	//System.out.println("in inner>>>>>>>");
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(RoleDTO.class);

            if (dto.getId() > 0) {
                criteria.add(Restrictions.eq("id", dto.getId()));
            }
            if (dto.getName() != null && dto.getName().length() > 0) {
                criteria.add(Restrictions.like("name", dto.getName() + "%"));
            }
            if (dto.getDescription() != null
                    && dto.getDescription().length() > 0) {
                criteria.add(Restrictions.like("description",
                        dto.getDescription() + "%"));
            }

            // if page size is greater than zero the apply pagination
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            e.printStackTrace();
          //  throw new ApplicationException("Exception in Role search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
        return list;
    }

    public List list() throws ApplicationException {
        return list(0, 0);
    }

    
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(RoleDTO.class);

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
                    "Exception : Exception in  Roles list");
        } finally {
            session.close();
        }

        log.debug("Model list End");
        return list;
    }
    
   
    public RoleDTO findByName(String name) throws ApplicationException {

        log.debug("Model findByName Started");
        Session session = null;
        RoleDTO dto = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(RoleDTO.class);
            criteria.add(Restrictions.eq("Name", name));
            List list = criteria.list();

            if (list.size() == 1) {
                dto = (RoleDTO) list.get(0);
            }

        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            e.printStackTrace();
//            THROW NEW APPLICATIONEXCEPTION(
//                    "EXCEPTION IN GETTING USER BY LOGIN " + E.GETMESSAGE());

        } finally {
            session.close();
        }

        log.debug("Model findByName End");
        return dto;
    }

	

}

	

