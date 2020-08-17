package com.yang.javaspringsoot.filter;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;

/**
 * @author:
 * @create: 2020-08-17 15:07
 **/
@WebFilter(filterName = "requestParamaFilter", urlPatterns = "/**")
public class RequestParamaFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(RequestParamaFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
            LOGGER.debug("============init Resquest parama Filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.debug("============doFilter Resquest parama Filter");
        HttpServletRequest httpServletRequest= (HttpServletRequest) request;

        HttpServletRequestWrapper wrapper = new HttpServletRequestWrapper(httpServletRequest){

            @Override
            public String getParameter(String name){
                String value = httpServletRequest.getParameter(name);
                if (StringUtils.isNotBlank(value)){
                    return value.replaceAll("fuck","***");
                }
                return  super.getParameter(name);
            }

            public String[] getParameterValues(String name){
                String[] values = httpServletRequest.getParameterValues(name);
                if (values != null && values.length > 0){
                    for (int i = 0; i < values.length; i++){
                        values[i] = values[i].replaceAll("fuck","***");
                    }
                    return  values;
                }
                return  super.getParameterValues(name);
            }

        };
        chain.doFilter(wrapper, response);
    }

    @Override
    public void destroy() {
        LOGGER.debug("============destroy Resquest parama Filter");
    }
}
