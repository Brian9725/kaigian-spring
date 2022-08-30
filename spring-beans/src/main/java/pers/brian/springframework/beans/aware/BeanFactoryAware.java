package pers.brian.springframework.beans.aware;

import pers.brian.springframework.beans.factory.BeanFactory;
import pers.brian.springframework.core.exception.SpringException;

/**
 * @author BrianHu
 * @create 2022-04-29 16:52
 **/
public interface BeanFactoryAware extends Aware {

    /**
     * 设置生产这个bean的BeanFactory
     *
     * @param beanFactory beanFactory
     * @throws SpringException 异常
     */
    void setBeanFactory(BeanFactory beanFactory) throws SpringException;
}
