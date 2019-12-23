package com.sameer.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;

public class Age {

    public static void main(String[] args) throws ParseException {
        int age = 0;
        String dob = "1994-04-15";
//        Date currentDate=new Date();
//        Date date;
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        date=(Date)formatter.parse(dob);
//
//        int years = currentDate.getYear() - date.getYear() ;
//
//
//        int d1 = Integer.parseInt(formatter.format(date));
//        int d2 = Integer.parseInt(formatter.format(currentDate));
//        age = (d2 - d1) / 10000;
//        System.out.println(age);


        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.parse(dob);
        Period diff = Period.between(dateOfBirth , currentDate);

        System.out.println(diff.getYears());

    }
}
