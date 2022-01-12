package pers.kaigian.springframework;

import lombok.extern.slf4j.Slf4j;
import pers.kaigian.springframework.context.KaiGianApplicationContext;

/**
 * @author BrianHu
 * @create 2021-09-03 22:16
 **/
@Slf4j
public class Test {
    public static void main(String[] args) {
        new KaiGianApplicationContext();
        while (true) {
            log.trace("kaigian");
            log.debug("kaigian");
            log.info("kaigian");
            log.warn("kaigian");
            log.error("kaigian");
        }
    }
}
