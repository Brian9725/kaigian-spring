package com.kaigian;

import com.kaigian.service.UserService;
import com.spring.core.KaiGianApplicationContext;

/**
 * @author hukaiyang
 * @date 2021-09-03 22:16
 **/
public class Test {
	public static void main(String[] args) {
		KaiGianApplicationContext applicationContext = new KaiGianApplicationContext(AppConfig.class);
		applicationContext.printBeanDefinitionMap();
		UserService userService = (UserService) applicationContext.getBean("userService");
		userService.test();
	}
}
