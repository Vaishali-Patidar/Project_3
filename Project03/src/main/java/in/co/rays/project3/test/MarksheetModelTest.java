package in.co.rays.project3.test;
 
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project3.dto.MarksheetDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.MarksheetModelHibImpl;
import in.co.rays.project3.model.MarksheetModelInt;
 
/**
 * Marksheet Model Test classes
 *  
 * @author Vaishali
 
 */
public class MarksheetModelTest {
 
    /**
     * Model object to test
     */
 
     public static MarksheetModelHibImpl model = new MarksheetModelHibImpl();
 
 //   public static MarksheetModelInt model = new MarksheetModelJDBCImpl();
 
    
    public static void main(String[] args) throws ApplicationException {
       // testAdd();
        // testDelete();
         testUpdate();
        // testFindByRollNo();
        // testFindByPK();
        //testList();
      //   testSearch();
     //   testMeritList();
 
    }
 
    /**
     * Tests add a Marksheet
     */
    public static void testAdd() {
 
        try {
            MarksheetDTO dto = new MarksheetDTO();
            //dto.setId(10L);
            dto.setStudentId(2L);
            dto.setRollNo("3");
            dto.setName(" sdfghjk");
            dto.setPhysics(00);
            dto.setChemistry(77);
            dto.setMaths(99);
            dto.setCreatedBy("admin");
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

            dto.setModifiedBy("admin");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            
            long pk = model.add(dto);
//            System.out.println("Test add succ");
//            MarksheetDTO addedDto = model.findByPK(pk);
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
     * Tests delete a Marksheet
     */
    public static void testDelete() {
 
        try {
            MarksheetDTO dto = new MarksheetDTO();
            long pk = 3L;
            dto.setId(pk);
            model.delete(dto);
//            MarksheetDTO deletedDto = model.findByPK(pk);
//            if (deletedDto != null) {
//                System.out.println("Test Delete fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests update a Marksheet
     */
    public static void testUpdate() {
 
        try {
           MarksheetDTO dto = model.findByPK(1L);
        	//MarksheetDTO dto =new MarksheetDTO();
           
          // dto.setName("Monika");
           dto.setPhysics(90);
           dto.setChemistry(70);
           dto.setMaths(89);
           dto.setStudentId(2L);
//        	dto.setId(2L);
//        	dto.setStudentId(2L);
//        	dto.setRollNo("2");
//            dto.setName("Vaishali");
//            dto.setPhysics(98);
//            dto.setChemistry(88);
//            dto.setMaths(88);
//            dto.setCreatedBy("admin");
//            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
//            dto.setModifiedBy("admin");
//            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
            model.update(dto);
          //  System.out.println("Test Update ");
//          MarksheetDTO updatedDTO = model.findByPK(1L);
//            if (!"rk choudhary".equals(updatedDTO.getName())) {
//                System.out.println("Test Update fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a marksheet by Roll No.
     */
 
    public static void testFindByRollNo() throws ApplicationException {
 
        //  MarksheetDTO dto = model.findByRollNo("3");
        	MarksheetDTO dto = new MarksheetDTO();
        	dto.setRollNo("2");
//            if (dto == null) {
//                System.out.println("Test Find By RollNo fail");
//            }
            System.out.println(dto.getId());
            System.out.println(dto.getRollNo());
            System.out.println(dto.getName());
            System.out.println(dto.getPhysics());
            System.out.println(dto.getChemistry());
            System.out.println(dto.getMaths());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
 
    }
 
    /**
     * Tests find a marksheet by PK.
     */
    public static void testFindByPK() {
        try {
            MarksheetDTO dto = new MarksheetDTO();
            long pk = 1L;
            dto = model.findByPK(pk);
//            if (dto == null) {
//                System.out.println("Test Find By PK fail");
//            }
            System.out.println(dto.getId());
            System.out.println(dto.getRollNo());
            System.out.println(dto.getName());
            System.out.println(dto.getPhysics());
            System.out.println(dto.getChemistry());
            System.out.println(dto.getMaths());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests list of Marksheets
     */
    public static void testList() {
        try {
            MarksheetDTO dto = new MarksheetDTO();
            List list = new ArrayList();
            list = model.list(0, 6);
            if (list.size() < 0) {
                System.out.println("Test List fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (MarksheetDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getRollNo());
                System.out.println(dto.getName());
                System.out.println(dto.getPhysics());
                System.out.println(dto.getChemistry());
                System.out.println(dto.getMaths());
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
     * Tests search a Marksheets
     */
 
    public static void testSearch() {
        try {
            MarksheetDTO dto = new MarksheetDTO();
            List list = new ArrayList();
            dto.setName("Marry");
            list = model.search(dto, 0, 10);
//            if (list.size() < 0) {
//                System.out.println("Test Search fail");
//            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (MarksheetDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getRollNo());
                System.out.println(dto.getName());
                System.out.println(dto.getPhysics());
                System.out.println(dto.getChemistry());
                System.out.println(dto.getMaths());
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
     * Tests get the meritlist of Marksheets
     */
    public static void testMeritList() {
        try {
            MarksheetDTO dto = new MarksheetDTO();
            List list = new ArrayList();
            list = model.getMeritList(1, 5);
            if (list.size() < 0) {
                System.out.println("Test List fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (MarksheetDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getRollNo());
                System.out.println(dto.getName());
                System.out.println(dto.getPhysics());
                System.out.println(dto.getChemistry());
                System.out.println(dto.getMaths());
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
