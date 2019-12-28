package com.sameer.database;

import com.sameer.dto.UserInfoDTO;
import com.sameer.model.UserInfo;

import java.util.ArrayList;

public interface IDatabaseOperations {

    public boolean insertUser(UserInfo userInfo) ;
    public ArrayList<UserInfo> getUsers()  ;
    public UserInfo retrieveSingleUser(int id)  ;
    public boolean updateUserData(UserInfo userInfo);
    public boolean deleteUserData(int id)  ;

}
