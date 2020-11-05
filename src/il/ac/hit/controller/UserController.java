package il.ac.hit.controller;

import il.ac.hit.model.HibernateCostManagerDAO;
import il.ac.hit.model.ICostManagerDAO;
import il.ac.hit.model.User;
import il.ac.hit.model.UserDAOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * This class receives User requests coming from the router,
 * handles them according to the client's requests
 */
public class UserController implements IController {

    private static ICostManagerDAO dao;

    /**
     * login action handles request to find and verify the user with the database.
     * @param request  the session is a reference to the request per user
     * @param response the session is a reference to the response per user
     * @throws UserDAOException if database is corrupted
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws UserDAOException {
        List<User> users;
        try {
            dao = HibernateCostManagerDAO.getHibernateCostManagerDAO();
            users = dao.getUsers();
            //get all users from database
            //getting parameters from the received request
            String username = request.getParameter("username");
            String pass = request.getParameter("password");

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUserName().equals(username) &&
                        users.get(i).getPassword().equals(pass)) {
                    request.getSession().setAttribute("userid", users.get(i).getUserId());
                    request.getSession().setAttribute("check", "User Logged in successfully");
                    break;
                } else

                    request.getSession().setAttribute("check", "User is not signed up");
            }
        } catch (UserDAOException e) {
            throw new UserDAOException("Error with Login", e);
        }
    }

    /**
     * action that handles a request to add a new user to the database if no such user exists create a new user.
     * @param request  the session is a reference to the request per user
     * @param response the session is a reference to the response per user
     * @throws UserDAOException if database is corrupted
     */
    public void signup(HttpServletRequest request, HttpServletResponse response) throws UserDAOException {

        System.out.println("the request is " + request.toString());
        List<User> usersList;
        try {
            dao = HibernateCostManagerDAO.getHibernateCostManagerDAO();
            usersList = dao.getUsers();
            boolean exists = false;
            if (request.getParameter("userid") != null) {
                for (int i = 0; i < usersList.size(); i++) {
                    if (usersList.get(i).getUserId() == Integer.parseInt(request.getParameter("userid"))) {
                        exists = true;
                        request.getSession().setAttribute("check1", "User already exists");
                        break;
                    }
                }

                if (!exists) {
                    int userId = Integer.parseInt(request.getParameter("userid"));
                    String userName = request.getParameter("username");
                    String lastName = request.getParameter("lastname");
                    String pass = request.getParameter("password");

                    User user = new User(userId, userName, lastName, pass);
                    dao.addUser(user);
                    request.getSession().setAttribute("check1", "User registered successfully");
                }
            }
        } catch (UserDAOException e) {
            throw new UserDAOException("Error with Sign-up", e);
        }
    }

    /**
     * action that invalidates the session of the user
     * @param request  the session is a reference to the request per user
     * @param response the session is a reference to the response per user
     */
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
    }
}
