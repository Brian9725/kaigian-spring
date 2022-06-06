package pers.brian.springframework.beans.aware;

import pers.brian.springframework.beans.exception.BeansException;
import pers.brian.springframework.beans.factory.BeanFactory;

/**
 * @author BrianHu
 * @create 2022-04-29 16:52
 **/
public interface BeanFactoryAware extends Aware {

    /**
     * 设置生产这个bean的BeanFactory
     *
     * @param beanFactory beanFactory
     * @throws BeansException 异常
     */
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
