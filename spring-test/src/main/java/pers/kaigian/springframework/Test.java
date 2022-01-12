package pers.kaigian.springframework;

import lombok.extern.slf4j.Slf4j;
import pers.kaigian.springframework.context.KaiGianApplicationContext;
import pers.kaigian.springframework.service.UserService;

/**
 * @author BrianHu
 * @create 2021-09-03 22:16
 **/
@Slf4j
public class Test {
    public static void main(String[] args) {
        KaiGianApplicationContext kaiGianApplicationContext = new KaiGianApplicationContext(AppConfig.class);
        UserService userService;
    }
}
