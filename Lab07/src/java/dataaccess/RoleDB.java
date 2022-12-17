/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Role;

/**
 *
 * @author WL
 */
public class RoleDB {

    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM role";
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                roles.add(new Role(rs.getInt(1),rs.getString(2)));
            }
            return roles;
        } catch (SQLException e) {
            System.out.println("SQLException.");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        return null;
    }

    public Role get(int role_id) {
        ConnectionPool cp = ConnectionPool.getInstance();
        Connection con = cp.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT role_name FROM role WHERE role_id=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, role_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return new Role(role_id,rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("SQLException.");
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            cp.freeConnection(con);
        }
        return null;
    }
}
