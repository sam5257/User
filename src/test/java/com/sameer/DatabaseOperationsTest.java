package com.sameer;

import com.sameer.database.DatabaseOperations;
import com.sameer.model.UserInfo;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DatabaseOperationsTest  {

    private DatabaseOperations databaseOperations=new DatabaseOperations();

    @Test
    public void testInsertUser()
    {
        UserInfo userInfo=new UserInfo();
        userInfo.setAge(33);
        userInfo.setFirstName("sameer");
        userInfo.setLastName("chatterjee");
        userInfo.setEmail("sam@gmail.com");
        userInfo.setDate("1987-04-14");
        boolean v=databaseOperations.insertUser(userInfo);
        assertEquals(true,v);

    }
}
