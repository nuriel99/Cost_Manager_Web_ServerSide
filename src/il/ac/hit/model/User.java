package il.ac.hit.model;

/**
 * this class represents the users in our project Cost Manager.
 */
public class User {

    private int userId;
    private String userName;
    private String lastName;
    private String password;

    /**
     * Empty constructor
     */
    public User() {
    }

    /**
     * This is our program's User constructor
     * @param userId user's userId
     * @param userName user's username
     * @param lastName user's lastName
     * @param password user's password
     * @throws UserDAOException if database is corrupted
     */
    public User(int userId, String userName, String lastName, String password) throws UserDAOException {
        setUserId(userId);
        setUserName(userName);
        setLastName(lastName);
        setPassword(password);
    }

    /**
     * get userId of the user
     * @return An int type containing the item’s userId.
     */
    public int getUserId() {
        return userId;
    }


    /**
     * Sets the user's userId and checking validation
     * @param userId An int type containing the user’s userId.
     * @throws UserDAOException if database is corrupted
     */
    public void setUserId(int userId) throws UserDAOException {
        String temp = userId + "";
        try {
            int checkedId = Integer.parseInt(temp); //if the exception was not thrown
            this.userId = userId;
        } catch (NumberFormatException ex) {
            throw new UserDAOException("wrong input type: incrementalValue");
        }
    }

    /**
     * get userName of the user
     * @return A string type containing the user’s userName.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the user's userName and checking validation
     * @param userName A string type containing the user’s userName.
     * @throws UserDAOException if database is corrupted
     */
    public void setUserName(String userName) throws UserDAOException {
        if (userName.equals(" ") || userName.length() == 0) {
            throw new UserDAOException("Empty string: date");
        } else this.userName = userName;
    }

    /**
     * get lastName of the user
     * @return A string type containing the user’s lastName.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the user's lastName and checking validation
     * @param lastName A string type containing the user’s lastName.
     * @throws UserDAOException if database is corrupted
     */
    public void setLastName(String lastName) throws UserDAOException {
        if (lastName.equals(" ") || lastName.length() == 0) {
            throw new UserDAOException("Empty string: date");
        } else this.lastName = lastName;
    }

    /**
     * get password of the user
     * @return A string type containing the user’s password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password and checking validation
     * @param password A string type containing the user’s password.
     * @throws UserDAOException if database is corrupted
     */
    public void setPassword(String password) throws UserDAOException {
        if (password.equals(" ") || password.length() == 0) {
            throw new UserDAOException("Empty string: date");
        } else this.password = password;
    }
}
