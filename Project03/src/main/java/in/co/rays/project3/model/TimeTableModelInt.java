package in.co.rays.project3.model;

import java.util.List;

import in.co.rays.project3.dto.TimeTableDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;

/**
 * Data Access Object of College
 *
 * @author Vaishali
 
 */
 
public interface TimeTableModelInt {
	
	
	 
	 
	public TimeTableDTO timeTableDuplicacy(long courseId, String semester, java.util.Date examDate) 
			throws ApplicationException; 

	 
	public TimeTableDTO timeTableDuplicacy(long courseId, long subjectId, java.util.Date examDate)
			throws ApplicationException;
		
	 
	public TimeTableDTO timeTableDuplicacy(long courseId, String semester, long subjectId)
			throws ApplicationException;
		
	
	   
    public long add(TimeTableDTO dto) throws ApplicationException,
            DuplicateRecordException
            ;

     
    public void update(TimeTableDTO dto) throws ApplicationException,
            DuplicateRecordException;

     
    public void delete(TimeTableDTO dto) throws ApplicationException;

     
    public TimeTableDTO name(String emailId) throws ApplicationException;

     
    public TimeTableDTO findByPK(long pk) throws ApplicationException;

     
    public List search(TimeTableDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

     
    public List search(TimeTableDTO dto) throws ApplicationException;

     
    public List list() throws ApplicationException;

     
    public List list(int pageNo, int pageSize) throws ApplicationException;

}


