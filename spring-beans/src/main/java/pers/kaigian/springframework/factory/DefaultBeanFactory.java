package pers.kaigian.springframework.factory;

import lombok.extern.slf4j.Slf4j;
import pers.kaigian.springframework.config.BeanDefinition;
import pers.kaigian.springframework.config.GenericBeanDefinition;
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
            return null;
        }
        return createBean(name, beanDefinitionMap.get(name));
    }

    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        log.info("创建bean：{}...", beanName);
        GenericBeanDefinition bd =(GenericBeanDefinition) beanDefinition;
        Class<?> beanClass = bd.getBeanClass();
        if (beanClass == null) {
            try {
                beanClass = ClassLoaderUtils.getSystemClassLoader().loadClass(bd.getBeanClassName());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        bd.setBeanClass(beanClass);
        Object object;
        try {
            object = beanClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeanException(e.getMessage(), BeanErrorCodeEnum.ERROR_CODE);
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
        return (Scope.SINGLETON.equals(beanDefinition.getScope()));
    }

    @Override
    public boolean isPrototype(String name) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new BeanException(BeanErrorCodeEnum.BEAN_NOT_EXISTS);
        }
        return (Scope.PROTOTYPE.equals(beanDefinition.getScope()));
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
