package pers.kaigian.springframework.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * @author BrianHu
 * @create 2022-01-11 15:36
 **/
@Getter
@ToString
public enum SpringErrorCodeEnum {

    //错误码
    SUCCESS_CODE("0", "成功"),
    ERROR_CODE("1", "失败"),
    SERVICE_NOT_SUPPORT("2", "暂不支持该功能");

    private String code;
    private String msg;

    public String msg() {
        return msg;
    }

    public String code() {
        return code;
    }

    SpringErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static SpringErrorCodeEnum getEnum(String code) {
        for (SpringErrorCodeEnum ele : SpringErrorCodeEnum.values()) {
            if (ele.code().equals(code)) {
                return ele;
            }
        }
        return null;
    }
}
