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

    private final int PAGE_SIZE = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        String username = (String) req.getSession().getAttribute("username");
        if (username != null) {
            pageSize(req, resp);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(req, resp);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
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
                        // Normally, user will not see this message, unless they remove the JS code in username input tag.
                        req.setAttribute("message", "The username can only contain English letters, numbers, or underscores.");
                    }
                    break;
                case "logout":
                    session.invalidate();
                    break;
                case "add":
                    items = (List<String>) session.getAttribute("items");
                    item = req.getParameter("item");
                    if (item != null && !item.equals("")) {
                        if (items == null) {
                            items = new ArrayList<>();
                            items.add(item);
                        } else {
                            items.add(item);
                        }
                        session.setAttribute("items", items);
                    } else {
                        // Normally, user will not see this message, unless they remove "required" in input tag.
                        req.setAttribute("message", "Please input an item name.");
                    }
                    break;
                case "delete":
                    items = (List<String>) session.getAttribute("items");
                    item = req.getParameter("radioItem");
                    if (item != null) {
                        int index = Integer.parseInt(req.getParameter("radioItem"));
                        items.remove(index);
                        session.setAttribute("items", items);
                    } else {
                        req.setAttribute("message", "Please select an item to delete.");
                    }
                    break;
            }
        }
        this.doGet(req, resp);
        return;
    }

    public void pageSize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<String> items = (List<String>) session.getAttribute("items");
        if (items != null) {
            int sumpage = (items.size() - 1) / PAGE_SIZE + 1;
            int page = 1;
            List<String> showItems;
            req.setAttribute("sumpage", sumpage);
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page"));
            } else {
                req.setAttribute("page", page);
            }
            if (page * PAGE_SIZE > items.size()) {
                showItems = items.subList((page - 1) * PAGE_SIZE, items.size());
            } else {
                showItems = items.subList((page - 1) * PAGE_SIZE, page * PAGE_SIZE);
            }
            req.setAttribute("showItems", showItems);
            req.setAttribute("page", page);
            req.setAttribute("PAGE_SIZE", PAGE_SIZE);
        }
    }
}
