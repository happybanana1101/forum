package com.jsq.forum.service;

import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.User;
import com.jsq.forum.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashSet;
import java.util.Set;

@Service
public class FollowService {
    @Autowired
    JedisUtil jedisUtil;
    @Autowired
    UserDao userDao;

    public void addFollow(Long userId_fans,Long userId_follow){
        Jedis jedis = jedisUtil.getJedis();
        jedis.sadd("fans-"+String.valueOf(userId_follow),String.valueOf(userId_fans));
        jedis.sadd("follow-"+String.valueOf(userId_fans),String.valueOf(userId_follow));
        jedis.close();
    }
    public void deleteFollow(Long userId_fans,Long userId_follow){
        Jedis jedis = jedisUtil.getJedis();
        jedis.srem("fans-"+String.valueOf(userId_follow),String.valueOf(userId_fans));
        jedis.srem("follow-"+String.valueOf(userId_fans),String.valueOf(userId_follow));
        jedis.close();
    }
    public boolean isFollow(Long otherUserId,Long userId){
        Jedis jedis = jedisUtil.getJedis();
        Boolean isfollow = jedis.sismember("fans-" + String.valueOf(userId), String.valueOf(otherUserId));
        jedis.close();
        return isfollow;
    }
    public long getFollowNum(long userId){
        Jedis jedis = jedisUtil.getJedis();
        Long follownum = jedis.scard("fans-" + String.valueOf(userId));
        jedis.close();
        return follownum;
    }
    public Set<User> getCommonFans(Long userId1, Long userId2) {
        Jedis jedis = jedisUtil.getJedis();
        Set<String> commenFans = jedis.sinter("fans-" + String.valueOf(userId1), "fans-" + String.valueOf(userId2));
        HashSet<User> commentFansUser = new HashSet<>();
        for(String s:commenFans){
            commentFansUser.add(userDao.getUserById(Long.parseLong(s)));
        }
        jedis.close();
        return commentFansUser;
    }
}
