package com.github.greenyears.core.context;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * HttpServletContext
 *
 * @author zhoumeiqin
 * @date 2020/4/23
 */
public class HttpServletContext {
    /**
     * 获取http request.
     * @return HttpServletRequest
     */
    public static HttpServletRequest getHttpServletRequest() {
        return getServletRequestAttributes().getRequest();
    }


    /**
     * 获取http response.
     * @return HttpServletResponse
     **/
    public static HttpServletResponse getHttpServletResponse() {
        return getServletRequestAttributes().getResponse();
    }

    /**
     * 获取 ServletRequestAttributes.
     * @return ServletRequestAttributes
     */
    private static ServletRequestAttributes getServletRequestAttributes() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        return (ServletRequestAttributes) requestAttributes;
    }
}
