package in.co.rays.project3.model;

import java.util.List;

import in.co.rays.project3.dto.FacultyDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DatabaseException;
import in.co.rays.project3.exception.DuplicateRecordException;

/** Data Access Object of Faculty
 * @author Vaishali
 */
public interface FacultyModelInt {
	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException;

	public void delete(FacultyDTO dto) throws ApplicationException;

	public void update(FacultyDTO dto) throws ApplicationException, DatabaseException;

	public List list() throws ApplicationException;

	public List list(int pageNo, int pageSize) throws ApplicationException;

	public List search(FacultyDTO dto) throws ApplicationException;

	public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException;

	public FacultyDTO findByPK(long pk) throws ApplicationException;

	public FacultyDTO findByEmailId(String emailId) throws ApplicationException;
}
