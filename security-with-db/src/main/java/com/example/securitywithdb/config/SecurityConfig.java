package com.example.securitywithdb.config;

import com.example.securitywithdb.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    BackdoorAuthenticationProvider backdoorAuthenticationProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*注意roles("xxx")的参数传入后，若无ROLE_前缀，则会加上，封装为GrantedAuthority，存储在List中*/
        auth
                .inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("123")).roles("USER")
                .and()
                .withUser("templates/admin").password(passwordEncoder.encode("123")).roles("USER", "ADMIN");
        /*将自定义的验证类注册进去*/
        auth.authenticationProvider(backdoorAuthenticationProvider);
        /*加入数据库验证类*/
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*注意matchers之间有先后顺序之分，若满足前面某个matchers，后面的matchers都不会进行匹配*/
        http
                .authorizeRequests()
                .antMatchers("/", "/index", "/error").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/templates/admin/**").hasRole("ADMIN")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/user")
                .usernameParameter("myusername").passwordParameter("mypassword")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
