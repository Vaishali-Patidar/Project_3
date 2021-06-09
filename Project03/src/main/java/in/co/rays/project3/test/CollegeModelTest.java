package in.co.rays.project3.test;
 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project3.dto.CollegeDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.CollegeModelHibImpl;
import in.co.rays.project3.model.CollegeModelInt;
 
/**
 * College Model Test classes
 *  
 * @author Vaishali  
 */
public class CollegeModelTest {
 
    
 
     public static CollegeModelHibImpl model = new CollegeModelHibImpl();
 
//    public static CollegeModelInt model = new CollegeModelJDBCImpl();
 
    
    public static void main(String[] args) {
       //  testAdd();
       // testDelete();
      //  testUpdate();
        // testFindByName();
        // testFindByPK();
        testSearch();
        //testList();
 
    }
 
    
    public static void testAdd() {
 
        try {
            CollegeDTO dto = new CollegeDTO();
            //dto.setId(1L);
            dto.setCollegeName("sdfghj");
          //  dto.setName("Shubham");
            dto.setAddress("asdfghjk");
            dto.setState("MP");
            dto.setCity("indore");
            dto.setPhoneNo("073124244");
            dto.setCreatedBy("Admin");
            dto.setModifiedBy("Admin");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
            long pk = model.add(dto);
            System.out.println("Test add succ");
//            CollegeDTO addedDto = model.findByPK(pk);
//            if (addedDto == null) {
//                System.out.println("Test add fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    }
 
    
    public static void testDelete() {
 
        try {
            CollegeDTO dto = new CollegeDTO();
            long pk = 2L;
            dto.setId(pk);
            model.delete(dto);
            System.out.println("Test Delete succ");
//            CollegeDTO deletedDto = model.findByPK(pk);
//            if (deletedDto != null) {
//                System.out.println("Test Delete fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    public static void testUpdate() {
 
        try {
        	CollegeDTO dto =new CollegeDTO();
          //  CollegeDTO dto = model.findByPK(7L);
        	dto.setId(1L);
        	dto.setCollegeName("Shubham");
           // dto.setName("ocm");
            model.update(dto);
//            CollegeDTO updatedDTO = model.findByPK(1L);
//            System.out.println("Test Update succ");
//            if (!"Trubaa".equals(updatedDTO.getName())) {
//                System.out.println("Test Update fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    }
 
    
 
    public static void testFindByName() {
 
        try {
            CollegeDTO dto = model.findByName("paul");
            if (dto == null) {
                System.out.println("Test Find By Name fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getCollegeName());
            System.out.println(dto.getAddress());
            System.out.println(dto.getState());
            System.out.println(dto.getCity());
            System.out.println(dto.getPhoneNo());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
   
    public static void testFindByPK() {
        try {
            CollegeDTO dto = new CollegeDTO();
            long pk = 4L;
            dto = model.findByPK(4L);
//            if (dto == null) {
//                System.out.println("Test Find By PK fail");
//            }
            System.out.println(dto.getId());
            System.out.println(dto.getCollegeName());
            System.out.println(dto.getAddress());
            System.out.println(dto.getState());
            System.out.println(dto.getCity());
            System.out.println(dto.getPhoneNo());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
 
    public static void testSearch() {
        try {
            CollegeDTO dto = new CollegeDTO();
            List list = new ArrayList();
            dto.setCollegeName("S");
            //dto.setName("Truba");
            list = model.search(dto, 1, 8);
            if (list.size() <0) {
                System.out.println("Test Search fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (CollegeDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getCollegeName());
                System.out.println(dto.getAddress());
                System.out.println(dto.getState());
                System.out.println(dto.getCity());
                System.out.println(dto.getPhoneNo());
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getModifiedDatetime());
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    
    public static void testList() {
 
        try {
            CollegeDTO dto = new CollegeDTO();
            List list = new ArrayList();
            list = model.list(0, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (CollegeDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getCollegeName());
                System.out.println(dto.getAddress());
                System.out.println(dto.getState());
                System.out.println(dto.getCity());
                System.out.println(dto.getPhoneNo());
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getModifiedDatetime());
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
}