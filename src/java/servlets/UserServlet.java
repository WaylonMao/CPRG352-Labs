package servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author WL
 */
public class UserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService us = new UserService();
        List<User> users = us.getAll();
        RoleService rs = new RoleService();
        List<Role> roles = rs.getRoles();
        req.setAttribute("roles", roles);
        req.setAttribute("users", users);

        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "edit": {
                String email = req.getParameter("email");
                UserService us = new UserService();
                User user = us.get(email);
                req.setAttribute("editUser", user);
                break;
            }
            case "cancel": {
                req.setAttribute("editUser", null);
                break;
            }
            case "update": {
                String email = req.getParameter("email");
                Integer active = req.getParameter("active") == null ? 0 : 1;
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String password = req.getParameter("password");
                Integer role = Integer.parseInt(req.getParameter("roleId"));
                User user = new User(email, active, firstName, lastName, password, role);
                UserService us = new UserService();
                String message = us.update(user);
                req.setAttribute("message", message);
                break;
            }
            case "add": {
                String email = req.getParameter("email");
                Integer active = req.getParameter("active") == null ? 0 : 1;
                String firstName = req.getParameter("firstName");
                String lastName = req.getParameter("lastName");
                String password = req.getParameter("password");
                Integer role = Integer.parseInt(req.getParameter("roleId"));
                User user = new User(email, active, firstName, lastName, password, role);
                UserService us = new UserService();
                String message = us.add(user);
                req.setAttribute("message", message);
                break;
            }
            case "delete": {
                String email = req.getParameter("email");
                // Just trying to avoid using JSTL :)
                String message = "Are you sure about that? "
                        + "<form method='post'><input type='hidden' name='action' value='remove' />"
                        + "<input type='hidden' name='email' value='" + email + "' />"
                        + "<input type='submit' value='Confirm' /></form><form method='post'>"
                        + "<input type='hidden' name='action' value='cancel' />"
                        + "<input type='submit' value='Cancel' /></form>";
                req.setAttribute("message", message);
                break;
            }
            case "remove": {
                String email = req.getParameter("email");
                UserService us = new UserService();
                String message = us.delete(us.get(email));
                req.setAttribute("message", message);
                break;
            }
            default:{
                req.setAttribute("message", "Please don't hack me -_-! Thank you!");
                break;
            }
        }
        UserService us = new UserService();
        List<User> users = us.getAll();
        RoleService rs = new RoleService();
        List<Role> roles = rs.getRoles();
        req.setAttribute("roles", roles);
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(req, resp);

        return;
    }

}
