package in.co.rays.project3.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Student DTO classes
 *
 * @author Vaishali
 */

public class StudentDTO extends BaseDTO implements DropdownList{

     
    private String firstName;
    
    private String lastName;
    
    private Date dob;
    
    private String mobileNo;
   
    private String email;
    
    private long collegeId;
    
    private String collegeName;
    
    

   
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getCollegeId() {
        return collegeId;
    }
    public void setCollegeId(Long collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }
    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }
	
	
   public String getkey() {
        return id + "";
    }

    
    public String getvalue() {
        return firstName + " " + lastName;
    }

}
