package in.co.rays.project3.dto;

import java.sql.Timestamp;
import java.util.Date;

/**
  * User DTO classes
 * @author Vaishali
 */

public class UserDTO extends BaseDTO implements DropdownList {
	
   
//    public static final String ACTIVE = "Active";
//    /**
//     * Lock Inactive constant for User
//     */
//    public static final String INACTIVE = "Inactive";
//    
//   
    private String FirstName;
    private String LastName;
    private String Login;
    private String Password;
//    private String ConfirmPassword;
    private Date Dob;
    private String MobileNo;
    private long Roleid;
    private String Gender;
 
   
   

	
	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

//	public String getConfirmPassword() {
//		return ConfirmPassword;
//	}
//
//	public void setConfirmPassword(String confirmPassword) {
//		ConfirmPassword = confirmPassword;
//	}

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		Dob = dob;
	}

	public String getMobileNo() {
		return MobileNo;
	}

	public void setMobileNo(String mobileNo) {
		MobileNo = mobileNo;
	}

	public long getRoleid() {
		return Roleid;
	}

	public void setRoleid(long roleid) {
		Roleid = roleid;
	}

	
	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	
		
	public String getkey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	public String getvalue() {
		// TODO Auto-generated method stub
		return FirstName + " " + LastName;
	}
 
}
