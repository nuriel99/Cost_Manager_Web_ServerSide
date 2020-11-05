package il.ac.hit.controller;

import il.ac.hit.model.*;
import org.hibernate.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * This class receives Item requests coming from the router,
 * handles them according to the client's requests
 */
public class ItemController implements IController {

    private static HibernateCostManagerDAO dao;
    private static int incval=5;

    /**
     * action that getting the requested items from User Iterface
     * @param request  the session is a reference to the request per user
     * @param response the session is a reference to the response per user
     * @throws ItemDAOException if database is corrupted
     */
    public void additem(HttpServletRequest request, HttpServletResponse response) throws ItemDAOException, IOException {
        try{
            dao= HibernateCostManagerDAO.getHibernateCostManagerDAO();

            //checking if parameters not null and getting parameters from the received request
            if(request.getSession().getAttribute("userid")!=null &&
               request.getParameter("id") !=null &&
               request.getParameter("cost")!=null &&
               request.getParameter("date")!=null &&
               request.getParameter("type")!=null ) {
                int userIdPerUser = (Integer) request.getSession().getAttribute("userid");
                String temp = request.getParameter("id");
                int idPerUser = Integer.parseInt(temp);
                int costPerUser = Integer.parseInt(request.getParameter("cost"));
                String datePerUser = request.getParameter("date");
                String typePerUser = request.getParameter("type");

                //creating a new Item object
                Item item = new Item(idPerUser, userIdPerUser, costPerUser, datePerUser, typePerUser, incval);
                //add item to db
                dao.addItem(item);
                //getting items according to input userId
                List<Item> itemList = dao.getItems(userIdPerUser);
                if (itemList != null)
                    request.getSession().setAttribute("tableitems", itemList);
            }
        } catch (ItemDAOException e){
            throw new ItemDAOException("Error with adding item",e);
        }
    }

    /**
     * action that getting all the items that match the month and year from User Iterface
     * @param request  the session is a reference to the request per user
     * @param response the session is a reference to the response per user
     * @throws ItemDAOException if database is corrupted
     */
    public void report(HttpServletRequest request, HttpServletResponse response) throws  ItemDAOException {
        List<Item> reportList;
        try{
            dao=HibernateCostManagerDAO.getHibernateCostManagerDAO();
            int userId = (Integer) request.getSession().getAttribute("userid");
            //getting parameters from the received request
            String month=request.getParameter("month");
            String year=request.getParameter("year");
            reportList=dao.getReport(month,year,userId);
            request.getSession().setAttribute("report", reportList);

        } catch (ItemDAOException e){
            throw new ItemDAOException("Error with report list",e);
        }
    }
}
