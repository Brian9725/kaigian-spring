package pers.brian.springframework.beans.support;

import pers.brian.springframework.beans.definition.RootBeanDefinition;

/**
 * @author BrianHu
 * @create 2022-04-27 16:24
 **/
public interface MergedBeanDefinitionPostProcessor extends BeanPostProcessor {

    /**
     * @param beanDefinition
     * @param beanType
     * @param beanName
     */
    void postProcessMergedBeanDefinition(RootBeanDefinition beanDefinition, Class<?> beanType, String beanName);

    /**
     * @param beanName
     */
    default void resetBeanDefinition(String beanName) {
    }
}
