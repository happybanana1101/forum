package com.jsq.forum.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class JedisUtil {
    private static Jedis jedis=new Jedis("localhost",6379);;
    public Jedis getJedis(){return jedis;}
}
