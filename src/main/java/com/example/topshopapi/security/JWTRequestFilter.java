package com.example.topshopapi.security;

import com.auth0.jwt.exceptions.JWTDecodeException;
import com.example.topshopapi.entity.User;
import com.example.topshopapi.repository.UserRepository;
import com.example.topshopapi.services.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private UserRepository userRepository;

    public JWTRequestFilter(JWTService jwtService, UserRepository userRepository) {
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @Override
    // Everytime api gets a request, this method is called.
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Does request have a header with "Authorization".
        String tokenHeader = request.getHeader("Authorization");

        // If token exists and starts with "Bearer", token to process is present.
        if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
            // Removing "Bearer " from token value.
            String token = tokenHeader.substring(7);
            try {
                String username = jwtService.getUsername(token);
                Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
                if (optionalUser.isPresent()) {
                    User user = optionalUser.get();
                    // Building authentication object.
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
                    // Setting the authentication objects details, so it can be recognized by Spring Security & Spring MVC.
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JWTDecodeException e) {}
        }
        filterChain.doFilter(request, response);
    }
}
