package pers.kaigian.springframework.utils;

/**
 * @author BrianHu
 * @create 2022-01-11 11:09
 **/
public class StringUtils {

    public static Boolean isEmpty(String string) {
        return (string == null || string.length() == 0);
    }

    public static Boolean isNotEmpty(String string) {
        return (string != null && string.length() > 0);
    }
}
