package in.co.rays.project3.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import in.co.rays.project3.dto.DropdownList;
import in.co.rays.project3.exception.ApplicationException;

// TODO: Auto-generated Javadoc
/**
 * HTML Utility class to produce HTML contents like Dropdown List.
 * @author Vaishali
 *
 */
public class HTMLUtility {

   
    public static String getList(String name, String selectedVal,
            HashMap<String, String> map) {

        StringBuffer sb = new StringBuffer("<select class='form-control'  dropdown-item name='" + name + "' >");

        Set<String> keys = map.keySet();
        String val = null;
        boolean select=true;
        if(select){
        	sb.append("<option style='width:160px; heihgt:10px' selected value=''>-------"+name+"--------</option>");
        }

        for (String key : keys) {
            val = map.get(key);
            if (key.trim().equals(selectedVal)) {
                sb.append("<option selected value='" + key + "'>" + val
                        + "</option>");
            } else {
                sb.append("<option value='" + key + "'>" + val + "</option>");
            }
        }
        sb.append("</select>");
        return sb.toString();
    }

   
    public static String getList(String name, String selectedVal, List list) {

        Collections.sort(list);

        List<DropdownList> dd = (List<DropdownList>) list;
        
       
       
       
        StringBuffer sb = new StringBuffer("<select dropdown-menu class='form-control' name='" + name + "'>");
		sb.append("<option style='width:160px; heihgt:10px' selected value=''>-------"+name+" --------</option>");
		
		String key = null;
        String val = null;

        for (DropdownList obj : dd) {
            key = obj.getkey();
            val = obj.getvalue();

            if (key.trim().equals(selectedVal)) {
                sb.append("<option selected value='" + key + "'>" + val
                        + "</option>");
            } else {
                sb.append("<option value='" + key + "'>" + val + "</option>");
            }
        }
        sb.append("</select>");
        System.out.println("get list 2=========" +sb.toString());
        return sb.toString();
    }
}

