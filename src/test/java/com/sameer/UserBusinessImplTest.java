package com.sameer;
import com.sameer.database.DatabaseOperations;
import com.sameer.model.UserInfo;
import org.junit.Test;

import com.sameer.business.UserBusinessImpl;

import java.util.ArrayList;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class UserBusinessImplTest {

    /*

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
/*
    @Test
    public void testSaveUser()
    {
        UserInfo userInfo= new UserInfo();
        userInfo.setFirstName("sam");
        userInfo.setLastName("chat");
        DatabaseOperations databaseOperations=mock(DatabaseOperations.class);
        when(databaseOperations.insertUser(userInfo)).thenReturn(true);
        userBusinessImpl.setDatabaseOperation(databaseOperations);

        boolean b=userBusinessImpl.saveUser(userInfo);
        assertEquals(true,b);

    }

 */

/*
    @Test
    public void testRestrieveUser()
    {
       ArrayList<UserInfo> users = new ArrayList<>();
       UserInfo userInfo= new UserInfo();
      userInfo.setFirstName("sameer");
         users.add(userInfo);

      DatabaseOperations databaseOperations=mock(DatabaseOperations.class);
      when(databaseOperations.getUsers()).thenReturn(users);

   //     IDatabaseOperations databaseOperationsMock = new DatabaseMock();

       userBusinessImpl.setDatabaseOperation(databaseOperations);
        ArrayList<UserInfo> userinfo= userBusinessImpl.retreiveUser();
        assertNotNull(userinfo);

    }

    @Test
    public void testUpdateUser()
    {

        UserInfo userInfo= new UserInfo();
        userInfo.setFirstName("sam");
        userInfo.setLastName("chat");
        DatabaseOperations databaseOperations=mock(DatabaseOperations.class);
        when(databaseOperations.updateUserData(userInfo)).thenReturn(true);
        userBusinessImpl.setDatabaseOperation(databaseOperations);

        boolean b=userBusinessImpl.updateUser(userInfo);
        assertEquals(true,b);

    }

    @Test
    public void testRetrieveUserWithId()
    {   int id =17;
        UserInfo userInfo= new UserInfo();
        userInfo.setFirstName("sam");
        userInfo.setLastName("chat");
        DatabaseOperations databaseOperations=mock(DatabaseOperations.class);
        when(databaseOperations.retrieveSingleUser(id)).thenReturn(userInfo);
   //     userBusinessImpl.setDatabaseOperation(databaseOperations);

        UserInfo userInfo1=userBusinessImpl.retrieveUserWithId(id);

        assertNotNull(userInfo1);
    }

    @Test
    public void testDeleteUser()
    {   int id =28;
        DatabaseOperations databaseOperations=mock(DatabaseOperations.class);
        when(databaseOperations.deleteUserData(id)).thenReturn(true);
 //       userBusinessImpl.setDatabaseOperation(databaseOperations);

        boolean b=userBusinessImpl.deleteUser(id);
        assertEquals(true,b);

    }

 */


}