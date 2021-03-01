package com.jsq.forum.dao;

import com.jsq.forum.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
@Repository
public class PointsDao {
    @Autowired
    JedisUtil jedisUtil;
    public void addUser(String username){
        Jedis jedis = jedisUtil.getJedis();
        jedis.zadd("points",0,username);
    }
    public void changePoint(String username,String option,Boolean useful){
        Jedis jedis = jedisUtil.getJedis();
        Double score = jedis.zscore("points",username);
        switch (option){
            case "addtopic":jedis.zadd("points",score+1,username);break;
            case "addAnswer":jedis.zadd("points",score+2,username);break;
            case "setuseful":if(useful) jedis.zadd("points",score+1,username);
                             else jedis.zadd("points",score-1,username);
                             break;
            case "deleteAnswer":if(useful) jedis.zadd("points",score-3,username);
                                else jedis.zadd("points",score-2,username);
                                break;
        }
    }
}
