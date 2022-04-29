package pers.brian.springframework.beans.exception;

/**
 * @author BrianHu
 * @create 2022-01-11 15:09
 **/
public class BeansException extends RuntimeException {

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 错误枚举
     */
    private BeansErrorCodeEnum beansErrorCodeEnum;

    public BeansException() {
        super();
    }

    public BeansException(String message) {
        super(message);
        this.errorMessage = message;
        this.beansErrorCodeEnum = BeansErrorCodeEnum.ERROR_CODE;
    }

    public BeansException(BeansErrorCodeEnum beansErrorCodeEnum) {
        super(beansErrorCodeEnum.getMsg());
        this.errorMessage = beansErrorCodeEnum.getMsg();
        this.beansErrorCodeEnum = beansErrorCodeEnum;
    }

    public BeansException(String message, BeansErrorCodeEnum beansErrorCodeEnum) {
        super(message);
        this.errorMessage = message;
        this.beansErrorCodeEnum = beansErrorCodeEnum;
    }
}
