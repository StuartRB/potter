package com.harry.potter.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.harry.potter.user.RegisteredUser;
import com.harry.potter.user.User;
import com.harry.potter.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    @Autowired
    UserRepository userRepository;



    public JwtAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Read the authorization header (where the JWT Token should be)
        String header = request.getHeader(JwtProperties.TOKEN_HEADER);

        // If it does not have a token or does not being with Bearer - chuck it out and carry on down the chain
        if (header == null || !header.startsWith(JwtProperties.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        // If header is present, try to get the user principle and perform authorization
        Authentication authentication = getUsernamePasswordAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Continue filter
        chain.doFilter(request, response);
    }

    private Authentication getUsernamePasswordAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtProperties.TOKEN_HEADER);

        if (token != null) {
            // Parse the token and validate it
            String username = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET.getBytes()))
                    .build()
                    .verify(token.replace(JwtProperties.TOKEN_PREFIX, ""))
                    .getSubject();

            // If username, search in DB by user

            if (username != null) {
                User user = userRepository.findByUsername(username);
                RegisteredUser registeredUser = new RegisteredUser(user);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, registeredUser.getAuthorities());
                return auth;
            }
        }
        return null;
    }
}
