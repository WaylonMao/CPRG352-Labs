package services;

import dataaccess.RoleDB;
import java.util.List;
import models.Role;

/**
 *
 * @author WL
 */
public class RoleService {
    private List<Role> roles;
    
    public RoleService(){
        this.getAll();
    }
    
    public void getAll(){
        RoleDB rd = new RoleDB();
        roles = rd.getAll();
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public String getRoleName(int roleId){
        RoleDB rd = new RoleDB();
        return rd.get(roleId).getName();
    }
    
    
}
