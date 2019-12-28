package com.sameer;

import com.sameer.database.DatabaseConnection;
import com.sameer.database.DatabaseOperations;
import com.sameer.model.UserInfo;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DatabaseOperationsTest {

    private DatabaseOperations databaseOperations=new DatabaseOperations();

    @Test
    public void testInsertUser() throws SQLException, ClassNotFoundException {
        UserInfo userInfo=new UserInfo();
        userInfo.setAge(33);
        userInfo.setFirstName("sameer");
        userInfo.setLastName("chatterjee");
        userInfo.setEmail("sam@gmail.com");
        userInfo.setDate("1987-04-14");
       /* DatabaseConnection databaseConnection=mock(DatabaseConnection.class);
        Connection connection=mock(Connection.class);
        when(DatabaseConnection.initializeDatabase()).thenReturn(connection);
        databaseOperations.insertUser(userInfo);

        */



    }




}
