package PaloosaBank.OnlineBanking.security;

import PaloosaBank.OnlineBanking.services.users.CustomUserDetailsSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {

    @Autowired
    CustomUserDetailsSevice customUserDetailsSevice;
    @Bean
    PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder(); }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/admin**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/admin**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PATCH, "/admin**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.PUT, "/admin**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.DELETE, "/admin**").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.GET, "/account_holder**").hasRole("HOLDER")
                .mvcMatchers(HttpMethod.PATCH, "/account_holder**").hasRole("HOLDER")
                .mvcMatchers(HttpMethod.PATCH, "/third_party**").hasRole("THIRD")
                .anyRequest().permitAll();

        httpSecurity.csrf().disable();

        return httpSecurity.build();
    }

}
