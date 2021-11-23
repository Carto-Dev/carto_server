package com.carto.server.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.apache.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class FirebaseAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (request.getServletPath().equals("/v1/auth/register")) {
            filterChain.doFilter(request, response);
        } else {

            String authorizationHeader = request.getHeader(AUTHORIZATION);

            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

                try {
                    String firebaseAuthToken = authorizationHeader.substring("Bearer ".length());
                    FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(firebaseAuthToken);

                    String firebaseId = decodedToken.getUid();

                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken(firebaseId, null, new ArrayList<>());

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                    filterChain.doFilter(request, response);
                } catch (FirebaseAuthException firebaseAuthException) {
                    Map<String, String> body = new HashMap<>();
                    body.put("message", firebaseAuthException.getAuthErrorCode().toString());

                    response.setStatus(FORBIDDEN.value());
                    response.setContentType(APPLICATION_JSON_VALUE);

                    new ObjectMapper().writeValue(response.getOutputStream(), body);
                } catch (Exception error) {
                    error.printStackTrace();

                    Map<String, String> body = new HashMap<>();
                    body.put("message", error.getMessage());

                    response.setStatus(INTERNAL_SERVER_ERROR.value());
                    response.setContentType(APPLICATION_JSON_VALUE);

                    new ObjectMapper().writeValue(response.getOutputStream(), body);

                }

            } else {
                Map<String, String> body = new HashMap<>();
                body.put("message", "NOT_AUTHORIZED");

                response.setStatus(FORBIDDEN.value());
                response.setContentType(APPLICATION_JSON_VALUE);

                new ObjectMapper().writeValue(response.getOutputStream(), body);
            }

        }

    }
}
