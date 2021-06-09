package in.co.rays.project3.dto;

/**
 * DropdownList interface is implemented by DTOs those are used to create drop
 * down list on HTML pages
 *
 * @author Vaishali
 */
public interface DropdownList {

    /**
     * Returns key of list element
     *
     * @return key
     */
    public String getkey();

    /**
     * Returns display text of list element
     *
     * @return value
     */
    public String getvalue();

}
