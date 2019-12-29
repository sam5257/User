package com.sameer.database;

import com.sameer.business.UserBusinessImpl;
import com.sameer.dto.UserInfoDTO;
import com.sameer.model.UserInfo;
import com.sameer.util.HibernateUtil;
import com.sameer.util.Response;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

//@Component("databaseOperations")
public class HibernateDatabaseOperations implements IDatabaseOperations {

    private static Session session;

    static {
        session = HibernateUtil.getSessionFactory().openSession();

    }

    private final static Logger logger = Logger.getLogger(HibernateDatabaseOperations.class);


    public Response insertUser(UserInfo userInfo) {
        long start = System.currentTimeMillis();
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(userInfo);
        session.getTransaction().commit();
        long end = System.currentTimeMillis();

        logger.info(end - start + "Time taken");
        return Response.TRUE;
    }


    @Override
    public ArrayList<UserInfo> getUsers() {
        return null;
    }

    @Override
    public UserInfo retrieveSingleUser(int id) {
        return null;
    }

    @Override
    public boolean updateUserData(UserInfo userInfo) {
        return false;
    }

    @Override
    public boolean deleteUserData(int id) {
        return false;
    }


}
