package pers.kaigian.springframework.scanner;

import lombok.extern.slf4j.Slf4j;
import pers.kaigian.springframework.annotation.Component;
import pers.kaigian.springframework.beans.BeanDefinition;
import pers.kaigian.springframework.core.annotation.Lazy;
import pers.kaigian.springframework.core.annotation.Scope;
import pers.kaigian.springframework.support.BeanDefinitionRegistry;
import pers.kaigian.springframework.utils.ClassLoaderUtils;
import pers.kaigian.springframework.utils.StringUtils;

import java.beans.Introspector;
import java.io.File;
import java.net.URL;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author BrianHu
 * @create 2022-01-11 14:44
 **/
@Slf4j
public class ClassPathBeanDefinitionScanner extends BeanDefinitionScanner {

    private final BeanDefinitionRegistry bdRegistry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry bdRegistry) {
        this.bdRegistry = bdRegistry;
    }

    public void scan(String... scanPaths) {
        log.info("BeanDefinition扫描开始");
        for (String scanPath : scanPaths) {
            log.info("开始扫描{}路径", scanPath);
            doScan(scanPath);
        }
    }

    private void doScan(String scanPath) {
        Set<BeanDefinition> bdSet = findBeanDefinition(scanPath);

        for (BeanDefinition bd : bdSet) {
            Object beanClass = bd.getClazz();
            if (!(beanClass instanceof Class<?>)) {
                String classPath = (String) beanClass;
                try {
                    beanClass = ClassLoaderUtils.getSystemClassLoader().loadClass(classPath);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (!(beanClass instanceof Class<?>)) {
                log.error("beanDefinition的clazz属性{}出错", beanClass);
                continue;
            }
            Class<?> clazz = (Class<?>) beanClass;

            bd.setClazz(clazz);
            processAnnotation(bd);

            registerBeanDefinition(bd);
        }

    }

    private File[] validateScanPath(String scanPath) {
        scanPath = scanPath.replace(".", "/");
        // TODO: 2022/1/12 这里是通过类加载的方式，Spring使用的是ASM技术
        ClassLoader classLoader = ClassLoaderUtils.getSystemClassLoader();
        URL resource = classLoader.getResource(scanPath);
        if (resource == null) {
            log.error("包扫描路径{}不存在", scanPath);
            return null;
        }
        File scanDir = new File(resource.getFile());
        if (!scanDir.isDirectory()) {
            log.error("包扫描路径{}不正确", scanPath);
            return null;
        }
        File[] scanFiles = scanDir.listFiles();
        if (scanFiles == null || scanFiles.length == 0) {
            log.info("包扫描路径{}下为空", scanPath);
            return null;
        }
        return scanFiles;
    }

    private Set<BeanDefinition> findBeanDefinition(String scanPath) {
        Set<BeanDefinition> bdSet = new LinkedHashSet<>();
        File[] scanFiles = validateScanPath(scanPath);
        ClassLoader classLoader = ClassLoaderUtils.getSystemClassLoader();
        if (scanFiles != null) {
            for (File scanFile : scanFiles) {
                String absolutePath = scanFile.getAbsolutePath();
                if (!absolutePath.endsWith(".class")) {
                    continue;
                }
                String loadPath = absolutePath.substring(absolutePath.indexOf("target\\classes\\") + 15, absolutePath.length() - 6);
                loadPath = loadPath.replace("\\", ".");
                Class<?> clazz = null;
                try {
                    clazz = classLoader.loadClass(loadPath);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                if (clazz != null && clazz.isAnnotationPresent(Component.class)) {
                    BeanDefinition bd = new BeanDefinition();
                    bd.setClazz(clazz);
                    bdSet.add(bd);
                }
            }
        }
        return bdSet;
    }

    private void initBeanDefinition(BeanDefinition bd) {
        Class<?> beanClass = (Class<?>) bd.getClazz();
        String beanName;
        String nameVal = beanClass.getAnnotation(Component.class).value();
        if (StringUtils.isNotEmpty(nameVal)) {
            beanName = nameVal;
        } else {
            beanName = Introspector.decapitalize(beanClass.getSimpleName());
        }
        bd.setBeanName(beanName);
        bd.setLazy(false);
        bd.setType(Scope.SINGLETON);
    }

    private void processAnnotation(BeanDefinition bd) {
        initBeanDefinition(bd);
        Class<?> beanClass = (Class<?>) bd.getClazz();
        if (beanClass.isAssignableFrom(Scope.class)) {
            String scope = beanClass.getAnnotation(Scope.class).value();
            bd.setType(scope);
        }
        if (beanClass.isAssignableFrom(Lazy.class)) {
            bd.setLazy(true);
        }
    }

    public void registerBeanDefinition(BeanDefinition bd) {
        bdRegistry.registerBeanDefinition(bd);
    }
}
