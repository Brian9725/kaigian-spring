package pers.brian.springframework.beans.support;

/**
 * @author kaigian
 **/
public interface InitializingBean {

    /**
     * bean属性设置后初始化前执行
     */
    void afterPropertiesSet();
}
