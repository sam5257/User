package com.sameer;
import org.junit.Test;

import com.sameer.business.BusinessClass;

import static org.junit.Assert.assertEquals;


public class BusinessClassTest {

    private BusinessClass businessClass = new BusinessClass();

    @Test
    public void testAge() {

        int age = businessClass.getAge("1994-04-25");

        assertEquals(age, 25);
    }

}