package com.sameer.controller;

import com.sameer.business.UserBusinessImpl;
import com.sameer.model.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.sameer.util.AppConfig;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UpdateValue extends HttpServlet {

    private UserBusinessImpl userBusinessImpl ;
    final static Logger logger=Logger.getLogger(UpdateValue.class);

    private static ApplicationContext context;

    static
    {
        context = new AnnotationConfigApplicationContext(AppConfig.class);

    }

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int id = Integer.valueOf(request.getParameter("id"));

            userBusinessImpl = (UserBusinessImpl) context.getBean("userBusinessImpl");

            UserInfo userInfo= userBusinessImpl.retrieveUserWithId(id);

            request.setAttribute("user",userInfo);
            request.getRequestDispatcher("/updatevalue.jsp").forward(request, response);

    /*
            PrintWriter out = response.getWriter();
            Connection con = DatabaseConnection.initializeDatabase();
            Statement stmt = null;
            stmt = con.createStatement();
            String id = request.getParameter("id");

            String sql = "select * from User where id=" + id;

            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            out.print("<html>");
            out.print("<body>");
            out.print("<form action=\"updatenewdata\" method=\"post\">");
            out.print("<input type=\"hidden\" name=\"id\" value=" + rs.getString(1) + ">");
            out.print("<p>First Name:</p>");
            out.print("<input type=\"text\" name=\"fname\" value=" + rs.getString(2) + ">");
            out.print("<br/>");
            out.print("<p>Last Name:</p>");
            out.print("<input type=\"text\" name=\"lname\" value=" + rs.getString(3) + ">");
            out.print("<br/>");
            out.print("<p>Email:</p>");
            out.print("<input type=\"email\" name=\"email\" value=" + rs.getString(4) + ">");
            out.print("<br/><br/>");
            out.print("<p>Date Of Birth:</p>");
            out.print("<input type=\"date\" name=\"dob\" value=" + rs.getString(5) + ">");
            out.print("<br/><br/><br/>");
            out.print("<input type=\"submit\"/>");
            out.print("</form>");
            out.print("<body>");
            out.print("</html>");

     */
        }

        catch (Exception e) {
logger.error("Inside UpdateValue class",e);
        }
    }

}