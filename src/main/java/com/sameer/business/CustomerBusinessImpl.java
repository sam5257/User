package com.sameer.business;

import com.sameer.database.DatabaseOperations;
import com.sameer.database.IDatabaseOperations;
import com.sameer.model.UserInfo;

import java.util.ArrayList;

public class CustomerBusinessImpl implements IUserBusiness {
    @Override
    public boolean saveUser(UserInfo userInfo) {
        return false;
    }

    @Override
    public ArrayList<UserInfo> retreiveUser() {
        return null;
    }

    @Override
    public boolean updateUser(UserInfo info) {
        return false;
    }

    @Override
    public UserInfo retrieveUserWithId(int id) {
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public void setDatabaseOperation(IDatabaseOperations databaseOperations) {

    }
}
