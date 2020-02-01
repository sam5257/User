package com.sameer.factory;

import com.sameer.model.UserInfo;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;



public class ValidatorImpl  implements IValidator {

    public boolean lengthValidator(UserInfo userInfo) {
        String firstName=userInfo.getFirstName();
        String lastName=userInfo.getLastName();
        if(firstName.length()>20 || lastName.length()>20)
            return true;
        else
          return false;
    }

    public boolean emptyFieldValidator(UserInfo userInfo) {
        String firstName = userInfo.getFirstName();
        String lastName = userInfo.getLastName();
        String email = userInfo.getEmail();
        String date = userInfo.getDate();
        if (firstName.isEmpty() || lastName.isEmpty() || date.isEmpty() || email.isEmpty()) {


            return true;
        }
        else {
            return false;
        }
    }

    public  boolean isValidEmail(UserInfo userInfo)
    {   String email=userInfo.getEmail();
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public boolean checkDuplicate(UserInfo userInfo) {

        return false;
    }

    @Override
    public boolean validate(UserInfo userInfo) {
        return false;
    }
}
