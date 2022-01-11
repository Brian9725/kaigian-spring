package pers.kaigian.springframework.beans;

import lombok.Data;

/**
 * @author BrianHu
 * @create 2021-09-06 10:24
 **/
@Data
public class BeanDefinition {

    /**
     * bean类型，如果类被加载则是Class对象，如果没有则是String类型的类路径
     */
    private Object clazz;

    /**
     * bean名称，单例bean唯一
     */
    private String beanName;

    /**
     * bean类型，单例、多例
     */
    private String type;

    /**
     * 是否懒加载
     */
    private boolean lazy;
}
