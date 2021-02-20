import com.jsq.forum.ForumApplication;
import com.jsq.forum.dao.UserDao;
import com.jsq.forum.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ForumApplication.class)
public class restest {
    @Autowired
    UserDao userDao;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void t(){
        User user = new User();
        user.setIntroduction("123");
        user.setUsername("123");
        user.setPassword("123");
        user.setCreated_date(new Date());
        userDao.addUser(user);
        System.out.println("1");
    }
}
