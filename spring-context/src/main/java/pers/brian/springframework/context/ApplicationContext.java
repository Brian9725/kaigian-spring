package pers.brian.springframework.context;

import pers.brian.springframework.beans.BeanDefinition;
import pers.brian.springframework.beans.factory.BeanFactory;
import pers.brian.springframework.beans.factory.DefaultListableBeanFactory;
import pers.brian.springframework.beans.support.BeanDefinitionRegistry;

/**
 * @author BrianHu
 * @create 2021-09-03 22:18
 **/
public class ApplicationContext implements BeanFactory, BeanDefinitionRegistry {

    private final DefaultListableBeanFactory beanFactory;

    public ApplicationContext() {
        this.beanFactory = new DefaultListableBeanFactory();
    }

    @Override
    public Object getBean(String name) {
        return beanFactory.getBean(name);
    }

    @Override
    public boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    @Override
    public boolean isSingleton(String name) {
        return beanFactory.isSingleton(name);
    }

    @Override
    public boolean isPrototype(String name) {
        return beanFactory.isPrototype(name);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }
}
