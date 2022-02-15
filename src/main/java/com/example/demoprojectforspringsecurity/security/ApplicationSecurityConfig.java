package com.example.demoprojectforspringsecurity.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demoprojectforspringsecurity.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(EMPLOYEE.name())
                .antMatchers(HttpMethod.DELETE, "/management/api/**").hasAuthority(ApplicationUserPermission.SERVICE_WRITE.getPermission())
                .antMatchers(HttpMethod.PUT, "/management/api/**").hasAuthority(ApplicationUserPermission.SERVICE_WRITE.getPermission())
                .antMatchers(HttpMethod.POST, "/management/api/**").hasAuthority(ApplicationUserPermission.SERVICE_WRITE.getPermission())
                .antMatchers(HttpMethod.GET, "/management/api/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    // Creating user inMemory
    // Password must be encoded else it does not work
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails sachinMewarUser =  User.builder()
                .username("sachinmewar")
                .password(passwordEncoder.encode("password"))
//                .roles(EMPLOYEE.name()) // ROLE_EMPLOYEE
                .authorities(EMPLOYEE.getGrantedAuthority())
                .build();

        UserDetails anuUser = User.builder()
                .username("anushka")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN.name())    // ROLE_ADMIN
                .authorities(ADMIN.getGrantedAuthority())
                .build();

        UserDetails jamesUser = User.builder()
                .username("james")
                .password(passwordEncoder.encode("password"))
//                .roles(ADMIN_TRAINEE.name())    // ROLE_ADMIN_TRAINEE
                .authorities(ADMIN_TRAINEE.getGrantedAuthority())
                .build();

        System.out.println("Admin.name()" + ADMIN.name());

                return new InMemoryUserDetailsManager(
                        sachinMewarUser,
                        anuUser,
                        jamesUser
                );
    }
}
