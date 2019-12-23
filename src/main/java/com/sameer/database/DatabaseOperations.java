package com.sameer.database;

import com.sameer.model.Constants;
import com.sameer.model.UserInfo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {

    Connection con = null;
    PreparedStatement st =null;

    public boolean insertUser(UserInfo userInfo) throws SQLException {
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
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }finally {
            st.close();
            con.close();
        }

        //TODO
        return true;
    }

    public ArrayList<UserInfo> getUsers() throws SQLException {
        ArrayList<UserInfo> users = new ArrayList<UserInfo>();
        Connection con = null;
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
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            st.close();
            con.close();
        }

        return users;
    }

    public UserInfo retrieveSingleUser(int id) throws SQLException, ClassNotFoundException {

        UserInfo u=new UserInfo();

        con = DatabaseConnection.initializeDatabase();
        st = con.prepareStatement(Constants.SINGLE_USER_QUERY);
        st.setInt(1,id);

        ResultSet rs=st.executeQuery();
        rs.next();
        u.setDate(rs.getString("date_of_birth"));
        u.setEmail(rs.getString("email"));
        u.setFirstName(rs.getString("first_name"));
        u.setLastName(rs.getString("last_name"));
        u.setId(rs.getInt("id"));

        return u;

    }

    public boolean updateUserData(UserInfo userInfo) throws  SQLException{

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
            e.printStackTrace();
            return false;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            st.close();
            con.close();
        }
        return true;
    }

    public boolean deleteUserData(int id) throws SQLException, ClassNotFoundException {
        con = DatabaseConnection.initializeDatabase();

        st = con.prepareStatement(Constants.DELETE_QUERY);
        st.setInt(1, id);
        st.executeUpdate();
        return true;

    }
}
