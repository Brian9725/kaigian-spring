package pers.brian.springframework.context.support;

/**
 * @author BrianHu
 * @create 2022-04-29 10:02
 **/
public interface AnnotationConfigRegistry {

    /**
     * 将componentClasses注册成BeanDefinition
     *
     * @param componentClasses 需要被注册的bean的Class对象
     */
    void register(Class<?>... componentClasses);

    /**
     * 扫描basePackages下的BeanDefinition
     *
     * @param basePackages 扫描路径
     */
    void scan(String... basePackages);


}
