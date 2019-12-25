package com.sameer.business;
import com.sameer.database.DatabaseOperations;
import com.sameer.database.IDatabaseOperations;
import com.sameer.model.UserInfo;
import org.apache.log4j.Logger;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;

public class UserBusinessImpl implements IUserBusiness{


    private IDatabaseOperations databaseOperations;
    final static Logger logger = Logger.getLogger(UserBusinessImpl.class);
    public UserBusinessImpl(){
            databaseOperations = new DatabaseOperations();
    }

    public boolean saveUser(UserInfo userInfo){

            //make operations on user
            return databaseOperations.insertUser(userInfo);

    }

    public ArrayList<UserInfo> retreiveUser(){

        ArrayList<UserInfo>  userInfoArrayList = null;


            //make operations on user
            userInfoArrayList = databaseOperations.getUsers();

            for(UserInfo userInfo : userInfoArrayList){
                int age = getAge(userInfo.getDate());
                userInfo.setAge(age);

            }

        Collections.sort(userInfoArrayList);

      //  Collections.sort(userInfoArrayList,BusinessClass.sortUserByAge);

        return userInfoArrayList;
    }


    /*
    public static Comparator<UserInfo> sortUserByAge =new Comparator<UserInfo>()
    {
            public int compare (UserInfo u1,UserInfo u2)
            {   int age1=u1.getAge();
                int age2=u2.getAge();
                return age1-age2;
            }
    };

     */

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

    public boolean updateUser(UserInfo info) {
        return  databaseOperations.updateUserData(info);
    }


    public UserInfo retrieveUserWithId(int id)  {
        UserInfo u=databaseOperations.retrieveSingleUser(id);
        int age = getAge(u.getDate());
        u.setAge(age);
        return u;
    }

    public boolean deleteUser(int id) {

              return databaseOperations.deleteUserData(id);
    }
}