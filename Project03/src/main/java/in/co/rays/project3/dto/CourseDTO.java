package in.co.rays.project3.dto;

import java.sql.Timestamp;

/**
 * Course DTO classes
 *
 * @author Vaishali
 */
public class CourseDTO extends BaseDTO implements DropdownList  {
	
	private String Course_Name;
	private int Course_Id;
	
	
	private String Discription;
	private String Duration;
	
	

    public String getCourse_Name() {
		return Course_Name;
	}
	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}
	public int getCourse_Id() {
		return Course_Id;
	}
	public void setCourse_Id(int course_Id) {
		Course_Id = course_Id;
	}
	public String getDiscription() {
		return Discription;
	}
	public void setDiscription(String discription) {
		Discription = discription;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	public String getkey() {
		// TODO Auto-generated method stub
		return id +"";
	}
	public String getvalue() {
		// TODO Auto-generated method stub
		return Course_Name;
	}
	    
	
}
