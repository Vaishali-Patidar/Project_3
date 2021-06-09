package in.co.rays.project3.model;

import java.util.List;

import in.co.rays.project3.dto.UserDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DatabaseException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.exception.RecordNotFoundException;

/**
 * Data Access Object of Users
 *
 * @author Vaishali
  */

public interface UserModelInt {

    
    public long add(UserDTO dto) throws ApplicationException,
            DuplicateRecordException;

   
    public void update(UserDTO dto) throws ApplicationException,
            DuplicateRecordException;

   
    public void delete(UserDTO dto) throws ApplicationException;

    
    public UserDTO findByLogin(String login) throws ApplicationException;

    
    public UserDTO findByPK(long pk) throws ApplicationException;

    
    public List search(UserDTO dto) throws ApplicationException;

   
    public List search(UserDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    
    public List list() throws ApplicationException;

   
    public List list(int pageNo, int pageSize) throws ApplicationException;

    
    public boolean changePassword(Long id, String oldPassword,
            String newPassword) throws RecordNotFoundException,
            ApplicationException;

   
    public UserDTO authenticate(String login, String password)
            throws ApplicationException;

   
    public boolean lock(String login) throws RecordNotFoundException,
            ApplicationException;

    
    public List getRoles(UserDTO dto) throws ApplicationException;

    public UserDTO updateAccess(UserDTO dto) throws ApplicationException,
            DuplicateRecordException;

    
    public long registerUser(UserDTO dto) throws ApplicationException,
            DuplicateRecordException;

    
    public boolean resetPassword(UserDTO dto) throws ApplicationException;

    
    public boolean forgetPassword(String login) throws ApplicationException,
            RecordNotFoundException;

}
