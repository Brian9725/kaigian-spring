package pers.kaigian.springframework.core;

import pers.kaigian.springframework.core.annotation.Component;
import pers.kaigian.springframework.core.annotation.Lazy;
import pers.kaigian.springframework.core.annotation.Scope;

import java.beans.Introspector;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author BrianHu
 * @create 2021-09-03 22:18
 **/
public class ApplicationContext {
    // beanDefinition缓存池
    protected Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    // 单例bean缓存池
    protected Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    // beanPostProcessor缓存池
    protected List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    protected void scan(String scanPath) {
        scanPath = scanPath.replace(".", "/");
        ClassLoader classLoader = ApplicationContext.class.getClassLoader();
        File scanFile = new File(classLoader.getResource(scanPath).getFile());
        if (scanFile.isDirectory()) {
            for (File file : scanFile.listFiles()) {
                String absolutePath = file.getAbsolutePath();
                if (!absolutePath.endsWith(".class")) {
                    continue;
                }
                String loadPath = absolutePath.substring(absolutePath.indexOf("target\\classes\\") + 15, absolutePath.length() - 6);
                loadPath = loadPath.replace("\\", ".");
                Class clazz = null;
                try {
                    clazz = classLoader.loadClass(loadPath);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (clazz.isAnnotationPresent(Component.class)) {
                    // 单独处理beanPostProcessor
                    if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                        BeanPostProcessor beanPostProcessor = null;
                        try {
                            beanPostProcessor = (BeanPostProcessor) clazz.newInstance();
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                        beanPostProcessors.add(beanPostProcessor);
                        continue;
                    }

                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setClazz(clazz);

                    Component componentAnnotation = (Component) clazz.getAnnotation(Component.class);
                    String beanName = componentAnnotation.value();
                    if ("".equals(beanName)) {
                        beanName = Introspector.decapitalize(clazz.getSimpleName());
                    }
                    beanDefinition.setBeanName(beanName);

                    String type = Scope.SINGLETON;
                    if (clazz.isAssignableFrom(Scope.class)) {
                        Scope scopeAnnotation = (Scope) clazz.getAnnotation(Scope.class);
                        type = scopeAnnotation.value();
                    }
                    beanDefinition.setType(type);

                    boolean isLazy = false;
                    if (clazz.isAssignableFrom(Lazy.class)) {
                        isLazy = true;
                    }
                    beanDefinition.setLazy(isLazy);

                    beanDefinitionMap.put(beanName, beanDefinition);
                }
            }
        }
    }

    protected Object createBean(String beanName, BeanDefinition beanDefinition) {
        Object bean = null;
        if (beanDefinitionMap.containsKey(beanName)) {
            Class clazz = beanDefinition.getClazz();
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

    public Object getBean(String beanName) {
        return null;
    }

    public void printBeanDefinitionMap() {
        if (!beanDefinitionMap.isEmpty()) {
            for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
        }
    }
}
