package com.hediyesilaozyurt.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    @Lazy
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Bearer sdjkks(token)
        final String authHeader;
        final String jwt;
        final String username;

        authHeader=request.getHeader("Authorization");
        //check out if header is null or is not a Bearer
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        //take token out
        jwt=authHeader.substring(7);
        username=jwtService.extractUsername(jwt);

        try{
            //check out if username exist or authenticated before
            if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
                //get userdetails from db
                UserDetails userDetails=this.userDetailsService.loadUserByUsername(username);

                if(userDetails!=null && jwtService.isTokenValid(jwt,userDetails)){
                    // put user into SecurityContext. This means now the user can be accepted.
                    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,
                            null,userDetails.getAuthorities());

                    authentication.setDetails(userDetails);

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (ExpiredJwtException e) {
            System.out.println("token is expired"+e.getMessage());
        }
        catch (Exception e) {
            System.out.println("general error"+e.getMessage());;
        }

        filterChain.doFilter(request,response);
    }
}
