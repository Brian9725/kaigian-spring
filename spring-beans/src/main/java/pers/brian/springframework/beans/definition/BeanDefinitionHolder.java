package pers.brian.springframework.beans.definition;

/**
 * @author BrianHu
 * @create 2022-04-27 16:44
 **/
public class BeanDefinitionHolder {

    private final String beanName;

    private final BeanDefinition beanDefinition;

    public BeanDefinitionHolder(String beanName, BeanDefinition beanDefinition) {
        this.beanName = beanName;
        this.beanDefinition = beanDefinition;
    }

    public String getBeanName() {
        return beanName;
    }

    public BeanDefinition getBeanDefinition() {
        return beanDefinition;
    }
}
