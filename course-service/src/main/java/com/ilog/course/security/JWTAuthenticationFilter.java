package com.ilog.course.security;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.ilog.course.security.SecurityConstants.EXPIRATION_TIME;
import static com.ilog.course.security.SecurityConstants.HEADER_STRING;
import static com.ilog.course.security.SecurityConstants.SECRET;
import static com.ilog.course.security.SecurityConstants.TOKEN_PREFIX;

import com.auth0.jwt.JWT;
import com.ilog.course.exception.UnAuthorizedException;
import com.ilog.course.model.user.User;
import com.ilog.course.service.user.IUserService;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private AuthenticationManager authenticationManager;
  private IUserService userService;

  JWTAuthenticationFilter(AuthenticationManager authenticationManager,
      IUserService userService) {
    this.authenticationManager = authenticationManager;
    this.userService = userService;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException {
    try {
      if (!req.getMethod().equals("POST")) {
        throw new AuthenticationServiceException(
            "Authentication method not supported: " + req.getMethod());
      }
      String username = obtainUsername(req);
      String password = obtainPassword(req);

      if (username == null) {
        username = "";
      }

      if (password == null) {
        password = "";
      }

      username = username.trim();

      UserDetails userDTO = userService.loadUserByUsername(username);
      return authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(userDTO.getUsername(), password,
              new ArrayList<>())
      );
    } catch (Exception e) {
      log.error("Authentication error", e);
      throw new UnAuthorizedException("Not Authorized...", e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
      FilterChain chain, Authentication auth) {
    String token = JWT.create()
        .withSubject(((User) auth.getPrincipal()).getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .sign(HMAC512(SECRET.getBytes()));
    res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
  }
}
