package edu.it.seguridad;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class Filtro extends OncePerRequestFilter {
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain next) throws ServletException, IOException {
		ArrayList<String> authorities = new ArrayList<String>();
		
		try {
			log.info("Pasando por el filtro...");
			log.info("url: "  + req.getRequestURI());
			
			// Si quiero permitir todo ?
			// Chain of responsability
			next.doFilter(req, res);
			
			/*
			if ("/login".equals(req.getRequestURI())) {
				next.doFilter(req, res);
				return;
			}
			
			var posibleToken = req.getHeader("X-TOKEN");
			if (posibleToken == null) {
				throw new RuntimeException("Lo echo del API");
			}
			log.info(posibleToken);
			
			SecurityContextHolder.clearContext();
			
			if (posibleToken.equals("MI-TOKEN-OPERADOR")) {
	        	authorities.add("ROLE_OPERADOR");
	        }
	        else if (posibleToken.equals("MI-TOKEN-ADMINISTRADOR")) {
	        	authorities.add("ROLE_ADMIN");
	        }
	        */
		}
		catch (Exception ex) {
			log.error(ex.getMessage());
			salirPorForbidden(res);
			return;
		}
		
		
		UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(
                        "NA", null,
                        authorities.stream()
                                .map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList()));

        SecurityContextHolder.getContext().setAuthentication(auth);

        next.doFilter(req, res);		
	}
	private void salirPorForbidden(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status " + HttpServletResponse.SC_UNAUTHORIZED + " Usuario no identificado");
    }
}
