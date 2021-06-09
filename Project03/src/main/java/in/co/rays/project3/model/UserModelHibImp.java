package in.co.rays.project3.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.rays.project3.dto.UserDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DatabaseException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.exception.RecordNotFoundException;
import in.co.rays.project3.util.EmailBuilder;
import in.co.rays.project3.util.EmailMessage;
import in.co.rays.project3.util.EmailUtility;
import in.co.rays.project3.util.HibDataSource;

/**
 * Hibernate Implementation of UserModel
 *
 * @author Vaishali
 
 */
public class UserModelHibImp implements UserModelInt {

    private static Logger log = Logger.getLogger(UserModelHibImp.class);

    
    public long add(UserDTO dto) throws ApplicationException,
            DuplicateRecordException {

        log.debug("Model add Started");
        long pk = 0;

   //     UserDTO dtoExist = findByLogin(dto.getLogin());

//        if (dtoExist != null) {
//            throw new DuplicateRecordException("LoginId is already exist");
//        }

        Session session = HibDataSource.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dto);
            pk = dto.getId();
           
            transaction.commit();
        }
       catch (HibernateException e) {
          //  log.error("Database Exception..", e);
            e.printStackTrace();
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            throw new ApplicationException("Exception in User Add "
//                    + e.getMessage());
        }
       finally {
            session.close();
       }
        log.debug("Model add End");
        return dto.getId();
    }

    
    public void delete(UserDTO dto) throws ApplicationException {
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
//            throw new ApplicationException("Exception in User Delete"
//                    + e.getMessage());
        } finally {
            session.close();
        }
        log.debug("Model delete End");
    }

    
    
    public UserDTO findByLogin(String login) throws ApplicationException {
        log.debug("Model findByLoginId Started");
        Session session = null;
        UserDTO dto = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(UserDTO.class);
            criteria.add(Restrictions.eq("Login", login));
            List list = criteria.list();

            if (list.size() == 1) {
                dto = (UserDTO) list.get(0);
            }

        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            e.printStackTrace();
//            throw new ApplicationException(
//                    "Exception in getting User by Login " + e.getMessage());

        } finally {
            session.close();
        }

        log.debug("Model findByLoginId End");
        return dto;
    }

    
    public UserDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        Session session = null;
        UserDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (UserDTO) session.get(UserDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return dto;
    }

   
    
    public void update(UserDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Session session = null;
        Transaction transaction = null;

//        UserDTO dtoExist = findByLogin(dto.getLogin());

        // Check if updated LoginId already exist
//        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
//            throw new DuplicateRecordException("LoginId is already exist");
//        }

        try {
            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
              
//                throw new ApplicationException("Exception in User Update"
//                       + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");
    }

   
    
    public List search(UserDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    
    
    public List search(UserDTO dto, int pageNo, int pageSize)
            throws ApplicationException {

        System.out.println("in method search 1-->" + dto.getFirstName());
        System.out.println("in method search 2->" + dto.getLogin());

        log.debug("Model search Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(UserDTO.class);

            if (dto.getId() > 0) {
                criteria.add(Restrictions.eq("id", dto.getId()));
            }
            if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
                criteria.add(Restrictions.like("FirstName", dto.getFirstName()
                        + "%"));
            }
            if (dto.getLastName() != null && dto.getLastName().length() > 0) {
                criteria.add(Restrictions.like("LastName", dto.getLastName()
                        + "%"));
            }
            if (dto.getLogin() != null && dto.getLogin().length() > 0) {
                criteria.add(Restrictions.like("Login", dto.getLogin() + "%"));
            }
            if (dto.getPassword() != null && dto.getPassword().length() > 0) {
                criteria.add(Restrictions.like("Password", dto.getPassword()
                        + "%"));
            }
            if (dto.getGender() != null && dto.getGender().length() > 0) {
                criteria.add(Restrictions.like("Gender", dto.getGender() + "%"));
            }
            if (dto.getDob() != null && dto.getDob().getDate() > 0) {
                criteria.add(Restrictions.eq("Dob", dto.getDob()));
            }
           
            if (dto.getRoleid() > 0) {
                criteria.add(Restrictions.eq("Roleid", dto.getRoleid()));
            }
            if (dto.getMobileNo()!= null && dto.getMobileNo().length()>0) {
                criteria.add(Restrictions.like("MobileNo", dto.getMobileNo()));
            }

            // if page size is greater than zero the apply pagination
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception in user search");
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
            Criteria criteria = session.createCriteria(UserDTO.class);

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

    
    
    public boolean changePassword(Long id, String oldPassword,
            String newPassword) throws RecordNotFoundException,
            ApplicationException {

        log.debug("model changePassword Started");
        boolean flag = false;
        UserDTO dtoExist = null;

        dtoExist = findByPK(id);
        if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
            dtoExist.setPassword(newPassword);
            try {
                update(dtoExist);
            } catch (DuplicateRecordException e) {
                log.error(e);
                throw new ApplicationException("LoginId is already exist");
            }
            flag = true;
        } else {
            throw new RecordNotFoundException("Login not exist");
        }

        HashMap<String, String> map = new HashMap<String, String>();

        map.put("login", dtoExist.getLogin());
        map.put("password", dtoExist.getPassword());
        map.put("firstName", dtoExist.getFirstName());
        map.put("lastName", dtoExist.getLastName());

        String message = EmailBuilder.getChangePasswordMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dtoExist.getLogin());
        msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);

        log.debug("Model changePassword End");
        return flag;

    }

    
    
    public UserDTO authenticate(String login, String password)
            throws ApplicationException {
        log.debug("Model authenticate Started");
        Session session = null;
        UserDTO dto = null;
        session = HibDataSource.getSession();
        Query q = session
                .createQuery("from UserDTO where login=? and password=? ");
        q.setString(0, login);
        q.setString(1, password);
        List list = q.list();
        if (list.size() > 0) {
            dto = (UserDTO) list.get(0);
        } else {
            dto = null;
        }
        log.debug("Model authenticate End");
        return dto;
    }

    
    
    public boolean lock(String login) throws RecordNotFoundException,
            ApplicationException {
        log.debug("Service lock Started");
        boolean flag = false;
        UserDTO dtoExist = null;
        try {
            dtoExist = findByLogin(login);
            if (dtoExist != null) {
           //     dtoExist.setLogin(UserDTO.ACTIVE);
                update(dtoExist);
                flag = true;
            } else {
                throw new RecordNotFoundException("LoginId not exist");
            }
        } catch (DuplicateRecordException e) {
            log.error("Application Exception..", e);
            throw new ApplicationException("Database Exception");
        }
        log.debug("Service lock End");
        return flag;
    }

    
    public UserDTO updateAccess(UserDTO dto) throws ApplicationException {

        return null;
    }
    
   
    
    public List getRoles(UserDTO dto) throws ApplicationException {
        return null;
    }
    
     
    public long registerUser(UserDTO dto) throws ApplicationException,
            DuplicateRecordException {

        log.debug("Model add Started");

        long pk = add(dto);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", dto.getLogin());
        map.put("password", dto.getPassword());

        String message = EmailBuilder.getUserRegistrationMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dto.getLogin());
        msg.setSubject("Registration is successful for ORS Project SUNRAYS Technologies");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);

        return pk;
    }
    
     
    
    public boolean resetPassword(UserDTO dto) throws ApplicationException {

        String newPassword = String.valueOf(new Date().getTime()).substring(0,
                4);
        UserDTO userData = findByPK(dto.getId());
        userData.setPassword(newPassword);

        try {
            update(userData);
        } catch (DuplicateRecordException e) {
            return false;
        }

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", dto.getLogin());
        map.put("password", dto.getPassword());

        String message = EmailBuilder.getForgetPasswordMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dto.getLogin());
        msg.setSubject("Password has been reset");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);

        return true;
    }
   
   
    public boolean forgetPassword(String login) throws ApplicationException,
            RecordNotFoundException {
        UserDTO userData = findByLogin(login);
        boolean flag = false;

        if (userData == null) {
            throw new RecordNotFoundException("Email Id Does not matched.");

        }

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("login", userData.getLogin());
        map.put("password", userData.getPassword());
        map.put("firstName", userData.getFirstName());
        map.put("lastName", userData.getLastName());
        String message = EmailBuilder.getForgetPasswordMessage(map);
        EmailMessage msg = new EmailMessage();
        msg.setTo(login);
        msg.setSubject("SUNARYS ORS Password reset");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);
        EmailUtility.sendMail(msg);
        flag = true;

        return flag;
    }

}
