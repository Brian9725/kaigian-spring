package pers.brian.springframework.beans.definition;

/**
 * @author BrianHu
 * @create 2022-04-27 16:44
 **/
public class BeanDefinitionHolder {

    private final BeanDefinition beanDefinition;

    private final String beanName;

    private final String[] aliases;

    public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName) {
        this(beanDefinition, beanName, null);
    }

    public BeanDefinitionHolder(BeanDefinition beanDefinition, String beanName, String[] aliases) {
        this.beanDefinition = beanDefinition;
        this.beanName = beanName;
        this.aliases = aliases;
    }

    public BeanDefinition getBeanDefinition(){
        return beanDefinition;
    }

    public String getBeanName() {
        return beanName;
    }
}
