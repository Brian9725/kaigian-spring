package pers.brian.springframework.beans.entity;

/**
 * @author BrianHu
 * @create 2022-04-29 16:27
 **/
public class PropertyValue {

    private final String name;

    private final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
