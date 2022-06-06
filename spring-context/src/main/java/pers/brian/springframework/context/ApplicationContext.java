package pers.brian.springframework.context;

import pers.brian.springframework.beans.factory.BeanFactory;

/**
 * @author BrianHu
 * @create 2021-09-03 22:18
 **/
public interface ApplicationContext extends BeanFactory {

    /**
     * 获取当前使用的BeanFactory
     *
     * @return BeanFactory
     */
    BeanFactory getBeanFactory();

}
