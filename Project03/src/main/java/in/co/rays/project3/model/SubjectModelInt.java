package in.co.rays.project3.model;




import java.util.List;

import in.co.rays.project3.dto.CollegeDTO;
import in.co.rays.project3.dto.SubjectDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DatabaseException;
import in.co.rays.project3.exception.DuplicateRecordException;

/**
 * Data Access Object of College
 *
 * @author Vaishali
 
 */


public interface SubjectModelInt {
	
	

	    public long add(SubjectDTO dto) throws ApplicationException,
	            DuplicateRecordException;

	   
	    public void update(SubjectDTO dto) throws ApplicationException,
	            DuplicateRecordException;

	   
	    public void delete(SubjectDTO dto) throws ApplicationException;

	    
	    public SubjectDTO findByName(String name) throws ApplicationException;

	   
	    public SubjectDTO findByPK(long pk) throws ApplicationException;

	    public List search(SubjectDTO dto, int pageNo, int pageSize)
	            throws ApplicationException;

	   
	    public List search(SubjectDTO dto) throws ApplicationException;

	    
	    public List list() throws ApplicationException;

	    
	    public List list(int pageNo, int pageSize) throws ApplicationException;

	}


