package pers.kaigian.springframework.factory;

import lombok.extern.slf4j.Slf4j;
import pers.kaigian.springframework.beans.BeanDefinition;
import pers.kaigian.springframework.beans.NullBean;
import pers.kaigian.springframework.core.annotation.Scope;
import pers.kaigian.springframework.exception.BeanErrorCodeEnum;
import pers.kaigian.springframework.exception.BeanException;
import pers.kaigian.springframework.support.BeanDefinitionRegistry;
import pers.kaigian.springframework.utils.ClassLoaderUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author BrianHu
 * @create 2022-01-11 15:04
 **/
@Slf4j
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
    public Object getBean(String name) {
        Object bean = singletonObjects.get(name);
        if (bean != null) {
            return bean;
        }
        if (!containsBean(name)) {
            log.warn("没有名为{}的bean", name);
            return new NullBean();
        }
        return createBean(name, beanDefinitionMap.get(name));
    }

    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        log.info("创建bean：{}...", beanName);
        Object clazz = beanDefinition.getClazz();
        if (!(clazz instanceof Class<?>)) {
            try {
                clazz = ClassLoaderUtils.getSystemClassLoader().loadClass((String) clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        beanDefinition.setClazz(clazz);
        Class<?> beanClass = (Class<?>) beanDefinition.getClazz();
        Object object = new NullBean();
        try {
            object = beanClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
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
            throw new BeanException(BeanErrorCodeEnum.BEAN_NOT_EXISTS);
        }
        return (Scope.SINGLETON.equals(beanDefinition.getType()));
    }

    @Override
    public boolean isPrototype(String name) {
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
