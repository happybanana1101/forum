import com.jsq.forum.ForumApplication;
import com.jsq.forum.dao.UserDao;
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

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForumApplication.class)
public class restest {
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    JedisUtil jedisUtil;
    @Test
    public void t(){
        Jedis jedis = jedisUtil.getJedis();
        jedis.set("a","1");
    }
}
