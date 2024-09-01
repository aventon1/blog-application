package com.aminaventon.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfiguration {



    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.builder()
                .username("user1@email.com")
                .password("{noop}password")
                .authorities("ROLE_USER")
                .build();

        UserDetails admin = User.builder()
                .username("user2@email.com")
                .password("{noop}password")
                .authorities("ROLE_ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1,admin);
    }



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

        // authorize pages
        http.authorizeHttpRequests(auth -> {
            auth.
                    requestMatchers(WHITELIST).permitAll();

            auth.
                    anyRequest().authenticated();
        });

        // set up login handling
        http.formLogin(formLogin ->
                formLogin
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/")
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
