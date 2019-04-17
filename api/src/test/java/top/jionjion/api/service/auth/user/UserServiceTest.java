package top.jionjion.api.service.auth.user;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import top.jionjion.api.bean.auth.user.User;


import javax.transaction.Transactional;

import static org.junit.Assert.*;

/**
 * @author Jion
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
@Slf4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    @Rollback
    public void test(){
        User user = new User();
        user.setUsername("Jion");
        user.setPassword("123456");
        user.setToken("sada");
        user.setUudi("sdadsdsad");
        user = userService.save(user);
        assertNotNull(user);
    }
}