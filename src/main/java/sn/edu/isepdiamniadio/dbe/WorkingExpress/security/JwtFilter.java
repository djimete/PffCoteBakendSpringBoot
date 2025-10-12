package sn.edu.isepdiamniadio.dbe.WorkingExpress.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.entite.Utilisateur;
import sn.edu.isepdiamniadio.dbe.WorkingExpress.repository.UtilisateurRepository;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;
    private final UtilisateurRepository utilisateurRepository;
    private Logger logger= LoggerFactory.getLogger(JwtFilter.class);

    public JwtFilter(JwtUtil jwtUtil, UtilisateurRepository utilisateurRepository) {
        this.jwtUtil = jwtUtil;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getHeader("Authorization")!= null){
            final String authHeader = request.getHeader("Authorization");
            logger.info("authHeader= {}",authHeader);
            String token = authHeader.substring(7);
            if(jwtUtil.validateToken(token)) {
                String email = jwtUtil.extractEmail(token);
                logger.info("email:{}", email);
                Utilisateur user = utilisateurRepository.findByEmail(email).orElse(null);
                if (user != null) {
                    SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getEmail(), new ArrayList<>()));
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
