package pers.kaigian.springframework.support;


import pers.kaigian.springframework.config.BeanDefinition;

/**
 * @author BrianHu
 * @create 2022-01-11 14:47
 **/
public interface BeanDefinitionRegistry {

    /**
     * 将BeanDefinition注册为一个bean
     *
     * @param beanName       bean名称
     * @param beanDefinition beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
