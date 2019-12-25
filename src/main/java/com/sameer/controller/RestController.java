package com.sameer.controller;
import com.google.gson.Gson;
import com.sameer.business.IUserBusiness;
import com.sameer.business.UserBusinessImpl;
import com.sameer.model.Message;
import com.sameer.model.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class RestController extends HttpServlet {

    private IUserBusiness userBusinessImpl =new UserBusinessImpl();
    final static Logger logger=Logger.getLogger(RestController.class);
    PrintWriter printWriter = null;
    Message message = new Message();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) {
        String body = getBody(request);

        Gson g=new Gson();
        UserInfo userInfo=g.fromJson(body,UserInfo.class);
        boolean isInserted = userBusinessImpl.saveUser(userInfo);

        if(isInserted){

            message.setMessage("user info saved");

        }else {

            message.setMessage("user info not saved");

        }
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            logger.error("Inside doPost method ",e);
        }
        printWriter.print(g.toJson(message));

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        int id = Integer.valueOf(request.getParameter("id"));
        try {
            UserInfo userInfo= userBusinessImpl.retrieveUserWithId(id);
            Gson gson=new Gson();
            printWriter=response.getWriter();
            printWriter.print(gson.toJson(userInfo));

        }
        catch (IOException e)
        {
            logger.error("Inside doGet method ",e);
        }
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("id"));
        try {
            boolean isDeleted= userBusinessImpl.deleteUser(id);
            Gson gson=new Gson();
            if(isDeleted){

                message.setMessage("user info deleted successfully");

            }else {

                message.setMessage("user info not deleted");

            }
            printWriter=response.getWriter();
            printWriter.print(gson.toJson(message));

        }
        catch (IOException e)
        {
            logger.error("Inside doDelete method",e);
        }
    }


    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        String body=getBody(request);
        Gson gson=new Gson();
        UserInfo userInfo=gson.fromJson(body,UserInfo.class);
        try {
            boolean isUpdated = userBusinessImpl.updateUser(userInfo);

            if (isUpdated) {

                message.setMessage("user info updated successfully");

            } else {

                message.setMessage("user info not updated");

            }
            printWriter = response.getWriter();
            printWriter.print(gson.toJson(message));

        } catch (IOException e)
        {}


    }

    private String getBody(HttpServletRequest request) {


        StringBuffer sb = new StringBuffer();
        BufferedReader bufferedReader = null;
        String content = "";

        try {
            //InputStream inputStream = request.getInputStream();
            //inputStream.available();
            //if (inputStream != null) {
            bufferedReader = request.getReader(); //new BufferedReader(new InputStreamReader(inputStream));
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
                sb.append(charBuffer, 0, bytesRead);
            }
            //} else {
            //        sb.append("");
            //}

        } catch (IOException e) {

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {

                }
            }
        }

        return sb.toString();

    }
}
