package pers.brian.springframework.context;

import pers.brian.springframework.context.annotation.ComponentScan;
import pers.brian.springframework.context.reader.AnnotatedBeanDefinitionReader;
import pers.brian.springframework.context.reader.ClassPathBeanDefinitionScanner;
import pers.brian.springframework.core.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自己的Spring上下文环境
 *
 * @author BrianHu
 * @create 2021-09-03 22:22
 **/
public class AnnotationConfigApplicationContext extends ApplicationContext {

    private final ClassPathBeanDefinitionScanner scanner;

    private final AnnotatedBeanDefinitionReader reader;

    private final List<String> scanPaths = new ArrayList<>();

    public AnnotationConfigApplicationContext() {
        super();
        this.scanner = new ClassPathBeanDefinitionScanner(this);
        this.reader = new AnnotatedBeanDefinitionReader(this);
    }

    public AnnotationConfigApplicationContext(Class<?>... configClasses) {
        this();
        register(configClasses);
        refresh();
    }

    public void register(Class<?>... configClasses) {
        for (Class<?> configClass : configClasses) {
            if (configClass.isAnnotationPresent(ComponentScan.class)) {
                String scanPath = configClass.getAnnotation(ComponentScan.class).value();
                if (StringUtils.isNotEmpty(scanPath)) {
                    scanPaths.add(scanPath);
                }
            }
        }
    }

    public void refresh() {
        String[] paths = scanPaths.toArray(new String[0]);
        scanner.scan(paths);
    }
}
