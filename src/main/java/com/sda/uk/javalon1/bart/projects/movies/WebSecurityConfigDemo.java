package com.sda.uk.javalon1.bart.projects.movies;

import com.sda.uk.javalon1.bart.projects.movies.user.UserDocument;
import com.sda.uk.javalon1.bart.projects.movies.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfigDemo extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public WebSecurityConfigDemo(@Autowired UserService userService) {
        this.userService = userService;

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/showMoviesPage", "/loginPage", "/registerPage", "/registerAction", "/").permitAll()
                .anyRequest().authenticated()

                .and()
                .formLogin()
                .loginPage("/loginPage")
                .permitAll()

                .and()
                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        List<UserDocument> allUsers = userService.getAllUsers();

        UserDetails[] users = allUsers.stream()
                .map(user -> transformToSpringUserDetails(user))
                .toArray(UserDetails[]::new);
        return new InMemoryUserDetailsManager(users);
    }

    private UserDetails transformToSpringUserDetails(UserDocument userDocument) {
        if (userDocument.getUsername().equals("admin")) {
            return User.withDefaultPasswordEncoder()
                    .username(userDocument.getUsername())
                    .password(userDocument.getPassword())
                    .roles("USER", "ADMIN")
                    .build();
        } else {
            return User.withDefaultPasswordEncoder()
                    .username(userDocument.getUsername())
                    .password(userDocument.getPassword())
                    .roles("USER")
                    .build();
        }
    }
}
