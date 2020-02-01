package com.sameer.factory;

public class ValidatorFactory {
        public static IValidator getValidator(FactoryName name){
            if(name == FactoryName.EMAIL){
                return new EmailValidator();
            }else if(name == FactoryName.NAME){
                return new NameValidator();
            }
            return null;
        }
    }

