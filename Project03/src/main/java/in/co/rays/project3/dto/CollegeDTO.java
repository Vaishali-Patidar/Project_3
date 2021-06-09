package in.co.rays.project3.dto;

import java.sql.Timestamp;

/**
 * Collage DTO classes
 *
 * @author Vaishali
 */


public class CollegeDTO extends BaseDTO implements DropdownList {
	
	
	

	    
//	    private String name;
//	    /**
//	     * Address of College
	    
	    private String address;
	   
	    private String state;
	    
	    private String city;
	    
	    private String phoneNo;

	    private String CollegeName;
	    
	    
	    
	   
//	    public String getName() {
//	        return name;
//	    }
//
//	    public void setName(String name) {
//	        this.name = name;
//	    }

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
	    }

	    public String getState() {
	        return state;
	    }

	    public void setState(String state) {
	        this.state = state;
	    }

	    public String getCity() {
	        return city;
	    }

	    public void setCity(String city) {
	        this.city = city;
	    }

	    public String getPhoneNo() {
	        return phoneNo;
	    }

	    public void setPhoneNo(String phoneNo) {
	        this.phoneNo = phoneNo;
	    }

		public String getCollegeName() {
			return CollegeName;
		}

		public void setCollegeName(String CollegeName) {
			this.CollegeName = CollegeName;
		}

		

	    public String getkey() {
	        return id + "";
	    }

	    public String getvalue() {
	        return CollegeName;
	    }

		
	}



