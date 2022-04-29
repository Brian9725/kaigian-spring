package pers.brian.springframework.beans.factory;

import pers.brian.springframework.beans.annotation.Scope;
import pers.brian.springframework.beans.BeanDefinition;
import pers.brian.springframework.beans.exception.BeansErrorCodeEnum;
import pers.brian.springframework.beans.exception.BeansException;
import pers.brian.springframework.beans.support.BeanDefinitionRegistry;
import pers.brian.springframework.beans.GenericBeanDefinition;
import pers.brian.springframework.beans.RootBeanDefinition;
import pers.brian.springframework.core.utils.ClassLoaderUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author BrianHu
 * @create 2022-01-11 15:04
 **/
public class DefaultListableBeanFactory implements BeanFactory, BeanDefinitionRegistry {

    /**
     * beanDefinition缓存池
     */
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 单例bean缓存池
     */
    protected Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public Object getBean(String name) {
        return doGetBean(name, null, (Object[]) null);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) {
        return doGetBean(name, requiredType, (Object[]) null);
    }

    @Override
    public Object getBean(String name, Object... args) {
        return doGetBean(name, null, args);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return doGetBean(null, requiredType, (Object[]) null);
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) {
        return doGetBean(null, requiredType, args);
    }

    private <T> T doGetBean(String name, Class<T> requiredType, Object... args) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition instanceof GenericBeanDefinition) {
            GenericBeanDefinition gbd = (GenericBeanDefinition) beanDefinition;
            try {
                return (T) gbd.getBeanClass().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected Object createBean(String beanName, RootBeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getBeanClass();
        if (clazz == null) {
            try {
                clazz = ClassLoaderUtils.getSystemClassLoader().loadClass(beanDefinition.getBeanClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        beanDefinition.setBeanClass(clazz);
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object object;
        try {
            object = beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException(e.getMessage(), BeansErrorCodeEnum.ERROR_CODE);
        }
        singletonObjects.put(beanName, object);
        return object;
    }

    @Override
    public boolean containsBean(String name) {
        return beanDefinitionMap.containsKey(name);
    }

    @Override
    public boolean isSingleton(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new BeansException(BeansErrorCodeEnum.BEAN_NOT_EXISTS);
        }
        return (Scope.SINGLETON.equals(beanDefinition.getScope()));
    }

    @Override
    public boolean isPrototype(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new BeansException(BeansErrorCodeEnum.BEAN_NOT_EXISTS);
        }
        return (Scope.PROTOTYPE.equals(beanDefinition.getScope()));
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
