package il.ac.hit.model;

/**
 * this class represents the items in our project Cost Manager.
 */
public class Item {

    private int id;
    private int userId;
    private int cost;
    private String date;
    private String type;
    private int incrementalValue;

    /**
     * Empty constructor
     */
    public Item(){
    }

    /**
     * This is our program's Item constructor
     * @param id item's id
     * @param userId item's user id
     * @param cost item's cost
     * @param date item's date
     * @param type item's type
     * @throws ItemDAOException if database is corrupted
     */
    public Item(int id, int userId, int cost, String date, String type) throws ItemDAOException{
        setId(id);
        setUserId(userId);
        setCost(cost);
        setDate(date);
        setType(type);
    }

    /**
     * This is our program's Item constructor
     * @param id item's id
     * @param userId item of user id
     * @param cost item's cost
     * @param date item's date
     * @param type item's type
     * @param incrementalValue item's incremental value for database
     * @throws ItemDAOException if database is corrupted
     */
    public Item(int id, int userId, int cost, String date, String type, int incrementalValue) throws ItemDAOException{
        this(id,userId,cost,date,type);
        setIncrementalValue(incrementalValue);
    }

    /**
     * get incremental value of the item
     * @return An int type containing the item’s incremental Value.
     */
    public int getIncrementalValue() {
        return incrementalValue;
    }

    /**
     * Sets the item's incrementalValue and checking validation
     * @param incrementalValue An int type containing the item’s incremental Value.
     * @throws ItemDAOException if database is corrupted
     */
    public void setIncrementalValue(int incrementalValue) throws ItemDAOException {
        String temp = incrementalValue + "";
        try {
            int checkedId = Integer.parseInt(temp); //if the exception was not thrown
            this.incrementalValue = incrementalValue;
        } catch (NumberFormatException ex) {
            throw new ItemDAOException("wrong input type: incrementalValue");
        }
    }

    /**
     * get id of the item
     * @return An int type containing the item’s id.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the item's id and checking validation
     * @param id An int type containing the item’s id.
     * @throws ItemDAOException if database is corrupted
     */
    public void setId(int id) throws ItemDAOException {
        String temp = id + "";
        try {
            int checkedId = Integer.parseInt(temp); //if the exception was not thrown
            this.id = id;
        } catch (NumberFormatException ex) {
            throw new ItemDAOException("wrong input type: id");
        }
    }
    /**
     * get userId of the item
     * @return An int type containing the item’s userId.
     */
    public int getUserId() {
        return userId;
    }


    /**
     * Sets the item's userId and checking validation
     * @param userId An int type containing the item’s userId.
     * @throws ItemDAOException if database is corrupted
     */
    public void setUserId(int userId) throws ItemDAOException {
        String temp = userId + "";
        try {
            int checkedId = Integer.parseInt(temp); //if the exception was not thrown
            this.userId = userId;
        } catch (NumberFormatException ex) {
            throw new ItemDAOException("wrong input type: userId");
        }
    }

    /**
     * get cost of the item
     * @return An int type containing the item’s cost.
     */
    public int getCost() {
        return cost;
    }

    /**
     * Sets the item's cost and checking validation
     * @param cost An int type containing the item’s cost.
     * @throws ItemDAOException if database is corrupted
     */
    public void setCost(int cost) throws ItemDAOException {
        String temp = cost + "";
        try {
            int checkedCost = Integer.parseInt(temp); //if the exception was not thrown
            this.cost = cost;
        } catch (NumberFormatException ex) {
            throw new ItemDAOException("wrong input type: id");
        }
    }

    /**
     * get date of the item
     * @return A string type containing the item’s date.
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the item's date and checking validation
     * @param date A string type containing the item’s date.
     * @throws ItemDAOException if database is corrupted
     */
    public void setDate(String date) throws ItemDAOException {
        if (date.equals(" ") || date.length() == 0) {
            throw new ItemDAOException("Empty string: date");
        } else this.date = date;
    }

    /**
     * get type of the item
     * @return A string type containing the item’s type.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the item's type and checking validation
     * @param type A string type containing the item’s type.
     * @throws ItemDAOException if database is corrupted
     */
    public void setType(String type) throws ItemDAOException {
        if (type.equals(" ") || type.length() == 0) {
            throw new ItemDAOException("Empty string: type");
        } else this.type = type;
    }

}
