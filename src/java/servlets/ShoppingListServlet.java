package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author WL
 */
public class ShoppingListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        String username = (String) req.getSession().getAttribute("username");
        if (username != null) {
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(req, resp);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        if (action != null) {
            HttpSession session = req.getSession();
            List<String> items;
            String item;
            switch (action) {
                case "register":
                    String username = req.getParameter("username");
                    if (username.matches("^\\w+$")) {
                        session.setAttribute("username", req.getParameter("username"));
                        getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(req, resp);
                    } else {
                        System.out.println("Error username");
                    }
                    break;
                case "logout":
                    session.invalidate();
                    break;
                case "add":
                    items = (List<String>) session.getAttribute("items");
                    item = req.getParameter("item");
                    if (item != null) {
                        if (items == null) {
                            items = new ArrayList<>();
                            items.add(item);
                        } else {
                            items.add(item);
                        }
                        session.setAttribute("items", items);
                    }
                    break;
                case "delete":
                    items = (List<String>) session.getAttribute("items");
                    int index = Integer.parseInt(req.getParameter("radioItem"));
                    items.remove(index);
                    session.setAttribute("items", items);
                    break;
            }
        }
        this.doGet(req, resp);
        return;
    }

}
