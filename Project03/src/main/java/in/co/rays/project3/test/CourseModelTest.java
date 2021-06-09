package in.co.rays.project3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project3.dto.CollegeDTO;
import in.co.rays.project3.dto.CourseDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.CollegeModelHibImpl;
import in.co.rays.project3.model.CourseModelHibImpl;

/**
 * Course Model Object test
 * @author Vaishali
 *
 */
public class CourseModelTest {
	
     public static CourseModelHibImpl model = new CourseModelHibImpl();
 
//    public static CollegeModelInt model = new CollegeModelJDBCImpl();
 
    
    public static void main(String[] args) {
        
    	testAdd();
      //  testDelete();
      // testUpdate();
     //   testFindByName();
        // testFindByPK();
     //    testSearch();
       //  testList();
 
    }
 
    /**
     * Tests add a College
     */
    public static void testAdd() {
 
        try {
            CourseDTO dto = new CourseDTO();
            //dto.setId(1L);
            dto.setCourse_Name("Chemistry");
            dto.setDiscription("admin");
            dto.setDuration("1year");
            dto.setCreatedBy("Admin");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            dto.setModifiedBy("Admin");
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
            long pk = model.add(dto);
            System.out.println("Test add succ");
//            CourseDTO addedDto = model.findByPK(pk);
//            if (addedDto == null) {
//                System.out.println("Test add fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    }
    /**
     * Tests delete a Course
     */
    public static void testDelete() {
 
        try {
            CourseDTO dto = new CourseDTO();
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
    /**
     * Tests update a Cousre
     */
    public static void testUpdate() {
 
        try {
        	CourseDTO dto =new CourseDTO();
          //  CollegeDTO dto = model.findByPK(7L);
        	dto.setId(1L);
        	dto.setCourse_Name("Corporate Java");
        	 dto.setDiscription("admin");
             dto.setDuration("1year");
             dto.setCreatedBy("Admin");
             dto.setModifiedBy("Admin");
             dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
             dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
            
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
    /**
     * Tests find a Cousre by Name.
     */
 
    public static void testFindByName() {
 
        try {
            CourseDTO dto = model.findByName("Physic");
//            if (dto == null) {
//                System.out.println("Test Find By Name fail");
//            }
            System.out.println(dto.getId());
            System.out.println(dto.getCourse_Name());
            System.out.println(dto.getDiscription());
            System.out.println(dto.getDuration());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getModifiedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getCreatedDatetime());
           
          
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
 
    }
    /**
     * Tests find a College by PK.
     */
    public static void testFindByPK() {
        try {
            CourseDTO dto = new CourseDTO();
            long pk = 1L;
            dto = model.findByPK(pk);
//            if (dto == null) {
//                System.out.println("Test Find By PK fail");
//            }
            System.out.println(dto.getId());
            System.out.println(dto.getCourse_Name());
            System.out.println(dto.getDiscription());
            System.out.println(dto.getDuration());
            
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
    /**
     * Tests search a College
     */
 
    public static void testSearch() {
        try {
            CourseDTO dto = new CourseDTO();
            List list = new ArrayList();
            dto.setCourse_Name("Chemistry");
            //dto.setName("Truba");
            list = model.search(dto, 1, 2);
            if (list.size() < 0) {
                System.out.println("Test Search fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (CourseDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getCourse_Name());
                System.out.println(dto.getDiscription());
                System.out.println(dto.getDuration());
                
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getModifiedDatetime());
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
    /**
     * Tests get List.
     */
    public static void testList() {
 
        try {
            CourseDTO dto = new CourseDTO();
            List list = new ArrayList();
            list = model.list(0, 6);
            if (list.size() <= 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (CourseDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getCourse_Name());
                System.out.println(dto.getDiscription());
                System.out.println(dto.getDuration());
               
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
