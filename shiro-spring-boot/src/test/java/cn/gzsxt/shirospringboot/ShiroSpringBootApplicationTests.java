package cn.gzsxt.shirospringboot;

import cn.gzsxt.shirospringboot.entity.User;
import cn.gzsxt.shirospringboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShiroSpringBootApplicationTests {


    @Resource
    private UserService userService;

    @Test
    public void contextLoads() {
        User admin = userService.findByUser("admin");
        System.out.println(admin);
    }

}
