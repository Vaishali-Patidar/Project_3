package in.co.rays.project3.exception;
/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 *
 * @author Vaishali

 */

public class DatabaseException extends Exception {
public DatabaseException(String msg){
	super(msg);
}
}
