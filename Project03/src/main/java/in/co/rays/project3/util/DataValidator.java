package in.co.rays.project3.util;

import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * This class validates input data
 *  
 * @author Vaishali
 */
 
public class DataValidator {

	   

	
	public static boolean isName(String name) { 

		String namereg = "^[^-\\s][\\p{L} .']+$";
		
		if (isNotNull(name) && name.matches(namereg)) {

			return true;
		} else {
			return false;
		}
	}
	
	
	public static boolean isPasswordLength(String val) {

		if (isNotNull(val) && val.length() >= 8 && val.length() <= 12) {
		
			
			return true;
		} else {
			return false;
		}
	}

	
	public static boolean isValidAge(String val)
	{
		
		boolean pass = false;
		if (isDate(val)) {
			Date cdate = new Date();
			try {
				SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
				Date userdate = format.parse(val);
				int age = cdate.getYear()-userdate.getYear();
				System.out.println("final age  "+age);
				if(age>=18){
					pass=true;
				}
			} catch (java.text.ParseException e) {
				
			}
		}
		
		return pass;
	}


public static boolean isPassword(String pass) { // my method created
        
		System.out.println("validate pass");
		String passreg = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20})";
		//String passreg="^[0-9a-zA-Z]{5}$";
		//String spass = pass.trim();
		//int checkIndex = spass.indexOf(" ");
                                //checkIndex==-1
		if (isNotNull(pass) && pass.matches(passreg) ) {
			System.out.println("true");
			return true;
		}

		else {
			return false;
		}
	}


public static boolean isRollNo(String roll) { 
	
	String rollreg = "[a-zA-Z]{2}[0-9]{4}";
	

	if (DataValidator.isNotNull(roll)) {

		boolean check = roll.matches(rollreg);
		System.out.println(check);
		return check;
	}

	else {

		return false;
	}
}


public static boolean isPhoneNo(String val) {

	String phonereg = "^[6-9][0-9]{9}$";
//	String phonereg = "^[6-9]{10}$";

	if (isNotNull(val)) {
		try {
			return val.matches(phonereg);
		} catch (NumberFormatException e) {
			return false;
		}

	} else {
		return false;
	}
}



public static boolean isPhoneLength(String val) {

	if (isNotNull(val) && val.length() == 10) {
		return true;
	} else {
		return false;
	}
}


	
    public static boolean isNull(String val) {
        if (val == null || val.trim().length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    
    public static boolean isNotNull(String val) {
        return !isNull(val);
    }

   

    public static boolean isInteger(String val) {

        if (isNotNull(val)) {
            try {
                int i = Integer.parseInt(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    public static boolean isLong(String val) {
        if (isNotNull(val)) {
            try {
                long i = Long.parseLong(val);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    
    public static boolean isEmail(String val) {

        String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (isNotNull(val)) {
            try {
                return val.matches(emailreg);
            } catch (NumberFormatException e) {
                return false;
            }

        } else {
            return false;
        }
    }

    
    public static boolean isDate(String val) {

        Date d = null;
        if (isNotNull(val)) {
            d = DataUtility.getDate(val);
        }
        return d != null;
    }

   
    public static void main(String[] args) {

        System.out.println("Not Null 2" + isNotNull("ABC"));
        System.out.println("Not Null 3" + isNotNull(null));
        System.out.println("Not Null 4" + isNull("123"));

        System.out.println("Is Int " + isInteger(null));
        System.out.println("Is Int " + isInteger("ABC1"));
        System.out.println("Is Int " + isInteger("123"));
        System.out.println("Is Int " + isNotNull("123"));
    }
}
