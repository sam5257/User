package com.sameer.business;
import com.sameer.database.DatabaseOperations;
import com.sameer.model.UserInfo;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;


public class BusinessClass {

    private DatabaseOperations databaseOperations;
    final static Logger logger = Logger.getLogger(BusinessClass.class);

    public BusinessClass(){
        databaseOperations = new DatabaseOperations();
    }

    public boolean saveUser(UserInfo userInfo){
        try {

            //make operations on user
            return databaseOperations.insertUser(userInfo);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<UserInfo> retreiveUser(){

        ArrayList<UserInfo>  userInfoArrayList = null;

        try {

            //make operations on user
            userInfoArrayList = databaseOperations.getUsers();

            for(UserInfo userInfo : userInfoArrayList){
                int age = getAge(userInfo.getDate());
                userInfo.setAge(age);

            }

        } catch (SQLException e) {
            logger.error("error in retrieveUser method", e);
        }

        return userInfoArrayList;
    }

    public int getAge(String dob) {

        //input validation

        //java regex

        if(dob == null || dob.equals("")){
            return -1;
        }

        logger.info("inside getAge method");

        int age = 0;
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.parse(dob);
        Period diff = Period.between(dateOfBirth , currentDate);

        age=diff.getYears();

        logger.info("leaving getAge method");
        return age;

    }

    public boolean updateUser(UserInfo info) throws SQLException {
        return  databaseOperations.updateUserData(info);
    }


    public UserInfo retrieveUserWithId(int id) throws SQLException, ClassNotFoundException {
        UserInfo u=databaseOperations.retrieveSingleUser(id);
        return u;
    }

    public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
        return databaseOperations.deleteUserData(id);
    }
}