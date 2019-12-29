package com.sameer.controller;

import com.google.gson.Gson;
import com.sameer.business.IUserBusiness;
import com.sameer.business.UserBusinessImpl;
import com.sameer.exception.UserException;
import com.sameer.model.Message;
import com.sameer.model.UserInfo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.sameer.util.Response;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RestController extends HttpServlet {

    private IUserBusiness userBusinessImpl;
    private final static Logger logger = Logger.getLogger(RestController.class);
    PrintWriter printWriter = null;
    Message message = new Message();

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) {
        Gson g = new Gson();
        try {
            String body = getBody(request);
            ApplicationContext context = new ClassPathXmlApplicationContext(
                    "spring-module.xml");

            userBusinessImpl= (UserBusinessImpl) context.getBean("userBusinessImpl");

            printWriter = response.getWriter();
            UserInfo userInfo = g.fromJson(body, UserInfo.class);
            Response userResponse = userBusinessImpl.saveUser(userInfo);

            if (userResponse==Response.TRUE) {

                message.setMessage("user info saved");

            }
            else if(userResponse==Response.INVALID_EMAIL)
            {
                message.setMessage("Duplicate Email");
            }
            else {

                message.setMessage("user info not saved");

            }


            printWriter.print(g.toJson(message));

        } catch (UserException e) {
            message.setMessage(e.getMessage());

            printWriter.print(g.toJson(message));

        } catch (IOException e) {
            logger.error("Inside doPost method ", e);
        }
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("id"));
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext(
                    "spring-module.xml");

            userBusinessImpl= (UserBusinessImpl) context.getBean("userBusinessImpl");
            UserInfo userInfo = userBusinessImpl.retrieveUserWithId(id);
            Gson gson = new Gson();
            printWriter = response.getWriter();
            printWriter.print(gson.toJson(userInfo));

        } catch (IOException e) {
            logger.error("Inside doGet method ", e);
        }
    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.valueOf(request.getParameter("id"));
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-module.xml");

        userBusinessImpl= (UserBusinessImpl) context.getBean("userBusinessImpl");
        try {

            boolean isDeleted = userBusinessImpl.deleteUser(id);
            Gson gson = new Gson();
            if (isDeleted) {

                message.setMessage("user info deleted successfully");

            } else {

                message.setMessage("user info not deleted");

            }
            printWriter = response.getWriter();
            printWriter.print(gson.toJson(message));

        } catch (IOException e) {
            logger.error("Inside doDelete method", e);
        }
    }


    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        String body = getBody(request);
        Gson gson = new Gson();
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "spring-module.xml");

        userBusinessImpl= (UserBusinessImpl) context.getBean("userBusinessImpl");
        UserInfo userInfo = gson.fromJson(body, UserInfo.class);
        try {
            boolean isUpdated = userBusinessImpl.updateUser(userInfo);

            if (isUpdated) {

                message.setMessage("user info updated successfully");

            } else {

                message.setMessage("user info not updated");

            }
            printWriter = response.getWriter();
            printWriter.print(gson.toJson(message));

        } catch (IOException e) {
        }


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
