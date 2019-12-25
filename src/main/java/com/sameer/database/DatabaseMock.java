package com.sameer.database;

import com.sameer.model.UserInfo;

import java.util.ArrayList;

public class DatabaseMock implements IDatabaseOperations {
    @Override
    public boolean insertUser(UserInfo userInfo) {
        return false;
    }

    @Override
    public ArrayList<UserInfo> getUsers() {

        ArrayList<UserInfo> users = new ArrayList<>();
        UserInfo userInfo= new UserInfo();
        userInfo.setFirstName("sameer");
        users.add(userInfo);
        return users;
    }

    @Override
    public UserInfo retrieveSingleUser(int id) {
        return null;
    }

    @Override
    public boolean updateUserData(UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean deleteUserData(int id) {
        return false;
    }
}
