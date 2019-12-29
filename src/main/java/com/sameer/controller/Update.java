package com.sameer.controller;

import com.sameer.business.IUserBusiness;
import com.sameer.business.UserBusinessImpl;
import com.sameer.model.UserInfo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Update extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IUserBusiness userBusinessImpl ;

    protected void doGet(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException{

        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-module.xml");

        userBusinessImpl = (UserBusinessImpl) context.getBean("userBusinessImpl");

     /*   userBusinessImpl.setDatabaseOperation(new DatabaseOperations());

      */
        ArrayList<UserInfo> userInfoArrayList= userBusinessImpl.retreiveUser();



        request.setAttribute("userList",userInfoArrayList);
        request.getRequestDispatcher("/update.jsp").forward(request, response);




/*
       try {
        PrintWriter out = response.getWriter();
        Connection con = DatabaseConnection.initializeDatabase();
        PreparedStatement ps= null;

            ps = con.prepareStatement("select * from User");

        out.print("<table width=50% border=1>");
        out.print("<caption>List Of User:</caption>");

        ResultSet rs=ps.executeQuery();


        ResultSetMetaData rsmd=rs.getMetaData();
        int total=rsmd.getColumnCount();
        out.print("<tr>");
        for(int i=2;i<=total;i++)
        {
            out.print("<th>"+rsmd.getColumnName(i)+"</th>");
        }
        out.print("<td>Action</td>");
        out.print("<td>Action</td>");
        out.print("</tr>");

        while(rs.next())
        {

            int id = rs.getInt(1);
            out.print("<tr><td>"+rs.getString(2)+" </td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td><td><a href='updatevalue?id="+id+"'>update</a></td><td><a href='delete?id="+id+"'>Delete</a></td></tr>");

        }

        out.print("</table>");

        }
        catch (Exception e) {
            e.printStackTrace();
        }



       */
    }

}
