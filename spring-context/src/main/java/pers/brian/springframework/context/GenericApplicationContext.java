package pers.brian.springframework.context;

import pers.brian.springframework.beans.definition.BeanDefinition;
import pers.brian.springframework.beans.factory.BeanFactory;
import pers.brian.springframework.beans.factory.DefaultListableBeanFactory;
import pers.brian.springframework.beans.registry.BeanDefinitionRegistry;

/**
 * @author BrianHu
 * @create 2022-04-29 9:59
 **/
public class GenericApplicationContext extends AbstractApplicationContext implements BeanDefinitionRegistry {

    private final DefaultListableBeanFactory beanFactory;

    public GenericApplicationContext() {
        this.beanFactory = new DefaultListableBeanFactory();
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }

    @Override
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
