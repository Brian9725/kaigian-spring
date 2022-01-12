package pers.kaigian.springframework.factory;

import pers.kaigian.springframework.beans.BeanDefinition;
import pers.kaigian.springframework.core.annotation.Scope;
import pers.kaigian.springframework.exception.BeanErrorCodeEnum;
import pers.kaigian.springframework.exception.BeanException;
import pers.kaigian.springframework.support.BeanDefinitionRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author BrianHu
 * @create 2022-01-11 15:04
 **/
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    /**
     * beanDefinition缓存池
     */
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 单例bean缓存池
     */
    protected Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) throws Throwable {
        return null;
    }

    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        return null;
    }

    @Override
    public boolean containsBean(String name) {
        return beanDefinitionMap.containsKey(name);
    }

    @Override
    public boolean isSingleton(String name) throws Throwable {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new BeanException(BeanErrorCodeEnum.BEAN_NOT_EXISTS);
        }
        return (Scope.SINGLETON.equals(beanDefinition.getType()));
    }

    @Override
    public boolean isPrototype(String name) throws Throwable {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new BeanException(BeanErrorCodeEnum.BEAN_NOT_EXISTS);
        }
        return (Scope.PROTOTYPE.equals(beanDefinition.getType()));
    }

    @Override
    public void registerBeanDefinition(BeanDefinition bd) {
        beanDefinitionMap.put(bd.getBeanName(), bd);
    }
}
