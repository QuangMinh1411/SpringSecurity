package com.heaven.ss_2022_c4_e1.config.filters;

import com.heaven.ss_2022_c4_e1.config.authentications.ApiKeyAuthentication;
import com.heaven.ss_2022_c4_e1.config.managers.CustomAuthenticationManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RequiredArgsConstructor
public class ApiKeyFilter extends OncePerRequestFilter {

    private final String key;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CustomAuthenticationManager manager = new CustomAuthenticationManager(key);

        var requestKey = request.getHeader("x-api-key");
        if("null".equals(requestKey)||requestKey==null){
            filterChain.doFilter(request,response);
        }
        var auth = new ApiKeyAuthentication(key);
        var a = manager.authenticate(auth);
        if(a.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(a);
            filterChain.doFilter(request,response);
        }else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }


    }
}
