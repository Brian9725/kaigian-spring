package pers.brian.springframework.beans.definition;

import pers.brian.springframework.beans.definition.AbstractBeanDefinition;

/**
 * @author BrianHu
 * @create 2022-04-27 16:18
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
