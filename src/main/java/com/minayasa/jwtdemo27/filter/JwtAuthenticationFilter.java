package com.minayasa.jwtdemo27.filter;

import com.minayasa.jwtdemo27.service.CustomUserDetailService;
import com.minayasa.jwtdemo27.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// call this filter one once per request
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get jwt token from header
        // validate jwt token
        String bearerToken = request.getHeader("Authorization");

        String username = null;
        String token = null;

        // check if token exist or has Bearer text
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            // extract jwt token from bearer Token
            token = bearerToken.substring(7);

            try {
                // extract username from token
                username = jwtUtil.extractUsername(token);

                // get user details for this user
                UserDetails userDetails = customUserDetailService.loadUserByUsername(username);

                // security checks
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    System.out.println("Invalid token!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid bearer token format");
        }

        // if all is well forward the filter request to the request endpoint
        filterChain.doFilter(request, response);
    }
}
