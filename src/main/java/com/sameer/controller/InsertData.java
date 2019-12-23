package com.sameer.controller;

import com.sameer.business.BusinessClass;
import com.sameer.model.UserInfo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet Name
public class InsertData extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private BusinessClass businessClass = new BusinessClass();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {
        try {

            UserInfo userInfo = new UserInfo();

            userInfo.setFirstName(request.getParameter("fname"));
            userInfo.setLastName(request.getParameter("lname"));
            userInfo.setEmail(request.getParameter("email"));
            userInfo.setDate(request.getParameter("dob"));

            boolean isInserted = businessClass.saveUser(userInfo);

            if(isInserted){

                request.getRequestDispatcher("inserted.jsp").forward(request, response);
            }else {

            }

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}