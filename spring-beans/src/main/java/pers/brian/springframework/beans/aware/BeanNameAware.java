package pers.brian.springframework.beans.aware;

/**
 * @author BrianHu
 * @create 2022-04-29 16:52
 **/
public interface BeanNameAware extends Aware {
    /**
     * 设置bean名称
     *
     * @param name bean名称
     */
    void setBeanName(String name);
}
