package com.microservice.skeleton.user.config;

import com.google.common.collect.Maps;
import com.microservice.skeleton.user.filter.XssAndSqlFilter;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * Web 配置类，主要配置与 CAS（中央认证服务）相关的过滤器，
 * 并包括 XSS 和 SQL 注入过滤器的配置。
 */
@Configuration
public class WebConfig {

    /**
     * 配置 CAS 认证过滤器
     *
     * @return AuthenticationFilter 实例
     */
    @Bean
    public AuthenticationFilter getAuthenticationFilter(){
        return new AuthenticationFilter();  // 返回一个新的 CAS 认证过滤器
    }

    /**
     * 注册 CAS 认证过滤器
     *
     * @return FilterRegistrationBean 配置 CAS 认证过滤器
     */
    @Bean
    public FilterRegistrationBean authenticationFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getAuthenticationFilter());  // 设置要注册的过滤器
        filterRegistrationBean.setOrder(1);  // 设置过滤器执行顺序
        filterRegistrationBean.setEnabled(true);  // 启用过滤器
        filterRegistrationBean.addUrlPatterns("/user/loginByCAS");  // 设置过滤器拦截的 URL 路径
        filterRegistrationBean.setName("CASFilter");  // 设置过滤器的名称

        // 设置过滤器的初始化参数
        Map<String, String> initParameters = Maps.newHashMap();
        initParameters.put("casServerLoginUrl", "http://authserver.ndnu.edu.cn/authserver/login");  // CAS 认证服务器登录地址
        initParameters.put("serverName", "http://210.34.24.34:40080");  // 当前应用的服务器地址
        filterRegistrationBean.setInitParameters(initParameters);  // 将初始化参数加入过滤器

        return filterRegistrationBean;  // 返回过滤器注册信息
    }

    /**
     * 配置 CAS 票据验证过滤器
     *
     * @return Cas20ProxyReceivingTicketValidationFilter 实例
     */
    @Bean
    public Cas20ProxyReceivingTicketValidationFilter getCas20ProxyReceivingTicketValidationFilter(){
        return new Cas20ProxyReceivingTicketValidationFilter();  // 返回 CAS 票据验证过滤器实例
    }

    /**
     * 注册 CAS 票据验证过滤器
     *
     * @return FilterRegistrationBean 配置 CAS 票据验证过滤器
     */
    @Bean
    public FilterRegistrationBean cas20ProxyReceivingTicketValidationFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getCas20ProxyReceivingTicketValidationFilter());  // 设置要注册的过滤器
        filterRegistrationBean.setName("CAS Validation Filter");  // 设置过滤器的名称
        filterRegistrationBean.setOrder(2);  // 设置过滤器的执行顺序
        filterRegistrationBean.setEnabled(true);  // 启用过滤器
        filterRegistrationBean.addUrlPatterns("/user/loginByCAS");  // 设置过滤器拦截的 URL 路径

        // 设置过滤器的初始化参数
        Map<String, String> initParameters = Maps.newHashMap();
        initParameters.put("encoding", "UTF-8");  // 设置编码
        initParameters.put("casServerUrlPrefix", "http://authserver.ndnu.edu.cn/authserver");  // CAS 服务器地址前缀
        initParameters.put("serverName", "http://210.34.24.34:40080");  // 当前应用的服务器地址
        filterRegistrationBean.setInitParameters(initParameters);  // 将初始化参数加入过滤器

        return filterRegistrationBean;  // 返回过滤器注册信息
    }

    /**
     * 配置 HTTP 请求包装过滤器
     *
     * @return HttpServletRequestWrapperFilter 实例
     */
    @Bean
    public HttpServletRequestWrapperFilter getHttpServletRequestWrapperFilter(){
        return new HttpServletRequestWrapperFilter();  // 返回一个新的请求包装过滤器
    }

    /**
     * 注册 HTTP 请求包装过滤器
     *
     * @return FilterRegistrationBean 配置 HTTP 请求包装过滤器
     */
    @Bean
    public FilterRegistrationBean httpServletRequestWrapperFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getHttpServletRequestWrapperFilter());  // 设置要注册的过滤器
        filterRegistrationBean.setName("CAS HttpServletRequest Wrapper Filter");  // 设置过滤器的名称
        filterRegistrationBean.setOrder(3);  // 设置过滤器的执行顺序
        filterRegistrationBean.setEnabled(true);  // 启用过滤器
        filterRegistrationBean.addUrlPatterns("/*");  // 设置过滤器拦截所有 URL 路径

        return filterRegistrationBean;  // 返回过滤器注册信息
    }

    /**
     * 配置 CAS 单点注销过滤器
     *
     * @return SingleSignOutFilter 实例
     */
    @Bean
    public SingleSignOutFilter getSingleSignOutFilter(){
        return new SingleSignOutFilter();  // 返回一个新的单点注销过滤器
    }

    /**
     * 注册 CAS 单点注销过滤器
     *
     * @return FilterRegistrationBean 配置 CAS 单点注销过滤器
     */
    @Bean
    public FilterRegistrationBean singleSignOutFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getSingleSignOutFilter());  // 设置要注册的过滤器
        filterRegistrationBean.setName("CAS Single Sign Out Filter");  // 设置过滤器的名称
        filterRegistrationBean.setOrder(4);  // 设置过滤器的执行顺序
        filterRegistrationBean.setEnabled(true);  // 启用过滤器
        filterRegistrationBean.addUrlPatterns("/*");  // 设置过滤器拦截所有 URL 路径

        return filterRegistrationBean;  // 返回过滤器注册信息
    }

    /**
     * 配置 XSS 和 SQL 注入过滤器
     *
     * @return XssAndSqlFilter 实例
     */
    @Bean
    public XssAndSqlFilter getXssAndSqlFilter(){
        return new XssAndSqlFilter();  // 返回一个新的 XSS 和 SQL 注入过滤器
    }

    /**
     * 注册 XSS 和 SQL 注入过滤器
     *
     * @return FilterRegistrationBean 配置 XSS 和 SQL 注入过滤器
     */
    @Bean
    public FilterRegistrationBean xssFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(getXssAndSqlFilter());  // 设置要注册的过滤器
        filterRegistrationBean.setOrder(1);  // 设置过滤器的执行顺序
        filterRegistrationBean.setEnabled(true);  // 启用过滤器
        filterRegistrationBean.addUrlPatterns("/*");  // 设置过滤器拦截所有 URL 路径

        // 设置过滤器的初始化参数
        Map<String, String> initParameters = Maps.newHashMap();
        initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");  // 排除静态资源
        initParameters.put("isIncludeRichText", "true");  // 设置是否包含富文本内容的过滤
        filterRegistrationBean.setInitParameters(initParameters);  // 将初始化参数加入过滤器

        return filterRegistrationBean;  // 返回过滤器注册信息
    }
}
