package il.ac.hit.model;


/**
 * Represents the exception class.
 * which is handling exceptions
 * related to HibernateCostManagerDAO that possible to happen.
 */
public class UserDAOException extends Exception {

    /**
     * Exception constructor with message
     * @param message Exception's message
     */
    public UserDAOException(String message) {
        super(message);
    }

    /**
     * Exception constructor with message and cause
     * @param message Exception's message
     * @param cause Exception's cause
     */
    public UserDAOException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Empty exception constructor
     */
    public UserDAOException() {
        super();
    }


}