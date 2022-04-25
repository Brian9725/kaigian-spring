package pers.kaigian.springframework.context;

import lombok.extern.slf4j.Slf4j;
import pers.kaigian.springframework.annotation.ComponentScan;
import pers.kaigian.springframework.scanner.ClassPathBeanDefinitionScanner;
import pers.kaigian.springframework.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 自己的Spring上下文环境
 *
 * @author BrianHu
 * @create 2021-09-03 22:22
 **/
@Slf4j
public class KaiGianApplicationContext extends ApplicationContext {

    private final ClassPathBeanDefinitionScanner bdScanner;

    private final List<String> scanPaths = new ArrayList<>();

    public KaiGianApplicationContext() {
        super();
        this.bdScanner = new ClassPathBeanDefinitionScanner(this);
    }

    public KaiGianApplicationContext(Class<?>... configClasses) {
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
        bdScanner.scan(paths);
    }
}
