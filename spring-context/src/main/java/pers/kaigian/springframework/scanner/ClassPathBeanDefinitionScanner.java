package pers.kaigian.springframework.scanner;

import lombok.extern.slf4j.Slf4j;
import pers.kaigian.springframework.annotation.Component;
import pers.kaigian.springframework.config.*;
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

    private final BeanDefinitionRegistry registry;

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
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

        for (BeanDefinition beanDefinition : bdSet) {
            // 解析Scope注解
            Class<?> beanClass = ((AbstractBeanDefinition) beanDefinition).getBeanClass();
            String scope;
            if (beanClass.isAssignableFrom(Scope.class)) {
                scope = beanClass.getAnnotation(Scope.class).value();
            } else {
                scope = BeanDefinition.SCOPE_SINGLETON;
            }
            beanDefinition.setScope(scope);

            // 生成beanName
            String beanName;
            String nameVal = beanClass.getAnnotation(Component.class).value();
            if (StringUtils.isNotEmpty(nameVal)) {
                beanName = nameVal;
            } else {
                beanName = Introspector.decapitalize(beanClass.getSimpleName());
            }

            // 为BeanDefinition设置默认属性
            if (beanDefinition instanceof AbstractBeanDefinition) {
                postProcessBeanDefinition((AbstractBeanDefinition) beanDefinition, beanName);
            }

            // 解析@Lazy、@Primary、@DependsOn、@Role、@Description
            if (beanDefinition instanceof GenericBeanDefinition) {
                if (beanClass.isAssignableFrom(Lazy.class)) {
                    beanDefinition.setLazyInit(true);
                }
            }

            // 检查Spring容器中是否已经存在该beanName
            BeanDefinitionHolder bdHolder = new BeanDefinitionHolder(beanDefinition, beanName);
            registerBeanDefinition(bdHolder, this.registry);
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
                    ScannedGenericBeanDefinition sbd = new ScannedGenericBeanDefinition();
                    sbd.setBeanClass(clazz);
                    bdSet.add(sbd);
                }
            }
        }
        return bdSet;
    }

    protected void postProcessBeanDefinition(AbstractBeanDefinition beanDefinition, String beanName) {
        // 设置BeanDefinition的默认值
        beanDefinition.setLazyInit(false);

        // AutowireCandidate表示某个Bean能否被用来做依赖注入
    }

    public void registerBeanDefinition(BeanDefinitionHolder bdHolder, BeanDefinitionRegistry registry) {
        registry.registerBeanDefinition(bdHolder.getBeanName(), bdHolder.getBeanDefinition());
    }
}
