package com.sameer.database;

import com.sameer.business.UserBusinessImpl;
import com.sameer.dto.UserInfoDTO;
import com.sameer.model.UserInfo;
import com.sameer.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Session;

import java.util.ArrayList;

public class HibernateDatabaseOperations implements IDatabaseOperations {

    private static Session session;

    static {
        session = HibernateUtil.getSessionFactory().openSession();

    }

    private final static Logger logger = Logger.getLogger(HibernateDatabaseOperations.class);


    public boolean insertUser(UserInfo userInfo) {
        long start = System.currentTimeMillis();
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(userInfo);
        session.getTransaction().commit();
        long end = System.currentTimeMillis();

        logger.info(end - start + "Time taken");
        return true;
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
