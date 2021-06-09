package in.co.rays.project3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project3.dto.SubjectDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.SubjectModelHibImpl;
import in.co.rays.project3.model.SubjectModelInt;

// TODO: Auto-generated Javadoc
/**
 * College Model Test classes
 *  
 * @author Vaishali
 *  
 */
public class SubjectModelTest {
	 
 	/** Model object to test. */
 public static SubjectModelInt model = new SubjectModelHibImpl();
 
 
 public static void main(String[] args) {
    testAdd();
    // testDelete();
    //testUpdate();
  //      testFindByName();
    // testFindByPK();
     //testSearch();
    // testList();

}
 
 private static void testList() {
	// TODO Auto-generated method stub
	 try {
		 SubjectDTO bean = new SubjectDTO();
         List list = new ArrayList();
         list = model.list(1, 10);
         if (list.size() < 0) {
             System.out.println("Test list fail");
         }
         Iterator it = list.iterator();
         while (it.hasNext()) {
             bean = (SubjectDTO) it.next();
             System.out.println(bean.getId());
             System.out.println(bean.getCourse_Id());
             System.out.println(bean.getCourse_Name());
             System.out.println(bean.getDescription());
             System.out.println(bean.getSubject_Name());
                         }

     } catch (ApplicationException e) {
         e.printStackTrace();
     }
 }

	


/**
  * Tests add a Course.
  */
 public static void testAdd(){
	 SubjectDTO dto=new SubjectDTO();
	 dto.setCourse_Id(2L);
	 dto.setSubject_Name("physics");;
	 dto.setCourse_Name("bee");
	 dto.setDescription("best");
	 dto.setCreatedBy("dfgf");
	 dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
	 dto.setModifiedBy("dfghj");
	 dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
	 
	
	 try {
		model.add(dto);
	} catch (ApplicationException e) {
		e.printStackTrace();
	} catch (DuplicateRecordException e) {
		e.printStackTrace();
	}
	 }
	 
 	/**
 	 * Test update.
 	 */
 	public static void testUpdate(){
		 SubjectDTO dto=new SubjectDTO();
	     dto.setId(1L);
		 dto.setSubject_Name("ath");;
		 dto.setCourse_Name("bee");
		 dto.setDescription("best");
		 try {
			model.update(dto);
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

 }


}
