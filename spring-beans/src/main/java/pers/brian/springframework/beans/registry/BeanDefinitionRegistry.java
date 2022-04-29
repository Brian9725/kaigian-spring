package pers.brian.springframework.beans.registry;

import pers.brian.springframework.beans.definition.BeanDefinition;

/**
 * 注册BeanDefinition
 *
 * @author BrianHu
 * @create 2022-01-11 14:47
 **/
public interface BeanDefinitionRegistry {

    /**
     * 将beanDefinition注册为一个bean
     *
     * @param beanName       bean名称
     * @param beanDefinition beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
