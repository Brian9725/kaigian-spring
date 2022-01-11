package pers.kaigian.springframework.context;

import pers.kaigian.springframework.beans.BeanDefinition;
import pers.kaigian.springframework.annotation.ComponentScan;
import pers.kaigian.springframework.core.annotation.Scope;

import java.util.Map;

/**
 * 自己的Spring上下文环境
 *
 * @author BrianHu
 * @create 2021-09-03 22:22
 **/
public class KaiGianApplicationContext extends ApplicationContext {

    private Class configClass;

    public KaiGianApplicationContext(Class configClass) {
        super();
        this.configClass = configClass;

        // 获取扫描路径
        ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String scanPath = componentScanAnnotation.value();
        System.out.println("包扫描路径是:" + scanPath);

        // 将类扫描成beanDefinition并缓存
        scan(scanPath);

        // 创建非懒加载的单例bean
        if (!beanDefinitionMap.isEmpty()) {
            for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
                String beanName = entry.getKey();
                BeanDefinition beanDefinition = entry.getValue();
                if (Scope.SINGLETON.equals(beanDefinition.getType()) && !beanDefinition.isLazy()) {
                    createBean(beanName, beanDefinition);
                }
            }
        }
    }

    @Override
    public Object getBean(String beanName) {
        Object bean = null;
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition != null) {
            if (Scope.SINGLETON.equals(beanDefinition.getType())) {
                bean = singletonObjects.get(beanName);
                if (bean == null) {
                    if (beanDefinition.isLazy()) {
                        bean = createBean(beanName, beanDefinition);
                    } else {
                        System.out.println("创建" + beanName + "时发生了异常");
                    }
                }
            }
            if (Scope.PROTOTYPE.equals(beanDefinition.getType())) {
                bean = createBean(beanName, beanDefinition);
            }
        }
        return bean;
    }
}
