package in.co.rays.project3.dto;

import java.util.Date;

/**
 * Faculty DTO classes
 *
 * @author Vaishali
 */
public class FacultyDTO extends BaseDTO implements DropdownList{

	private String First_Name;
	private String Last_Name;
	private String Gender;
	private Date DOJ;
	private String Qualification;
	private String Email_id;
	private String Mobile_No;
	private long College_id;
	private String CollegeName;
	private long Course_id;
	private String Course_Name;
	private long Subject_id;
	private String Subject_Name;
	
	
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public Date getDOJ() {
		return DOJ;
	}
	public void setDOJ(Date dOJ) {
		DOJ = dOJ;
	}
	public String getQualification() {
		return Qualification;
	}
	public void setQualification(String qualification) {
		Qualification = qualification;
	}
	public String getEmail_id() {
		return Email_id;
	}
	public void setEmail_id(String email_id) {
		Email_id = email_id;
	}
	public String getMobile_No() {
		return Mobile_No;
	}
	public void setMobile_No(String mobile_No) {
		Mobile_No = mobile_No;
	}
	public long getCollege_id() {
		return College_id;
	}
	public void setCollege_id(long college_id) {
		College_id = college_id;
	}
	public String getCollegeName() {
		return CollegeName;
	}
	public void setCollegeName(String collegeName) {
		CollegeName = collegeName;
	}
	public long getCourse_id() {
		return Course_id;
	}
	public void setCourse_id(long course_id) {
		Course_id = course_id;
	}
	public String getCourse_Name() {
		return Course_Name;
	}
	public void setCourse_Name(String course_Name) {
		Course_Name = course_Name;
	}
	public long getSubject_id() {
		return Subject_id;
	}
	public void setSubject_id(long subject_id) {
		Subject_id = subject_id;
	}
	public String getSubject_Name() {
		return Subject_Name;
	}
	public void setSubject_Name(String subject_Name) {
		Subject_Name = subject_Name;
	}
	public String getkey() {
		// TODO Auto-generated method stub
		return id +"";
	}
	public String getvalue() {
		// TODO Auto-generated method stub
		return First_Name;
	}
	
	
	

}
