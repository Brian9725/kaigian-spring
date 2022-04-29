package pers.brian.springframework.beans.support;

import pers.brian.springframework.beans.exception.BeansException;

/**
 * bean销毁时的扩展点
 *
 * @author BrianHu
 * @create 2022-04-29 15:38
 **/
public interface DestructionAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * 销毁前逻辑
     *
     * @param bean     bean对象
     * @param beanName bean名称
     * @throws BeansException 异常
     */
    void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException;

    /**
     * 判断该bean是否需要被销毁
     *
     * @param bean bean对象
     * @return 是否需要被销毁
     */
    default boolean requiresDestruction(Object bean) {
        return true;
    }

}
