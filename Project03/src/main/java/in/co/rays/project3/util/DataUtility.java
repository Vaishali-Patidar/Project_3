package in.co.rays.project3.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * Data Utility class to format data from one format to another
 *  
 * @author Vaishali
 */
 
public class DataUtility {
 
    /** Application Date Format. */
    public static final String APP_DATE_FORMAT = "dd-MM-yy";
 
    /** The Constant APP_TIME_FORMAT. */
    public static final String APP_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
 
    /** Date formatter. */
    private static final SimpleDateFormat formatter = new SimpleDateFormat(
            APP_DATE_FORMAT);
 
    /** The Constant timeFormatter. */
    private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(
            APP_TIME_FORMAT);
 
    /**
     * Trims and trailing and leading spaces of a String
     *  
     *
     * @param val the val
     * @return the string
     */
    public static String getString(String val){
		if(DataValidator.isNotNull(val)){
			return val.trim();
		}else{
			return val;
		}
	}
	
	/**
	 * Gets the string data.
	 *
	 * @param val the val
	 * @return the string data
	 */
	public static String getStringData(Object val){
		if(val!=null){
			return val.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * Gets the int.
	 *
	 * @param val the val
	 * @return the int
	 */
	public static int getInt(String val){
		if(DataValidator.isInteger(val)){
			return Integer.parseInt(val);
		}else{
			return 0;
		}
	}
	
	/**
	 * Gets the long.
	 *
	 * @param val the val
	 * @return the long
	 */
	public static Long getLong(String val){
		if(DataValidator.isLong(val)){
			return Long.parseLong(val);
		}else{
			return (long) 0;
		}
	}
	
	/**
	 * Gets the date.
	 *
	 * @param val the val
	 * @return the date
	 */
	public static Date getDate(String val){
		Date date=null;
		try{
			date=formatter.parse(val);
			
		}catch(Exception e){
		}
		
		return date;
		}
		
	/**
	 * Gets the date string.
	 *
	 * @param date the date
	 * @return the date string
	 */
	public static String getDateString(Date date){
		try{
			return formatter.format(date);
		}catch(Exception e){
			
		}
		return "";
		
	}
	
	/**
	 * Gets the date.
	 *
	 * @param date the date
	 * @param day the day
	 * @return the date
	 */
	public static Date getDate(Date date,int day){
		return null;
	}
	
	/**
	 * Ge timestamp.
	 *
	 * @param val the val
	 * @return the timestamp
	 */
	public static Timestamp geTimestamp(String val){
		Timestamp timeStamp=null;
		try{
			timeStamp=new Timestamp(timeFormatter.parse(val).getTime());
			
		}catch(Exception e){
			return null;
		}
		return timeStamp;
		
	}
	
	/**
	 * Gets the time stamp.
	 *
	 * @param l the l
	 * @return the time stamp
	 */
	public static  Timestamp getTimeStamp(long l){
		Timestamp timeStamp=null;
		try{
			timeStamp=new Timestamp(l);
			
		}catch(Exception e){
			return null;
		}
		return timeStamp;
	}
	
	/**
	 * Gets the current time stamp.
	 *
	 * @return the current time stamp
	 */
	public static Timestamp getCurrentTimeStamp(){
		Timestamp timeStamp=null;
		try{
			timeStamp=new Timestamp(new Date().getTime());
		}catch(Exception e){
			
		}return timeStamp;
		
	}
	
	/**
	 * Gets the timestamp.
	 *
	 * @param tm the tm
	 * @return the timestamp
	 */
	public static long getTimestamp(Timestamp tm) {
        try {
            return tm.getTime();
        } catch (Exception e) {
            return 0;
        }
    }
}
