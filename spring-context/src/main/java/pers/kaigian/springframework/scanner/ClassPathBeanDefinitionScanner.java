package pers.kaigian.springframework.scanner;

import pers.kaigian.springframework.annotation.Component;
import pers.kaigian.springframework.beans.BeanDefinition;
import pers.kaigian.springframework.core.annotation.Lazy;
import pers.kaigian.springframework.core.annotation.Scope;
import pers.kaigian.springframework.extension.BeanPostProcessor;
import pers.kaigian.springframework.support.BeanDefinitionRegistry;
import pers.kaigian.springframework.utils.ClassLoaderUtils;

import java.beans.Introspector;
import java.io.File;
import java.net.URL;

/**
 * @author BrianHu
 * @create 2022-01-11 14:44
 **/
public class ClassPathBeanDefinitionScanner extends BeanDefinitionScanner {

    private final BeanDefinitionRegistry bdRegistry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry bdRegistry) {
        this.bdRegistry = bdRegistry;
    }

    public void scan(String scanPath) {
        doScan(scanPath);
    }

    private void doScan(String scanPath) {
        scanPath = scanPath.replace(".", "/");
        ClassLoader classLoader = ClassLoaderUtils.getSystemClassLoader();
        URL resource = classLoader.getResource(scanPath);
        if (resource == null) {
            System.out.println("包扫描路径不存在");
            return;
        }
        File scanFile = new File(resource.getFile());
        if (scanFile.isDirectory() && scanFile.listFiles() != null) {
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
                        // beanPostProcessors.add(beanPostProcessor);
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

                    registerBeanDefinition(beanDefinition);
                }
            }
        }
    }

    public void registerBeanDefinition(BeanDefinition bd) {
        bdRegistry.registerBeanDefinition(bd);
    }
}
