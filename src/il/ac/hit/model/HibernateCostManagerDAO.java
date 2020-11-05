package il.ac.hit.model;

import org.hibernate.*;
import org.hibernate.cfg.AnnotationConfiguration;
import java.util.ArrayList;
import java.util.List;

/**
 * a class representing model in our project
 * which is managing our database
 */
public class HibernateCostManagerDAO implements ICostManagerDAO {

    private static SessionFactory factory;
    private static HibernateCostManagerDAO costManagerDAO=null;

    /**
     * Empty constructor
     */
    private HibernateCostManagerDAO() {
    }

    /**
     * A design pattern for our database
     * Singleton implementation for HibernateCostManagerDAO
     * @return static class member
     */
    public static HibernateCostManagerDAO getHibernateCostManagerDAO(){
        if(costManagerDAO==null){
            costManagerDAO=new HibernateCostManagerDAO();
        }
        return costManagerDAO;
    }

    /**
     * A design pattern for our database
     * for getting a session per user
     * @return return static class member ,session factory
     */
    public static SessionFactory getSessionFactory(){
        if(factory==null){
            factory=new AnnotationConfiguration().configure().buildSessionFactory();
        }
        return factory;
    }

    /**
     * getting instance of session ,saving the object of the specific item to our database
     * if not succeed a rollback will be committed and an error message will be sent
     * finally closing the session upon completion
     * @param ob Item's object
     * @throws ItemDAOException if database is corrupted
     */
    @Override
    public void addItem(Item ob) throws ItemDAOException {
        Transaction tx=null;
            Session session = getSessionFactory().openSession();
            try {
                tx = session.beginTransaction();
                session.save(ob);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                throw new ItemDAOException("Can't add item", e);
            } finally {
                try {
                    session.close();
                } catch (HibernateException e) {

                }
            }
    }

    /**
     * getting instance of session.
     * getting all items from database for this specific user's id,
     * if not succeed a rollback will be committed and an error message will be sent
     * finally will close the session upon completion
     * @param userId getting user's id
     * @return list of items of this specific user's id
     * @throws ItemDAOException if database is corrupted
     */
    @Override
    public List<Item> getItems(int userId) throws ItemDAOException {
        Transaction tx=null;
        List<Item> list =new ArrayList<>();

        Session session = getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            List items = session.createQuery("from Item").list();
            List userIdPerUser = session.createQuery("select userId from Item").list();

            for (int i = 0; i < items.size(); i++)
                if (userIdPerUser.get(i).equals(userId))
                    list.add((Item) items.get(i));

            session.getTransaction().commit();
        }
        catch (HibernateException e){

            if(tx!=null)
                tx.rollback();
            throw new ItemDAOException("Can't get list of items",e);
        }
        finally{
            try{
                session.close();
            }
            catch (HibernateException e){

            }
        }
        return list;
    }

    /**
     * getting instance of session ,saving the object of the specific user to our database
     * if not succeed a rollback will be committed and an error message will be sent
     * finally closing the session upon completion
     * @param ob User's object
     * @throws UserDAOException if database is corrupted
     */
    @Override
    public void addUser(User ob) throws UserDAOException {

        Transaction tx=null;
        if(!isUserExist(ob)){

        Session session = getSessionFactory().openSession();
            try {
                tx = session.beginTransaction();
                session.save(ob);
                session.getTransaction().commit();
            } catch (HibernateException e) {
                if (tx != null) tx.rollback();
                throw new UserDAOException("Can't add user", e);
            } finally {
                try {
                    session.close();
                } catch (HibernateException e) {

                }
            }
        }
    }

    /**
     * getting instance of session.
     * getting all users from database,
     * if not succeed a rollback will be committed and an error message will be sent
     * finally will close the session upon completion
     * @return list of items of this specific user's id
     * @throws UserDAOException if database is corrupted
     */
    @Override
    public List<User> getUsers() throws UserDAOException {

        Transaction tx=null;
        List<User> list =new ArrayList<>();

        Session session = getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            List users = session.createQuery("from User").list();
            for (int i = 0; i < users.size(); i++)
                list.add((User) users.get(i));

            session.getTransaction().commit();
        }
        catch (HibernateException e){

            if(tx!=null)
                tx.rollback();
            throw new UserDAOException("Can't get list of users",e);
        }
        finally{
            try{
                session.close();
            }
            catch (HibernateException e){

            }
        }
        return list;
    }

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
    @Override
    public List<Item> getReport(String month, String year ,int userId) throws ItemDAOException {
        Transaction tx=null;
        List<Item> list =new ArrayList<>();

        Session session = getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            List items = session.createQuery("from Item").list();
            List dateFromItem = session.createQuery("select date from Item").list();
            List userIdFromItem = session.createQuery("select userId from Item").list();

            for (int i = 0; i < items.size(); i++) {
                String str = (String) dateFromItem.get(i);
                String[] afterSplit = str.split("-");
                int userIdFromDB=(Integer)userIdFromItem.get(i);
                if (afterSplit[0].equals(year) && afterSplit[1].equals(month) && userId==userIdFromDB)
                    list.add((Item) items.get(i));
            }
            session.getTransaction().commit();
        }
        catch (HibernateException e){

            if(tx!=null)
                tx.rollback();
            throw new ItemDAOException("Can't get list of items",e);
        }
        finally{
            try{
                session.close();
            }
            catch (HibernateException e){

            }
        }
        return list;
    }

    /**
     * getting instance of session.
     * checking if the user exists in the database,
     * if not succeed a rollback will be committed and an error message will be sent
     * finally will close the session upon completion
     * @param ob User's object
     * @return a boolean parameter that is true if user exists and false otherwise
     * @throws UserDAOException if database is corrupted
     */
    @Override
    public boolean isUserExist(User ob) throws UserDAOException {
        Transaction tx=null;
        Session session = getSessionFactory().openSession();
        boolean flag = false;
        try{
            tx=session.beginTransaction();
            List users = session.createQuery("select userId from User").list();
            for (Object x:users) {
                if(x.equals(ob.getUserId())) {
                    flag=true;
                    break;
                }
            }
            session.getTransaction().commit();
        }
        catch (HibernateException e){

            if(tx!=null)
                tx.rollback();
            throw new UserDAOException("User not exist",e);
        }
        finally{
            try{
                session.close();
            }
            catch (HibernateException e){

            }
        }
        return flag;
    }
}
