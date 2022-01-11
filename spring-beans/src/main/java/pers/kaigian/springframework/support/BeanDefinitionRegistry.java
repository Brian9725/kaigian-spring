package pers.kaigian.springframework.support;

import pers.kaigian.springframework.beans.BeanDefinition;

/**
 * @author BrianHu
 * @create 2022-01-11 14:47
 **/
public interface BeanDefinitionRegistry {

    /**
     * 将bd注册为一个bean
     *
     * @param bd beanDefinition
     */
    void registerBeanDefinition(BeanDefinition bd);
}
