package pers.brian.springframework.beans.support;

/**
 * @author BrianHu
 * @create 2021-09-06 23:44
 **/
public interface BeanPostProcessor {

    /**
     * bean初始化前执行
     *
     * @param bean     bean对象
     * @param beanName bean名称
     * @return bean对象
     */
    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    /**
     * bean初始化后执行
     *
     * @param bean     bean对象
     * @param beanName bean名称
     * @return bean对象
     */
    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
