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
import models.Role;
import models.User;

/**
 *
 * @author awarsyle
 */
public class RoleDB {
    public Role getRole(int roleID) throws SQLException {

        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            Role role = null;
            String preparedQuery = "SELECT RoleID, RoleName FROM role_table WHERE RoleID=?";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt(1, roleID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String roleName = rs.getString(2);
                role = new Role(roleID, roleName);
            }

            return role;
        } finally {
            connectionPool.freeConnection(connection);
        }
    }
    
    public ArrayList<Role> getAll() throws SQLException{
        
        ConnectionPool connectionPool = null;
        Connection connection = null;
        Role role = null;
        ArrayList<Role> roleList = new ArrayList<>();
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();
            
            
            String preparedQuery = "SELECT RoleID, RoleName FROM role_table";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int roleId = rs.getInt(1);
                String roleName = rs.getString(2);
                role = new Role(roleId, roleName);
                roleList.add(role);
            }

            return roleList;
        } finally {
            connectionPool.freeConnection(connection);
        }        
    }
    
    public void insert(Role role) throws SQLException{
        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            
            String preparedQuery = "insert into role_table (roleID, roleName)" +
                " values (?, ?)";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt (1, role.getRoleID());
            ps.setString (2, role.getRoleName());
            ResultSet rs = ps.executeQuery();            
        } finally {
            connectionPool.freeConnection(connection);
        }
    }
    
    public void delete(Role role) throws SQLException{
        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            
            String preparedQuery = "DELETE FROM role_table WHERE RoleID = ?";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt (1, role.getRoleID());
            ResultSet rs = ps.executeQuery();            
        } finally {
            connectionPool.freeConnection(connection);
        }
    }
    
    public void update(Role role) throws SQLException{
        ConnectionPool connectionPool = null;
        Connection connection = null;
        try {
            connectionPool = ConnectionPool.getInstance();
            connection = connectionPool.getConnection();

            
            String preparedQuery = "Update";
            PreparedStatement ps = connection.prepareStatement(preparedQuery);
            ps.setInt (1, role.getRoleID());
            ResultSet rs = ps.executeQuery();            
        } finally {
            connectionPool.freeConnection(connection);
        }
    }
}
