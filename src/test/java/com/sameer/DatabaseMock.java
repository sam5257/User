package com.sameer;

import com.sameer.database.IDatabaseOperations;
import com.sameer.model.UserInfo;
import com.sameer.util.Response;

import java.util.ArrayList;

public class DatabaseMock implements IDatabaseOperations {
    @Override
    public Response insertUser(UserInfo userInfo) {
        return Response.FALSE;
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
