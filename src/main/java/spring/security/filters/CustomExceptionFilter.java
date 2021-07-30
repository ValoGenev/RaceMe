package spring.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import spring.exception.CustomErrorMessage;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomExceptionFilter extends OncePerRequestFilter {



    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {


        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (BadCredentialsException e) {


            System.out.println(e.getMessage());

            resolver.resolveException(httpServletRequest, httpServletResponse, null, e);

            httpServletResponse.resetBuffer();
            httpServletResponse.setStatus(401);
            httpServletResponse.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpServletResponse.getOutputStream();
            httpServletResponse.getOutputStream().print(new ObjectMapper().writeValueAsString(new CustomErrorMessage("Bad Credentials",401)));
            httpServletResponse.flushBuffer();
        }
    }


}
