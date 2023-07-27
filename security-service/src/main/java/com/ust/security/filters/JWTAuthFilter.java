package com.ust.security.filters;

import com.ust.security.service.JWTService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JWTAuthFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        final Optional<String> header = Optional.ofNullable(request.getHeader("Authorization"));
        final String token;
        final String userEmail;
        if (header.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }
        if (header.isPresent() && !header.get().startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = header.get().substring(7);
        userEmail = jwtService.extractUserName(token);
        log.warn("CLAIMS => " + jwtService.extractClaims(token).entrySet().toString());
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
            if (jwtService.isTokenValid(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken
                        = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                var authorities = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).findFirst().orElseThrow();
                log.warn("-----Filter----");
                log.warn(authorities);
                log.warn(new WebAuthenticationDetailsSource().buildDetails(request).toString());
                log.warn(authenticationToken.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining()));
                log.warn("-----Filter----");
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
