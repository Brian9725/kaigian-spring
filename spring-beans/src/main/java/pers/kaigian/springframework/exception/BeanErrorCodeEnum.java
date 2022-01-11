package pers.kaigian.springframework.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @author BrianHu
 * @create 2022-01-11 15:16
 **/
@Getter
@ToString
public enum BeanErrorCodeEnum {

    //错误码
    SUCCESS_CODE("0", "成功"),
    ERROR_CODE("1", "失败"),
    BEAN_ALREADY_EXISTS("2", "bean已存在"),
    BEAN_NOT_EXISTS("3", "bean不存在");

    private String code;
    private String msg;

    public String msg() {
        return msg;
    }

    public String code() {
        return code;
    }

    BeanErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BeanErrorCodeEnum getEnum(String code) {
        for (BeanErrorCodeEnum ele : BeanErrorCodeEnum.values()) {
            if (ele.code().equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
