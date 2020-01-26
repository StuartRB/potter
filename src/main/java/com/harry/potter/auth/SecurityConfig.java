package com.harry.potter.auth;

import com.harry.potter.user.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    RegisteredUserService registeredUserService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
//                .inMemoryAuthentication()
//                .withUser("User").password(passwordEncoder().encode("User123")).roles("USER")
//                .and()
//                .withUser("Admin").password(passwordEncoder().encode("Admin123")).roles("ADMIN", "MANAGER", "USER")
//                .and()
//                .withUser("Manager").password(passwordEncoder().encode("Manager123")).roles("MANAGER", "USER");

        .authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/api/public/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/user/").permitAll()
                .antMatchers(HttpMethod.POST, "/api/user/").hasRole("ADMIN")
                .antMatchers("/api/private/**").hasRole("USER")
                .and()
                .httpBasic();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setPasswordEncoder(dummyPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(registeredUserService);
        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    PasswordEncoder dummyPasswordEncoder(){
        return new DummyPasswordEncoder();
    }
}
