package pers.kaigian.springframework.config;

/**
 * @author hukaiyang
 * @version V1.0
 * @create 2022-04-26 10:00
 * @contact hukaiyang@huice.com
 * @company 掌上先机 (http://www.huice.com)
 **/
public class GenericBeanDefinition extends AbstractBeanDefinition {

    /**
     * 父BeanDefinition名称
     */
    private String parentName;

    public GenericBeanDefinition() {
        super();
    }

    @Override
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    @Override
    public String getParentName() {
        return parentName;
    }
}
