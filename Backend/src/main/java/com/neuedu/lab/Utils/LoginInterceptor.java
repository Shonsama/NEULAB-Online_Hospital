package com.neuedu.lab.Utils;


import com.neuedu.lab.Token.TokenState;
import com.neuedu.lab.Token.Tokenizer;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
    private final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        logger.info("request请求地址path[{}] uri[{}]", request.getServletPath(),request.getRequestURI());
        //request.getHeader(String) 从请求头中获取数据
        //从请求头中获取用户token（登陆凭证根据业务而定）
        String token= request.getParameter("token");
        Map<String, Object> result = Tokenizer.validToken(token);
        String state = (String) result.get("state");
        if (state.equals(TokenState.VALID.toString())){
            JSONObject payload = (JSONObject) result.get("data");
            request.setAttribute("payload", payload);
            return true;
        }else if (state.equals(TokenState.INVALID.toString())) {
            // 按照通用错误格式将返回数据写入response
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"code\":\"500\", \"msg\":\"Invalid token\"}");
            response.sendRedirect("/");
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