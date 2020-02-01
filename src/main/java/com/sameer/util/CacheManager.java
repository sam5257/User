package com.sameer.util;

import com.sameer.model.UserInfo;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CacheManager {

    private final static Logger logger = Logger.getLogger(CacheManager.class);

    private  Map<Integer, UserInfo> cache = new HashMap<Integer, UserInfo>();

    public void insertCache(UserInfo userInfo) {
        logger.info("inside insertCache" +userInfo);

        Integer key = userInfo.getId();
        cache.put(key, userInfo);

    }


    public boolean checkCache(int id) {
        Integer key = id;
        boolean available = cache.containsKey(key);
        logger.info("is available : " +available);
        if (available) {
            return true;
        } else
            return false;
    }

    public UserInfo returnCache(Integer key) {
        logger.info("Inside returnCache");
        UserInfo userInfo = cache.get(key);
        return userInfo;
    }

}
