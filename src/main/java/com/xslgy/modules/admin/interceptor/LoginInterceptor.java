package com.xslgy.modules.admin.interceptor;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        String uri = request.getRequestURI();
        Object cacheToken = request.getSession(true).getAttribute("token");
        String cachedToken = null;
        if (cacheToken != null) {
            cachedToken = cacheToken.toString();
        }
        if ((StringUtils.isEmpty(token) || (!StringUtils.isEmpty(token) && !token.equals(cachedToken))) && uri.startsWith("/admin")) {
            log.info("用户未登录");
            ObjectNode result = JsonNodeFactory.instance.objectNode();
            result.put("isError", true);
            ObjectNode error = JsonNodeFactory.instance.objectNode();
            error.put("code", 403);
            error.put("message", "user not login");
            result.put("error", error);
            response.getWriter().write(result.toString());
            return false;
        } else {
            return true;
        }
    }
}
