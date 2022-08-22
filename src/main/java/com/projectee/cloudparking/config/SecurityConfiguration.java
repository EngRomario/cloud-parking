package com.projectee.cloudparking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
//        System.out.println(passwordEncoder().encode("dio@12345"));

        UserDetails user = User.withUsername("user")
                .password("{bcrypt}$2a$10$Q6ZysQ.UzBuxBJ35.dF7l.AjkesxdMTYq9ywkonFOZ2zyJ9a8F7E6")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .antMatchers("/swagger-ui/**").permitAll()
                        .antMatchers("/swagger-resources/**").permitAll()
                        .antMatchers("/webjars/**").permitAll()
                        .antMatchers("/v2/api-docs/**").permitAll()
                        .antMatchers("/csrf/**").permitAll()
                        .antMatchers("/*.js").permitAll()
                        .antMatchers("/*.css").permitAll()
                        .antMatchers("/*.ico").permitAll()
                        .antMatchers("/*.png").permitAll()
                        .antMatchers("/").permitAll()
                        .antMatchers(HttpMethod.POST,"/parking/**" ).permitAll()
                        .antMatchers(HttpMethod.POST,"/swagger-ui/**" ).permitAll()
                        .anyRequest().authenticated()
                )
                .httpBasic()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable();
        return http.build();
    }
}
