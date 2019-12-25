package geektime.im.lecture.dao;


import geektime.im.lecture.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findByEmail() {
        User user = userRepository.findByEmailEquals("zhangsan@gmail.com");
        Assert.assertNotNull(user);
        System.out.println(user.toString());
    }

    @Test
    public void findUsersByUidIsNot() {
        List<User> userList= userRepository.findUsersByUidIsNot(1000L);
        Assert.assertFalse(userList.isEmpty());
        userList.forEach(user->System.out.println(user.toString()));
    }
}