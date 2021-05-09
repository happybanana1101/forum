package com.jsq.forum.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class JedisUtil {
    private static Jedis jedis=new Jedis("121.5.114.106",6379);
    public Jedis getJedis(){return jedis;}
}
