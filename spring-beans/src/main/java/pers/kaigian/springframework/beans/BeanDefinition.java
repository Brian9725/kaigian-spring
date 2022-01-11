package pers.kaigian.springframework.beans;

/**
 * @author BrianHu
 * @create 2021-09-06 10:24
 **/
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
    private boolean isLazy;

    public Object getClazz() {
        return clazz;
    }

    public void setClazz(Object clazz) {
        this.clazz = clazz;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }
}
