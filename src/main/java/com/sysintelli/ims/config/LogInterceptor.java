/**
 * @CopyRight DeckITPL
 */
package com.sysintelli.ims.config;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chandrakant
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    Logger log = org.slf4j.LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception arg3)
            throws Exception {
//        log.info("Request Completed!");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object, ModelAndView model)
            throws Exception {
//        log.info("Method executed");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authCredentials = request.getHeader("Authorization");
//        System.out.println("authCredentials:::" + authCredentials);
        String pathInfo = request.getServletPath();/////api/auth
//        System.out.println("pathInfo::::::" + pathInfo);//remove after successfully work
        if (pathInfo.equals("/api/auth") || pathInfo.contains("/api/auth/checkToken/")
                || pathInfo.contains("/api/auth/user") || pathInfo.contains("/user/uploadImage/")
                || pathInfo.contains("/api/auth/forgotPW") //add create and forgot password also
                || pathInfo.contains("/scheduler/")
                || pathInfo.contains("/item/items")) {//mail functionality
            return true;

        }
        return true;
    }
}
