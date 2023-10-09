package todo.configs.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import todo.services.imp.UserServiceImp;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    public JwtAuthFilter(JwtService jwtService, UserServiceImp userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    private final JwtService jwtService;
    private final UserServiceImp userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bear")) {
            String token = authorization.split(" ")[1];
            boolean isValid = jwtService.isValid(token);

            if (isValid) {
                String userEmail = jwtService.getUserEmail(token);
                UserDetails loadedUser = userService.loadUserByEmail(userEmail);

                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(loadedUser, null, loadedUser.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }

        filterChain.doFilter(request, response);
    }
}
