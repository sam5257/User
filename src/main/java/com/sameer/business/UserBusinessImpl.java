package com.sameer.business;

import com.sameer.database.IDatabaseOperations;
import com.sameer.exception.UserException;
import com.sameer.model.UserInfo;
import com.sameer.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
public class UserBusinessImpl implements IUserBusiness {


    private final static Logger logger = Logger.getLogger(UserBusinessImpl.class);

    private IValidator validator;

    private IDatabaseOperations databaseOperations;

    private CacheManager cacheManager;

    @Autowired
    public void setDatabaseOperation(IDatabaseOperations databaseOperations) {
        this.databaseOperations = databaseOperations;
    }

    @Autowired
    public void setValidator(IValidator validator) {
        this.validator = validator;
    }

    @Autowired
    public void setcacheManager(CacheManager cacheManager)
    {
        this.cacheManager=cacheManager;
    }




    public Response saveUser(UserInfo userInfo) throws UserException {
        // UserInfoDTO userInfoDTO=new UserInfoDTO();
        userInfo.setFirstName(userInfo.getFirstName());
        userInfo.setLastName(userInfo.getLastName());
        userInfo.setEmail(userInfo.getEmail());
        userInfo.setDate(userInfo.getDate());

        if(validator.checkDuplicate(userInfo))
        {

        }

        //if validator returns false the throw new UserException
        if (validator.lengthValidator(userInfo)) {   //make operations on user

            throw new UserException("Length Validate fails");

        }

        if (validator.emptyFieldValidator(userInfo)) {

            throw new UserException("Empty Field");

        }

        if (!validator.isValidEmail(userInfo)) {
            throw new UserException("Email not valid");
        }


        return  databaseOperations.insertUser(userInfo);
    }

    public ArrayList<UserInfo> retreiveUser() {

        ArrayList<UserInfo> userInfoArrayList = null;
        //make operations on user
        userInfoArrayList = databaseOperations.getUsers();

        for (UserInfo userInfo : userInfoArrayList) {
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

        if (dob == null || dob.equals("")) {
            return -1;
        }

        logger.info("inside getAge method");

        int age = 0;
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.parse(dob);
        Period diff = Period.between(dateOfBirth, currentDate);

        age = diff.getYears();

        logger.info("leaving getAge method");
        return age;

    }

    public boolean updateUser(UserInfo info) {
        return databaseOperations.updateUserData(info);
    }


    public UserInfo retrieveUserWithId(int id) {
        UserInfo userInfo;
        boolean available=cacheManager.checkCache(id);
        if (available)
        {  userInfo=cacheManager.returnCache(id);

        }
        else
            {
            userInfo = databaseOperations.retrieveSingleUser(id);
            cacheManager.insertCache(userInfo);
        }
        int age = getAge(userInfo.getDate());
        userInfo.setAge(age);
        return userInfo;
    }



    public boolean deleteUser(int id) {

        return databaseOperations.deleteUserData(id);
    }
}