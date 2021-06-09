package in.co.rays.project3.dto;
import java.sql.Timestamp;

import in.co.rays.project3.dto.BaseDTO;

/**
 * Subject DTO classes
 *
 * @author Vaishali
 */

public class SubjectDTO extends BaseDTO implements DropdownList {

	private String Subject_Name;
	private String Course_Name;
	private long Course_Id;
	private String Description;
	
	public long getCourse_Id() {
		return Course_Id;
	}
	public void setCourse_Id(long course_Id) {
		Course_Id = course_Id;
	}
	
	
	
	public String getSubject_Name() {
		return Subject_Name;
	}
	public void setSubject_Name(String subject_Name) {
		Subject_Name = subject_Name;
	}
	public String getCourse_Name() {
		return Course_Name;
	}
	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	
	
	public String getkey() {
		// TODO Auto-generated method stub
		return id +"";
	}
	public String getvalue() {
		// TODO Auto-generated method stub
		return Subject_Name;
	}
	
	
	
}
