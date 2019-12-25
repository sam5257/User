package com.sameer.database;

import com.sameer.model.Constants;
import com.sameer.model.UserInfo;
import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseOperations implements IDatabaseOperations{

    Connection con = null;
    PreparedStatement st =null;
    final static Logger logger = Logger.getLogger(DatabaseOperations.class);

    public boolean insertUser(UserInfo userInfo) {
        // Initialize the database


        try {
            con = DatabaseConnection.initializeDatabase();

         st = con.prepareStatement(Constants.INSERT_QUERY);

        st.setString(1, userInfo.getFirstName());
        st.setString(2, userInfo.getLastName());
        st.setString(3, userInfo.getEmail());
        st.setString(4, userInfo.getDate());

        st.executeUpdate();

        } catch (SQLException e) {
            logger.error("Inside insertUser method",e);
            return false;
        } catch (ClassNotFoundException e) {
            logger.error("Inside insertUser method",e);
            return false;
        }

        //TODO
        return true;
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
