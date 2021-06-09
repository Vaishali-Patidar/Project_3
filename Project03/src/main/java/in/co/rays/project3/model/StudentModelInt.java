package in.co.rays.project3.model;

import java.util.List;

import in.co.rays.project3.dto.StudentDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DatabaseException;
import in.co.rays.project3.exception.DuplicateRecordException;

/**
 * Data Access Object of Student
 *
 * @author Vaishali
  */

public interface StudentModelInt {

   
    public long add(StudentDTO dto) throws ApplicationException,
            DuplicateRecordException;

   
    public void update(StudentDTO dto) throws ApplicationException,
            DuplicateRecordException;

   
    public void delete(StudentDTO dto) throws ApplicationException;

    
    public StudentDTO findByEmailId(String emailId) throws ApplicationException;

    
    public StudentDTO findByPK(long pk) throws ApplicationException;

   
    public List search(StudentDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    
    public List search(StudentDTO dto) throws ApplicationException;

   
    public List list() throws ApplicationException;

    
    public List list(int pageNo, int pageSize) throws ApplicationException;

}