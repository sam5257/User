package com.sameer.database;

import com.sameer.model.Constants;
import com.sameer.model.UserInfo;
import com.sameer.util.Response;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;

@Component
public class DatabaseOperations implements IDatabaseOperations{

    private static Connection con;
    PreparedStatement st =null;
    final static Logger logger = Logger.getLogger(DatabaseOperations.class);

    static {
        try {
            con =  DatabaseConnection.initializeDatabase();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Response insertUser(UserInfo userInfo) {
        // Initialize the database
        try {

            logger.info(userInfo+ "  "+Thread.currentThread().getState());


            synchronized (this) {

                st = con.prepareStatement(Constants.INSERT_QUERY);
                st.setString(1, userInfo.getFirstName());
                st.setString(2, userInfo.getLastName());
                st.setString(3, userInfo.getEmail());
                st.setString(4, userInfo.getDate());

                st.executeUpdate();

            }

        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            logger.error("Duplicate Email .",e);
            if(e.getMessage().contains("email"))
                return Response.INVALID_EMAIL;
            else
                return Response.FALSE;
        }
        catch (SQLException e) {
            logger.error(userInfo);
            logger.error("Inside insertUser method",e);
            return Response.FALSE;
        }

        //TODO
        return Response.TRUE;
    }

    public ArrayList<UserInfo> getUsers()  {
        ArrayList<UserInfo> users = new ArrayList<>();
        PreparedStatement st = null;
        try {
            con = DatabaseConnection.initializeDatabase();
            st = con.prepareStatement(Constants.RETRIEVE_QUERY);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                UserInfo u = new UserInfo();
                u.setDate(rs.getString("date_of_birth"));
                u.setEmail(rs.getString("email"));
                u.setFirstName(rs.getString("first_name"));
                u.setLastName(rs.getString("last_name"));
                u.setId(rs.getInt("id"));
                users.add(u);
            }

        } catch (SQLException e) {
                logger.error("Inside getUsers method",e);

        } catch (ClassNotFoundException e) {
            logger.error("Inside getUsers method",e);
        }

        return users;
    }

    public UserInfo retrieveSingleUser(int id)  {
        UserInfo u = new UserInfo();
        try {


            con = DatabaseConnection.initializeDatabase();
            st = con.prepareStatement(Constants.SINGLE_USER_QUERY);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            rs.next();
            u.setDate(rs.getString("date_of_birth"));
            u.setEmail(rs.getString("email"));
            u.setFirstName(rs.getString("first_name"));
            u.setLastName(rs.getString("last_name"));
            u.setId(rs.getInt("id"));


        }
        catch (SQLException e) {
            logger.error("Inside retrieveSingleUser method",e);

        } catch (ClassNotFoundException e) {
            logger.error("Inside retrieveSingleUser method",e);

        }
        return u;
    }

    public boolean updateUserData(UserInfo userInfo) {

        try {
            con = DatabaseConnection.initializeDatabase();

            st = con.prepareStatement(Constants.UPDATE_QUERY);

            st.setString(1, userInfo.getFirstName());
            st.setString(2, userInfo.getLastName());
            st.setString(3, userInfo.getEmail());
            st.setString(4, userInfo.getDate());
            st.setInt(5,userInfo.getId());

            st.executeUpdate();


        } catch (SQLException e) {
            logger.error("Inside updateUserData method",e);
            return false;
        } catch (ClassNotFoundException e) {
            logger.error("Inside updateUserData method",e);
            return false;
        }
        return true;
    }

    public boolean deleteUserData(int id)  {
        try {
            con = DatabaseConnection.initializeDatabase();

            st = con.prepareStatement(Constants.DELETE_QUERY);
            st.setInt(1, id);
            st.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Inside deleteUserData method",e);
            return false;
        }
        catch (ClassNotFoundException e)
        {
            logger.error("inside deleteUserData method",e);
        }
        return true;
    }
}