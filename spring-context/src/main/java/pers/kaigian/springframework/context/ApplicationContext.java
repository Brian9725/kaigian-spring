package pers.kaigian.springframework.context;

import pers.kaigian.springframework.config.BeanDefinition;
import pers.kaigian.springframework.factory.BeanFactory;
import pers.kaigian.springframework.factory.DefaultBeanFactory;
import pers.kaigian.springframework.support.BeanDefinitionRegistry;

/**
 * @author BrianHu
 * @create 2021-09-03 22:18
 **/
public class ApplicationContext implements BeanFactory, BeanDefinitionRegistry {

    private final DefaultBeanFactory beanFactory;

    public ApplicationContext() {
        this.beanFactory = new DefaultBeanFactory();
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
        this.beanFactory.registerBeanDefinition(beanName, beanDefinition);
    }
}
