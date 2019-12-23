package com.sameer.controller;

import com.sameer.business.BusinessClass;
import com.sameer.database.DatabaseConnection;
import com.sameer.model.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class delete extends HttpServlet {

    private BusinessClass businessClass = new BusinessClass();
    Connection con = null;
    PreparedStatement st =null;
    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        try {

            int id = Integer.valueOf(request.getParameter("id"));
            boolean isDeleted=businessClass.deleteUser(id);
            response.sendRedirect("/User/servlet/update");


   /*         con = DatabaseConnection.initializeDatabase();
            PrintWriter out = response.getWriter();
            Connection con = DatabaseConnection.initializeDatabase();
            Statement stmt = null;
            stmt = con.createStatement();
            int id = Integer.parseInt(request.getParameter("id"));
            String sql = "delete from User where id=" + id;
            stmt.executeUpdate(sql);
            out.print("<script>alert('Deleted successfully.')</script>");
            response.sendRedirect("/User/servlet/update");


    */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
