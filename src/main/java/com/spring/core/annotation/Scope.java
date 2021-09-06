package com.spring.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author hukaiyang
 * @date 2021-09-06 10:03
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Scope {
	String SINGLETON = "singleton";
	String PROTOTYPE = "prototype";

	String value() default SINGLETON;
}
