package in.co.rays.project3.test;
 
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.project3.dto.UserDTO;
import in.co.rays.project3.exception.ApplicationException;
import in.co.rays.project3.exception.DuplicateRecordException;
import in.co.rays.project3.exception.RecordNotFoundException;
import in.co.rays.project3.model.UserModelHibImp;

import in.co.rays.project3.model.UserModelInt;
 
/**
 * User Model Test classes
 *  
 * @author Vaishali
 
 */
public class UserModelTest {
 
    /**
     * Model object to test
     */
 
    public static UserModelHibImp model = new UserModelHibImp();
 
  //  public static UserModelInt model = new UserModelJDBCImpl();
 
    
    public static void main(String[] args) throws RecordNotFoundException, ParseException, ApplicationException, DuplicateRecordException{
       testAdd();
        //testDelete();
       // testUpdate();
         //testFindByPK();
        // testFindByLogin();
        // testSearch();
        // testList();
       // testGetRoles();
      //  testAuthenticate();
        // testchangePassword();
        // testRegisterUser();
         //testforgetPassword();
        //testresetPassword();
    }
 
   
    public static void testAdd() throws ParseException, ApplicationException, DuplicateRecordException {
 
    //    try {
            UserDTO dto = new UserDTO();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
 
          //  dto.setId(1L);
            dto.setRoleid(1L);
            dto.setFirstName("Damini");
            dto.setLastName("Borade");
            dto.setLogin("daminiborade1@gmail.com");
            dto.setPassword("12345");
//            dto.setConfirmPassword("1234");
            dto.setDob(sdf.parse("06-11-1980"));
            dto.setGender("Male");
            dto.setMobileNo("1234567890");
            dto.setCreatedBy("Admin");
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
            dto.setModifiedBy("Admin");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            
            long pk = model.add(dto);
           // System.out.println("Successfully add");
        //    System.out.println(dto.getDob());
//            UserDTO addedDto = model.findByPK(pk);
//            if (addedDto == null) {
//                System.out.println("Test add fail");
//            }
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        } catch (DuplicateRecordException e) {
//            e.printStackTrace();
//        }
// 
    }
 
