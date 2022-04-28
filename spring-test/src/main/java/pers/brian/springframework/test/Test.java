package pers.brian.springframework.test;

import lombok.extern.slf4j.Slf4j;
import pers.brian.springframework.context.AnnotationConfigApplicationContext;
import pers.brian.springframework.test.service.UserService;

/**
 * @author BrianHu
 * @create 2021-09-03 22:16
 **/
@Slf4j
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) annotationConfigApplicationContext.getBean("userService");
        userService.test();
    }
}
