package in.co.rays.project3.model;

import antlr.collections.List;
import in.co.rays.project3.dto.RoleDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DatabaseException;
import in.co.rays.project3.exception.DuplicateRecordException;

/**
 * Data Access Object of Role
 *
 * @author Vaishali
 
 */

public interface RoleModelInt {
	
	
    public long add(RoleDTO dto) throws ApplicationException,
            DuplicateRecordException;

    public void update(RoleDTO dto) throws ApplicationException,
            DuplicateRecordException;

    
    public void delete(RoleDTO dto) throws ApplicationException;

    
    public RoleDTO findByName(String name) throws ApplicationException;

    
    public RoleDTO findByPK(long pk) throws ApplicationException;

   
    public java.util.List search(RoleDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    public java.util.List search(RoleDTO dto) throws ApplicationException;

    public java.util.List list() throws ApplicationException;

    
    public java.util.List list(int pageNo, int pageSize) throws ApplicationException;
    
    
     

}



