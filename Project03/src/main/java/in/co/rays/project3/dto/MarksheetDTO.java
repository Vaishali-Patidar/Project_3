package in.co.rays.project3.dto;

import java.sql.Timestamp;

/**
 * Marksheet DTO classes
 *
 * @author  Vaishali
 *
 */

public class MarksheetDTO extends BaseDTO implements DropdownList{

     
    private String rollNo;
    
    private long studentId;
    
    private String name;
    
    private Integer physics;
    
    private Integer chemistry;
   
    private Integer maths;
    
   
    public String getRollNo() {
        return rollNo;
    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhysics() {
        return physics;
    }

    public void setPhysics(Integer physics) {
        this.physics = physics;
    }

    public Integer getChemistry() {
        return chemistry;
    }

    public void setChemistry(Integer chemistry) {
        this.chemistry = chemistry;
    }

    public Integer getMaths() {
        return maths;
    }

    public void setMaths(Integer maths) {
        this.maths = maths;
    }

	
	
    public String getkey() {
        return id + "";
    }

    public String getvalue() {
        return rollNo;
    }

}

