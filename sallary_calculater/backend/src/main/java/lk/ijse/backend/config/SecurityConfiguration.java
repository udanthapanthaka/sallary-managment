package lk.ijse.backend.config;

import lk.ijse.backend.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }


//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                /*.cors()
                .and()*/
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/v1/auth/authenticate",
                                "/api/v1/user/register",
                                "/api/v1/departments/save",
                                "/api/v1/departments/delete/{id}",
                                "/api/v1/departments/update",
                                "/api/v1/departments/getAll",
                                "api/v1/attendance/save",
                                "/api/v1/attendance/delete/{id}",
                                "/api/v1/attendance/update",
                                "/api/v1/attendance/getAll",
                                "api/v1/overtime/save",
                                "/api/v1/overtime/delete/{id}",
                                "/api/v1/overtime/update",
                                "/api/v1/overtime/getAll",
                                "/api/v1/leaves/save",
                                "/api/v1/leaves/update",
                                "/api/v1/leaves/getAll",
                                "/api/v1/leaves/get/{id}",
                                "/api/v1/leaves/delete/{id}",
                                "/api/v1/payroll/**",
                                "/api/v1/employees/save",
                                "/api/v1/employees/getAll",
                                "/api/v1/employees/update",
                                "/api/v1/employees/delete/{id}",
                                "/api/v1/employees/{id}",
                                "/api/v1/employees/email/{email}",
                                "/api/v1/employees/department/{departmentId}",
                                "/api/v1/employees/exists/id/{id}",
                                "/api/v1/employees/exists/email/{email}",
                                "/uploads/**",
                                "/api/v1/auth/refreshToken",
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll() // Allow these endpoints without authentication
                        .anyRequest().authenticated() // Secure all other endpoints
                )
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .cors(Customizer.withDefaults())
                .build();
    }



}
