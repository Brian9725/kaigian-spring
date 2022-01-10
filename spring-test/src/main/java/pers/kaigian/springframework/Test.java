package pers.kaigian.springframework;

import pers.kaigian.springframework.core.KaiGianApplicationContext;
import pers.kaigian.springframework.service.UserService;

/**
 * @author BrianHu
 * @create 2021-09-03 22:16
 **/
public class Test {
    public static void main(String[] args) {
        KaiGianApplicationContext applicationContext = new KaiGianApplicationContext(AppConfig.class);
        applicationContext.printBeanDefinitionMap();
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();
    }
}
