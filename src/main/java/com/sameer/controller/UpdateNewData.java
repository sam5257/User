package com.sameer.controller;

import com.sameer.business.UserBusinessImpl;
import com.sameer.model.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;

public class UpdateNewData extends HttpServlet {

    final static Logger logger=Logger.getLogger(UpdateNewData.class);
    private UserBusinessImpl userBusinessImpl = new UserBusinessImpl();
    Connection con = null;
    PreparedStatement st =null;
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        try {

            UserInfo userInfo =new UserInfo();

            userInfo.setFirstName(request.getParameter("fname"));
            userInfo.setLastName(request.getParameter("lname"));
            userInfo.setEmail(request.getParameter("email"));
            userInfo.setDate(request.getParameter("dob"));
            userInfo.setId(Integer.valueOf(request.getParameter("id")));

            boolean isUpdated= userBusinessImpl.updateUser(userInfo);
            response.sendRedirect("/User/servlet/update");

/*
         con = DatabaseConnection.initializeDatabase();

            PrintWriter out = response.getWriter();
            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
                st = con
                        .prepareStatement("update User set first_name=?,last_name=? ,email=?,date_of_birth=? where id=?");

            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer

            // Same for second parameter
            st.setInt(5, Integer.parseInt(request.getParameter("id")));
            st.setString(1, request.getParameter("fname"));
            st.setString(2, request.getParameter("lname"));
            st.setString(3, request.getParameter("email"));
            st.setString(4,request.getParameter("dob") );

            // Execute the insert command using executeUpdate()
            // to make changes in database
            st.executeUpdate();
            out.print("alert('Updated successfully.')");
            response.sendRedirect("/User/servlet/update");


 */
        }

         catch (Exception e) {
            logger.error("Inside UpdateNewData",e);
        }
    }




}
