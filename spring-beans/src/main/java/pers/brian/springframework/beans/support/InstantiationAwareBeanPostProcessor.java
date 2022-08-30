package pers.brian.springframework.beans.support;

import pers.brian.springframework.beans.entity.PropertyValues;
import pers.brian.springframework.core.exception.SpringException;

/**
 * @author BrianHu
 * @create 2022-04-27 16:22
 **/
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 实例化前
     *
     * @param beanClass 用来创建bean的Class对象
     * @param beanName  bean名称
     * @return 创建出来的bean
     * @throws SpringException 异常
     */
    default Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws SpringException {
        return null;
    }

    /**
     * 实例化后
     *
     * @param bean     实例化的bean
     * @param beanName bean名称
     * @return true
     * @throws SpringException 异常
     */
    default boolean postProcessAfterInstantiation(Object bean, String beanName) throws SpringException {
        return true;
    }

    /**
     * 处理bean的属性
     *
     * @param pvs      已经设置的属性名称对象集合
     * @param bean     bean对象
     * @param beanName bean名称
     * @return bean的属性名称对象集合
     * @throws SpringException 异常
     */
    default PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws SpringException {
        return null;
    }
}
