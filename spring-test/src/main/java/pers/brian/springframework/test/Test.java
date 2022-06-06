package pers.brian.springframework.test;

import pers.brian.springframework.context.AnnotationConfigApplicationContext;
import pers.brian.springframework.test.service.UserService;

/**
 * @author BrianHu
 * @create 2021-09-03 22:16
 **/
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) annotationConfigApplicationContext.getBean("userService");
        userService.test();
    }
}
