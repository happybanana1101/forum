package com.jsq.forum.util;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Component
public class JedisUtil {
    private JedisPool jedisPool;
    public Jedis getJedis(){return jedisPool.getResource();}
    public JedisUtil(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(30);
        jedisPoolConfig.setMaxIdle(10);
        //jedisPool = new JedisPool(jedisPoolConfig,"121.5.114.106",6379,3000,"jiang123");
        jedisPool = new JedisPool(jedisPoolConfig,"127.0.0.1",6379,3000,"jiang123");
    }

}
