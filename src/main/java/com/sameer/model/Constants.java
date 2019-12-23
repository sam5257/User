package com.sameer.model;

public class Constants {

    public static final String INSERT_QUERY = "insert into User(first_name,last_name,email,date_of_birth) values(?, ?,?,?)";
    public static final String RETRIEVE_QUERY = "select * from User";
    public static final String SINGLE_USER_QUERY = "select * from User where id=?";
    public static final String UPDATE_QUERY = "update User set first_name=?,last_name=? ,email=?,date_of_birth=? where id=?";
    public static final String DELETE_QUERY = "delete from User where id=?";
}
