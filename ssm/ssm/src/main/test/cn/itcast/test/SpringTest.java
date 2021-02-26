package cn.itcast.test;

import cn.itcast.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {




    @Test
    public void testSpring() {
        // 加载配置文件
        ClassPathXmlApplicationContext ca = new ClassPathXmlApplicationContext("classpath:ApplicationContext.xml");
        // 获取对象
        UserServiceImpl userService = (UserServiceImpl) ca.getBean("userService");
        // 调用方法
        userService.findAll();
    }
}
