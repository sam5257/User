package com.sameer.controller;

import com.sameer.business.CustomerBusinessImpl;
import com.sameer.business.IUserBusiness;
import com.sameer.business.UserBusinessImpl;
import com.sameer.model.UserInfo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

// Servlet Name
public class InsertData extends HttpServlet {
    private static final long serialVersionUID = 1L;
    final static Logger logger=Logger.getLogger(InsertData.class);

    private IUserBusiness userBusiness;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException
    {
        try {
              String userType = request.getParameter("userType");
            if(userType!=null && userType.equalsIgnoreCase("Customer")){
                userBusiness = new CustomerBusinessImpl();
            }else{
                userBusiness = new UserBusinessImpl();
            }

            UserInfo userInfo = new UserInfo();

            userInfo.setFirstName(request.getParameter("fname"));
            userInfo.setLastName(request.getParameter("lname"));
            userInfo.setEmail(request.getParameter("email"));
            userInfo.setDate(request.getParameter("dob"));

            boolean isInserted = userBusiness.saveUser(userInfo);

        }
        catch (Exception e) {
            logger.error("Inside InsertData method");
        }
    }
}