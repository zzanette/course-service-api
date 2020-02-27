package com.ilog.course.security;

import static com.ilog.course.security.SecurityConstants.DEFAULT_PUBLIC_ACCESS;
import static com.ilog.course.security.SecurityConstants.PUBLIC_COURSE_AUTHOR_URL;
import static com.ilog.course.security.SecurityConstants.PUBLIC_COURSE_CONTENT_URL;
import static com.ilog.course.security.SecurityConstants.PUBLIC_COURSE_URL;
import static com.ilog.course.security.SecurityConstants.SIGN_UP_URL;

import com.ilog.course.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@Profile("!test-integration")
public class WebSecurity extends WebSecurityConfigurerAdapter {

  private IUserService userService;
  private BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public WebSecurity(IUserService userService, BCryptPasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.cors().and().csrf().disable()
        .authorizeRequests()
          .antMatchers(HttpMethod.OPTIONS).permitAll()
          .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
          .antMatchers(HttpMethod.GET, PUBLIC_COURSE_URL).permitAll()
          .antMatchers(HttpMethod.GET, PUBLIC_COURSE_AUTHOR_URL).permitAll()
          .antMatchers(HttpMethod.GET, PUBLIC_COURSE_CONTENT_URL).permitAll()
          .antMatchers(DEFAULT_PUBLIC_ACCESS).permitAll()
          .anyRequest().authenticated()
        .and()
          .addFilter(new JWTAuthenticationFilter(authenticationManager(), userService))
          .addFilter(new JWTAuthorizationFilter(authenticationManager()))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
  }

  @Bean
  public CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
    return source;
  }
}
