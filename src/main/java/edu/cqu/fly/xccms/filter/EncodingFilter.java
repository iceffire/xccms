package edu.cqu.fly.xccms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
/**
 * <p>EncodingFilter.java</p>
 * <p>解决乱码问题的过滤器 </p>
 *
 */
  public class EncodingFilter implements Filter {

    private String encoding="UTF-8";

	public void init(FilterConfig filterconfig) throws ServletException {
		encoding = filterconfig.getInitParameter("encoding");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterchain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpRequest.setCharacterEncoding(encoding);
		filterchain.doFilter(request, response);

	}

	public void destroy() {

	}

      }
