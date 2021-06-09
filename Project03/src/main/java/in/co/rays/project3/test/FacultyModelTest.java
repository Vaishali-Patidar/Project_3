package in.co.rays.project3.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project3.dto.FacultyDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.FacultyModelHibImpl;
import in.co.rays.project3.model.FacultyModelInt;

/**
 * Faculty Model Object test
 * @author Vaishali
 *
 */
public class FacultyModelTest {
	 
 	/** Model object to test. */
 
	 public static FacultyModelHibImpl model = new FacultyModelHibImpl();
	 
 	
	    public static void main(String[] args) throws Exception {
	        testAdd();
	        // testDelete();
	        //testUpdate();
	       //  testFindByPK();
	        // testFindByEmailId();
	       // testSearch();
	         //testList();
	 
	    }
	    
    	
//    	public static void testAdd(){
//	    	FacultyDTO dto=new FacultyDTO();
//	    	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy");
//	    	dto.setCollege_id(1L);
//	    	dto.setCollege_Name("davv");
//	    	try {
//				model.add(dto);
//			} catch (ApplicationException e) {
//				e.printStackTrace();
//			} catch (DuplicateRecordException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	    }
//	    
	    
	    public static void testAdd() throws DuplicateRecordException, ParseException, ApplicationException {
	    	
	    	FacultyDTO dto = new FacultyDTO();
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy");
	    	dto.setFirst_Name("aadhar");
	    	dto.setLast_Name("Jain");
	    	dto.setGender("Female");
	    	dto.setDOJ((sdf.parse("6-7-1993")));
	    	dto.setQualification("BCA");
	    	dto.setEmail_id("qwertyuio@gmail.com");
	    	dto.setMobile_No("9090909090");
	    	dto.setCollege_id(10L);
	    	dto.setCollegeName("iippssmm");
	    	dto.setCourse_id(10L);
	    	dto.setCourse_Name("Software");
	    	dto.setSubject_id(10L);
	    	dto.setSubject_Name("Development");
	    	dto.setCreatedBy("Admin");
	    	dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
	    	dto.setModifiedBy("Admin");
	    	dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
	    	System.out.println("Inserted");
	    	long pk=model.add(dto);
	    

//			try {
//				FacultyDTO bean = new FacultyDTO();
//
//				SimpleDateFormat sdf = new SimpleDateFormat("dd-mm-yy");
//				bean.setFirst_Name("ranu");
//				bean.setLast_Name("sharma");
//				bean.setGender("Male");
//				//bean.setDOJ("6/4/1993");
//				bean.setDOJ(sdf.parse("6-8-1993"));
//				bean.setQualification("BA");
//				 bean.setEmail_id("vipul@gmail.com");
//					bean.setMobile_No("8602789566");
//					bean.setCollege_id(45L);
//					bean.setCollegeName("asdfghj");
//					bean.setCourse_id(50);
//					bean.setCourse_Name("xsdfghj");
//					bean.setSubject_id(50);
//					bean.setSubject_Name("sdfghjk");
//					bean.setCreatedBy("Admin");
//					bean.setModifiedBy("Admin");
//					bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
//					bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
//					
//				
//	long pk = model.add(bean);
//					System.out.println("Inserted");
//			//	System.out.println("Test add succ");
//		
//			} catch (ApplicationException e) {
//				e.printStackTrace();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		}

    	/**
    	 * Test update.
    	 */
    	public static void testUpdate() throws DuplicateRecordException{
	    	FacultyDTO dto=new FacultyDTO();
	    	SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy");
	    	dto.setId(1L);
	    	dto.setCollege_id(1);
	    	dto.setCollegeName("cdgi");
	    	try {
				model.update(dto);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
	    }
	    
    	/**
    	 * Test delete.
    	 */
    	public static void testDelete(){
	    	FacultyDTO dto=new FacultyDTO();
	    	dto.setId(1L);
	    	try {
				model.delete(dto);
			} catch (ApplicationException e) {
				e.printStackTrace();
			}
	    }
	        
        	/**
        	 * Test search.
        	 */
        	public static void testSearch() {
	        	 
	            try {
	                FacultyDTO dto = new FacultyDTO();
	                List list = new ArrayList();
	                // dto.setFirstName("ranjit");
	                // dto.setLastName("ch");
	                dto.setId(1l);
	     //dto.setFirstName("shivam");
	                list = model.search(dto, 0, 0);
	                if (list.size() < 0) {
	                    System.out.println("Test Serach fail");
	                }
	                Iterator it = list.iterator();
	                while (it.hasNext()) {
	                    dto = (FacultyDTO) it.next();
	                    System.out.println(dto.getId());
	                    System.out.println(dto.getFirst_Name());
	                    System.out.println(dto.getLast_Name());
	                               }
	     
	            } catch (ApplicationException e) {
	                e.printStackTrace();
	            }
	     
	        }

	        /**
        	 * Test list.
        	 */
        	public static void testList() {
	        	 
	            try {
	            	FacultyDTO dto = new FacultyDTO();
	                List list = new ArrayList();
	                list = model.list();
	                if (list.size() < 0) {
	                    System.out.println("Test list fail");
	                }
	                Iterator it = list.iterator();
	                while (it.hasNext()) {
	                    dto = (FacultyDTO) it.next();
	                    System.out.println(dto.getId());
	                    System.out.println(dto.getFirst_Name());
	                    System.out.println(dto.getLast_Name());
	                    System.out.println(dto.getGender());
	                    System.out.println(dto.getCourse_Name());
	                    System.out.println(dto.getCollegeName());
	                    System.out.println(dto.getMobile_No());
	                    System.out.println(dto.getQualification());
	                    System.out.println(dto.getGender());
	                    System.out.println(dto.getSubject_Name());
	                               }
	     
	            } catch (ApplicationException e) {
	                e.printStackTrace();
	            }
	        }
	     
        	/**
    		 * Tests find a College by PK.
    		 */
    		public static void testFindByPK() {
    			try {
    				FacultyDTO bean = new FacultyDTO();
    				long pk = 1;
    				bean = model.findByPK(pk);
    				if (bean == null) {
    					System.out.println("Test Find By PK fail");
    				}
    				System.out.println(bean.getId());
    				System.out.println(bean.getFirst_Name());
    				System.out.println(bean.getLast_Name());
    				System.out.println(bean.getDOJ());
    				  System.out.println(bean.getQualification());
    				  System.out.println(bean.getEmail_id());
    				System.out.println(bean.getMobile_No());
    				System.out.println(bean.getCollege_id());
    				System.out.println(bean.getCourse_id());
    			   System.out.println(bean.getSubject_id());
    			 
    				
    			} catch (ApplicationException e) {
    				e.printStackTrace();
    			}

    		}
    		
	    	
	    }
	    

