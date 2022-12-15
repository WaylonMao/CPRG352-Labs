package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author William Mao
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String logout = req.getParameter("logout");
        HttpSession session = req.getSession();
        if (logout != null) {
            session.invalidate();
            session = req.getSession();
            req.setAttribute("message", "Successfully logged out!");
        }
        User user = (User) session.getAttribute("user");
        if (user != null) {
            resp.sendRedirect("home");
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        return;

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new AccountService().login(username, password);
        if (user == null) {
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("message", "Invalid username or password!");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
        }
    }

}
