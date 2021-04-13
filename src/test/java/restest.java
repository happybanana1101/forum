import com.jsq.forum.ForumApplication;
import com.jsq.forum.dao.MessageDao;
import com.jsq.forum.dao.TopicDao;
import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.Message;
import com.jsq.forum.model.Topic;
import com.jsq.forum.model.User;
import com.jsq.forum.util.JedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;

import java.sql.DataTruncation;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForumApplication.class)
public class restest {
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JedisUtil jedisUtil;
    @Autowired
    MessageDao messageDao;
    @Autowired
    TopicDao topicDao;

    @Test
    public void t(){
        Jedis jedis = jedisUtil.getJedis();
        Integer integer = Integer.valueOf("123");
        System.out.println(integer);
        Topic topic = topicDao.findTopicById((long)110);
        try {
            System.out.println(topic.getCreatedDate().toString());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date  = simpleDateFormat.format(topic.getCreatedDate());
            System.out.println(date);
            topic.setCreatedDate(simpleDateFormat.parse(jedis.hget("110","created_date")));
            System.out.println(topic.getCreatedDate());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
