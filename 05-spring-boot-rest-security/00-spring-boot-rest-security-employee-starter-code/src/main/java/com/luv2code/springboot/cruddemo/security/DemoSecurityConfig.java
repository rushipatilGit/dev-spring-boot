package com.luv2code.springboot.cruddemo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC ... and no more hardcoded users.

    @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource){

        return new JdbcUserDetailsManager(dataSource);

    }

    // For Authorization and Retricting access based on the Roles.
    // Setting up the Authorization Roles for the Given End points and HTTP Methods
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "api/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST,"api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT,"api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"api/employees/**").hasRole("ADMIN")
        );

        // use Http basic authentication
        http.httpBasic(Customizer.withDefaults());
        //Disable Cross Site Request Forgery (CSRF)
        // in general, not required for stateless REST APIs that use POST, PUT, DELETE, and/or PATCH
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

       /*
// For Authenticating the User with Username and password and assigning the Roles.
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(john,mary,susan);

    }

     */

}
