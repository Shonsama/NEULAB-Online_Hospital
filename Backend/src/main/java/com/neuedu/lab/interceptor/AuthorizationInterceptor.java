package com.neuedu.lab.interceptor;

import com.neuedu.lab.token.TokenState;
import com.neuedu.lab.token.Tokenizer;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        // 从request中得到token
        String token = request.getParameter("token");
        // WeNEULogger.i("token: "+token);
        Map<String, Object> result = Tokenizer.validToken(token);
        String state = (String) result.get("state");
        if (state.equals(TokenState.VALID.toString())) {
            JSONObject payload = (JSONObject) result.get("data");
            request.setAttribute("payload", payload);
            return true;
        } else if (state.equals(TokenState.INVALID.toString())) {
            // 按照通用错误格式将返回数据写入response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"code\":\"500\", \"msg\":\"Invalid token\"}");
            return false;
        } else if (state.equals(TokenState.EXPIRED.toString())) {
            //按照通用错误格式将返回数据写入response.
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"code\":\"500\", \"msg\":\"Expired token\"}");
            return false;
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
    {}
}
