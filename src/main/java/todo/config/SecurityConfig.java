package todo.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import todo.config.jwt.JwtAuthFilter;
import todo.config.jwt.JwtService;
import todo.enums.Levels;
import todo.enums.MainLevels;
import todo.enums.TodoRouteLevels;
import todo.helpers.EnumToArrayConverter;
import todo.helpers.EnumUtil;
import todo.service.imp.UserServiceImp;

import java.util.Arrays;
import java.util.List;

@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserServiceImp userService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder () {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, userService);
    }

    @Bean
    public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

        String[] levelsRoles = EnumUtil.getEnumNames(Levels.class);
        String[] userRoles = EnumUtil.getEnumNames(Levels.class);
        String[] mainRoles = EnumUtil.getEnumNames(MainLevels.class);
        String[] todoRouteRoles = EnumUtil.getEnumNames(TodoRouteLevels.class);


        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/management/**").hasAnyRole(mainRoles).anyRequest().authenticated()
                        .requestMatchers("/todo").hasAnyRole(todoRouteRoles).anyRequest().authenticated()
                        .requestMatchers("/users").hasAnyRole(userRoles).anyRequest().authenticated()
                        .requestMatchers("/**").permitAll())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService () {
        UserDetails user = User.builder()
                .username("admin")
                .password(bCryptPasswordEncoder().encode("123"))
                .roles(Levels.USER.toString())
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
