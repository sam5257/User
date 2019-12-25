package com.sameer.business;

import com.sameer.database.DatabaseOperations;
import com.sameer.database.IDatabaseOperations;
import com.sameer.model.UserInfo;

import java.util.ArrayList;

public interface IUserBusiness {

    public boolean saveUser(UserInfo userInfo);

    public ArrayList<UserInfo> retreiveUser();

    public boolean updateUser(UserInfo info);

    public UserInfo retrieveUserWithId(int id);

    public boolean deleteUser(int id);

    void setDatabaseOperation(IDatabaseOperations databaseOperations);
}
