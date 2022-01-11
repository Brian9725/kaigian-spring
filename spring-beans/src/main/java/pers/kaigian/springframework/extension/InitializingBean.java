package pers.kaigian.springframework.extension;

/**
 * @author BrianHu
 * @create 2021-09-06 23:41
 **/
public interface InitializingBean {

    /**
     * bean属性设置后初始化前执行
     */
    void afterPropertiesSet();
}
