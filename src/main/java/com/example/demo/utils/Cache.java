package com.example.demo.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class Cache {
    Jedis jedis = new Jedis();


    public void saveTokenUser(String token, long userId) {
        jedis.set("token:" + token, String.valueOf(userId));
    }

    public long getUserIdByToken(String token) {
        String cachedResponse = jedis.get("token:" + token);
        if (cachedResponse == null) {
            return -1;
        }
        return Long.valueOf(cachedResponse);
    }
}
