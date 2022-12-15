package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author William Mao
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user =  (User) session.getAttribute("user");
        if(user == null){
            resp.sendRedirect("login");            
        } else{
            getServletContext().getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
        }
        return;
    }

}
