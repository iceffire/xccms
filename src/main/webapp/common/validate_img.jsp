<%@page import="edu.cqu.fly.xccms.util.RandomImageGenerator"%>
<%@ page language="java" import="java.util.*,java.io.*"
	pageEncoding="UTF-8"%>
<%
	String RANDOM_IMAGES_KEY = "RANDOMIMAGES";
	if (session != null) {
		String randomString = RandomImageGenerator.random(4);//生成种子
		session.setAttribute(RANDOM_IMAGES_KEY, randomString);//将种子放到session里面
		response.setContentType("image/jpeg");//设置图像生成格式
		RandomImageGenerator.render(randomString,response.getOutputStream());//输出到页面
		response.flushBuffer();  
		out.clear();  
		out = pageContext.pushBody();  
	}
%>