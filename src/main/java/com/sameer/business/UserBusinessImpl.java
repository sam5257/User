package com.sameer.business;

import com.sameer.database.IDatabaseOperations;
import com.sameer.exception.UserException;
import com.sameer.factory.FactoryName;
import com.sameer.factory.IValidator;
import com.sameer.factory.ValidatorFactory;
import com.sameer.model.UserInfo;
import com.sameer.util.CacheManager;
import com.sameer.util.Response;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;

@Component
public class UserBusinessImpl implements IUserBusiness {


    private final static Logger logger = Logger.getLogger(UserBusinessImpl.class);

    private IDatabaseOperations databaseOperations;` `

    private CacheManager cacheManager;

    @Autowired
    private void setDatabaseOperations(IDatabaseOperations databaseOperations) {
        this.databaseOperations = databaseOperations;
    }

    @Autowired
    private void setCacheManager(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }


    public Response saveUser(UserInfo userInfo) throws UserException {

        // UserInfoDTO userInfoDTO=new UserInfoDTO();
        userInfo.setFirstName(userInfo.getFirstName());
        userInfo.setLastName(userInfo.getLastName());
        userInfo.setEmail(userInfo.getEmail());
        userInfo.setDate(userInfo.getDate());

        Response response = null;
        if (validateUser(userInfo)) {
            response = databaseOperations.insertUser(userInfo);
        }
        return response;
    }

    private boolean validateUser(UserInfo userInfo) throws UserException {

        IValidator validator = null;


        validator = ValidatorFactory.getValidator(FactoryName.NAME);

        //if validator returns false the throw new UserException
        if (!validator.validate(userInfo)) {   //make operations on user
            throw new UserException("Length Validation fails");
        }

        validator = ValidatorFactory.getValidator(FactoryName.EMAIL);

        if (!validator.validate(userInfo)) {
            throw new UserException("Email not valid");
        }

        return true;
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

        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.parse(dob);
        Period diff = Period.between(dateOfBirth, currentDate);

        int age = diff.getYears();

        logger.info("leaving getAge method");
        return age;

    }

    public boolean updateUser(UserInfo info) {
        return databaseOperations.updateUserData(info);
    }


    public UserInfo retrieveUserWithId(int id) {
        UserInfo userInfo;
        boolean available = cacheManager.checkCache(id);
        if (available) {
            userInfo = cacheManager.returnCache(id);

        } else {
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