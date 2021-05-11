package com.jsq.forum.service;

import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.Fans;
import com.jsq.forum.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FansService {
    @Autowired
    UserDao userDao;
    @Autowired
    JedisUtil jedisUtil;

    public List<Fans> getFans(String id){
        Jedis jedis = jedisUtil.getJedis();
        Set<String> allJedisFans = jedis.smembers("fans-" + id);
        ArrayList<Fans> allfans = new ArrayList<>();
        for (String s: allJedisFans){
            Fans fans = new Fans();
            fans.setId(Long.valueOf(s));
            fans.setIntroduction(userDao.getIntroductionById(Long.valueOf(s)));
            fans.setUsername(userDao.getUsernameById(Integer.valueOf(s)));
            allfans.add(fans);
        }
        jedis.close();
        return allfans;
    }
}
