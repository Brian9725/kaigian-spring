package pers.kaigian.springframework.context;

import lombok.extern.slf4j.Slf4j;

/**
 * 自己的Spring上下文环境
 *
 * @author BrianHu
 * @create 2021-09-03 22:22
 **/
@Slf4j
public class KaiGianApplicationContext extends ApplicationContext {

    public KaiGianApplicationContext() {
        log.info("kaigian");
    }
}
