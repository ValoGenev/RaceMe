package spring.security.filters;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import spring.security.authentication.OAuthCustomAuthentication;
import spring.security.provider.GitHubAuthenticationProvider;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

public class OAuthFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;

    @Autowired
    public OAuthFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String code =  httpServletRequest.getParameter("code");
        String type = httpServletRequest.getParameter("type");

        System.out.println(code);
        System.out.println(type);

        OAuthCustomAuthentication oAuthCustomAuthentication = new OAuthCustomAuthentication(code,null);

        Authentication auth = authenticationManager.authenticate(oAuthCustomAuthentication);

        if(auth.isAuthenticated()){

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/social/login");
    }
}
