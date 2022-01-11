package pers.kaigian.springframework.factory;

import pers.kaigian.springframework.beans.BeanDefinition;
import pers.kaigian.springframework.core.annotation.Scope;
import pers.kaigian.springframework.exception.BeanErrorCodeEnum;
import pers.kaigian.springframework.exception.BeanException;
import pers.kaigian.springframework.exception.SpringErrorCodeEnum;
import pers.kaigian.springframework.exception.SpringException;
import pers.kaigian.springframework.extension.BeanPostProcessor;
import pers.kaigian.springframework.extension.InitializingBean;
import pers.kaigian.springframework.support.BeanDefinitionRegistry;

import java.util.ArrayList;
import java.util.List;
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

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();


    @Override
    public Object getBean(String name) throws Throwable {
        Object bean = null;
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition != null) {
            if (Scope.SINGLETON.equals(beanDefinition.getType())) {
                bean = singletonObjects.get(name);
                if (bean == null) {
                    if (beanDefinition.isLazy()) {
                        bean = createBean(name, beanDefinition);
                    } else {
                        System.out.println("创建" + name + "时发生了异常");
                    }
                }
            }
            if (Scope.PROTOTYPE.equals(beanDefinition.getType())) {
                bean = createBean(name, beanDefinition);
            }
        }
        return bean;
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws Throwable {
        throw new SpringException(SpringErrorCodeEnum.SERVICE_NOT_SUPPORT);
    }

    @Override
    public Object getBean(String name, Object... args) throws Throwable {
        throw new SpringException(SpringErrorCodeEnum.SERVICE_NOT_SUPPORT);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws Throwable {
        throw new SpringException(SpringErrorCodeEnum.SERVICE_NOT_SUPPORT);
    }

    @Override
    public <T> T getBean(Class<T> requiredType, Object... args) throws Throwable {
        throw new SpringException(SpringErrorCodeEnum.SERVICE_NOT_SUPPORT);
    }

    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        if (beanDefinitionMap.containsKey(beanName)) {
            Class clazz = (Class) beanDefinition.getClazz();
            try {
                bean = clazz.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                beanPostProcessor.postProcessBeforeInitialization(bean, beanName);
            }

            // TODO:设置属性

            if (bean instanceof InitializingBean) {
                ((InitializingBean) bean).afterPropertiesSet();
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
                beanPostProcessor.postProcessAfterInitialization(bean, beanName);
            }

            singletonObjects.put(beanName, bean);
        }
        return bean;
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
