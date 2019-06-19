package com.neuedu.lab.interceptors;

import com.neuedu.lab.token.TokenState;
import com.neuedu.lab.token.Tokenizer;
import net.minidev.json.JSONObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    private static Logger logger = LogManager.getLogger(LoginInterceptor.class);

    //这个方法是在访问接口之前执行的，我们只需要在这里写验证登陆状态的业务逻辑，就可以在用户调用指定接口之前验证登陆状态了
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //每一个项目对于登陆的实现逻辑都有所区别，我这里使用最简单的Session提取User来验证登陆。
        HttpSession session = request.getSession();
        //这里的User是登陆时放入session的
        String user_account = (String) session.getAttribute("user_account");
        String token = request.getParameter("token");
        //如果session中没有user，表示没登陆
        if (user_account != null){
            return true;    //如果session里有user，表示该用户已经登陆，放行，用户即可继续调用自己需要的接口
        }
        else if(token != null){
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
        else {
            //忽略当前请求，如果一个用户调用了需要登陆才能使用的接口，如果他没有登陆这里会直接忽略掉
            //利用response给用户返回提示信息，告诉他没登陆
            System.out.println(request.getRequestURL());
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