    /**
     * Tests delete a User
     */
    public static void testDelete() {
 
        try {
            UserDTO dto = new UserDTO();
           // long pk = 2L;
            dto.setId(2L);
            model.delete(dto);
//            UserDTO deletedDto = model.findByPK(pk);
//            if (deletedDto != null) {
//                System.out.println("Test Delete fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests update a User
     */
    public static void testUpdate() throws ParseException {
 
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy");
            UserDTO dto =new UserDTO();
            //UserDTO dto = model.findByLogin("ranjitchoudhary20@gmail.com");
            dto.setId(6L);
            dto.setFirstName("Shanu");
            dto.setLastName("Soni");
            dto.setLogin("Shanu@gmail.com");
            dto.setPassword("12345");
            dto.setMobileNo("890790807");
            dto.setRoleid(4L);
            dto.setDob(sdf.parse("06-11-1980"));
            dto.setGender("Male");
            dto.setCreatedBy("admin");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            dto.setModifiedBy("admin");
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
//            dto.setMobileNo("953656556");
//            dto.setDob(sdf.parse("31/12/1990"));
// 
            model.update(dto);
 
//            UserDTO updatedDTO = model
//                    .findByLogin("ranjitchoudhary20@gmail.com    ");
//            if (!"ttttttt".equals(updatedDTO.getFirstName())) {
//                System.out.println("Test Update fail");
//            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests find a User by PK.
     */
    public static void testFindByPK() {
        try {
            UserDTO dto = new UserDTO();
            long pk = 1L;
            dto = model.findByPK(pk);
            if (dto == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getFirstName());
            System.out.println(dto.getLastName());
            System.out.println(dto.getLogin());
            System.out.println(dto.getPassword());
            System.out.println(dto.getDob());
            System.out.println(dto.getRoleid());
            
            System.out.println(dto.getGender());
          
          
            System.out.println(dto.getMobileNo());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests find a User by Login.
     */
    public static void testFindByLogin() {
        UserDTO dto = new UserDTO();
        try {
            dto = model.findByLogin("soni@gmail.com");
//            if (dto == null) {
//                System.out.println("Test Find By PK fail");
//            }
           System.out.println(dto.getId());
            System.out.println(dto.getFirstName());
            System.out.println(dto.getLastName());
            System.out.println(dto.getLogin());
            System.out.println(dto.getPassword());
            System.out.println(dto.getDob());
            System.out.println(dto.getRoleid());
          
            System.out.println(dto.getGender());
            
            
            System.out.println(dto.getMobileNo());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests get List.
     */
    public static void testList() {
 
        try {
            UserDTO dto = new UserDTO();
            List list = new ArrayList();
            list = model.list(0, 1);
//            if (list.size() < 0) {
//                System.out.println("Test list fail");
//            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (UserDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getFirstName());
                System.out.println(dto.getLastName());
                System.out.println(dto.getLogin());
                System.out.println(dto.getPassword());
                System.out.println(dto.getDob());
                System.out.println(dto.getRoleid());
            
                System.out.println(dto.getGender());
               
                System.out.println(dto.getMobileNo());
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedDatetime());
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    /**
     * Tests get Roles.
     */
    public static void testGetRoles() {
 
        try {
            UserDTO dto = new UserDTO();
            List list = new ArrayList();
            dto.setRoleid(2L);
            list = model.getRoles(dto);
            if (list.size() < 0) {
                System.out.println("Test Get Roles fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (UserDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getFirstName());
                System.out.println(dto.getLastName());
                System.out.println(dto.getLogin());
                System.out.println(dto.getPassword());
                System.out.println(dto.getDob());
               // System.out.println(dto.getRoleid());
                System.out.println(dto.getGender());
                System.out.println(dto.getMobileNo());
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedDatetime());
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
 
    
    public static void testSearch() {
 
        try {
            UserDTO dto = new UserDTO();
            List list = new ArrayList();
            // dto.setFirstName("ranjit");
            // dto.setLastName("ch");
           // dto.setLogin("ranjitchoudhary20@gmail.com");
            dto.setId(1L);
 
            list = model.search(dto, 0, 0);
            if (list.size() < 0) {
                System.out.println("Test Serach fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (UserDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getFirstName());
                System.out.println(dto.getLastName());
                System.out.println(dto.getLogin());
                System.out.println(dto.getPassword());
                System.out.println(dto.getDob());
                System.out.println(dto.getRoleid());
                 System.out.println(dto.getGender());
                System.out.println(dto.getMobileNo());
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedDatetime());
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    /**
     * Tests authenticate User.
     */
    public static void testAuthenticate() {
 
        try {
        
            
                UserDTO dto = new UserDTO();
                dto.setLogin("soni@gmail.com");
    			//dto.setLogin("boradedamini1@gmail.com");

    		//bean.setPassword("admin");
    	//	bean = model.authenticate(bean.getLogin(), bean.getPassword());
    			model.authenticate("soni@gmail.com","12345");
    	//	System.out.println();
    			if (dto != null) {
    				System.out.println("Successfully login");

    			} else {
    				System.out.println("Invalied login Id & password");
    			}

    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
//            // dto.setLogin("ranjitchoudhar20@gmail.com");
//            // dto.setPassword("rr");
//            dto = model.authenticate(dto.getLogin(), dto.getPassword());
//            dto.setLogin("soni@gmail.com");
//            dto.setPassword("12345");
//            if (dto != null) {
//                System.out.println("Successfully login");
// 
//            } 
//            else {
//                System.out.println("Invalid login Id & password");
//            }
// 
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//    }
// 
    
    public static void testchangePassword()  {
 
        try {
            UserDTO dto = model.findByLogin("ranjitchoudhary20@gmail.com");
            String oldPassword = dto.getPassword();
            dto.setId(15l);
            dto.setPassword("rr");
            String newPassword = dto.getPassword();
            try {
                model.changePassword(15L, oldPassword, newPassword);
            } catch (RecordNotFoundException e) {
                e.printStackTrace();
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
    
 
    public static void testRegisterUser() throws ParseException {
        try {
            UserDTO dto = new UserDTO();
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
 
            // dto.setId(8L);
            dto.setFirstName("vipin");
            dto.setLastName("kumawat");
            dto.setLogin("ranjitch11oudha12ry20@gmail.com");
            dto.setPassword("4444");
//            dto.setConfirmPassword("4444");
            dto.setDob(sdf.parse("11/20/2015"));
            dto.setGender("Male");
            dto.setRoleid(2);
            long pk = model.registerUser(dto);
            System.out.println("Successfully register");
            System.out.println(dto.getFirstName());
            System.out.println(dto.getLogin());
            System.out.println(dto.getLastName());
            System.out.println(dto.getDob());
            UserDTO registerDto = model.findByPK(pk);
            if (registerDto == null) {  
                System.out.println("Test add fail");
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }
    }
 
    
    public static void testforgetPassword() {
        try {
            boolean b = model.forgetPassword("Mayursoni@gmail.com");
 
            System.out.println("Suucess : Test Forget Password Success");
 
        } catch (RecordNotFoundException e) {
            e.printStackTrace();
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }

    public static void testresetPassword() throws RecordNotFoundException  {
        UserDTO dto = new UserDTO();
        try {
            dto = model.findByLogin("ranjitchoudhary20@gmail.com");
            if (dto != null) {
                boolean pass = model.resetPassword(dto);
                if (pass = false) {
                    System.out.println("Test Update fail");
                }
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
 
}
