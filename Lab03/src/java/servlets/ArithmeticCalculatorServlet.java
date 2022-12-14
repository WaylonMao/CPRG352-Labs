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
@WebServlet(name = "ArithmeticCalculator", urlPatterns = {"/arithmetic"})
public class ArithmeticCalculatorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String result = "---";
        request.setAttribute("result", result);
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String operation = request.getParameter("operation");
        String result = "invalid";
        request.setAttribute("first", first);
        request.setAttribute("last", last);
        if (first == null || last == null || !first.matches("^[0-9]+$") || !last.matches("^[0-9]+$")) {
            request.setAttribute("result", result);
            getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);
            return;
        } else {
            int a = Integer.parseInt(first);
            int b = Integer.parseInt(last);
            int c = 0;
            switch (operation) {
                case "+":
                    c = a + b;
                    break;
                case "-":
                    c = a - b;
                    break;
                case "*":
                    c = a * b;
                    break;
                case "%":
                    try {
                        c = a % b;
                    } catch (ArithmeticException e) {
                        request.setAttribute("result", result);
                        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);
                        return;
                    }
                    break;
            }
            result = Integer.toString(c);
            request.setAttribute("first", first);
            request.setAttribute("last", last);
            request.setAttribute("result", result);
        }
        getServletContext().getRequestDispatcher("/WEB-INF/arithmeticcalculator.jsp").forward(request, response);
        return;
    }

}
