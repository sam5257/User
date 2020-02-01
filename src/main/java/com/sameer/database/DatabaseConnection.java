package com.sameer.database;

import org.springframework.context.annotation.PropertySource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// This class can be used to initialize the database connection
@PropertySource("classpath:db.properties")
public class DatabaseConnection {

    private static DatabaseConnection databaseConnection=null;
    private static Connection con=null;


    // private constructor to force use of initializeDatabase() to create Singleton object.
    private DatabaseConnection() {
      /*  String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";
        // Database name to access
        String dbName = "demoprj";
        String dbUsername = "root";
        String dbPassword = "root@123";

       */

        Properties properties=new Properties();
    //    String fname="db.properties";
        InputStream in = getClass().getClassLoader().getResourceAsStream("db.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String dbDriver = properties.getProperty("dbDriver");
        String dbURL = properties.getProperty("dbURL");
        String dbName = properties.getProperty("dbName");
        String dbUsername = properties.getProperty("dbUsername");
        String dbPassword = properties.getProperty("dbPassword");

        try {
            Class.forName(dbDriver); //It will load the Driver class in JVM.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = DriverManager.getConnection(dbURL + dbName,
                    dbUsername,
                    dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException {
            if(con!=null)
            {
                return  con;
            }
            else {
                synchronized (DatabaseConnection.class) {
                    databaseConnection = new DatabaseConnection();
                    return con;
                }
            }
    }

  /*  public static Connection initializeDatabase()
            throws SQLException, ClassNotFoundException
    {
        // Initialize all the information regarding
        // Database Connection
       String dbDriver = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:3306/";
        // Database name to access
        String dbName = "demoprj";
        String dbUsername = "root";
        String dbPassword = "root@123";

     */

   /*     Class.forName(dbDriver); //It will load the Driver class in JVM.
        // https://javainterviewpoint.com/use-of-class-forname-in-java/
        Connection con = DriverManager.getConnection(dbURL + dbName,
                dbUsername,
                dbPassword);


        return con;


    */
    }


