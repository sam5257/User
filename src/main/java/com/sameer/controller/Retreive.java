package com.sameer.controller;

import com.sameer.business.IUserBusiness;
import com.sameer.business.UserBusinessImpl;
import com.sameer.database.DatabaseOperations;
import com.sameer.model.UserInfo;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class Retreive extends HttpServlet {


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {



        IUserBusiness userBusinessImpl =new UserBusinessImpl();
        userBusinessImpl.setDatabaseOperation(new DatabaseOperations());
        ArrayList<UserInfo> userInfoArrayList= userBusinessImpl.retreiveUser();

        request.setAttribute("userList",userInfoArrayList);
        request.getRequestDispatcher("/retrieve.jsp").forward(request, response);



//        PrintWriter out = response.getWriter();
//        out.print("<table width=50% border=1>");
//        out.print("<caption>List Of User:</caption>");
//        out.print("<tr>");
//        out.print("<th>First Name</th>");
//        out.print("<th>Last Name</th>");
//        out.print("<th>Email</th>");
//        out.print("<th>Age</th>");
//        out.print("</tr>");
//
//
//        for(UserInfo info:userInfoArrayList)
//        {
//            out.print("<tr><td>"+info.getFirstName()+" </td><td>"+info.getLastName()+"</td><td>"+info.getEmail()+"</td><td>"+info.getAge()+"</td></tr>");
//
//        }
//
//        out.print("</table>");


    }

/*    public void backup(HttpServletRequest request,
                       HttpServletResponse response){

        try {
            PrintWriter out = response.getWriter();
            Connection con = DatabaseConnection.initializeDatabase();
            PreparedStatement ps=con.prepareStatement("select * from User");

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

            out.print("</tr>");



            while(rs.next())
            {
                out.print("<tr><td>"+rs.getString(2)+" </td><td>"+rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+rs.getString(5)+"</td></tr>");

            }

            out.print("</table>");


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }


    }

    */


}
