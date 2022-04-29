package pers.brian.springframework.beans.factory;

/**
 * 表示BeanFactory可做一些自定义配置
 *
 * @author BrianHu
 * @create 2022-04-29 14:11
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory {

    /**
     * 设置父BeanFactory
     *
     * @param parentBeanFactory 父BeanFactory
     */
    void setParentBeanFactory(BeanFactory parentBeanFactory);

    /**
     * 设置使用的类加载器
     *
     * @param beanClassLoader 使用的类加载器
     */
    void setBeanClassLoader(ClassLoader beanClassLoader);

    /**
     * 获取加载BeanClass的类加载器
     *
     * @return 加载BeanClass的类加载器
     */
    ClassLoader getBeanClassLoader();
}
