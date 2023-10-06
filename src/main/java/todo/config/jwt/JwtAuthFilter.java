package todo.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;
import todo.service.imp.UserServiceImp;

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
    }
}
