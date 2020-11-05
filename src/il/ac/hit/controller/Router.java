package il.ac.hit.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Servlet implementation class RouterServlet
 * directs the incoming requests to the requested Controller
 */
@WebServlet("/controller/*")
public class Router extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static String CONTROLLERS_PACKAGE = "il.ac.hit.controller";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Router() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * Called by the server (via the service method) to allow a servlet to handle a GET request.
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            response.setContentType("text/html");
            // TODO Auto-generated method stub

            // Returns the part of this request's URL from the protocol name up to
            // the query string in the first line of the HTTP request.
            String text = request.getRequestURI();
            //Splits this string around matches of the given regular expression.
            String[] texts = text.split("/");
            // extracting controller and action
            String controller = texts[3];
            String action = texts[4];

            // building the full qualified name of the controller
            String temp = controller + "Controller";
            String controllerClassName = CONTROLLERS_PACKAGE + "."
                    + temp.substring(0, 1).toUpperCase() + temp.substring(1);

            // instantiating the controller class and calling
            // the action method on the controller object
            Class clazz = Class.forName(controllerClassName);
            Method method = clazz.getMethod(action, HttpServletRequest.class, HttpServletResponse.class);

            if(request.getSession().getAttribute("userid")==null && action.equals("additem")) {
                PrintWriter out = response.getWriter();
                out.println("User is not logged in");
            }
            else{
                //activate the action method by reflection
                method.invoke(clazz.getDeclaredConstructor().newInstance(), request, response);
                // creating a RequestDispatcher object that points at the JSP document
                // which is view of our action
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/" + action + ".jsp");
                dispatcher.include(request, response);
            }

        }catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e) {
            //Prints this throwable and its backtrace to the standard error stream.
            e.printStackTrace();
        }
    }

    /**
     * Called by the server (via the service method) to allow a servlet to handle a POST request.
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }
}
