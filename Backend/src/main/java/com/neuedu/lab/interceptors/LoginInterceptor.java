package com.neuedu.lab.interceptors;


import com.neuedu.lab.token.TokenState;
import com.neuedu.lab.token.Tokenizer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static Logger logger = LogManager.getLogger(LoginInterceptor.class);
    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            String token = request.getParameter("token");
            if (token != null) {
                System.out.println(request.getRequestURL() + "登录:[token]:" + token);
                Map<String, Object> result = Tokenizer.validToken(token);
                String state = (String) result.get("state");
                if (state.equals(TokenState.VALID.toString())) {
                    net.minidev.json.JSONObject payload = (net.minidev.json.JSONObject) result.get("data");
                    request.setAttribute("payload", payload);
                    Enumeration paramNames = request.getAttributeNames();
                    while (paramNames.hasMoreElements()) {
                        System.out.println(paramNames.nextElement());
                    }
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
            } else {
                //忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
                //利用response给用户返回提示信息，告诉他没登陆
                System.out.println(request.getRequestURL() + "拦截 未检测到token或session");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"code\":\"500\", \"msg\":\"Access Denied\"}");
                return false;
            }


    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
