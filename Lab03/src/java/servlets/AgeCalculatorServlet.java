package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WL
 */
@WebServlet(name = "AgeCalculator", urlPatterns = {"/age"})
public class AgeCalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Load a JSP and forward to the browser
        getServletContext().getRequestDispatcher("/WEB-INF/agecalculator.jsp").forward(request, response);
        return; //IMPORTANT! Stop the code call
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String age = request.getParameter("age");

        request.setAttribute("age", age);

        if (age == null || age.equals("")) {
            String message = "You must give your current age.";
            request.setAttribute("nextage", message);
            getServletContext().getRequestDispatcher("/WEB-INF/agecalculator.jsp").forward(request, response);
            return;
        }
        if (!age.matches("^[0-9]+$")) {
            String message = "You must enter a number.";
            request.setAttribute("nextage", message);
            getServletContext().getRequestDispatcher("/WEB-INF/agecalculator.jsp").forward(request, response);
            return;
        } else {
            String message = "Your age next birthday will be " + String.valueOf(Integer.parseInt(age) + 1);
            request.setAttribute("nextage", message);
        }

        getServletContext().getRequestDispatcher("/WEB-INF/agecalculator.jsp").forward(request, response);
        return;
    }
}
