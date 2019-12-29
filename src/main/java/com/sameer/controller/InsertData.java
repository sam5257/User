package com.sameer.controller;

import com.sameer.business.IUserBusiness;
import com.sameer.business.UserBusinessImpl;
import com.sameer.exception.UserException;
import com.sameer.model.UserInfo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sameer.util.AppConfig;
import com.sameer.util.Response;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

// Servlet Name
public class InsertData extends HttpServlet {

    private final static Logger logger = Logger.getLogger(InsertData.class);

    private IUserBusiness userBusiness;
    private PrintWriter printWriter = null;

    private static boolean useHibernate=true;
    private static ApplicationContext context;

    static {

        if (!useHibernate) {
            context = new ClassPathXmlApplicationContext("spring-module.xml");
        } else {
            context = new AnnotationConfigApplicationContext(AppConfig.class);
        }

    }


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        try {

            /*
            String userType = request.getParameter("userType");
            if (userType != null && userType.equalsIgnoreCase("Customer")) {
                userBusiness = new CustomerBusinessImpl();
            } else {
                userBusiness = new UserBusinessImpl();
                if (useHibernate) {
                     userBusiness.setDatabaseOperation(new HibernateDatabaseOperations());
                } else {
                    userBusiness.setDatabaseOperation(new DatabaseOperations());
                }

            }

             */

            userBusiness = (UserBusinessImpl) context.getBean("userBusinessImpl");



            UserInfo userInfo = new UserInfo();

            userInfo.setFirstName(request.getParameter("fname"));
            userInfo.setLastName(request.getParameter("lname"));
            userInfo.setEmail(request.getParameter("email"));
            userInfo.setDate(request.getParameter("dob"));

            Response userResponse = userBusiness.saveUser(userInfo);

            printWriter = response.getWriter();


            if (userResponse==Response.TRUE) {

                printWriter.print("user info saved");

            }
            else if(userResponse==Response.INVALID_EMAIL)
            {
                printWriter.print("Duplicate Email");
            }

            else {

                printWriter.print("User not saved.");

            }

        } catch (UserException e) {
            printWriter.print("user info not saved");

        } catch (IOException e) {
            logger.error("Inside Insert data , doPost method ", e);
        } catch (Exception e) {
            logger.error("Inside InsertData method", e);
        }
    }
}