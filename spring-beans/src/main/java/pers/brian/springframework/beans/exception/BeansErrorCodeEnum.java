package pers.brian.springframework.beans.exception;

/**
 * @author BrianHu
 * @create 2022-01-11 15:16
 **/
public enum BeansErrorCodeEnum {

    //错误码
    SUCCESS_CODE("0", "成功"),
    ERROR_CODE("1", "失败"),
    BEAN_ALREADY_EXISTS("2", "bean已存在"),
    BEAN_NOT_EXISTS("3", "bean不存在");

    private final String code;

    private final String msg;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    BeansErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static BeansErrorCodeEnum getEnum(String code) {
        for (BeansErrorCodeEnum ele : BeansErrorCodeEnum.values()) {
            if (ele.getCode().equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
