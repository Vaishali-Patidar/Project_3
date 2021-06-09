package in.co.rays.project3.test;

import java.sql.Timestamp;

//import in.co.rays.project3.model.RoleModelJDBCImpl;
 
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project3.dto.RoleDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.RoleModelHibImpl;
 
/**
 * Role Model Test classes
 *  
 * @author Vaishali 
   
 */

public class RoleModelTest {
	/**
     * Model object to test
     */
 
     public static RoleModelHibImpl model = new RoleModelHibImpl();
 
    //public static RoleModelInt model = new RoleModelJDBCImpl();
 
    
	public static void main(String[]args) throws ApplicationException, DuplicateRecordException {
		testAdd();
	//	testUpdate();
	//	testDelete();
		//testFindByPk();
		//testFindByName();
		//testSearch();
		//testList();
	}

	private static void testList() {
        try {
            RoleDTO dto = new RoleDTO();
            List list = new ArrayList();
          
            list = model.list(0, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (RoleDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getName());
                System.out.println(dto.getDescription());
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
		
	

	private static void testSearch() {
        try {
            RoleDTO dto = new RoleDTO();
            List list = new ArrayList();
            dto.setId(1L);;
            list = model.search(dto, 0, 0);
//            if (list.size() < 0) {
//                System.out.println("Test Serach fail");
//            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (RoleDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getName());
                System.out.println(dto.getDescription());
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedDatetime());
             
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 

		
	}

	private static void testFindByName() {
		 try {
	            RoleDTO dto = new RoleDTO();
	            dto = model.findByName("Student");
//	            if (dto == null) {
//	                System.out.println("Test Find By PK fail");
//	            }
	            System.out.println(dto.getId());
	            System.out.println(dto.getName());
	            System.out.println(dto.getDescription());
	            System.out.println(dto.getCreatedBy());
	            System.out.println(dto.getCreatedDatetime());
	            System.out.println(dto.getModifiedBy());

	            System.out.println(dto.getModifiedDatetime());
	            
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
		
	}

	private static void testFindByPk() {
		try {
            RoleDTO dto = new RoleDTO();
            long pk = 5L;
            dto = model.findByPK(pk);
//            if (dto == null) {
//                System.out.println("Test Find By PK fail");
//            }
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getDescription());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }		
	}

	private static void testDelete() {
		try {
            RoleDTO dto = new RoleDTO();
            long pk = 4L;
            dto.setId(pk);
            model.delete(dto);
            System.out.println("Test Delete succ");
            RoleDTO deletedDto = model.findByPK(pk);
//            if (deletedDto != null) {
//                System.out.println("Test Delete fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }		
	}

	private static void testUpdate() throws ApplicationException, DuplicateRecordException {

		// try {
	            //RoleDTO dto = model.findByPK(4L);
			 RoleDTO dto = new RoleDTO();
			 dto.setId(1L);
	            dto.setName("HR");
	            dto.setDescription("This is the HR Dep");
	            dto.setCreatedBy("admin");
	            dto.setModifiedBy("admin");
	            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
	            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
	            
	            model.update(dto);
	 
//	            RoleDTO updatedDTO = model.findByPK(6L);
//	            System.out.println("Test Update ");
//	            if (!"cmc limited".equals(updatedDTO.getName())) {
//	                System.out.println("Test Update fail");
//	            }
	        } 
//		 catch (ApplicationException e) {
//	            e.printStackTrace();
//	        } catch (DuplicateRecordException e) {
//	            e.printStackTrace();
//	        }
//	    }		
//}

	private static void testAdd()  {
		try{
RoleDTO dto =new RoleDTO();
//SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//dto.setId(1L);
dto.setName("Faculty");
dto.setDescription("Faculty");
dto.setCreatedBy("Faculty");
dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
dto.setModifiedBy("Faculty");
dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
long pk =model.add(dto);


System.out.println("Add Susscessfully");
//RoleDTO addedDto = model.findByPK(pk);
//if (addedDto == null) {
//    System.out.println("Test add fail");
//}
	}
		catch(ApplicationException e){
		e.printStackTrace();
	}catch(DuplicateRecordException e){
		e.printStackTrace();
	}

}
}