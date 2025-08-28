package myl.codeio.hello;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import myl.codeio.hello.utils.Jwtutils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class jwtFilter extends OncePerRequestFilter {
    @Autowired
    private Jwtutils jwtutils;
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader=request.getHeader("Authorization");

        if(authHeader!=null && authHeader.startsWith(("Bearer "))){
            String token=authHeader.substring(7);
            if(jwtutils.validateJwtToken(token)){
                String email= jwtutils.extractEmail(token);
                var auth=new UsernamePasswordAuthenticationToken(email,null, List.of());//"Here is a logged-in user whose identity is this email. We don’t need their password anymore, and they currently have no roles."
                SecurityContextHolder.getContext().setAuthentication(auth);//This tells Spring Security:Hey, we have authenticated this request. From now on, treat this user (with the given email) as logged in for the rest of the request."
            }
        }
        filterChain.doFilter(request,response);//Without this line, the request would get stuck inside your JWT filter and never reach your endpoints.
    }
}
