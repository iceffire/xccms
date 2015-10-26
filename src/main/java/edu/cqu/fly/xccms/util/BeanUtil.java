package edu.cqu.fly.xccms.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BeanUtil<T>{

	@SuppressWarnings("unchecked")
	public T getBean(String beanName){
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		return (T) applicationContext.getBean(beanName);
		
	}
}
