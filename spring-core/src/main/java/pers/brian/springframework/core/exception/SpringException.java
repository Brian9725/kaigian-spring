package pers.brian.springframework.core.exception;

/**
 * @author BrianHu
 * @create 2022-01-11 15:36
 **/
public class SpringException extends RuntimeException {

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 错误枚举
     */
    private SpringErrorCodeEnum springErrorCodeEnum;

    public SpringException() {
        super();
    }

    public SpringException(String message) {
        super(message);
        this.errorMessage = message;
        this.springErrorCodeEnum = SpringErrorCodeEnum.ERROR_CODE;
    }

    public SpringException(SpringErrorCodeEnum springErrorCodeEnum) {
        super(springErrorCodeEnum.msg());
        this.errorMessage = springErrorCodeEnum.msg();
        this.springErrorCodeEnum = springErrorCodeEnum;
    }

    public SpringException(String message, SpringErrorCodeEnum springErrorCodeEnum) {
        super(message);
        this.errorMessage = message;
        this.springErrorCodeEnum = springErrorCodeEnum;
    }
}
