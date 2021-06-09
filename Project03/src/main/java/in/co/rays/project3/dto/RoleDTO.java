package in.co.rays.project3.dto;


import java.sql.Timestamp;

/**
 * role Marksheet DTO classes

 *
 * @author  Vaishali
 */

public class RoleDTO  extends BaseDTO implements DropdownList{
	
    public static final int ADMIN = 1;
    public static final int STUDENT = 2;
    public static final int COLLEGE_SCHOOL = 3;
    public static final int KIOSK = 4;

   

    private String Name;

    
    private String Description;
    

   

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

	public String getkey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	public String getvalue() {
		// TODO Auto-generated method stub
		return Name;
	}

	
	


   
}
