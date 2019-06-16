package com.neuedu.lab;

import com.neuedu.lab.security.FuryAuthFailureHandler;
import com.neuedu.lab.security.FuryAuthSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserSafetyService userSafetyService;
    @Autowired
    private FuryAuthSuccessHandler furyAuthSuccessHandler;
    @Autowired
    private FuryAuthFailureHandler furyAuthFailureHandler;

    /**
     * 配置拦截器保护请求
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    //"/login"不进行权限验证
                    .antMatchers("/static/**").permitAll()
                    .antMatchers("/#/","/index.html").permitAll()
                    .anyRequest().authenticated()   //其他的需要登陆后才能访问
                    .and()
                    .formLogin()
                    .loginPage("/")
                    //loginProcessingUrl用于指定前后端分离的时候调用后台登录接口的名称
                    .loginProcessingUrl("/user-service/check-vaild")
                    .permitAll()
                    //配置登录成功的自定义处理类
                    .successHandler(furyAuthSuccessHandler)
//                    //配置登录失败的自定义处理类
                    .failureHandler(furyAuthFailureHandler)
                    .and()
                    //loginProcessingUrl用于指定前后端分离的时候调用后台注销接口的名称
                    .logout().logoutUrl("/logout")
//                    .logoutSuccessHandler(myLogoutSuccessHandler)
//                    .and()
//                    //配置没有权限的自定义处理类
//                    .exceptionHandling().accessDeniedHandler(restAuthAccessDeniedHandler)
                    .and()
                    .cors()//新加入
                    .and()
                    .csrf().disable();// 取消跨站请求伪造防护
    }

    /**
     * 配置user-detail服务
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER"));
    }
}