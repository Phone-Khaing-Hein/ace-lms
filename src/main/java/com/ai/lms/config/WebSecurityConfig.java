package com.ai.lms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ai.lms.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource(name = "MyUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.formLogin(form -> form.loginPage("/login"));
		http.logout(logout -> logout.logoutUrl("/logout").invalidateHttpSession(true).logoutSuccessUrl("/login")).rememberMe().key("remember-me").tokenValiditySeconds(5).rememberMeCookieName("remember-me");
		http.exceptionHandling().accessDeniedPage("/denied-page");
		http.authorizeHttpRequests(auth -> auth
				.mvcMatchers("/login").permitAll()
				.mvcMatchers("/admin/**").hasAuthority(User.Role.Admin.name())
				.mvcMatchers("/teacher/**").hasAuthority(User.Role.Teacher.name())
                .mvcMatchers("/student/**").hasAuthority(User.Role.Student.name()));

    }
}
