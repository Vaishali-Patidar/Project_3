package in.co.rays.project3.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project3.dto.TimeTableDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.model.ModelFactory;
import in.co.rays.project3.model.TimeTableModelInt;
import in.co.rays.project3.model.TimeTableModelInt;

/**
 * TimeTable Model Object test
 * @author Vaishali
 *
 */
public class TimeTableModelTest {
	
	
	/** The model. */
public static TimeTableModelInt model=ModelFactory.getInstance().getTimeTableModel();
	
	
	public static void main(String[] args) throws ParseException, ApplicationException, DuplicateRecordException {
		TestAdd();
	//TestCheckByCourseName();
		//testSearch();
		//testFindByPK();
		//testUpadte();
	}
	
	
	
	private static void testUpadte() throws ParseException {
		try{
		TimeTableDTO bean = model.findByPK(2);
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yy");
		Date date=sdf.parse("14-9-1996");
		//bean.setId(1L);
		
		bean.setCourse_Name("MATHS");
		bean.setSubject_Name("pure");
//		bean.setCourse_Id(1);
//		bean.setCourse_Name("St.paul");
//		bean.setSubject_Id(4);
//		bean.setSubject_Name("Java");
//		//bean.setExam_Date("10-8-1996");
//		bean.setExam_Time("2Pm");
//		bean.setSemester("5");
//		bean.setCreatedBy("hr");
//		bean.setModifiedBy("hr");
//		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
//		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
//				
		model.update(bean);
		System.out.println("Upadated");

//		TimetableDTO updatedbean = model.findByPK(2);
		/*if (!"Material Technology".equals(updatedbean.getSubjectName())) {
			System.out.println("Test Update fail");
		}else{
			System.out.println("Test Update Successfully");
		}*/
		System.out.println("-------------------------------");
	} catch (ApplicationException e) {
		e.printStackTrace();
	} catch (DuplicateRecordException ex) {
		ex.printStackTrace();
	}

		
	}



	public static void testSearch() throws ApplicationException{
		
		TimeTableDTO dto=new TimeTableDTO();
		
		
	    List list=	model.search(dto);
	    
	    Iterator it =list.iterator();
	    while (it.hasNext()) {
	    	TimeTableDTO dto1 = (TimeTableDTO) it.next();
			System.out.println(dto1.getCourse_Id());
			System.out.println(dto.getCourse_Name());
	 		
		}
	}
	
	 /**
 	 * Test find by PK.
 	 */
 	public static void testFindByPK() {
	        try {
	        	TimeTableDTO dto = new TimeTableDTO();
	            long pk = 1L;
	            dto = model.findByPK(pk);
	            if (dto == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(dto.getId());
	            System.out.println(dto.getCourse_Name());
	         //   System.out.println(dto.getDescription());
	            System.out.println(dto.getExam_Time());
	            System.out.println(dto.getSemester());
	            System.out.println(dto.getSubject_Name());
	            System.out.println(dto.getExam_Date());
	           System.out.println(dto.getCreatedBy());
	            System.out.println(dto.getModifiedBy());
	            System.out.println(dto.getCreatedDatetime());
	            System.out.println(dto.getModifiedDatetime());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	 
	    }
	 
	
	

	
	public static void TestAdd() throws ParseException, ApplicationException, DuplicateRecordException{
		String date="17/12/2019";
		SimpleDateFormat sdt=new SimpleDateFormat("dd/mm/yy");
		Date d= sdt.parse(date);
		
		TimeTableDTO dto=new TimeTableDTO();
//		dto.setCourse_Id(1);
//		dto.setExam_Date(d);
		dto.setCourse_Id(2L);
		//dto.setCourse_Name("phython");
		//dto.setSubject_Name("core");
		dto.setSubject_Id(2L);
		dto.setExam_Date(sdt.parse("06/11/1980"));
		dto.setExam_Time("07:00 AM to 8:00 AM");
		dto.setSemester("6th");
		dto.setCreatedBy("admin");
		dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
		dto.setModifiedBy("admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
		model.add(dto);
		System.out.println("test Successfull");
		
		
	}
	
	
	
	/*public static void TestCheckByCourseName() throws ApplicationException, ParseException{
		
		String date="17/12/2019";
		SimpleDateFormat sdt=new SimpleDateFormat("MM/dd/yyyy");
		Date d= sdt.parse(date);
		
		TimeTableDTO dto=model.checkByCourseName(1,d);
	if(dto!=null){
		System.out.println("success");
	}else {
		System.out.println("failure");
	}
		
		
		dto.getCourseId();
		
		
	
	
	
	}
	*/}	
	

