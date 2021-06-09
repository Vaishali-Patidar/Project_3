package in.co.rays.project3.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.project3.dto.UserDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DatabaseException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.exception.RecordNotFoundException;
import in.co.rays.project3.util.EmailBuilder;
import in.co.rays.project3.util.EmailMessage;
import in.co.rays.project3.util.EmailUtility;
import in.co.rays.project3.util.JDBCDataSource;

/**
 * JDBC Implementation of UserModel
 *
 * @author Vaishali
  */
public class UserModelJdbcImp implements UserModelInt {
    private static Logger log = Logger.getLogger(UserModelJdbcImp.class);

    
    public Integer nextPK() throws DatabaseException {
        log.debug("Model nextPK Started");
        Connection conn = null;
        int pk = 0;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn
                    .prepareStatement("SELECT MAX(ID) FROM ST_USER");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                pk = rs.getInt(1);
            }
            rs.close();

        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new DatabaseException("Exception : Exception in getting PK");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model nextPK End");
        return pk + 1;
    }

   
    public long add(UserDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        int pk = 0;

        UserDTO existDto = findByLogin(dto.getLogin());

        if (existDto != null) {
            throw new DuplicateRecordException("Login Id already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO ST_USER VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, pk);
            pstmt.setString(2, dto.getFirstName());
            pstmt.setString(3, dto.getLastName());
            pstmt.setString(4, dto.getLogin());
            pstmt.setString(5, dto.getPassword());
            pstmt.setDate(6, new java.sql.Date(dto.getDob().getTime()));
            pstmt.setString(7, dto.getMobileNo());
            pstmt.setLong(8, dto.getRoleid());
          
            pstmt.setString(10, dto.getGender());
           
            pstmt.setString(11, dto.getCreatedBy());
            pstmt.setString(12, dto.getModifiedBy());
            pstmt.setTimestamp(13,dto.getCreatedDatetime());
            pstmt.setTimestamp(14,dto.getModifiedDatetime());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException("Exception : Exception in add User");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model add End");
        return pk;
    }

   
    public void delete(UserDTO dto) throws ApplicationException {
        log.debug("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM ST_USER WHERE ID=?");
            pstmt.setLong(1, dto.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();

        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in delete User");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model delete Started");
    }

   
    @Override
    public UserDTO findByLogin(String login) throws ApplicationException {
        log.debug("Model findByLogin Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_USER WHERE LOGIN=?");
        UserDTO dto = null;
        Connection conn = null;
        System.out.println("sql" + sql);

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setFirstName(rs.getString(2));
                dto.setLastName(rs.getString(3));
                dto.setLogin(rs.getString(4));
                dto.setPassword(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setMobileNo(rs.getString(7));
                dto.setRoleid(rs.getLong(8));
                
                dto.setGender(rs.getString(9));
               
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by login");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByLogin End");
        return dto;
    }

   
    @Override
    public UserDTO findByPK(long pk) throws ApplicationException {
        log.debug("Model findByPK Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE ID=?");
        UserDTO dto = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, pk);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setFirstName(rs.getString(2));
                dto.setLastName(rs.getString(3));
                dto.setLogin(rs.getString(4));
                dto.setPassword(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setMobileNo(rs.getString(7));
                dto.setRoleid(rs.getLong(8));
               
                dto.setGender(rs.getString(9));
               
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));

            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by pk");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByPK End");
        return dto;
    }

    
    @Override
    public void update(UserDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model update Started");
        Connection conn = null;

        UserDTO dtoExist = findByLogin(dto.getLogin());
        // Check if updated LoginId already exist
        if (dtoExist != null && !(dtoExist.getId() == dto.getId())) {
            throw new DuplicateRecordException("LoginId is already exist");
        }

        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,PASSWORD=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,UNSUCCESSFUL_LOGIN=?,GENDER=?,LAST_LOGIN=?,USER_LOCK=?,REGISTERED_IP=?,LAST_LOGIN_IP=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
            pstmt.setString(1, dto.getFirstName());
            pstmt.setString(2, dto.getLastName());
            pstmt.setString(3, dto.getLogin());
            pstmt.setString(4, dto.getPassword());
            pstmt.setDate(5, new java.sql.Date(dto.getDob().getTime()));
            pstmt.setString(6, dto.getMobileNo());
            pstmt.setLong(7, dto.getRoleid());
           
            pstmt.setString(8, dto.getGender());
            
            pstmt.setString(9, dto.getCreatedBy());
            pstmt.setString(10, dto.getModifiedBy());
            pstmt.setTimestamp(11,dto.getCreatedDatetime());
            pstmt.setTimestamp(12,dto.getModifiedDatetime());
            pstmt.setLong(13, dto.getId());
            pstmt.executeUpdate();
            conn.commit(); // End transaction
            pstmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : Delete rollback exception "
                                + ex.getMessage());
            }
            throw new ApplicationException("Exception in updating User ");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model update End");
    }

    
    @Override
    public List search(UserDTO dto) throws ApplicationException {
        return search(dto, 0, 0);
    }

    
    @Override
    public List search(UserDTO dto, int pageNo, int pageSize)
            throws ApplicationException {
        log.debug("Model search Started");
        StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE 1=1");

        if (dto != null) {
            if (dto.getId() > 0) {
                sql.append(" AND id = " + dto.getId());
            }
            if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
                sql.append(" AND FIRST_NAME like '" + dto.getFirstName() + "%'");
            }
            if (dto.getLastName() != null && dto.getLastName().length() > 0) {
                sql.append(" AND LAST_NAME like '" + dto.getLastName() + "%'");
            }
            if (dto.getLogin() != null && dto.getLogin().length() > 0) {
                sql.append(" AND LOGIN like '" + dto.getLogin() + "%'");
            }
            if (dto.getPassword() != null && dto.getPassword().length() > 0) {
                sql.append(" AND PASSWORD like '" + dto.getPassword() + "%'");
            }
            if (dto.getDob() != null && dto.getDob().getDate() > 0) {
                sql.append(" AND DOB = " + dto.getGender());
            }
            if (dto.getMobileNo() != null && dto.getMobileNo().length() > 0) {
                sql.append(" AND MOBILE_NO = " + dto.getMobileNo());
            }
            if (dto.getRoleid() > 0) {
                sql.append(" AND ROLE_ID = " + dto.getRoleid());
            }
           
            if (dto.getGender() != null && dto.getGender().length() > 0) {
                sql.append(" AND GENDER like '" + dto.getGender() + "%'");
            }
           
        }

        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;

            sql.append(" Limit " + pageNo + ", " + pageSize);
            // sql.append(" limit " + pageNo + "," + pageSize);
        }

        System.out.println(sql);
        ArrayList list = new ArrayList();
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setFirstName(rs.getString(2));
                dto.setLastName(rs.getString(3));
                dto.setLogin(rs.getString(4));
                dto.setPassword(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setMobileNo(rs.getString(7));
                dto.setRoleid(rs.getLong(8));
                
                dto.setGender(rs.getString(9));
               
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));

                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in search user");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model search End");
        return list;
    }

   
    @Override
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    
    @Override
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from ST_USER");
        // if page size is greater than zero then apply pagination
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }

        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                UserDTO dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setFirstName(rs.getString(2));
                dto.setLastName(rs.getString(3));
                dto.setLogin(rs.getString(4));
                dto.setPassword(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setMobileNo(rs.getString(7));
                dto.setRoleid(rs.getLong(8));
               
                dto.setGender(rs.getString(9));
               
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));

                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of users");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;

    }

    
    @Override
    public UserDTO authenticate(String login, String password)
            throws ApplicationException {
        log.debug("Model authenticate Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_USER WHERE LOGIN = ? AND PASSWORD = ?");
        UserDTO dto = null;
        Connection conn = null;

        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setFirstName(rs.getString(2));
                dto.setLastName(rs.getString(3));
                dto.setLogin(rs.getString(4));
                dto.setPassword(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setMobileNo(rs.getString(7));
                dto.setRoleid(rs.getLong(8));
               
                dto.setGender(rs.getString(9));
                
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));

            }
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception : Exception in get roles");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model authenticate End");
        return dto;
    }

   
    @Override
    public boolean lock(String login) throws RecordNotFoundException,
            ApplicationException {
        log.debug("Service lock Started");
        boolean flag = false;
        UserDTO dtoExist = null;
        try {
            dtoExist = findByLogin(login);
            if (dtoExist != null) {
             //   dtoExist.setLock(UserDTO.ACTIVE);
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

   
    @Override
    public List getRoles(UserDTO dto) throws ApplicationException {
        log.debug("Model get roles Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_USER WHERE role_Id=?");
        Connection conn = null;
        List list = new ArrayList();
        try {

            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setLong(1, dto.getRoleid());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new UserDTO();
                dto.setId(rs.getLong(1));
                dto.setFirstName(rs.getString(2));
                dto.setLastName(rs.getString(3));
                dto.setLogin(rs.getString(4));
                dto.setPassword(rs.getString(5));
                dto.setDob(rs.getDate(6));
                dto.setMobileNo(rs.getString(7));
                dto.setRoleid(rs.getLong(8));
               
                dto.setGender(rs.getString(9));
              
                dto.setCreatedBy(rs.getString(10));
                dto.setModifiedBy(rs.getString(11));
                dto.setCreatedDatetime(rs.getTimestamp(12));
                dto.setModifiedDatetime(rs.getTimestamp(13));

                list.add(dto);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception : Exception in get roles");

        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model get roles End");
        return list;
    }

    
    @Override
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

    @Override
    public UserDTO updateAccess(UserDTO dto) throws ApplicationException {
        return null;
    }

   
    @Override
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

    
    @Override
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
        map.put("firstName", dto.getFirstName());
        map.put("lastName", dto.getLastName());

        String message = EmailBuilder.getForgetPasswordMessage(map);

        EmailMessage msg = new EmailMessage();

        msg.setTo(dto.getLogin());
        msg.setSubject("Password has been reset");
        msg.setMessage(message);
        msg.setMessageType(EmailMessage.HTML_MSG);

        EmailUtility.sendMail(msg);

        return true;
    }

   
    @Override
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
