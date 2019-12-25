package com.sameer;
import org.junit.Test;

import com.sameer.business.UserBusinessImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class UserBusinessImplTest {

    private UserBusinessImpl userBusinessImpl = new UserBusinessImpl();

    @Test
    public void testAge() {

        int age = userBusinessImpl.getAge("1994-04-25");

        assertEquals(age, 25);
    }

    @Test
    public void testInvaliAge() {

        int age = userBusinessImpl.getAge("1994-04-25");

        assertNotEquals(age, 30);
    }

}