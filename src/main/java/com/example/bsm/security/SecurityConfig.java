package com.example.bsm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Bean
   public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }
   @Bean
   SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
     return   httpSecurity.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/registers","/blood-banks")
                                .permitAll()
//                                .requestMatchers("/users/{userid}")
//                                .hasAuthority("OWNER_ADMIN")
                                .anyRequest()
                                .authenticated())
                .formLogin(Customizer.withDefaults())
                .build();
   }
}
