package com.klik.security;

import com.klik.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

// CRITICAL: Remove the @Component annotation to stop Spring from auto-adding this filter
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService customUserDetailsService;

    // Define a list of public URLs that do not require a JWT
    private static final List<String> PUBLIC_URLS = Arrays.asList(
            "/api/auth/login",
            "/api/auth/register",
            "/tretmani",
            "/rezervacije",
            "/placanja",
            "/korisnici",
            "/register",
            "/swagger-ui",
            "/v3/api-docs"
    );

    public JwtFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestPath = request.getRequestURI();
        System.out.println("JwtFilter - Request path: " + requestPath + ", isPublicUrl: " + isPublicUrl(requestPath));
        if (isPublicUrl(requestPath)) {
            filterChain.doFilter(request, response);
            return;
        }

        // The rest of the logic only runs for secured endpoints.
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractUsername(token);

                // Load UserDetails to get the authorities (roles)
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                // Create a full authentication token with UserDetails and authorities
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                // Set the authentication in the SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        } else {
            // For a secured endpoint, a missing token is a 401 error.
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid JWT.");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private boolean isPublicUrl(String path) {
        return PUBLIC_URLS.stream().anyMatch(path::startsWith);
    }
}
