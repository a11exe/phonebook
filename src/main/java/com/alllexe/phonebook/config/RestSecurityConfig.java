/**
 * @author Alexander Abramov (alllexe@mail.ru)
 * @version 1
 * @since 14.02.2020
 */
package com.alllexe.phonebook.config;

import com.alllexe.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
            .antMatchers("/api/**")
            .and()
                .httpBasic()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
            .antMatchers(
                "/csrf",
                "/v2/api-docs",
                "/swagger-resources/**",
                "/swagger-ui.html",
                "/webjars/**"
            ).permitAll()
                //.authenticationEntryPoint(restAuthenticationEntryPoint())
            .and()
                .formLogin().disable()
                .logout().disable();
//        http
//            .requestMatchers()
//            .antMatchers("/api/**")
//            .and()
//            .httpBasic()
//            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and().authorizeRequests()
//            .antMatchers(
//                "/", "/csrf",
//                "/v2/api-docs",
//                "/swagger-resources/**",
//                "/swagger-ui.html",
//                "/webjars/**"
//            ).permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
            .passwordEncoder(passwordEncoder);
    }

    @Bean
    public AuthenticationEntryPoint restAuthenticationEntryPoint(){
        BasicAuthenticationEntryPoint entryPoint =
            new BasicAuthenticationEntryPoint();
        entryPoint.setRealmName("rest");
        return entryPoint;
    }
}
