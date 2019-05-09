/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dal;

import com.context.DBContext;
import com.entity.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tiny
 */
public class UserDAO {

  // Return a list of available users in the system
  public List<Users> selectAll() throws Exception {
    String select = "select * from Users";
    Connection conn = new DBContext().getConnection();
    PreparedStatement ps = conn.prepareStatement(select);
    ResultSet rs = ps.executeQuery();
    List<Users> users = new ArrayList<>();
    while (rs.next()) {
      String userName = rs.getString("username");
      String displayName = rs.getString("displayName");
      users.add(new Users(userName, displayName));
    }
    ps.close();
    conn.close();
    return users;
  }

  public void addUser(Users u) throws Exception {
    List<Users> users = selectAll();
    boolean foundUser = false;
    // Check if users u exists or not
    for (Users x : users) {
      if (x.getUsername().equalsIgnoreCase(u.getUsername())) {
        foundUser = true;
      }
    }

    // Insert a given user u to Users table
    if (!foundUser) {
      String insert = "insert into Users values(?,?)";
      Connection conn = new DBContext().getConnection();
      PreparedStatement ps = conn.prepareStatement(insert);
      ps.setString(1, u.getUsername());
      ps.setString(2, u.getDisplayName());
      ps.executeUpdate();
      ps.close();
      conn.close();
    }
  }

}
