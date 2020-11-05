/**
 *
 */
package il.ac.hit.model;

import java.util.List;

/**
 * Represents the interface of HibernateCostManagerDAO class
 * gives more accessibility and flexibility to the code
 */
public interface ICostManagerDAO {

    /**
     * getting instance of session ,saving the object of the specific item to our database
     * if not succeed a rollback will be committed and an error message will be sent
     * finally closing the session upon completion
     * @param ob Item's object
     * @throws ItemDAOException if database is corrupted
     */
    public void addItem(Item ob) throws ItemDAOException;

    /**
     * getting instance of session.
     * getting all items from database for this specific user's id,
     * if not succeed a rollback will be committed and an error message will be sent
     * finally will close the session upon completion
     * @param userId getting user's id
     * @return list of items of this specific user's id
     * @throws ItemDAOException if database is corrupted
     */
    public List<Item> getItems(int userId) throws ItemDAOException;

    /**
     * getting instance of session ,saving the object of the specific user to our database
     * if not succeed a rollback will be committed and an error message will be sent
     * finally closing the session upon completion
     * @param ob User's object
     * @throws UserDAOException if database is corrupted
     */
    public void addUser(il.ac.hit.model.User ob) throws UserDAOException;

    /**
     * getting instance of session.
     * getting all users from database,
     * if not succeed a rollback will be committed and an error message will be sent
     * finally will close the session upon completion
     * @return list of items of this specific user's id
     * @throws UserDAOException if database is corrupted
     */
    public List<User> getUsers() throws UserDAOException;

    /**
     * getting instance of session.
     * getting all the items that match the month and year from database,
     * if not succeed a rollback will be committed and an error message will be sent
     * finally will close the session upon completion
     * @param month specific month of bought items that the user want to view
     * @param year specific year of bought items that the user want to view
     * @return list of items that corresponding to the input parameters
     * @throws ItemDAOException if database is corrupted
     */
    public List<Item> getReport(String month ,String year,int userId) throws ItemDAOException;

    /**
     * getting instance of session.
     * checking if the user exists in the database,
     * if not succeed a rollback will be committed and an error message will be sent
     * finally will close the session upon completion
     * @param ob User's object
     * @return a boolean parameter that is true if user exists and false otherwise
     * @throws UserDAOException if database is corrupted
     */
    public boolean isUserExist(il.ac.hit.model.User ob) throws UserDAOException;
}
