package com.sameer.business;

import com.sameer.model.UserInfo;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.regex.Pattern;


@Component("validator")
public class ValidatorImpl  implements  IValidator{
    @Override
    public boolean lengthValidator(UserInfo userInfo) {
        String firstName=userInfo.getFirstName();
        String lastName=userInfo.getLastName();
        if(firstName.length()>20 || lastName.length()>20)
            return true;
        else
          return false;
    }

    @Override
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

    @Override
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

    @Override
    public boolean checkDuplicate(UserInfo userInfo) {

        return false;
    }

}
