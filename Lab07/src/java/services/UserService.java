package services;

import java.util.List;
import dataaccess.UserDB;
import models.User;

/**
 *
 * @author WL
 */
public class UserService {

    public List<User> getAll() {
        List<User> users;
        UserDB ud = new UserDB();
        RoleService rs = new RoleService();
        users = ud.getAll();
        for (int i = 0; i < users.size(); i++) {
            users.get(i).setRoleName(rs.getRoleName(users.get(i).getRole()));
        }
        return users;
    }

    public User get(String email) {
        UserDB ud = new UserDB();
        RoleService rs = new RoleService();
        User user = ud.get(email);
        user.setRoleName(rs.getRoleName(user.getRole()));
        return user;
    }

    public String add(User user) {
        UserDB ud = new UserDB();
        if (!validate(user)) {
            return "Invalid user data! Please try again.";
        }
        if(ud.get(user.getEmail())!=null){
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
        int active = user.getActive();
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String password = user.getPassword();
        int role = user.getRole();
        if (email == null || email.isEmpty() || email.length() > 40
                || !email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {
            return false;
        }
        if (active != 0 && active != 1) {
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
