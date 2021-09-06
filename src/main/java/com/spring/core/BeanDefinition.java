package com.spring.core;

/**
 * @author hukaiyang
 * @date 2021-09-06 10:24
 **/
public class BeanDefinition {
	private Class clazz;
	private String beanName;
	private String type;
	private boolean isLazy;

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isLazy() {
		return isLazy;
	}

	public void setLazy(boolean lazy) {
		isLazy = lazy;
	}
}
