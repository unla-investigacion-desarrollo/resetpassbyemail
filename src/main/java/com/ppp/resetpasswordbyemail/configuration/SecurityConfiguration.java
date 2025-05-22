package com.ppp.resetpasswordbyemail.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter; //Error con esta libreria
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
import com.unla.agroecologiaiot.filters.AuthenticationFilter;
import com.unla.agroecologiaiot.filters.AuthorizationFilter;
import com.unla.agroecologiaiot.services.ITokenService;
*/

import com.ppp.resetpasswordbyemail.repositories.ApplicationUserRepository;
import com.ppp.resetpasswordbyemail.repositories.SessionRepository;
import com.ppp.resetpasswordbyemail.services.implementation.ApplicationUserService;
import com.ppp.resetpasswordbyemail.services.ITokenService;
import com.ppp.resetpasswordbyemail.constants.SecurityConstants;

import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

public class SecurityConfiguration {

    private ApplicationUserService userService;
    @Autowired
    private ApplicationUserRepository applicationUserRepository;
    @Autowired
    private SessionRepository sessionRepository;
    @Autowired
    private ITokenService tokenService;

    @Value("${CORS_CONFIG_REACT_SERVER_URL_AND_PORT}")
    private String corsServerUrlAndPort;

    public SecurityConfiguration(ApplicationUserService userService) {
        this.userService = userService;
    }

  //  @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .requestMatchers(SecurityConstants.SWAGGER_URL_WHITELIST).permitAll()
                .anyRequest().authenticated()
                .and()
              //  .addFilter(getAuthenticationFilter())
              //  .addFilter(new AuthorizationFilter(authenticationManager(), applicationUserRepository))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(SecurityConstants.REFRESH_TOKEN_URL).requestMatchers(SecurityConstants.INTERNO)
                .requestMatchers(SecurityConstants.METRIC_READINGS);
    }

   /*
   public AuthenticationFilter getAuthenticationFilter() throws Exception {
        final AuthenticationFilter authFilter = new AuthenticationFilter(authenticationManager(),
                applicationUserRepository, sessionRepository, tokenService);
        authFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
        return authFilter;
    }
    */ 

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(corsServerUrlAndPort));
        configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setExposedHeaders(Arrays.asList("X-Auth-Token", "Authorization", "Access-Control-Allow-Origin",
                "Access-Control-Allow-Credentials"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

  //  @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
