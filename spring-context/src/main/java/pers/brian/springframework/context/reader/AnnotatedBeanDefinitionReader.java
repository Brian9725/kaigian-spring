package pers.brian.springframework.context.reader;

import pers.brian.springframework.beans.support.BeanDefinitionRegistry;

/**
 * @author BrianHu
 * @create 2022-04-27 16:53
 **/
public class AnnotatedBeanDefinitionReader {

    private final BeanDefinitionRegistry bdRegistry;

    public AnnotatedBeanDefinitionReader(BeanDefinitionRegistry bdRegistry) {
        this.bdRegistry = bdRegistry;
    }
}
