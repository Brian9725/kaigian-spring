package com.spring.core;

import com.sun.istack.internal.Nullable;

/**
 * @author hukaiyang
 * @date 2021-09-06 23:44
 **/
public interface BeanPostProcessor {
	@Nullable
	default Object postProcessBeforeInitialization(Object bean, String beanName) {
		return bean;
	}

	@Nullable
	default Object postProcessAfterInitialization(Object bean, String beanName) {
		return bean;
	}
}
