package com.sameer.database;

import com.sameer.dto.UserInfoDTO;
import com.sameer.model.UserInfo;
import com.sameer.util.Response;

import java.util.ArrayList;

public interface IDatabaseOperations {


    public Response insertUser(UserInfo userInfo) ;
    public ArrayList<UserInfo> getUsers()  ;
    public UserInfo retrieveSingleUser(int id)  ;
    public boolean updateUserData(UserInfo userInfo);
    public boolean deleteUserData(int id)  ;

}
