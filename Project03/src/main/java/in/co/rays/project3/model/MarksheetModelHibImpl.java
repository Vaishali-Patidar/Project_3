package in.co.rays.project3.model;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project3.dto.MarksheetDTO;
import in.co.rays.project3.dto.StudentDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DatabaseException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.util.HibDataSource;

/**
 * Hibernate Implementation of Marksheet Model
 *
 * @author Vaishali
 */
public class MarksheetModelHibImpl implements MarksheetModelInt {

    private static Logger log = Logger.getLogger(MarksheetModelHibImpl.class);

    
    public long add(MarksheetDTO dto) throws ApplicationException,
            DuplicateRecordException {

        log.debug("Model add Started");
        long pk = 0;
        System.out.println("In add");

        //get Student Name
        StudentModelInt sModel = ModelFactory.getInstance().getStudentModel();
        StudentDTO studentDTO = sModel.findByPK(dto.getStudentId());
        dto.setName(studentDTO.getFirstName() + " " + studentDTO.getLastName());
        

//        MarksheetDTO duplicateMarksheet = findByRollNo(dto.getRollNo());
//
//        if (duplicateMarksheet != null) {
//            throw new DuplicateRecordException("Roll Number already exists");
//        }

        Session session = HibDataSource.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
           // pk = (Long) session.save(dto);
            session.save(dto);
            transaction.commit();
        } catch (Exception e) {
        	e.printStackTrace();
            log.error(e);
            if (transaction != null) {
                transaction.rollback();
            }
//            throw new ApplicationException("Exception in Marksheet Add "
//                    + e.getMessage());
        } finally {
            session.close();
        }

        // Send Email

        log.debug("Model add End");
        return pk;
    }

    
    
    public void delete(MarksheetDTO dto) throws ApplicationException {

        log.debug("Model delete Started");
        MarksheetDTO dtoExist = findByPK(dto.getId());
        if (dtoExist == null) {
            throw new ApplicationException("Marksheet does not exist");
        }

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
            throw new ApplicationException("Exception in Marksheet Delete"
                    + e.getMessage());
        } finally {
            session.close();
        }

        log.debug("Model delete End");
    }

    
    public MarksheetDTO findByRollNo(String rollNo) throws ApplicationException {

        log.debug("Model findByRollNo Started");
        MarksheetDTO dto = null;
        Session session = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(MarksheetDTO.class);
            criteria.add(Restrictions.eq("rollNo", rollNo));
            List list = criteria.list();
            if (list.size() == 1) {
                dto = (MarksheetDTO) list.get(0);
            } else {
                dto = null;
            }

        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException(
                    "Exception in getting Marksheet by Rollno "
                            + e.getMessage());

        } finally {
            session.close();
        }
        log.debug("Model findByRollNo End");
        return dto;
    }

   
    
    public MarksheetDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        Session session = null;
        MarksheetDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (MarksheetDTO) session.get(MarksheetDTO.class, pk);
        } catch (Exception e) {
            log.error(e);
            e.printStackTrace();
//            throw new ApplicationException(
//                    "Exception in getting Marksheet by pk" + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return dto;
    }

    
    
    public void update(MarksheetDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Session session = null;
        Transaction transaction = null;

        MarksheetDTO dtoExist = findByRollNo(dto.getRollNo());

        // Check if updated Roll no already exist
        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
            throw new DuplicateRecordException("Roll No is already exist");
        }

        
     //   get Student Name
        StudentModelInt sModel = ModelFactory.getInstance().getStudentModel();
        StudentDTO studentDTO = sModel.findByPK(dto.getStudentId());
        dto.setName(studentDTO.getFirstName() + " " + studentDTO.getLastName());

        try {

            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
        } catch (Exception e) {
            log.error(e);
            if (transaction != null) {
                transaction.rollback();
            }
//            throw new ApplicationException("Exception in Marksheet Update"
//                    + e.getMessage());
        } finally {
            session.close();
        }

        log.debug("Model update End");
    }

   
    
    public List search(MarksheetDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    
    
    public List search(MarksheetDTO dto, int pageNo, int pageSize)
            throws ApplicationException {

        log.debug("Model search Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(MarksheetDTO.class);

            if (dto.getId() > 0) {
                criteria.add(Restrictions.eq("id", dto.getId()));
            }
            if (dto.getRollNo() != null && dto.getRollNo().length() > 0) {
                criteria.add(Restrictions.eq("rollNo", dto.getRollNo()));
            }
            if (dto.getName() != null && dto.getName().length() > 0) {
                criteria.add(Restrictions.like("name", dto.getName() + "%"));
            }
            if (dto.getPhysics() != null && dto.getPhysics() > 0) {
                criteria.add(Restrictions.eq("physics", dto.getPhysics()));
            }
            if (dto.getChemistry() != null && dto.getChemistry() > 0) {
                criteria.add(Restrictions.eq("chemistry", dto.getChemistry()));
            }
            if (dto.getMaths() != null && dto.getMaths() > 0) {
                criteria.add(Restrictions.eq("maths", dto.getMaths()));
            }

            // if page size is greater than zero the apply pagination
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("Exception in Marksheet Search "
                    + e.getMessage());
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
            Criteria criteria = session.createCriteria(MarksheetDTO.class);

            // if page size is greater than zero then apply pagination
            if (pageSize > 0) {
                pageNo = ((pageNo - 1) * pageSize) + 1;
                criteria.setFirstResult(pageNo);
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("Exception in  Marksheet List"
                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model list End");
        return list;
    }

    
    public List getMeritList(int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model getMeritList Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();

            StringBuffer hql = new StringBuffer(
                    "from MarksheetDTO order by (physics + chemistry + maths) desc");

            // if page size is greater than zero then apply pagination
            if (pageSize > 0) {
                pageNo = ((pageNo - 1) * pageSize) + 1;
                hql.append(" limit " + pageNo + "," + pageSize);
            }

            System.out.println(hql.toString());
            Query query = session.createQuery(hql.toString());

            list = query.list();

        } catch (Exception e) {
            log.error(e);
            throw new ApplicationException("Exception in  marksheet list"
                    + e.getMessage());
        } finally {
            session.close();
        }

        log.debug("Model getMeritList End");
        return list;
    }

}
