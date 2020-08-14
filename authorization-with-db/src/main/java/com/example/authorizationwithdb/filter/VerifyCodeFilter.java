package com.example.authorizationwithdb.filter;

import com.mysql.cj.util.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截login的POST请求，检查验证码是否为空或者验证码是否正确
 */
@Component
public class VerifyCodeFilter extends GenericFilterBean {
    private String filterProcessUrl = "/login";

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if ("POST".equalsIgnoreCase(request.getMethod()) && filterProcessUrl.equals(request.getServletPath())) {
            String requestCaptcha = request.getParameter("code");
            System.out.println(requestCaptcha);
            String genCaptcha = (String) request.getSession().getAttribute("ver_code");
            System.out.println(genCaptcha);
            if (StringUtils.isNullOrEmpty(requestCaptcha)) {
                throw new AuthenticationServiceException("验证码不能为空");
            }
            if (!genCaptcha.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                throw new AuthenticationServiceException("验证码错误");
            }
        }
        filterChain.doFilter(request, response);
    }
}
