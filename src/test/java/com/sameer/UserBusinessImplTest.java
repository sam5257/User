package com.sameer;
import com.sameer.database.DatabaseMock;
import com.sameer.database.DatabaseOperations;
import com.sameer.database.IDatabaseOperations;
import com.sameer.model.UserInfo;
import org.junit.Test;

import com.sameer.business.UserBusinessImpl;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserBusinessImplTest {

    private UserBusinessImpl userBusinessImpl = new UserBusinessImpl();


    @Test
    public void testAge() {

        int age = userBusinessImpl.getAge("1994-04-25");

        assertEquals(25, age);
    }

    @Test
    public void testInvalidAge() {

        int age = userBusinessImpl.getAge("1994-04-25");

        assertNotEquals(age, 30);
    }
    @Test
    public void testRestrieveUser()
    {
//        ArrayList<UserInfo> users = new ArrayList<>();
//        UserInfo userInfo= new UserInfo();
//        userInfo.setFirstName("sameer");
//        users.add(userInfo);

//       DatabaseOperations databaseOperations=mock(DatabaseOperations.class);
//       when(databaseOperations.getUsers()).thenReturn(users);

        IDatabaseOperations databaseOperationsMock = new DatabaseMock();


       userBusinessImpl.setDatabaseOperation(databaseOperationsMock);

        ArrayList<UserInfo> userinfo= userBusinessImpl.retreiveUser();

        assertNotNull(userinfo);

    }



}