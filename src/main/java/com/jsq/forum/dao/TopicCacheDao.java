package com.jsq.forum.dao;

import com.jsq.forum.model.Topic;
import com.jsq.forum.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

@Repository
public class TopicCacheDao {
    @Autowired
    JedisUtil jedisUtil;

    public void addTopic(Topic topic){
        Jedis jedis = jedisUtil.getJedis();
        HashMap<String, String> map = new HashMap<>();
        if (null != topic.getCategory()) map.put("category",topic.getCategory());
        if (null != topic.getCode()) map.put("code",topic.getCode());
        if (null != topic.getContent()) map.put("content",topic.getContent());
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String format = simpleDateFormat.format(topic.getCreatedDate());
            map.put("created_date",format);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (null != topic.getTitle()) map.put("title",topic.getTitle());
        if (null != topic.getIdUser()) map.put("id_user", String.valueOf(topic.getIdUser()));
        jedis.hmset(String.valueOf(topic.getId()),map);
        jedis.close();
    }

    public Topic getTopic(long id)  {
        String _id = String.valueOf(id);
        Jedis jedis = jedisUtil.getJedis();
        Topic topic = new Topic();
        topic.setId(id);
        topic.setIdUser(Integer.valueOf(jedis.hget(_id,"id_user")));
        topic.setCode(jedis.hget(_id,"code"));
        topic.setCategory(jedis.hget(_id,"category"));
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            topic.setCreatedDate(simpleDateFormat.parse(jedis.hget(_id,"created_date")));
        }catch (Exception e){
            e.printStackTrace();
        }
        topic.setTitle(jedis.hget(_id,"title"));
        topic.setContent(jedis.hget(_id,"content"));
        jedis.close();
        return topic;
    }
}
