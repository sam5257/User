package com.sameer.factory;

import com.sameer.model.UserInfo;

import java.util.regex.Pattern;

public class EmailValidator implements IValidator {
    @Override
    public boolean validate(UserInfo userInfo) {
        String email=userInfo.getEmail();
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }


}
