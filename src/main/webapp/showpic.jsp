<%@page import="java.io.*"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Properties"%>
<%@page import="edu.cqu.fly.xccms.util.ComonUtil"%>
<%@page contentType="application/json;charset=utf-8" language="java"
	errorPage=""%>
<%
	String picname = request.getParameter("pictruename");
	if (picname != null && !"".equals(picname)) {

		File file = new File(ComonUtil.getfileCfg().getProperty(
				"fileroot")
				+ "/image/" + picname);
		FileInputStream fin = new FileInputStream(file);
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = fin.read(buffer, 0, 1024)) != -1) {
			response.getOutputStream().write(buffer, 0, len);
			out.clear();
			out = pageContext.pushBody();
		}

		fin.close();
	}
%>
