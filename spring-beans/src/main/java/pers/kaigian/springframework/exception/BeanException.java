package pers.kaigian.springframework.exception;

import lombok.Data;

/**
 * @author BrianHu
 * @create 2022-01-11 15:09
 **/
@Data
public class BeanException extends RuntimeException {

    /**
     * 错误信息
     */
    private String errorMessage;

    /**
     * 错误枚举
     */
    private BeanErrorCodeEnum beanErrorCodeEnum;

    public BeanException() {
        super();
    }

    public BeanException(String message) {
        super(message);
        this.errorMessage = message;
        this.beanErrorCodeEnum = BeanErrorCodeEnum.ERROR_CODE;
    }

    public BeanException(BeanErrorCodeEnum beanErrorCodeEnum) {
        super(beanErrorCodeEnum.getMsg());
        this.errorMessage = beanErrorCodeEnum.getMsg();
        this.beanErrorCodeEnum = beanErrorCodeEnum;
    }

    public BeanException(String message, BeanErrorCodeEnum beanErrorCodeEnum) {
        super(message);
        this.errorMessage = message;
        this.beanErrorCodeEnum = beanErrorCodeEnum;
    }
}
