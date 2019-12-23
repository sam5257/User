package com.sameer.controller;


import com.sameer.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseTest {

    public static void main(String[] args) {
        DatabaseConnection databaseConnection= new DatabaseConnection();
        try {
            // Initialize the database
            Connection con = DatabaseConnection.initializeDatabase();

            // Create a SQL query to insert data into demo table
            // demo table consists of two columns, so two '?' is used
            PreparedStatement st = con
                    .prepareStatement("insert into User(first_name,last_name,email,date_of_birth) values('sameer','chatterjee','sameer.chatterji@gmail.com','1994-04-15')");

            // For the first parameter,
            // get the data using request object
            // sets the data to st pointer


            // to make changes in database
            st.executeUpdate();

            // Close all the connections
            st.close();
            con.close();


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
