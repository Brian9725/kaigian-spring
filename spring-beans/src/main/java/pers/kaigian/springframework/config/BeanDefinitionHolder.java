package pers.kaigian.springframework.config;

/**
 * @author hukaiyang
 * @version V1.0
 * @create 2022-04-26 09:51
 * @contact hukaiyang@huice.com
 * @company 掌上先机 (http://www.huice.com)
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
