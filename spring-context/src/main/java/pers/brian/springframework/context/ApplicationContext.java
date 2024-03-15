package pers.brian.springframework.context;

import pers.brian.springframework.beans.factory.BeanFactory;

/**
 * @author kaigian
 **/
public interface ApplicationContext extends BeanFactory {

    /**
     * 获取当前使用的BeanFactory
     *
     * @return BeanFactory
     */
    BeanFactory getBeanFactory();

}
