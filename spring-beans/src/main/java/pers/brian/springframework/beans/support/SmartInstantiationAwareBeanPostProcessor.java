package pers.brian.springframework.beans.support;

import pers.brian.springframework.beans.exception.BeansException;

import java.lang.reflect.Constructor;

/**
 * @author BrianHu
 * @create 2022-04-29 15:37
 **/
public interface SmartInstantiationAwareBeanPostProcessor extends InstantiationAwareBeanPostProcessor {

    /**
     * 推断bean类型
     *
     * @param beanClass 用于生成bean的Class对象
     * @param beanName  bean名称
     * @return 推测的bean类型
     * @throws BeansException 异常
     */
    default Class<?> predictBeanType(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    /**
     * 选择合适的构造方法
     *
     * @param beanClass 用于生成bean的Class对象
     * @param beanName  bean名称
     * @return 用于实例化bean的构造方法
     * @throws BeansException 异常
     */
    default Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        return null;
    }

    /**
     * 主要用于解决循环引用的问题
     *
     * @param bean     bean对象
     * @param beanName bean名称
     * @return bean对象
     * @throws BeansException 异常
     */
    default Object getEarlyBeanReference(Object bean, String beanName) throws BeansException {
        return bean;
    }

}

