package com.portfolio.sarvech.configurations;

import com.portfolio.sarvech.helper.Message;
import com.portfolio.sarvech.helper.MessageType;
import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfigurations {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

//        Configure URLS
        httpSecurity.authorizeHttpRequests(authorize->{
            authorize.requestMatchers("/admin/**").authenticated();
            authorize.anyRequest().permitAll();
        });

//        configure login form
        httpSecurity.formLogin(formLogin->{
            formLogin.loginPage("/login/admin-sarvech");
            formLogin.loginProcessingUrl("/authenticate");
            formLogin.successHandler((request, response, authentication) -> {
                HttpSession session = request.getSession();
                session.setAttribute("message", new Message("Welcome back to your portfolio Admin Panel!", MessageType.SUCCESS));
                response.sendRedirect("/admin/dashboard");
            });
            formLogin.failureHandler((request, response, exception) -> {
                HttpSession session = request.getSession();
                System.out.println("failed login");
                session.setAttribute("message", new Message("Invalid username or password!", MessageType.ERROR));
                response.sendRedirect("/login/admin-sarvech");
            });
//            formLogin.usernameParameter("username");
//            formLogin.passwordParameter("password");

        });
//        configure logout
        httpSecurity.logout(logout->{
            logout.logoutUrl("/logout");
            logout.logoutSuccessHandler((request, response, authentication) -> {
                HttpSession session = request.getSession();
                session.setAttribute("message", new Message("You have successfully logged out!", MessageType.WARNING));
                response.sendRedirect("/login/admin-sarvech");
            });
        });
        httpSecurity.csrf(AbstractHttpConfigurer::disable);


        return httpSecurity.build();
    }

    //    IN MEMORY DEFAULT USERS
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
