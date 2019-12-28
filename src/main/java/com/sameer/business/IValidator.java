package com.sameer.business;

import com.sameer.model.UserInfo;


public interface IValidator {

    boolean lengthValidator(UserInfo userInfo);
    boolean emptyFieldValidator(UserInfo userInfo);
    boolean isValidEmail(UserInfo userInfo);
}
