package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
@WebFilter(urlPatterns={"/*"},filterName="EncodeFilter", initParams={@WebInitParam(name="encode",value="UTF-8")})
public class EcodingFilter implements Filter {
	private String  encode = null;
	@Override
	public void init(FilterConfig config) throws ServletException {
		//��ȡ��ʼ������
	     String encode =config.getInitParameter("encode");
	      if(this.encode == null){
	           this.encode =encode;  //���������ļ���Ԥ����ַ�������
	      }
		System.out.println("ecodingFilter��ʼ��");
	}

	@Override
	public void doFilter(ServletRequest res, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		//���resδ���ñ��룬���������
		if(null==res.getCharacterEncoding()){
		    res.setCharacterEncoding(encode);
			resp.setCharacterEncoding(encode);
			resp.setContentType("text/html; charset"+encode);
		}
		chain.doFilter(res, resp);
	}

	@Override
	public void destroy() {
		 encode = null;
		System.out.println("ecodingFilter���٣�");
	}

}
