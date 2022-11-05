package services;

import java.util.List;
import dataaccess.UserDB;
import models.Role;
import models.User;

/**
 *
 * @author WL
 */
public class UserService {

    public List<User> getAll() {
        List<User> users;
        UserDB ud = new UserDB();
        users = ud.getAll();
        return users;
    }

    public User get(String email) {
        UserDB ud = new UserDB();
        User user = ud.get(email);
        System.out.println("Get: " + user);
        return user;
    }

    public String add(User user) {
        UserDB ud = new UserDB();
        if (!validate(user)) {
            return "Invalid user data! Please try again.";
        }
        if (ud.get(user.getEmail()) != null) {
            return "User's email address already exists!";
        }
        ud.insert(user);
        return "User added successfully!";
    }

    public String update(User user) {
        UserDB ud = new UserDB();
        User targetUser = ud.get(user.getEmail());
        if (targetUser == null) {
            return "User not found!";
        }
        if (validate(user)) {
            ud.update(user);
            return "User updated successfully!";
        }
        return "Invalid user data! Please try again.";
    }

    public String delete(User user) {
        UserDB ud = new UserDB();
        User targetUser = ud.get(user.getEmail());
        if (targetUser == null) {
            return "User not found!";
        }
        ud.delete(user);
        return "User deleted successfully!";
    }

    private boolean validate(User user) {
        String email = user.getEmail();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        Role role = user.getRole();
        if (email == null || email.isEmpty() || email.length() > 40
                || !email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
            return false;
        }
        if (firstName == null || firstName.isEmpty() || firstName.length() > 20) {
            return false;
        }
        if (lastName == null || lastName.isEmpty() || lastName.length() > 20) {
            return false;
        }
        if (password == null || password.isEmpty() || password.length() > 20) {
            return false;
        }
        return true;
    }
}
