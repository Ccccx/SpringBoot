package com.cx.filter;

import com.cx.bean.UserContans;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

/**
 * @author CX
 * @create 2018/3/15
 */
/*@Component
@ServletComponentScan
@WebFilter(filterName = "SSOFilter",urlPatterns = "*//*")*/
public class SSOFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(SSOFilter.class);
    private static String SSO_LOGIN_URL = "http://127.0.0.1:8080/sso/login?redirectUrl=";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("SSO Filter init ...");
        UserContans.getInstance();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("SSO doFilter init ...");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        log.info("uri : {}", uri);
        if (uri.contains("sso/login")) {
            filterChain.doFilter(servletRequest,servletResponse);
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("token".equals(cookie.getName())) {
                    String value = (String) UserContans.getInstance().getKey(cookie.getValue());
                    if (StringUtils.isNotEmpty(value)) {
                        filterChain.doFilter(servletRequest,servletResponse);
                    }
                    break;
                }
            }
        }
        response.sendRedirect(SSO_LOGIN_URL + URLDecoder.decode(request.getRequestURL().toString(),"utf-8"));
    }

    @Override
    public void destroy() {
        log.info("SSO Filter destroy ...");
    }
}
