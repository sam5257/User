package com.sameer.business;

import com.sameer.model.UserInfo;

public class Test {

    public static void main(String[] args) {
        UserInfo userInfo=new UserInfo();
        userInfo.setFirstName("sameer");
        userInfo.setLastName("chat");
        userInfo.setEmail("faa@gmail.com");
        userInfo.setDate("1994-04-12");

        ValidatorImpl validator=new ValidatorImpl();
        boolean valid=validator.isValidEmail(userInfo);
        System.out.println(valid);
    }
}
