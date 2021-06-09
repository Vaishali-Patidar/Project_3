package in.co.rays.project3.test;
 
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project3.dto.StudentDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.StudentModelHibImpl;
import in.co.rays.project3.model.StudentModelInt;
 
/**
 * Student Model Test classes
 *  
 * @author Vaishali
  */
public class StudentModelTest {
 
    /**
     * Model object to test
     */
 
     public static StudentModelHibImpl  model = new StudentModelHibImpl();
 
 //   public static StudentModelInt model = new StudentModelJDBCImpl();
 
   
    public static void main(String[] args) throws ParseException {
      //   testAdd();
        // testDelete();
        //testUpdate();
       //  testFindByPK();
       //  testFindByEmailId();
        testSearch();
        // testList();
 
    }
 
   
    public static void testAdd() throws ParseException {
 
        try {
            StudentDTO dto = new StudentDTO();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 
//            dto.setId(1L);
            dto.setFirstName("ram");
            dto.setLastName("Sharma");
            dto.setDob(sdf.parse("06/11/1991"));
            dto.setMobileNo("9165289078");
            dto.setEmail("shamsharma@gmail.com");
            dto.setCollegeId(7L);
            dto.setCollegeName("Maheswari");
            dto.setCreatedBy("Admin");
            dto.setModifiedBy("Admin");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
            long pk = model.add(dto);
            System.out.println("Test add succ");
            StudentDTO addedDto = model.findByPK(pk);
            if (addedDto == null) {
                System.out.println("Test add fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests delete a Student
     */
    public static void testDelete() {
 
        try {
            StudentDTO dto = new StudentDTO();
            long pk = 3L;
            dto.setId(pk);
            model.delete(dto);
            System.out.println("Test Delete succ");
//            StudentDTO deletedDto = model.findByPK(pk);
//            if (deletedDto != null) {
//                System.out.println("Test Delete fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    
    public static void testUpdate() throws ParseException {
 
        try {
            System.out.println("Test Update scc111");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
          //  StudentDTO dto = model.findByPK(10L);
            StudentDTO dto = new StudentDTO();
            
            //System.out.println("Test Update scc222"+dto.getCollegeName());
            dto.setId(4L);
            dto.setFirstName("ram");
            dto.setLastName("Soni");
            dto.setMobileNo("9165254357");
            dto.setEmail("Shamsoni@gmail.com");
            dto.setDob(sdf.parse("06-11-1980"));
         //   dto.setDob(sdf.parse("12-05-1996"));
            dto.setCollegeId(4L);
            dto.setCollegeName("lnct");
            model.update(dto);
//            StudentDTO updatedDTO = model.findByPK(10L);
//            if (!"ram".equals(updatedDTO.getFirstName())) {
//                System.out.println("Test Update fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests find a Student by PK.
     */
    public static void testFindByPK() {
        try {
            StudentDTO dto = new StudentDTO();
            long pk = 1L;
            dto = model.findByPK(pk);
//            if (dto == null) {
//                System.out.println("Test Find By PK fail");
//            }
            System.out.println(dto.getId());
            System.out.println(dto.getFirstName());
            System.out.println(dto.getLastName());
            System.out.println(dto.getDob());
            System.out.println(dto.getMobileNo());
            System.out.println(dto.getEmail());
            System.out.println(dto.getCollegeId());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a Student by EmailId.
     */
    public static void testFindByEmailId() {
        try {
            StudentDTO dto = new StudentDTO();
            dto = model.findByEmailId("Shamsoni@gmail.com");
//            if (dto == null) {
//                System.out.println("Test Find By EmailId fail");
//            }
            System.out.println(dto.getId());
            System.out.println(dto.getFirstName());
            System.out.println(dto.getLastName());
            System.out.println(dto.getDob());
            System.out.println(dto.getMobileNo());
            System.out.println(dto.getEmail());
            System.out.println(dto.getCollegeId());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests get Search
     */
    public static void testSearch() {
 
        try {
            StudentDTO dto = new StudentDTO();
            List list = new ArrayList();
        //    dto.setId(2L);
           // dto.setFirstName("rk");
            dto.setCollegeName("ips");
            list = model.search(dto, 0, 2);
            if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (StudentDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getFirstName());
                System.out.println(dto.getLastName());
                System.out.println(dto.getDob());
                System.out.println(dto.getMobileNo());
                System.out.println(dto.getEmail());
                System.out.println(dto.getCollegeId());
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getModifiedDatetime());
                System.out.println(dto.getCollegeName());
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
            StudentDTO dto = new StudentDTO();
            List list = new ArrayList();
            list = model.list(0, 2);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (StudentDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getFirstName());
                System.out.println(dto.getLastName());
                System.out.println(dto.getDob());
                System.out.println(dto.getMobileNo());
                System.out.println(dto.getEmail());
                System.out.println(dto.getCollegeId());
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
