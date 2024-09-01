package com.aminaventon.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfiguration {

    private static final String[] WHITELIST = {
            "/",
            "/favicon.ico",
            "/css/**",
            "/js/**",
            "/imgs/**",
            "/fonts/**",
            "/webjars/**",
            "/registration",

    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // authorize pages without authetication
        http.authorizeHttpRequests(auth -> {
            auth.
                    requestMatchers(WHITELIST).permitAll();

            auth.anyRequest().authenticated();
        });

        // set up login handling
        http.formLogin(formLogin ->
                formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error")
                        .permitAll()
        );

        // set up logout handling
        http.logout(formLogout -> {
            formLogout.
                    logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout");
        });

        http.httpBasic(withDefaults());


        return http.build();
    }


}
