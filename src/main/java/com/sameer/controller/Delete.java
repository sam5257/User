package com.sameer.controller;

import com.sameer.business.UserBusinessImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class Delete extends HttpServlet {

    private UserBusinessImpl userBusinessImpl = new UserBusinessImpl();
    Connection con = null;
    PreparedStatement st =null;
    final static Logger logger= Logger.getLogger(Delete.class);

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        try {

            int id = Integer.valueOf(request.getParameter("id"));
            boolean isDeleted= userBusinessImpl.deleteUser(id);
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
            logger.error("Inside delete class",e);
        }
    }

}
