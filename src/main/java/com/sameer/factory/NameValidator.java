package com.sameer.factory;

import com.sameer.model.UserInfo;

public class NameValidator implements IValidator {
    @Override
    public boolean validate(UserInfo userInfo) {
        String firstName = userInfo.getFirstName();
        String lastName = userInfo.getLastName();
        String email = userInfo.getEmail();
        String date = userInfo.getDate();
        if ((!firstName.isEmpty() && firstName.length() <= 20)
                || (!lastName.isEmpty() && lastName.length() <=20)
                || (!date.isEmpty() && date.length() <= 20)
                || (!email.isEmpty()) && email.length() <=20) {

            return true;
        }
        else {
            return false;
        }
    }


}
