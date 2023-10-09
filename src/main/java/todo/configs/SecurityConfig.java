package todo.configs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;
import todo.configs.jwt.JwtAuthFilter;
import todo.configs.jwt.JwtService;
import todo.enums.Levels;
import todo.enums.MainLevels;
import todo.enums.TodoRouteLevels;
import todo.helpers.EnumUtil;
import todo.services.imp.UserServiceImp;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private UserServiceImp userService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
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
                        .requestMatchers("/management").hasAnyRole(mainRoles)
                        .requestMatchers("/management/**").hasAnyRole(mainRoles)
                        .requestMatchers("/todo").hasAnyRole(todoRouteRoles)
                        .requestMatchers("/todo/**").hasAnyRole(todoRouteRoles)
                        .requestMatchers("/users/**").permitAll()
                        .requestMatchers("/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/swagger-ui/").permitAll()
                        .requestMatchers("/v3/api-docs/").permitAll()
                        .anyRequest().authenticated()
                )

                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().requestMatchers(
                "v2/api-docs",
                "configuration/ui",
                "swagger-resources/**",
                "swagger-resources/**",
                "configuration/security",
                "swagger-ui.html",
                "/webjars/**"
                );

    }
}
